import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static boolean isEnd = false;
    public static final boolean DEBUG = false;

    static ArrayList<String> categoryDocList = new ArrayList<>();
    static ArrayList<Documents> documentsList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        HttpClient httpClient = new HttpClient();

        // Token API : 제출용 토큰 받아오기
        System.out.println(">> 1. Get Token API (Token API)");

        int responseCode = httpClient.getSubmitToken(HttpClient.BASE_URL + "token/" + HttpClient.LOGIN_TOKEN);
        System.out.println(responseCode);

        if (DEBUG == true || responseCode == HttpURLConnection.HTTP_OK) {

            // Seed API : 수집해야 하는 카테고리별 문서의 URL 목록 받아오기

            categoryDocList = httpClient.getDocURLList(HttpClient.BASE_URL + "seed/", HttpClient.SUBMIT_TOKEN);
            for (String docURL : categoryDocList) System.out.println(docURL);

            int cnt = 0;
            while (cnt <= 30000) {

                cnt++;
                System.out.println("iteration :: " + cnt);

                // Document API : 수집해야 하는 카테고리별 문서의 URL 목록 받아오기

                for (int i = 0; i < categoryDocList.size(); i++) {

                    Documents documents = httpClient.getDocuments(HttpClient.BASE_URL + categoryDocList.get(i), HttpClient.SUBMIT_TOKEN);
                    documentsList.add(documents);
                }

                // 받아온 이미지의 중복 제거 -> FeatureList Extraction -> FeatureList SAVE or FeatureList DELETE
                for (int i = 0; i < documentsList.size(); i++) {

                    Documents documents = documentsList.get(i);
                    if (documents.next_url.charAt(0) == '/') {

                        documents.next_url = documents.next_url.substring(1, documents.next_url.length());
                    }

                    String currentURL = "";
                    String nextURL = HttpClient.BASE_URL + documents.next_url;

                    int retryCnt = 0;
                    // 미생성이 아닌 경우
                    while (true) {

                        if ((currentURL.equals(nextURL) && documents.images.length == 0)) {

                            if (retryCnt < 3000) {

                                retryCnt++;

                                currentURL = nextURL;
                                documents = httpClient.getDocuments(nextURL, HttpClient.SUBMIT_TOKEN);
                                if (documents == null || currentURL == null || nextURL == null || documents.next_url == null)
                                    break;
                                if (documents.next_url.charAt(0) == '/') {

                                    documents.next_url = documents.next_url.substring(1, documents.next_url.length());
                                }

                                nextURL = HttpClient.BASE_URL + documents.next_url;
                            } else {

                                break;
                            }
                        }
                        // 중복 제거
                        HashSet<String> addDocId = new HashSet<>();
                        HashSet<String> deleteDocId = new HashSet<>();

                        for (Documents.Image image : documents.images) {

                            String type = image.type;
                            String id = image.id;

                            if (type.equals("add")) {

                                if (!addDocId.contains(id)) {

                                    if (deleteDocId.contains(id)) deleteDocId.remove(id);
                                    else addDocId.add(id);
                                }

                            } else if (type.equals("del")) {

                                if (!deleteDocId.contains(id)) {

                                    if (addDocId.contains(id)) addDocId.remove(id);
                                    else deleteDocId.add(id);
                                }
                            }
                        }

                        // FeatureList Extraction + Add
                        FeatureList featureList;

                        StringBuilder api_URL = new StringBuilder(HttpClient.BASE_URL + "image/feature?id=");
                        int idx = 0;
                        for (String id : addDocId) {

                            //if (DEBUG) System.out.println(id);
                            api_URL.append(id + ",");
                            idx++;

                            if (idx == 50) {

                                // Call FeatureList Extraction API
                                api_URL.deleteCharAt(api_URL.length() - 1);
                                featureList = httpClient.getFeatureExtraction(api_URL.toString(), HttpClient.SUBMIT_TOKEN);

                                // Call Feature Save API
                                if (featureList != null) {

                                    SaveFeatureList saveFeatureList = new SaveFeatureList();
                                    saveFeatureList.data = featureList.features;

                                    httpClient.saveFeatures(HttpClient.BASE_URL + "images/feature", HttpClient.SUBMIT_TOKEN, new Gson().toJson(saveFeatureList));
                                }

                                api_URL = new StringBuilder(HttpClient.BASE_URL + "image/feature?id=");
                                idx = 0;
                            }
                        }

                        if (idx > 0) {

                            // Call FeatureList Extraction API
                            api_URL.deleteCharAt(api_URL.length() - 1);
                            featureList = httpClient.getFeatureExtraction(api_URL.toString(), HttpClient.SUBMIT_TOKEN);

                            if (featureList != null) {

                                // Call Feature Save API

                                SaveFeatureList saveFeatureList = new SaveFeatureList();
                                saveFeatureList.data = featureList.features;

                                httpClient.saveFeatures(HttpClient.BASE_URL + "image/feature", HttpClient.SUBMIT_TOKEN, new Gson().toJson(saveFeatureList));
                            }
                        }

                        // FeatureList Extraction + Delete
                        ArrayList<String> deleteIds = new ArrayList<>();
                        idx = 0;
                        for (String id : deleteDocId) {

                            //if (DEBUG) System.out.println(id);
                            deleteIds.add(id);
                            idx++;

                            if (idx == 50) {

                                // Call Feature Delete API
                                DeleteFeatureList deleteFeatureList = new DeleteFeatureList(deleteIds);
                                httpClient.deleteFeatures(HttpClient.BASE_URL + "images/feature", HttpClient.SUBMIT_TOKEN, new Gson().toJson(deleteFeatureList));

                                deleteIds = new ArrayList<>();
                                idx = 0;
                            }
                        }

                        // Call Feature Delete API
                        if (deleteIds.size() > 0) {

                            DeleteFeatureList deleteFeatureList = new DeleteFeatureList(deleteIds);
                            httpClient.deleteFeatures(HttpClient.BASE_URL + "image/feature", HttpClient.SUBMIT_TOKEN, new Gson().toJson(deleteFeatureList));
                        }

                        currentURL = nextURL;

                        documents = httpClient.getDocuments(nextURL, HttpClient.SUBMIT_TOKEN);
                        if (documents == null || currentURL == null || nextURL == null || documents.next_url == null)
                            break;
                        if (documents.next_url.charAt(0) == '/') {

                            documents.next_url = documents.next_url.substring(1, documents.next_url.length());
                        }

                        nextURL = HttpClient.BASE_URL + documents.next_url;
                    }
                }
            } // ~ while loop
        }
    } // ~ main
}


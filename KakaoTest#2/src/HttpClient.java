import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Token API
 * Seed API
 * Document API
 * FeatureList Extraction API
 * FeatureList Save API
 * FeatureList Delete API
 */
public class HttpClient {

    public static final String LOGIN_TOKEN = "U3Oww236-rjx-Hgj6GG-eBlv7U2NpQOQ3zQBvhwK2qER3yz";
    public static final String BASE_URL = "http://api.welcome.kakao.com/";

    public static String SUBMIT_TOKEN = "";

    private URL url;
    private HttpURLConnection conn;
    private InputStream in;
    private OutputStream out;

    HttpClient() {

    }

    // Token API
    public int getSubmitToken(String api_url) throws IOException {

        int responseCode = 0;

        try {

            url = new URL(api_url);

            conn = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(conn.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) sb.append(line);
            br.close();

            SUBMIT_TOKEN = sb.toString();

            // Console 디버그 로그 출력
            System.out.println(SUBMIT_TOKEN);

            responseCode = conn.getResponseCode();

        } catch (Exception e) {


            // 10분 내에 생성한 토큰이 존재할 때, response body에서 Token API를 가져온다
            if (conn.getResponseCode() == HttpURLConnection.HTTP_FORBIDDEN) {

                System.out.println("Token API :: response: 403");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String line;

                StringBuilder sb = new StringBuilder();

                while ((line = br.readLine()) != null) sb.append(line);
                br.close();

                SUBMIT_TOKEN = sb.toString();

                // Console 디버그 로그 출력
                System.out.println(SUBMIT_TOKEN);
            } else {

                e.printStackTrace();
            }
        }

        return responseCode;
    }

    // Seed API
    public ArrayList<String> getDocURLList(String api_url, String authToken) throws IOException {

        ArrayList<String> docList = new ArrayList<>();

        try {

            url = new URL(api_url);

            conn = (HttpURLConnection) url.openConnection();
            if (authToken != null) conn.setRequestProperty("X-Auth-Token", authToken);

            in = new BufferedInputStream(conn.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                line = line.substring(1, line.length());
                docList.add(line);
            }
            br.close();

        } catch (Exception e) {

            if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAVAILABLE) {

                System.out.println("getDocuments :: 503, HTTP_UNAVAILABLE");
            } else if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {

                Main.isEnd = true;
            } else {

                e.printStackTrace();
            }
        }

        return docList;
    }

    // Document API
    public Documents getDocuments(String api_url, String authToken) throws IOException {

        Documents documents = null;

        try {

            url = new URL(api_url);

            conn = (HttpURLConnection) url.openConnection();
            if (authToken != null) conn.setRequestProperty("X-Auth-Token", authToken);

            in = new BufferedInputStream(conn.getInputStream());

            // JSON Parsing
            Gson gson = new Gson();
            documents = gson.fromJson(new InputStreamReader(in), Documents.class);

        } catch (Exception e) {

            if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAVAILABLE) {

                System.out.println("getDocuments :: 503, HTTP_UNAVAILABLE");
            } else if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {

                Main.isEnd = true;
            } else {

                e.printStackTrace();
            }
        }

        return documents;
    }

    // FeatureList Extraction API
    public FeatureList getFeatureExtraction(String api_url, String authToken) throws IOException {

        FeatureList featureList = null;

        try {

            url = new URL(api_url);

            conn = (HttpURLConnection) url.openConnection();
            if (authToken != null) conn.setRequestProperty("X-Auth-Token", authToken);

            in = new BufferedInputStream(conn.getInputStream());

            // JSON Parsing
            Gson gson = new Gson();
            featureList = gson.fromJson(new InputStreamReader(in), FeatureList.class);

        } catch (Exception e) {

            if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAVAILABLE) {

                System.out.println("getDocuments :: 503, HTTP_UNAVAILABLE");
            } else if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {

                Main.isEnd = true;
            } else {

                e.printStackTrace();
            }

            featureList = null;
        }

        return featureList;
    }

    // FeatureList Save API
    public void saveFeatures(String api_url, String authToken, String content) throws IOException {

        try {

            url = new URL(api_url);

            conn = (HttpURLConnection) url.openConnection();
            if (authToken != null) conn.setRequestProperty("X-Auth-Token", authToken);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-type", "application/json");

            out = new DataOutputStream(conn.getOutputStream());
            out.write(content.getBytes());
            out.flush();

            int responseCode = conn.getResponseCode();

        } catch (Exception e) {

            if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAVAILABLE) {

                System.out.println("getDocuments :: 503, HTTP_UNAVAILABLE");
            } else if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {

                Main.isEnd = true;
            } else {

                e.printStackTrace();
            }
        }
    }

    // FeatureList Delete API
    public void deleteFeatures(String api_url, String authToken, String content) throws IOException {

        try {

            url = new URL(api_url);

            conn = (HttpURLConnection) url.openConnection();
            if (authToken != null) conn.setRequestProperty("X-Auth-Token", authToken);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-type", "application/json");

            out = new DataOutputStream(conn.getOutputStream());
            out.write(content.getBytes());
            out.flush();

            int responseCode = conn.getResponseCode();

        } catch (Exception e) {

            if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAVAILABLE) {

                System.out.println("getDocuments :: 503, HTTP_UNAVAILABLE");
            } else if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {

                Main.isEnd = true;
            } else {

                e.printStackTrace();
            }
        }
    }
}

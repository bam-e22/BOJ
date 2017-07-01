import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#12208 Super 2048 (Small)
 * https://www.acmicpc.net/problem/12208
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int T; // test case
        int N; // length of board
        String dir; // direction
        int[][] map; // map

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            dir = st.nextToken();

            map = new int[N][N];

            for (int i = 0; i < N; i++) {

                Arrays.fill(map[i], 0);
            }

            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {

                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("Case #" + (t + 1) + ":");
            customMove(map, N, dir);

        }
    }

    static int[][] move(int[][] map, int N, String direction) {

        switch (direction) {

            case "up":

                for (int j = 0; j < N; j++) {

                    int blank = map[0][j] == 0 ? 1 : 0;
                    int bound = 0;
                    for (int i = 1; i < N; i++) {

                        if (map[i][j] == 0) {

                            blank++;
                        } else {

                            // 병합 가능
                            if (map[i][j] == map[i - blank - 1 < bound ? bound : i - blank - 1][j]) {

                                map[i - blank - 1 < bound ? bound : i - blank - 1][j] *= 2;
                                map[i][j] = 0;

                                bound = i - blank;
                                blank++;
                            }

                            // 병합 불가능
                            else {

                                if (blank != 0) {

                                    map[i - blank][j] = map[i][j];
                                    map[i][j] = 0;

                                    bound = i - blank;

                                }
                            }
                        }
                    }
                }
                break;

            case "down":

                for (int j = 0; j < N; j++) {

                    int blank = map[N - 1][j] == 0 ? 1 : 0;
                    int bound = N - 1;
                    for (int i = N - 2; i >= 0; i--) {

                        if (map[i][j] == 0) {

                            blank++;
                        } else {

                            // 병합 가능
                            if (map[i][j] == map[i + blank + 1 > bound ? bound : i + blank + 1][j]) {

                                map[i + blank + 1 > bound ? bound : i + blank + 1][j] *= 2;
                                map[i][j] = 0;

                                bound = i + blank;
                                blank++;
                            }

                            // 병합 불가능
                            else {

                                if (blank != 0) {

                                    map[i + blank][j] = map[i][j];
                                    map[i][j] = 0;

                                    bound = i + blank;

                                }
                            }
                        }
                    }
                }
                break;

            case "left":

                for (int i = 0; i < N; i++) {

                    int blank = map[i][0] == 0 ? 1 : 0;
                    int bound = 0;
                    for (int j = 1; j < N; j++) {

                        if (map[i][j] == 0) {

                            blank++;
                        } else {

                            // 병합 가능
                            if (map[i][j] == map[i][j - blank - 1 < bound ? bound : j - blank - 1]) {

                                map[i][j - blank - 1 < bound ? bound : j - blank - 1] *= 2;
                                map[i][j] = 0;

                                bound = j - blank;
                                blank++;
                            }

                            // 병합 불가능
                            else {

                                if (blank != 0) {

                                    map[i][j - blank] = map[i][j];
                                    map[i][j] = 0;

                                    bound = j - blank;

                                }
                            }
                        }
                    }
                }
                break;

            case "right":

                for (int i = 0; i < N; i++) {

                    int blank = map[i][N - 1] == 0 ? 1 : 0;
                    int bound = N - 1;
                    for (int j = N - 2; j >= 0; j--) {

                        if (map[i][j] == 0) {

                            blank++;
                        } else {

                            // 병합 가능
                            if (map[i][j] == map[i][j + blank + 1 > bound ? bound : j + blank + 1]) {

                                map[i][j + blank + 1 > bound ? bound : j + blank + 1] *= 2;
                                map[i][j] = 0;

                                bound = j + blank;
                                blank++;
                            }

                            // 병합 불가능
                            else {

                                if (blank != 0) {

                                    map[i][j + blank] = map[i][j];
                                    map[i][j] = 0;

                                    bound = j + blank;

                                }
                            }
                        }
                    }
                }
                break;
        }

        return map;
    }

    static void customMove(int[][] map, int N, String direction) {

        map = move(map, N, direction);

        printMap(map, N);
    }

    static void printMap(int[][] map, int N) {

        for (int i = 0; i < N; i++) {


            for (int j = 0; j < N; j++) {

                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


}

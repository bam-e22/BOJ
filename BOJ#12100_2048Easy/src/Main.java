import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#12100 2048 (Easy)
 * https://www.acmicpc.net/problem/12094
 */

public class Main {

    static final int FINAL_STEP = 5;
    static int MAX_VALUE = 0;

    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;

    public static void main(String[] args) throws IOException {

        int N; // 보드의 크기
        int[][] map; // 보드

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {

            Arrays.fill(map[i], 0);
        }

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (N == 1) {

            System.out.println(map[0][0]);
        } else {

            backTracking(map, N, 0);
        }

        System.out.println(MAX_VALUE);

    } // main

    static void backTracking(int[][] map, int N, int step) {

        // 5 Step 도달 시
        if (step == FINAL_STEP) {

            int tempValue = checkMaxValue(map, N);
            MAX_VALUE = tempValue > MAX_VALUE ? tempValue : MAX_VALUE;

            return;
        }

        // 4 방향
        for (int direction = 0; direction < 4; direction++) {

            int[][] mapCopy = new int[N][N];
            for (int i = 0; i < N; i++) {

                mapCopy[i] = Arrays.copyOf(map[i], N);
            }

            mapCopy = move(mapCopy, N, direction);
            backTracking(mapCopy, N, step + 1);
        }
    }

    static int checkMaxValue(int[][] map, int N) {

        int maxValue = 0;
        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                maxValue = map[i][j] > maxValue ? map[i][j] : maxValue;
            }
        }

        return maxValue;
    }

    static int[][] move(int[][] map, int N, int direction) {

        switch (direction) {

            case UP:

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

            case DOWN:

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

            case LEFT:

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

            case RIGHT:

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

}

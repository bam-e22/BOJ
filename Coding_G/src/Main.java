/**
 * 보행자 천국
 * 카카오내비 개발자인 제이지는 시내 중심가의 경로 탐색 알고리즘 개발 업무를 담당하고 있다.
 * 최근 들어 보행자가 자유롭고 편리하게 걸을 수 있도록 보행자 중심의 교통 체계가 도입되면서 도심의 일부 구역은 자동차 통행이 금지되고,
 * 일부 교차로에서는 보행자 안전을 위해 좌회전이나 우회전이 금지되기도 했다.
 * 복잡해진 도로 환경으로 인해 기존의 경로 탐색 알고리즘을 보완해야 할 필요가 생겼다.
 * 도시 중심가의 지도는 m × n 크기의 격자 모양 배열 city_map으로 주어진다.
 * 자동차는 오른쪽 또는 아래 방향으로 한 칸씩 이동 가능하다.
 */

public class Main {

    static final int MOD = 20170805;

    static int[][] H = new int[501][501]; // i행 j열에서 오른쪽으로 갈 수 있는 경우의 수
    static int[][] V = new int[501][501]; // i행 j열에서 아래쪽으로 갈 수 있는 경우의 수

    public static void main(String[] args) {

        int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
        int m = 3;
        int n = 6;

        System.out.println(solution(m, n, cityMap));
    }

    public static int solution(int m, int n, int[][] cityMap) {

        H[0][0] = 1;
        V[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) continue;

                switch (cityMap[i][j]) {

                    case 0:

                        H[i][j] = (j - 1 >= 0 ? H[i][j - 1] : 0) + (i - 1 >= 0 ? V[i - 1][j] : 0);
                        V[i][j] = (j - 1 >= 0 ? H[i][j - 1] : 0) + (i - 1 >= 0 ? V[i - 1][j] : 0);

                        H[i][j] %= MOD;
                        V[i][j] %= MOD;
                        break;

                    case 1:

                        H[i][j] = 0;
                        V[i][j] = 0;
                        break;

                    case 2:

                        H[i][j] = (j - 1 >= 0 ? H[i][j - 1] : 0);
                        V[i][j] = (i - 1 >= 0 ? V[i - 1][j] : 0);

                        H[i][j] %= MOD;
                        V[i][j] %= MOD;
                        break;
                }
            }
        }

        return H[m - 1][n - 2] + V[m - 2][n - 1];
    }
}

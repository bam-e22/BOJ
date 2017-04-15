import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#3190 뱀
 * https://www.acmicpc.net/problem/3190
 */

public class Main {

    static int[][] map = new int[102][102];
    static int[][] direction = new int[102][102];

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    //static final int LEFT = 0;
    //static final int UP = 1;
    static final int RIGHT = 2;
    //static final int DOWN = 3;

    static final int BLANK = 0;
    static final int SNAKE = 1;
    static final int APPLE = 2;

    static final int INF = 100;

    public static void main(String[] args) throws IOException {

        int N; // map size
        int K; // apple
        int L; // 뱀의 방향 변환 개수
        int headRow = 1, headCol = 1; // 머리 위치
        int tailRow = 1, tailCol = 1; // 꼬리 위치
        int curDir = RIGHT; // 머리 방향

        // input - 맵 정보(맵 크기, 사과)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = APPLE;
        }

        // input - 뱀 이동정보
        L = Integer.parseInt(br.readLine());

        MoveInfo[] info = new MoveInfo[L + 1];
        for (int i = 0; i < L; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            info[i] = new MoveInfo(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
        }
        info[L] = new MoveInfo(INF, 'D');

        // solve
        // 초기 위치
        map[headRow][headCol] = SNAKE;
        direction[headRow][headCol] = curDir;

        int step = 0;
        boolean ret = false;

        // 뱀 이동 시작
        for (int i = 0; i < L + 1 && !ret; i++) {

            // 뱀 이동 정보를 차례로 꺼내오기
            int X = info[i].time;
            char C = info[i].rotateDir;

            // 시간 차이만큼 이동
            int diff = X;
            if (i > 0) diff = info[i].time - info[i - 1].time;

            while (diff-- > 0) {

                step++;

                // 맵 위치에 방향 기록
                direction[headRow][headCol] = curDir;

                // 머리 이동
                headRow += dRow[curDir];
                headCol += dCol[curDir];

                // 종료 - 벽에 충돌한 경우
                if (headRow < 1 || headRow > N || headCol < 1 || headCol > N) {

                    System.out.println(step);
                    ret = true;
                    break;
                }

                // 진행 - 빈칸인 경우
                if (map[headRow][headCol] == BLANK) {

                    // 맵업데이트
                    map[headRow][headCol] = SNAKE;
                    direction[headRow][headCol] = curDir;

                    map[tailRow][tailCol] = BLANK;

                    // 꼬리 이동
                    int tailDir = direction[tailRow][tailCol];
                    tailRow += dRow[tailDir];
                    tailCol += dCol[tailDir];
                }

                // 진행 - 사과를 만난 경우
                else if (map[headRow][headCol] == APPLE) {

                    // 맵 업데이트
                    map[headRow][headCol] = SNAKE;
                    direction[headRow][headCol] = curDir;
                }

                // 종료 - 자기 자신을 만난 경우
                else if (map[headRow][headCol] == SNAKE) {

                    System.out.println(step);
                    ret = true;
                    break;
                }
            }

            // 방향 갱신
            curDir = C == 'L' ? (curDir - 1 + 4) % 4 : (curDir + 1) % 4;

        } // ~ for loop
    } // ~ main
}

class MoveInfo {

    int time;
    char rotateDir;

    MoveInfo(int time, char rotateDir) {

        this.time = time;
        this.rotateDir = rotateDir;
    }
}

class Point {

    int row, col;

    Point(int row, int col) {

        this.row = row;
        this.col = col;
    }
}
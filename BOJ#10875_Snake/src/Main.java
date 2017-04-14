import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ#10875 뱀
 * https://www.acmicpc.net/problem/10875
 */

public class Main {

    static final int LEFT = 0;
    static final int UP = 1;
    static final int RIGHT = 2;
    static final int DOWN = 3;

    static final int HORIZON = 0;
    static final int VERTICAL = 1;

    static final int ROTATE_LEFT = -1;
    static final int ROTATE_RIGHT = 1;

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int size = 2 * L + 1;

        int curRow = L;
        int curCol = L;
        int curDir = RIGHT;

        // 4개의 경계선 입력
        ArrayList<Line> lineList = new ArrayList<>();   // line 저장 리스트
        lineList.add(new Line(-1, size, size, size));           // 오른쪽 경계선
        lineList.add(new Line(size, -1, size, size));           // 아래쪽 경계선
        lineList.add(new Line(-1, -1, -1, size));  // 위쪽 경계선
        lineList.add(new Line(-1, -1, size, -1));  // 왼쪽 경계선

        // input & solve
        int time;
        int rotateDir;
        long collisionTime = 0L;    // 충돌 시간
        for (int i = 0; i <= N; i++) {

            if (i < N) {

                StringTokenizer st = new StringTokenizer(br.readLine());

                time = Integer.parseInt(st.nextToken());
                rotateDir = st.nextToken().charAt(0) == 'L' ? ROTATE_LEFT : ROTATE_RIGHT;
            } else {

                // 마지막에 긴 선분을 하나 추가하여 뱀이 무조건 충돌할 수 있게 해준다
                time = INF;
                rotateDir = ROTATE_LEFT;
            }

            int t = INF;

            // 현재 선분과 저장된 선분들 충돌 검사, 가장 짧은 충돌시간을 찾는다
            //for (Line prevLine : lineList) {
            for (int l = 0; l < lineList.size() - 2; l++) {

                Line prevLine = lineList.get(l);

                switch (curDir) {

                    case LEFT:

                        if ((prevLine.dir == HORIZON && prevLine.row1 == curRow && curCol > prevLine.col2)
                                || (prevLine.dir == VERTICAL && curRow >= prevLine.row1 && curRow <= prevLine.row2 && curCol > prevLine.col2)) {

                            t = Math.min(t, curCol - prevLine.col2);
                        }
                        break;

                    case UP:

                        if ((prevLine.dir == VERTICAL && prevLine.col1 == curCol && curRow > prevLine.row2)
                                || (prevLine.dir == HORIZON && curCol >= prevLine.col1 && curCol <= prevLine.col2 && curRow > prevLine.row2)) {

                            t = Math.min(t, curRow - prevLine.row2);
                        }
                        break;

                    case RIGHT:

                        if ((prevLine.dir == HORIZON && prevLine.row1 == curRow && curCol < prevLine.col1)
                                || (prevLine.dir == VERTICAL && curRow >= prevLine.row1 && curRow <= prevLine.row2 && curCol < prevLine.col1)) {

                            t = Math.min(t, prevLine.col1 - curCol);
                        }
                        break;

                    case DOWN:

                        if ((prevLine.dir == VERTICAL && prevLine.col1 == curCol && curRow < prevLine.row1)
                                || (prevLine.dir == HORIZON && curCol >= prevLine.col1 && curCol <= prevLine.col2 && curRow < prevLine.row1)) {

                            t = Math.min(t, prevLine.row1 - curRow);
                        }
                        break;
                }
            }

            // 충돌한 경우
            if (t <= time) {

                collisionTime += t;

                System.out.println(collisionTime);
                return;
            }

            // 충돌하지 않은 경우
            collisionTime += time;

            // 현재 선분 생성
            int nextRow = curRow + dRow[curDir] * time;
            int nextCol = curCol + dCol[curDir] * time;

            Line curLine = new Line(curRow, curCol, nextRow, nextCol);
            lineList.add(curLine);

            // 다음 진행 설정
            curRow = nextRow;
            curCol = nextCol;
            curDir = (curDir + rotateDir + 4) % 4;
        }
    }
}

class Line {

    int row1, col1, row2, col2;
    int dir;

    Line(int row1, int col1, int row2, int col2) {

        this.row1 = row1;
        this.col1 = col1;
        this.row2 = row2;
        this.col2 = col2;

        setDirection();
        setPoint();
    }

    private void setDirection() {

        // HORIZON
        if (this.row1 == this.row2) {

            this.dir = 0;
        }
        // VERTICAL
        else {

            this.dir = 1;
        }
    }

    private void setPoint() {

        if (row1 > row2) {

            row1 ^= row2;
            row2 ^= row1;
            row1 ^= row2;
        }

        if (col1 > col2) {

            col1 ^= col2;
            col2 ^= col1;
            col1 ^= col2;
        }
    }
}
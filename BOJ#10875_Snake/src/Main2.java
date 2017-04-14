import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#10875 뱀
 * https://www.acmicpc.net/problem/10875
 */

public class Main2 {

    static final int LEFT = 0;
    static final int UP = 1;
    static final int RIGHT = 2;
    static final int DOWN = 3;

    static final boolean DEBUG = false;

    static final long MOD =1000000000L;

    public static void main(String[] args) throws IOException {

        System.out.println(1000000000 % MOD);

        boolean isAdded = false;
        int L; // map 의 크기 <= 100,000,000
        int N; // 머리 회전 횟수 <= 1,000
        LineInfo[] info;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        L = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        info = new LineInfo[N + 1];

        Point curPoint = new Point(L, L);
        int curDir = RIGHT;

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            char rotate = st.nextToken().charAt(0);

            int nextDir = changeDir(rotate, curDir);
            switch (curDir) {

                case LEFT:
                    info[i] = new LineInfo(new Point(curPoint.x - time, curPoint.y),
                            new Point(curPoint.x, curPoint.y), time, LEFT, nextDir);

                    curPoint.x = info[i].start.x;
                    curPoint.y = info[i].start.y;
                    break;

                case UP:
                    info[i] = new LineInfo(new Point(curPoint.x, curPoint.y - time),
                            new Point(curPoint.x, curPoint.y), time, UP, nextDir);

                    curPoint.x = info[i].start.x;
                    curPoint.y = info[i].start.y;
                    break;

                case RIGHT:
                    info[i] = new LineInfo(new Point(curPoint.x, curPoint.y),
                            new Point(curPoint.x + time, curPoint.y), time, RIGHT, nextDir);

                    curPoint.x = info[i].end.x;
                    curPoint.y = info[i].end.y;
                    break;

                case DOWN:
                    info[i] = new LineInfo(new Point(curPoint.x, curPoint.y),
                            new Point(curPoint.x, curPoint.y + time), time, DOWN, nextDir);

                    curPoint.x = info[i].end.x;
                    curPoint.y = info[i].end.y;
                    break;
            }

            curDir = nextDir;
        }

        if (curPoint.x >= 0 && curPoint.x <= 2 * L && curPoint.y >= 0 && curPoint.y <= 2 * L) {

            if (DEBUG) System.out.println("---------------ISADDED");
            isAdded = true;

            switch (curDir) {

                case LEFT:
                    info[N] = new LineInfo(new Point(curPoint.x - (2 * L + 1), curPoint.y),
                            new Point(curPoint.x, curPoint.y), (2 * L + 1), curDir, curDir);
                    break;

                case UP:
                    info[N] = new LineInfo(new Point(curPoint.x, curPoint.y - (2 * L + 1)),
                            new Point(curPoint.x, curPoint.y), (2 * L + 1), curDir, curDir);
                    break;

                case RIGHT:
                    info[N] = new LineInfo(new Point(curPoint.x, curPoint.y),
                            new Point(curPoint.x + (2 * L + 1), curPoint.y), (2 * L + 1), curDir, curDir);
                    break;

                case DOWN:
                    info[N] = new LineInfo(new Point(curPoint.x, curPoint.y),
                            new Point(curPoint.x, curPoint.y + (2 * L + 1)), (2 * L + 1), curDir, curDir);
                    break;
            }
        }

        // solve

        if (DEBUG) System.out.println(Arrays.toString(info));
        if (DEBUG) System.out.println();
        if (DEBUG) System.out.println();

        // O(N^2)
        long bodyCollisionTime = Long.MAX_VALUE;
        long boundaryCollisionTime = 0L;
        long notCollisionTime = 0L;
        boolean isBoundaryCollision = false;
        boolean isBodyCollision = false;

        if (DEBUG) System.out.println("isAdded=" + isAdded);
        if (DEBUG) System.out.println("N=" + (isAdded ? N + 1 : N));
        if (DEBUG) System.out.println("(isAdded ? N + 1 : N)=" + (isAdded ? N + 1 : N));
        //for (int i = 0; i < (isAdded ? N + 1 : N) && !isBoundaryCollision; i++) {
        for (int i = 0; i < (isAdded ? N + 1 : N); i++) {

            long collisionTime = 0;
            for (int j = i + 2; j < (isAdded ? N + 1 : N); j++) {
                //for (int j = N-1; j > i+1; j--) {

                if (DEBUG) System.out.println("Loop : i -> " + i + ", j -> " + j);

                if (i == j) {

                    continue;
                }

                // 1. check body collision
                if (checkBodyCollision(info[i], info[j])) {

                    if (DEBUG) System.out.println("#body collision at i ->" + i + ", j -> " + j);

                    for (int k = 0; k < j; k++) {

                        collisionTime += info[k].time;
                    }
                    collisionTime += calcBodyCollision(info[j], info[i]);

                    isBodyCollision = true;
                    if (DEBUG) System.out.println(" ==> " + collisionTime);
                    bodyCollisionTime = bodyCollisionTime > collisionTime ? collisionTime : bodyCollisionTime;

                    break;
                }

                if (DEBUG) System.out.println();

            } // inner loop


            // 2. check boundary collision
            long diff = checkBoundaryCollision(info[i], L);
            if (diff > 0) {

                if (DEBUG) System.out.println("#boundary collision at i ->" + i);

                for (int k = 0; k < i; k++) {

                    boundaryCollisionTime += info[k].time;
                }
                boundaryCollisionTime += diff;

                isBoundaryCollision = true;
                if (DEBUG) System.out.println(" ==> " + boundaryCollisionTime);
                break;
            }

            if (DEBUG) System.out.println();
            if (DEBUG) System.out.println();
            if (DEBUG) System.out.println();
        }// outer loop

        if (isBoundaryCollision) {

            if (DEBUG) System.out.println("isBoundaryCollision");

            System.out.println(Math.min(bodyCollisionTime, boundaryCollisionTime));
        } else if (!isBodyCollision && !isBoundaryCollision) {

            if (DEBUG) System.out.println("isNotCollision");

            for (int i = 0; i < N; i++) {

                notCollisionTime += info[i].time;
            }

            if (DEBUG) System.out.println("ddd" + notCollisionTime);
            if (N == 0) {

                notCollisionTime = L + 1;
            } else {

                notCollisionTime += calcNotCollisionTime(info[N - 1], L);
            }

            if (DEBUG) System.out.println("ddd" + notCollisionTime);

            System.out.println(notCollisionTime);
        } else {

            if (DEBUG) System.out.println("isBodyCollision");

            System.out.println(bodyCollisionTime);
        }
    }

    static long calcNotCollisionTime(LineInfo line, int L) {

        long diff = 0;

        switch (line.nextDir) {

            case LEFT:

                diff = line.start.x - 0;
                break;

            case UP:

                diff = line.start.y - 0;
                break;

            case RIGHT:

                diff = (2 * L) - line.end.x;
                break;

            case DOWN:

                diff = (2 * L) - line.end.y;
                break;
        }

        return diff > 0 ? diff + 1 : 0;
    }

    static long calcBodyCollision(LineInfo line1, LineInfo line2) {

        long diff = 0;

        switch (line1.dir) {

            case LEFT:

                diff = line1.end.x - line2.end.x;
                break;

            case UP:
                diff = line1.end.y - line2.end.y;
                break;

            case RIGHT:
                diff = line2.start.x - line1.start.x;
                break;

            case DOWN:
                diff = line2.start.y - line1.start.y;
                break;
        }

        return diff;
    }

    static boolean checkBodyCollision(LineInfo line1, LineInfo line2) {

        //boolean ret1 = false, ret2 = false;
        long p1, p2 = 0;

        //-> fn(A, B, C) = (x1y2 - x2y1) + (x2y3 - x3y2) + (x3y1 - x1y3)
        //-> fn(A, B, D) = (x1y2 - x2y1) + (x2y4 - x4y2) + (x4y1 - x1y4)

        // 1. line1, line2
        p1 = (line1.start.x * line1.end.y - line1.end.x * line1.start.y) + (line1.end.x * line2.start.y - line2.start.x * line1.end.y) + (line2.start.x * line1.start.y - line1.start.x * line2.start.y);
        p2 = (line1.start.x * line1.end.y - line1.end.x * line1.start.y) + (line1.end.x * line2.end.y - line2.end.x * line1.end.y) + (line2.end.x * line1.start.y - line1.start.x * line2.end.y);

        //p1 %= MOD;
        //p2 %= MOD;

        p1 = p1 < 0 ? -1 : p1 == 0 ? 0 : 1;
        p2 = p2 < 0 ? -1 : p2 == 0 ? 0 : 1;
        long product1 = (p1 * p2) % MOD;
        //ret1 = product1 < 0 ? true : false;
        if (DEBUG) System.out.println("product1=" + product1);

        // 2. line2, line1
        p1 = (line2.start.x * line2.end.y - line2.end.x * line2.start.y) + (line2.end.x * line1.start.y - line1.start.x * line2.end.y) + (line1.start.x * line2.start.y - line2.start.x * line1.start.y);
        p2 = (line2.start.x * line2.end.y - line2.end.x * line2.start.y) + (line2.end.x * line1.end.y - line1.end.x * line2.end.y) + (line1.end.x * line2.start.y - line2.start.x * line1.end.y);

        //p1 %= MOD;
        //p2 %= MOD;

        p1 = p1 < 0 ? -1 : p1 == 0 ? 0 : 1;
        p2 = p2 < 0 ? -1 : p2 == 0 ? 0 : 1;

        long product2 = (p1 * p2) % MOD;
        //ret2 = product2 < 0 ? true : false;
        if (DEBUG) System.out.println("product2=" + product2);

        // 둘다 < 0 : 교차
        if (product1 < 0 && product2 < 0) {

            return true;
        }
        // 같은 부호 : 교차X
        else if (product1 * product2 > 0) {

            return false;
        }

        // 하나는 음수 하나는 양수 : 교차 X
        else if (product1 * product2 < 0) {

            return false;
        }

        // 둘중 하나만 0
        else if (product1 + product2 != 0 && product1 * product2 == 0) {

            return false;
        }

        // 둘다 0 : 충돌하거나 충돌하지 않음
        else if (product1 == 0 && product2 == 0) {

            // 수직
            if ((line1.dir + line2.dir) % 2 != 0) {

                if (product1 == 0 && product2 == 0) {

                    return true;
                } else {

                    return false;
                }
            }
            // 수평
            else {

                // 세로
                if (line1.dir == UP || line1.dir == DOWN) {

                    if ((line1.start.y < line2.start.y && line1.end.y < line2.start.y) ||
                            (line2.start.y < line1.start.y && line2.end.y < line1.start.y)) {

                        return false;
                    }

                    return true;
                }

                // 가로
                else {

                    if ((line1.start.x < line2.start.x && line1.end.x < line2.start.x) ||
                            (line2.start.x < line1.start.x && line2.end.x < line1.start.x)) {

                        return false;
                    }

                    return true;
                }
            }
        }

        return false;
    }


    static long checkBoundaryCollision(LineInfo line, int L) {

        long diff = 0;

        switch (line.dir) {

            case LEFT:
                if (line.start.x < 0) {

                    diff = line.end.x - 0;
                }
                break;

            case UP:
                if (line.start.y < 0) {

                    diff = line.end.y - 0;
                }
                break;

            case RIGHT:
                if (line.end.x > 2 * L) {

                    diff = (2 * L) - line.start.x;
                }
                break;

            case DOWN:
                if (line.end.y > 2 * L) {

                    diff = (2 * L) - line.start.y;
                }
                break;
        }

        return diff > 0 ? diff + 1 : 0;
    }


    static int changeDir(char rotate, int curDir) {

        // direction
        if (rotate == 'L') {

            curDir = curDir - 1 < 0 ? 3 : curDir - 1;
        } else if (rotate == 'R') {

            curDir = curDir + 1 > 3 ? 0 : curDir + 1;
        }

        return curDir;
    }
}

class LineInfo {

    Point start;
    Point end;
    int time;
    int dir;
    int nextDir;

    LineInfo(Point start, Point end, int time, int dir, int nextDir) {

        this.start = start;
        this.end = end;
        this.time = time;
        this.dir = dir;
        this.nextDir = nextDir;
    }

    @Override
    public String toString() {

        return "(" + start.x + "," + start.y + ") <-> (" + end.x + "," + end.y + ")"
                + ", dir=" + (dir == 0 ? "LEFT" : dir == 1 ? "UP" : dir == 2 ? "RIGHT" : "DOWN");
    }
}

class Point {

    long x;
    long y;

    Point(long x, long y) {

        this.x = x;
        this.y = y;
    }
}
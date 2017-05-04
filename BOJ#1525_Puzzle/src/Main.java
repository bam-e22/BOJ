import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#1525 퍼즐
 * https://www.acmicpc.net/problem/1525
 */

public class Main {

    static final int SIZE = 3;

    // left, up, right, down
    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};
    static final long[] dShift = {-4, -12, 4, 12};

    public static void main(String[] args) throws IOException {

        int row = -1, col = -1;
        // 현재 map, 종료 map
        long state = 0L, endState = 0L;
        HashSet<Long> discovered = new HashSet<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < SIZE; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < SIZE; j++) {

                int number = Integer.parseInt(st.nextToken());

                if (number == 0) {

                    row = i;
                    col = j;
                }

                state <<= 4;
                state |= number;
            }
        }

        for (int i = 1; i < 9; i++) {

            endState <<= 4;
            endState |= i;
        }
        endState <<= 4;

        // solve - bfs

        // 이동횟수
        int cnt = -1;

        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(row, col, state));
        discovered.add(state);

        while (!queue.isEmpty()) {

            cnt++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                Point u = queue.poll();

                // 종료 조건
                if (u.state == endState) {

                    System.out.println(cnt);
                    return;
                }

                // 4방향 탐색
                for (int j = 0; j < 4; j++) {

                    int nextRow = u.row + dRow[j];
                    int nextCol = u.col + dCol[j];

                    // map 범위를 벗어나는 경우
                    if (nextRow < 0 || nextRow > 2 || nextCol < 0 || nextCol > 2) continue;

                    // nextState 계산

                    // 제거할 퍼즐 조각 위치 (0~8) : nextRow, nextCol -> nextPos
                    long nextPos = 8 - ((nextRow * 3) + nextCol);

                    // 제거할 퍼즐 조각
                    long piece = u.state & (15L << (nextPos * 4));

                    // 제거한다
                    long nextState = u.state - piece;

                    // 제거한 퍼즐 조각을 현재 위치에 추가한다
                    if (dShift[j] > 0) piece <<= dShift[j];
                    else piece >>= -dShift[j];

                    nextState += piece;

                    // nextState를 이미 방문한 경우
                    if (discovered.contains(nextState)) continue;

                    // nextState를 방문하지 않은 경우
                    discovered.add(nextState);
                    queue.add(new Point(nextRow, nextCol, nextState));
                }
            }
        }

        System.out.println(-1);
    }
}

class Point {

    int row, col;
    long state;

    Point(int row, int col, long state) {

        this.row = row;
        this.col = col;
        this.state = state;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#1012 유기농 배추
 * https://www.acmicpc.net/problem/1012
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int T; // 테스트케이스
        int K; // 배추가 심어져 있는 위치의 개수
        ArrayList<Position> posList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            Integer.parseInt(st.nextToken());
            Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            for (int k = 0; k < K; k++) {

                st = new StringTokenizer(br.readLine());
                posList.add(new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            System.out.println(bfs(posList));
        }
    } // end main

    static int bfs(ArrayList<Position> posList) {

        int count = 0;
        Position pos;

        Queue<Position> queue = new LinkedList<>();

        while (!posList.isEmpty()) {

            queue.add(posList.get(0));
            posList.get(0).visited = true;

            while (!queue.isEmpty()) {

                pos = queue.poll();
                posList.remove(pos);

                for (Position check : posList) {

                    if (!check.visited && (Math.abs(pos.X - check.X) + Math.abs(pos.Y - check.Y) == 1)) {

                        queue.add(check);
                        check.visited = true;
                    }
                }
            }

            count++;
        }

        return count;
    }
}

// 배추 위치
class Position {

    int X;
    int Y;
    boolean visited;

    Position(int X, int Y) {

        this.X = X;
        this.Y = Y;
        this.visited = false;
    }

    @Override
    public String toString() {

        return "(" + X + ", " + Y + ")";
    }
}
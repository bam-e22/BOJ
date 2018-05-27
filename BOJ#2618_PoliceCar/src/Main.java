import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#2618 경찰차
 * https://www.acmicpc.net/problem/2618
 */

public class Main {

    static final int FIRSTCAR = 1;
    static final int SECONDCAR = 2;
    static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {

        int N; // 도로의 개수
        int W; // 사건의 개수
        Point[] point; // 사건의 위치
        Point[] car = new Point[3]; // 경찰차 위치

        int sum = 0; // 움직인 거리의 합
        int[] order; // 사건 별 담당 경찰차 정보

        int[][] dp; // w 번째까지 사건을 처리 했을 때, 비용의 최소값

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        car[FIRSTCAR] = new Point(1, 1);
        car[SECONDCAR] = new Point(N, N);

        order = new int[W];
        point = new Point[W];
        for (int i = 0; i < W; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            point[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            order[i] = EMPTY;
        }

        for (int i = 0; i < W; i++) {

            int distance1 = 0;
            int distance2 = 0;
            // 비교 1
            distance1 = Math.abs(car[FIRSTCAR].x - point[i].x) + Math.abs(car[FIRSTCAR].y - point[i].y);

            // 비교 2
            distance2 = Math.abs(car[SECONDCAR].x - point[i].x) + Math.abs(car[SECONDCAR].y - point[i].y);

            // assign
            if (distance1 < distance2) {

                sum += distance1;
                order[i] = FIRSTCAR;

                car[FIRSTCAR].x = point[i].x;
                car[FIRSTCAR].y = point[i].y;


            } else {

                sum += distance2;
                order[i] = SECONDCAR;

                car[SECONDCAR].x = point[i].x;
                car[SECONDCAR].y = point[i].y;
            }
        }

        System.out.println(sum);
        for (int policeCar : order) {

            System.out.println(policeCar);
        }


    }

}

class Point {

    int x;
    int y;

    public Point(int x, int y) {

        this.x = x;
        this.y = y;
    }
}
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#11650 좌표 정렬하기
 * https://www.acmicpc.net/problem/11650
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N; // 점의 개수
        Coordinate[] coord;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        coord = new Coordinate[N];

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            coord[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(coord);

        for (int i = 0; i < N; i++) {

            bw.write(coord[i] + "\n");
        }

        bw.flush();

        bw.close();
        br.close();
    }
}

class Coordinate implements Comparable<Coordinate> {

    int x;
    int y;

    Coordinate(int x, int y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Coordinate o) {

        return this.x < o.x ? -1 : this.x > o.x ? 1 : this.y < o.y ? -1 : 1;
    }

    @Override
    public String toString() {

        return x + " " + y;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1263 시간 관리
 * https://www.acmicpc.net/problem/1263
 */

public class Main {

    static int N;
    static TimeTable[] table = new TimeTable[1001];

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            table[i] = new TimeTable(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // solve

        // S(일을 끝내야 하는 시간)를 기준으로 오름차순 정렬
        Arrays.sort(table, 0, N);

        // time : 일을 끝마칠 수 있게, 가장 늦게 일을 시작하는 시간
        int time = table[N - 1].S - table[N - 1].T;
        for (int i = N - 2; i >= 0; i--) {

            // 다음 일을 위해, 현재 납기일 보다 더 빨리 끝마쳐야 한다면
            if (table[i].S > time) {

                table[i].S = time;
            }

            time = table[i].S - table[i].T;
        }

        if (time < 0) System.out.println(-1);
        else System.out.println(time);
    }
}

class TimeTable implements Comparable<TimeTable> {

    int T;
    int S;

    TimeTable(int T, int S) {

        this.T = T;
        this.S = S;
    }

    @Override
    public int compareTo(TimeTable o) {

        return this.S < o.S ? -1 : this.S == o.S ? this.T <= o.T ? -1 : 1 : 1;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#6068 시간 관리하기
 * https://www.acmicpc.net/problem/6068
 */

public class Main {

    static int N;
    static TimeTable[] table = new TimeTable[1001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            table[i] = new TimeTable(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(table, 0, N);

        int time = table[N - 1].S - table[N - 1].T;
        for (int i = N - 2; i >= 0; i--) {

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
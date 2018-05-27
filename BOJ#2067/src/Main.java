import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int E_TIME = 4;
    static final int E_WAIT_TIME = 10;
    static final int W_TIME = 20;

    static int N;
    static int[] step = new int[32];
    static int[] dp = new int[32];
    static int min = Integer.MAX_VALUE;

    static {

        Arrays.fill(dp, -1);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {

            step[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Arrays.toString(step));
    }

    static void solve(int idx, int time) {

        if (idx == N - 1) {

            min = min > time ? time : min;
            return;
        }

        // 멈추는 경우
        solve(idx + 1, time + E_WAIT_TIME);

        // 안서고 계단을 이용하는 경우
        solve(idx + 1, time + E_WAIT_TIME);
    }

}

































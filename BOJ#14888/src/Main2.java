import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static int N;
    static int[] A = new int[12];
    static int[] OP = new int[4];

    static int maxValue = -1000000001;
    static int minValue = 1000000000;

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {

            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        // +, -, *, /
        for (int i = 0; i < 4; i++) {

            OP[i] = Integer.parseInt(st.nextToken());
        }

        // Solve
        int[] cmd = new int[N - 1];
        dfs(cmd, 0);

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    static void dfs(int[] cmd, int depth) {

        if (depth == N - 1) {

            int ret = calc(cmd);

            maxValue = maxValue < ret ? ret : maxValue;
            minValue = minValue > ret ? ret : minValue;
            return;
        }

        for (int i = 0; i < 4; i++) {

            if (OP[i] == 0) continue;

            OP[i]--;
            cmd[depth] = i;

            dfs(cmd, depth + 1);

            OP[i]++;
        }
    }

    static int calc(int[] cmd) {

        int ret = A[0];

        for (int i = 0; i < N - 1; i++) {

            switch (cmd[i]) {

                case 0:
                    ret += A[i + 1];
                    break;

                case 1:
                    ret -= A[i + 1];
                    break;

                case 2:
                    ret *= A[i + 1];
                    break;

                case 3:
                    ret /= A[i + 1];
                    break;
            }
        }

        return ret;
    }
}
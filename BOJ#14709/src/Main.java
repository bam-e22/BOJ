import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final String SUCCESS = "Wa-pa-pa-pa-pa-pa-pow!";
    static final String FAIL = "Woof-meow-tweet-squeek";


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[][] sign = new boolean[6][6];

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sign[a][b] = true;
            sign[b][a] = true;
        }

        boolean ret = checkSign(sign);
        System.out.println(ret ? SUCCESS : FAIL);
    }

    static boolean checkSign(boolean[][] sign) {

        // 엄지
        if (sign[1][2] || sign[1][5] ) return false;
        // 중지
        if (sign[3][2]|| sign[3][5]) return false;
        // 약지
        if (sign[4][2]|| sign[4][5]) return false;

        if (!(sign[1][3] && sign[3][4] && sign[4][1])) return false;

        return true;
    }
}

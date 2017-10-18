import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int C, P;
    static int[] height = new int[101];

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < C; i++) {

            height[i] = Integer.parseInt(st.nextToken());
        }

        // Solve

        int ret = 0;
        switch (P) {

            case 1:
                ret = check("0") + check("0000");
                break;

            case 2:
                ret = check("00");
                break;

            case 3:
                ret = check("001") + check("10");
                break;

            case 4:
                ret = check("100") + check("01");
                break;

            case 5:
                ret = check("000") + check("01") + check("101") + check("10");
                break;

            case 6:
                ret = check("000") + check("00") + check("011") + check("20");
                break;

            case 7:
                ret = check("000") + check("00") + check("110") + check("02");
                break;
        }

        System.out.println(ret);
    }

    static int check(String S) {

        char[] tetrisHeight = S.toCharArray();
        int n = tetrisHeight.length;

        int ret = 0;
        for (int col = 0; col <= C - n; col++) {

            // col 위치에 따라 테트리스 높이에 basisColHeight가 일정하게 추가되어야 하는데,
            // 테트리스 높이와 각 칸과의 차이가 일정한지만 검사하면 되므로 생략한다
            /*

            for (int i = 0; i < n; i++) {

                tetrisHeight[i] += basisColHeight;
            }
            */

            boolean ok = true;
            int diff = tetrisHeight[0] - height[col];

            // 테트리스와 각 칸 사이에 공백이 있는지 확인( = 테트리스와 각 칸의 diff가 일정한지 확인)
            for (int j = 0; j < n; j++) {

                if (tetrisHeight[j] - height[col + j] != diff) ok = false;
            }

            if (ok) ret++;
        }

        return ret;
    }
}

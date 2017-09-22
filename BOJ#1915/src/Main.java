import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int maxValue = 0;

        for (int i = 0; i < n; i++) {

            String inputLine = br.readLine();
            for (int j = 0; j < m; j++) {

                map[i][j] = inputLine.charAt(j) - '0';

                if (map[i][j] == 0) continue;

                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (map[i - 1][j - 1] > 0 && map[i][j - 1] > 0 && map[i - 1][j] > 0) {

                        map[i][j] = Math.min(map[i - 1][j - 1], Math.min(map[i - 1][j], map[i][j - 1])) + 1;
                    }
                }

                maxValue = maxValue < map[i][j] ? map[i][j] : maxValue;
            }
        }

        System.out.println(maxValue * maxValue);
    }
}

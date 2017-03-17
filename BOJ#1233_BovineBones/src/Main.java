import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * BOJ#1233 주사위
 * https://www.acmicpc.net/problem/1233
 */

public class Main {

    public static void main(String[] args) throws IOException {

        HashMap<Integer, Integer> map = new HashMap<>();

        int S1, S2, S3;
        int maxCount = 0;
        int maxValue = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S1 = Integer.parseInt(st.nextToken());
        S2 = Integer.parseInt(st.nextToken());
        S3 = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= S1; i++) {

            for (int j = 1; j <= S2; j++) {

                for (int k = 1; k <= S3; k++) {

                    map.put(i + j + k, map.get(i + j + k) == null ? 1 : map.get(i + j + k) + 1);

                    if (maxCount < map.get(i + j + k)) {

                        maxCount = map.get(i + j + k);
                        maxValue = i + j + k ;
                    }
                }
            }
        }

        System.out.println(maxValue);

    }
}

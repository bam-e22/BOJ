import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static HashMap<Integer, Integer> aMap = new HashMap<>();
    static HashMap<Integer, Integer> bMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // A
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            int a = Integer.parseInt(st.nextToken());

            for (int j = 2; j * j <= a; j++) {

                int cnt = 0;
                while (a % j == 0) {

                    a /= j;
                    cnt++;
                }

                if (aMap.containsKey(j)) aMap.put(j, aMap.get(j) + cnt);
                else aMap.put(j, cnt);
            }

            if (a > 1) {

                if (aMap.containsKey(a)) aMap.put(a, aMap.get(a) + 1);
                else aMap.put(a, 1);
            }
        }

        // B
        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {

            int b = Integer.parseInt(st.nextToken());

            for (int j = 2; j * j <= b; j++) {

                int cnt = 0;
                while (b % j == 0) {

                    b /= j;
                    cnt++;
                }

                if (bMap.containsKey(j)) bMap.put(j, bMap.get(j) + cnt);
                else bMap.put(j, cnt);
            }

            if (b > 1) {

                if (bMap.containsKey(b)) bMap.put(b, bMap.get(b) + 1);
                else bMap.put(b, 1);
            }
        }

        // Calc
        long gcd = 1L;
        boolean checkNum = false;

        for (Integer key : aMap.keySet()) {

            if (!bMap.containsKey(key)) continue;

            int min = Math.min(aMap.get(key), bMap.get(key));

            for (int i = 0; i < min; i++) {

                gcd *= key;

                if (gcd > 1000000000) {

                    checkNum = true;
                    gcd %= 1000000000;
                }
            }
        }

        if (checkNum) System.out.printf("%09d", gcd);
        else System.out.println(gcd);

        /*
        BigInteger a = BigInteger.valueOf(1);
        BigInteger b = BigInteger.valueOf(1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            a = a.multiply(new BigInteger(st.nextToken()));
        }
        
        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {

            b = b.multiply(new BigInteger(st.nextToken()));
        }

        String ret = a.gcd(b).toString();

        if (ret.length() > 9) {

            System.out.println(ret.substring(ret.length() - 9, ret.length()));
        } else {

            System.out.println(ret);
        }
        */
    }
}

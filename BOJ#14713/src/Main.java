import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        LinkedHashSet<String>[] set = new LinkedHashSet[N];

        String[] S = new String[N];
        for (int i = 0; i < N; i++) {

            S[i] = br.readLine();
            set[i] = new LinkedHashSet<>();

            StringTokenizer st = new StringTokenizer(S[i]);

            while (st.hasMoreTokens()) {

                String next = st.nextToken();

                // 단어가 2번 이상 등장하는 경우
                if (set[i].contains(next)) {

                    System.out.println("Impossible");
                    return;
                } else {

                    set[i].add(next);
                }
            }
        }

        String L = br.readLine();

        // 한 문장에서 순서가 달라진 경우
        // 단어를 말하는 도중 다른 앵무새가 말을 가로챈 경우

        // Solve
        StringTokenizer st = new StringTokenizer(L);

        while (st.hasMoreTokens()) {

            String word = st.nextToken();

            boolean ret = false;
            for (int i = 0; i < N; i++) {

                if (set[i].size() != 0 && word.equals(set[i].iterator().next())) {

                    ret = true;
                    set[i].remove(word);
                    break;
                }
            }

            // head에 단어가 없는 경우
            if (!ret) {

                System.out.println("Impossible");
                return;
            }
        }

        // 아직 말하지 않은 단어가 있으면
        for (int i = 0; i < N; i++) {

            if (set[i].size() != 0) {

                System.out.println("Impossible");
                return;
            }
        }

        System.out.println("Possible");
    }
}

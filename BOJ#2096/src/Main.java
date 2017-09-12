import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;

    static final int INF = 100000000;

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] currentLine = new int[3];

        int[] maxScore = new int[3];
        int[] minScore = new int[3];

        Arrays.fill(minScore, INF);

        // Solve
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) currentLine[i] = Integer.parseInt(st.nextToken());

        System.arraycopy(currentLine, 0, maxScore, 0, 3);
        System.arraycopy(currentLine, 0, minScore, 0, 3);

        for (int i = 1; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) currentLine[j] = Integer.parseInt(st.nextToken());

            int[] tempMax = {0, 0, 0};
            int[] tempMin = {INF, INF, INF};

            for (int col = 0; col < 3; col++) {

                switch (col) {

                    case 0:
                        for (int j = 0; j < 2; j++) {
                            tempMax[col] = Math.max(tempMax[col], maxScore[j] + currentLine[col]);
                            tempMin[col] = Math.min(tempMin[col], minScore[j] + currentLine[col]);
                        }
                        break;

                    case 1:
                        for (int j = 0; j < 3; j++) {
                            tempMax[col] = Math.max(tempMax[col], maxScore[j] + currentLine[col]);
                            tempMin[col] = Math.min(tempMin[col], minScore[j] + currentLine[col]);
                        }
                        break;

                    case 2:
                        for (int j = 1; j < 3; j++) {
                            tempMax[col] = Math.max(tempMax[col], maxScore[j] + currentLine[col]);
                            tempMin[col] = Math.min(tempMin[col], minScore[j] + currentLine[col]);
                        }
                        break;
                }
            }

            System.arraycopy(tempMax, 0, maxScore, 0, 3);
            System.arraycopy(tempMin, 0, minScore, 0, 3);
        }

        int max = Math.max(maxScore[0], Math.max(maxScore[1], maxScore[2]));
        int min = Math.min(minScore[0], Math.min(minScore[1], minScore[2]));

        System.out.println(max + " " + min);
    }
}

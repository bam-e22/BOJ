import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        double ave = 0.0;
        double maxScore = 0.0;
        double[] score = new double[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            score[i] = Double.parseDouble(st.nextToken());
            ;
            maxScore = maxScore < score[i] ? score[i] : maxScore;
        }

        for (int i = 0; i < N; i++) {

            ave += score[i] / maxScore * 100;
        }

        ave /= N;

        System.out.printf("%.2f", ave);
    }
}

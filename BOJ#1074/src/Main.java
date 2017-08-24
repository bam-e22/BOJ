import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int unitSize = (int) Math.pow(2, N - 1);

        System.out.println(solve(unitSize, r, c));
    }

    static int solve(int size, int r, int c) {

        // Base Case (더이상 나눌 수 없는 경우)
        if (size <= 1) {

            return (r * 2) + c;
        }
        // 나눌 수 있는 경우
        else {

            // divide & merge
            // 위치 확인

            return ((r / size) * 2 * size * size) + ((c / size) * size * size) + solve(size / 2, r % size, c % size);
        }
    }
}

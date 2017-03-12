import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1965 상자넣기
 * https://www.acmicpc.net/problem/1965
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int n; // 상자의 개수
        int[] list; // 상자의 크기 List

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        n = Integer.parseInt(br.readLine());
        list = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {

            list[i] = Integer.parseInt(st.nextToken());
        }

        // solve : O(NlogN)
        System.out.println(getLISLength(list, n));

    }

    static int getLISLength(int[] list, int n) {

        int[] tailTable = new int[n];
        int length;

        // 초기값
        tailTable[0] = list[0];
        length = 1;

        for (int i = 1; i < n; i++) {

            if (list[i] < tailTable[0]) {

                tailTable[0] = list[i];
            } else if (list[i] > tailTable[length - 1]) {

                tailTable[length] = list[i];
                length++;
            } else {

                int idx = Arrays.binarySearch(tailTable, 0, length, list[i]);
                idx = idx < 0 ? -idx - 1 : idx;

                tailTable[idx] = list[i];
            }
        }

        return length;
    }
}

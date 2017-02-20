import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * BOJ#2532 먹이사슬
 * https://www.acmicpc.net/problem/2532
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N;
        Animal[] list;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new Animal[N];

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            Integer.parseInt(st.nextToken());

            list[i] = new Animal(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // start 값을 기준으로 오름차순 정렬 + end 값을 기준으로 내림차순 정렬
        Arrays.sort(list);


        // solve
        System.out.println(solve(list, N));
    }

    // end 값을 이용하여 최장 길이 감소 수열 LDS 만들기
    static int solve(Animal[] list, int N) {

        Integer[] tailTable = new Integer[N];
        int lengthOfLIS;

        // 초기값
        tailTable[0] = list[0].end;
        lengthOfLIS = 1;

        // solve
        for (int i = 1; i < N; i++) {

            if (list[i].isEqual(list[i - 1])) {

                continue;
            }

            // 후보값이 LDS 처음 값보다 큰 경우
            if (list[i].end >= tailTable[0]) {

                tailTable[0] = list[i].end;
            }

            // 후보값이 LDS 마지막 값보다 작은 경우
            else if (list[i].end <= tailTable[lengthOfLIS - 1]) {

                tailTable[lengthOfLIS++] = list[i].end;
            }

            // 후보값이 LDS 처음 값보다 작고, LDS 마지막 값보다 큰 경우
            else {

                // 1. 찾는 값이 없는 경우 -> 넣어야 할 자리를 return
                // 예) [9 8 8 8 6 4 2] 에서 7을 찾으면 4가 return 된다
                int idx = Arrays.binarySearch(tailTable, 0, lengthOfLIS, list[i].end, Comparator.reverseOrder());

                idx = idx < 0 ? -idx - 1 : idx;

                // 2. 찾는 값이 있는 경우 + 여러 개 있는 경우
                // 예) [9 8 8 8 8 ....8 7] 에서 8을 binarySearh로 찾으면 어떤 idx가 나올지 모른다.
                while (tailTable[idx] == list[i].end) {

                    idx++;
                }

                tailTable[idx] = list[i].end;
            }
        }

        return lengthOfLIS;
    }

} // main

class Animal implements Comparable {

    int start;
    int end;

    Animal(int start, int end) {

        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Object o) {

        // start 값을 기준으로 오름차순 + end 값을 기준으로 내림차순
        return this.start < ((Animal) o).start ? -1 : this.start > ((Animal) o).start ? 1 : this.end > ((Animal) o).end ? -1 : 1;
    }

    @Override
    public String toString() {

        return "(" + start + ", " + end + ")";
    }

    boolean isEqual(Animal animal) {

        return this.start == animal.start && this.end == animal.end;
    }
}
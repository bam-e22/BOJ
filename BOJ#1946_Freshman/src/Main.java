import java.io.IOException;
import java.util.Scanner;

/**
 * BOJ#1946 신입사원
 * https://www.acmicpc.net/problem/1946
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int T; // 테스트 케이스 개수
        int N; // 지원자의 숫자 <= 100,000
        Grade[] grade; // 서류, 면접 등수

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int t = 0; t < T; t++) {

            N = sc.nextInt();

            grade = new Grade[N + 1];

            for (int i = 0; i < N; i++) {

                int a = sc.nextInt();
                int b = sc.nextInt();

                grade[a] = new Grade(a, b);
            }

            int ans = 1;
            int best = grade[1].b;
            for (int i = 2; i <= N; i++) {

                if (grade[i].b < best) {

                    best = best > grade[i].b ? grade[i].b : best;
                    ans++;
                }
            }

            System.out.println(ans);
        }
    }
}

class Grade implements Comparable<Grade> {

    int a; // 서류 등수
    int b; // 면접 등수

    Grade(int a, int b) {

        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Grade o) {

        return this.a < o.a ? -1 : 1;
    }

    @Override
    public String toString() {

        return "(" + a + "," + b + ")";
    }
}

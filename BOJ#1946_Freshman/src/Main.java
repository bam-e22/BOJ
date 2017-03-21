import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * BOJ#1946 신입사원
 * https://www.acmicpc.net/problem/1946
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int T; // 테스트 케이스 개수
        int N; // 지원자의 숫자 <= 100,000
        Grade[] grade; // 서류, 면접 등수
        TreeSet<Grade> set;

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int t = 0; t < T; t++) {

            N = sc.nextInt();

            grade = new Grade[N];
            set = new TreeSet<>();

            for (int i = 0; i < N; i++) {

                grade[i] = new Grade(sc.nextInt(), sc.nextInt());
            }

            Arrays.sort(grade);

            set.add(grade[0]);

            int ans = 0;
            int min = set.first().b;
            for (int i = 1; i < N; i++) {

                if (grade[i].b < min) {

                    min = min > grade[i].b ? grade[i].b : min;
                    ans++;
                }
            }

            System.out.println(ans + 1);
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

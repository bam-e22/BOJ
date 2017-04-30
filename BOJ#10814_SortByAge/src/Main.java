import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ#10814 나이순 정렬
 * https://www.acmicpc.net/problem/10814
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Member> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            pq.add(new Member(i, Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        bw.write(pq.poll().toString());
        while (!pq.isEmpty()) {

            bw.newLine();
            bw.write(pq.poll().toString());
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class Member implements Comparable<Member> {

    int order;
    int age;
    String name;

    Member(int order, int age, String name) {

        this.order = order;
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Member o) {

        return this.age < o.age ? -1 : this.age == o.age ? (this.order < o.order ? -1 : 1) : 1;
    }

    @Override
    public String toString() {

        return age + " " + name;
    }
}
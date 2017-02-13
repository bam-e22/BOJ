import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#1966 프린터 큐
 * https://www.acmicpc.net/problem/1966
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int T;
        int N; // 문서의 수
        int M;
        Queue<Doc> docsQueue;
        Queue<Integer> pq;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            docsQueue = new LinkedList<Doc>();
            pq = new PriorityQueue<Integer>();

            // input
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {

                int priority = Integer.parseInt(st.nextToken());

                docsQueue.offer(new Doc(i, priority));
                pq.offer(-priority);
            }

            // solve
            int count = 0;

            while (true) {


                Doc element = docsQueue.poll();
                int highRank = -pq.peek();

                if (element.priority < highRank) {

                    docsQueue.offer(element);
                } else {

                    pq.poll();
                    count++;

                    if (element.name == M) {

                        break;
                    }
                }
            }

            System.out.println(count);
        }
    }
}

class Doc {

    int name;
    int priority;

    Doc(int n, int p) {

        this.name = n;
        this.priority = p;
    }
}

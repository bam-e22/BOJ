import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            int N = Integer.parseInt(br.readLine());

            Node[] nodes = new Node[N];

            for (int i = 0; i < N; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());

                nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()));
            }

            UnionFind unionFind = new UnionFind(N);

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {

                    // 조건 만족 시 Union
                    if (unionFind.find(i) != unionFind.find(j) && checkDistance(nodes[i], nodes[j])) {

                        unionFind.union(i, j);
                    }
                }
            }

            bw.write(unionFind.getGroupCnt() + "\n");
        } // ~ Testcase Loop

        bw.flush();

        bw.close();
        br.close();
    }

    static private boolean checkDistance(Node node1, Node node2) {

        if (((node2.row - node1.row) * (node2.row - node1.row) + (node2.col - node1.col) * (node2.col - node1.col)) <= (node1.R + node2.R) * (node1.R + node2.R)) {

            return true;
        }

        return false;
    }
}

class Node {

    int row, col;
    int R;

    Node(int row, int col, int R) {

        this.row = row;
        this.col = col;
        this.R = R;
    }
}

class UnionFind {

    private int[] root;
    private int cnt;

    UnionFind(int V) {

        root = new int[V];
        Arrays.fill(root, -1);

        cnt = V;
    }

    // 각 정점의 root 노드를 찾는 함수
    int find(int node) {

        // root 노드이면
        if (root[node] < 0) {

            return node;
        }

        return root[node] = find(root[node]);
    }

    // 결합 함수
    void union(int node1, int node2) {

        int root1 = find(node1);
        int root2 = find(node2);

        // 이미 같은 그룹인 경우
        // if (root1 == root2) return;

        // 작은 그룹을 더 큰 그룹에 결합한다
        if (root[root1] > root[root2]) {

            root1 ^= root2;
            root2 ^= root1;
            root1 ^= root2;
        }

        root[root1] += root[root2];
        root[root2] = root1;

        cnt--;
    }

    // node를 포함하는 그룹의 Size
    int getSize(int node) {

        return -root[find(node)];
    }

    int getGroupCnt() {

        return cnt;
    }
}
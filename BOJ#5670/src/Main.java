import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int SIZE = 26;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = br.readLine()) != null) {

            Trie trie = new Trie();

            int N = Integer.parseInt(line);

            String[] str = new String[N];

            for (int i = 0; i < N; i++) {

                str[i] = br.readLine();
                trie.insert(str[i]);
            }

            for (int i = 0; i < SIZE; i++) {

                if (trie.root.children[i] != null) {

                    trie.check(trie.root.children[i], 1);
                }
            }

            System.out.printf("%.2f", (double) cnt / N);
            System.out.println();

            cnt = 0;
        }


    }
}

class Trie {

    TrieNode root;

    Trie() {

        root = new TrieNode();
    }

    private int toNumber(char c) {

        return c - 'a';
    }

    void insert(String key) {

        int length = key.length();
        TrieNode currentNode = root;

        for (int i = 0; i < length; i++) {

            int next = toNumber(key.charAt(i));

            if (currentNode.children[next] == null) {

                currentNode.children[next] = new TrieNode();
                currentNode.nChild++;
            }


            currentNode = currentNode.children[next];
        }

        currentNode.isTerminal = true;
    }

    void check(TrieNode node, int ret) {

        if (node.isTerminal) {

            Main.cnt += ret;
        }

        if (node.nChild >= 2) {

            ret++;
        }

        if (node.isTerminal && node.nChild == 1) {

            ret++;
        }

        for (int i = 0; i < Main.SIZE; i++) {

            if (node.children[i] != null) {

                check(node.children[i], ret);
            }
        }

    }
}

class TrieNode {

    TrieNode[] children = new TrieNode[Main.SIZE];
    boolean isTerminal;

    int nChild = 0;

    TrieNode() {

        isTerminal = false;

        for (int i = 0; i < Main.SIZE; i++) {

            children[i] = null;
        }
    }
}
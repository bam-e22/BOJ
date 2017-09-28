import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int bitmask = 0;

        int M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        String cmd;
        int num = 0;
        while (M-- > 0) {

            st = new StringTokenizer(br.readLine());

            cmd = st.nextToken();
            if (st.hasMoreTokens()) num = Integer.parseInt(st.nextToken());

            switch (cmd) {

                case "add":
                    bitmask |= 1 << num;
                    break;

                case "remove":
                    bitmask &= ~(1 << num);
                    break;

                case "check":
                    bw.write(((bitmask & (1 << num)) >> num) + "\n");
                    break;

                case "toggle":
                    bitmask ^= (1 << num);
                    break;

                case "all":
                    bitmask |= (1 << 21) - 1;
                    break;

                case "empty":
                    bitmask = 0;
                    break;
            }
        }

        bw.flush();

        bw.close();
        br.close();
    }
}

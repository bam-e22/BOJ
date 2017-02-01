import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#1700 멀티탭 스케쥴링
 * https://www.acmicpc.net/problem/1700
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N; // 멀티탭 구멍의 개수
        int K; // 전기용품 총 사용 횟수
        Device[] schedule;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        schedule = new Device[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {

            schedule[i] = new Device(Integer.parseInt(st.nextToken()), 0);
        }

        for (int i = 0; i < K; i++) {

            schedule[i].choosability = 0;
            for (int j = i + 1; j < K; j++) {

                if (schedule[i].name == schedule[j].name) {

                    schedule[i].choosability = 100 - (j - i);
                    break;
                }
            }
        }

        // solve
        System.out.println(solve(N, K, schedule));
    }

    static int solve(int N, int K, Device[] schedule) {

        int ans = 0;
        Device[] usedDevices = new Device[N];

        if (N >= K) {

            return 0;
        }

        int used = 0;

        for (int i = 0; i < K; i++) {

            // 최초
            if (used == 0) {

                usedDevices[used] = schedule[i];
                used++;
            }

            // 최초가 아닌 경우
            else {

                // 이미 꽂혀있는 경우
                boolean inUse = false;
                int lowestChoosability = 100;
                int lowestChoosabilityIdx = -1;
                for (int j = 0; j < used; j++) {

                    if (usedDevices[j].name == schedule[i].name) {

                        // 선택 가능성 갱신
                        usedDevices[j].choosability = schedule[i].choosability;
                        inUse = true;
                        break;
                    }

                    if (lowestChoosability > usedDevices[j].choosability) {

                        lowestChoosability = usedDevices[j].choosability;
                        lowestChoosabilityIdx = j;
                    }
                }

                // 꽃혀있지 않은 경우
                if (!inUse) {

                    // 자리가 있는 경우
                    if (used < N) {

                        usedDevices[used] = schedule[i];
                        used++;
                    } else {

                        // 자리가 없는 경우
                        usedDevices[lowestChoosabilityIdx].name = schedule[i].name;
                        usedDevices[lowestChoosabilityIdx].choosability = schedule[i].choosability;
                        ans++;


/*                        System.out.print(": ");
                        for (int j = 0; j < usedDevices.length; j++) {

                            System.out.print(usedDevices[j].name + " ");

                        }*/

                    }
                }
            }

            for (int j = 0; j < usedDevices.length; j++) {

                if (usedDevices[j] != null) {
                    usedDevices[j].choosability++;
                }
            }
            //System.out.println("**" + usedDevices[0].name + ", " + usedDevices[0].choosability + "**");
        }

        return ans;
    }
}

class Device {

    int name;
    int choosability;

    Device(int name, int choosability) {

        this.name = name;
        this.choosability = choosability;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#1924 2007년
 * https://www.acmicpc.net/problem/1924
 */

public class Main {

    public static void main(String[] args) throws IOException {

        String[] week = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int days = 0;

        for (int month = 1; month < x; month++) {


            days += getDays(month);
        }

        days += y;

        System.out.println(week[days % 7]);
    }

    static int getDays(int month) {

        int day = 0;

        switch (month) {

            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;

            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;

            case 2:
                day = 28;
                break;

        }

        return day;
    }
}

import java.io.IOException;

public class Solution {

    public static void main(String[] args) throws IOException {

        System.out.println(countBits(2947367));
    }

    public static int countBits(int number) {

        int cnt = 0;

        while (number != 0) {

            if (number % 2 == 1) {

                cnt++;
            }

            number /= 2;
        }

        return cnt;
    }
}

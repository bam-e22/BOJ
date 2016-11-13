import java.util.*;
import java.io.*;

class Main2 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());

		String[] reader = new String[6];
		reader = br.readLine().split(" ");

		long[] arr = new long[6];
		for (int i = 0; i < 6; i++) {

			arr[i] = Long.parseLong(reader[i]);
		}

		long[] arr2 = { arr[0] + arr[1], arr[0] + arr[2], arr[0] + arr[3], arr[0] + arr[4], arr[5] + arr[1], arr[5] + arr[2], arr[5] + arr[3],
				arr[5] + arr[4], arr[1] + arr[2], arr[1] + arr[3], arr[4] + arr[2], arr[4] + arr[3] };
		long[] arr3 = { arr[0] + arr[1] + arr[2], arr[0] + arr[1] + arr[3], arr[0] + arr[4] + arr[2], arr[0] + arr[3] + arr[4],
				arr[5] + arr[1] + arr[2], arr[5] + arr[1] + arr[3], arr[5] + arr[4] + arr[2], arr[5] + arr[3] + arr[4] };
		Arrays.sort(arr);
		Arrays.sort(arr2);
		Arrays.sort(arr3);

		System.out.println(arr3[0] + ", " + arr2[0] + ", " + arr[0]);
		if (n == 1) {
			System.out.println(arr[0] + arr[1] + arr[2] + arr[3] + arr[4]);
		} else {
			System.out.println((5 * n * n - 16 * n + 12) * arr[0] + (8 * n - 12) * arr2[0] + 4 * arr3[0]);
		}

	}
}
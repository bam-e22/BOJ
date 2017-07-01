import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		double a, b;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		a = (double) Integer.parseInt(st.nextToken());
		b = (double) Integer.parseInt(st.nextToken());

		BigDecimal c = new BigDecimal(a/b);
		System.out.println(c);
	}

}

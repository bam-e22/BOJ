import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#2745 진법 변환
 * https://www.acmicpc.net/problem/2745
 */

public class Main {

	public static void main(String[] args) throws IOException {

		char[] N;
		int B;
		int sum = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = st.nextToken().toCharArray();
		B = Integer.parseInt(st.nextToken());
		
		
		for (int i = N.length-1; i >= 0; i--) {
			
			if(N[i] >= '0' && N[i] <= '9') { 
				
				sum += (N[i] - '0') * Math.pow(B, (N.length-1) - i);
			}
			else if (N[i] >= 'A' && N[i] <= 'Z') {
				
				sum += (N[i] - 'A' + 10) * Math.pow(B, (N.length-1) - i);
			}
		}

		System.out.println(sum);
	}
}

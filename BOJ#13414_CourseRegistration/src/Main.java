import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

/*
 * BOJ#13414 수강 신청
 * https://www.acmicpc.net/problem/13414
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int K; // 수강 가능 인원
		int L; // 대기 목록

		LinkedHashSet<String> set = new LinkedHashSet<String>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		String temp;
		for (int i = 0; i < L; i++) {

			temp = br.readLine();
			
			if (set.contains(temp)) {

				set.remove(temp);
			}
			set.add(temp);
		}

		Iterator<String> it = set.iterator();

		
		while(it.hasNext()) {
			
			bw.write(it.next() + "\n");
			if(--K < 1) break;
		}

		bw.flush();

		bw.close();
		br.close();

	}

}

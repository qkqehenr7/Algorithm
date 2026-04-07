import java.io.*;
import java.util.*;

public class Main {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, W, V;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N+1][K+1]; // 물건의 개수, 누적 무게
		
		for (int cnt = 1; cnt <= N; cnt++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			
			for (int total = 0; total <= K; total++) {
				if (total - W >= 0) {
					dp[cnt][total] = Math.max(dp[cnt-1][total], dp[cnt-1][total-W] + V);
				} else {
					dp[cnt][total] = dp[cnt-1][total];
				}
			}
		}
		
		int result = 0;
		for (int i = 0; i <= K; i++) {
			result = Math.max(dp[N][i], result);
		}
		
		System.out.println(result);
	}
}
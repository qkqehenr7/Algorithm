import java.io.*;
import java.util.*;

public class Main {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, R, G, B;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());			
		dp = new int[N+1][3]; // 집 번호, 마지막 색		
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			if (i == 1) {
				dp[1][0] = R;
				dp[1][1] = G;
				dp[1][2] = B;
			} else {
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + R;
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + G;
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + B;
			}
		}

		System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]));
	}
}
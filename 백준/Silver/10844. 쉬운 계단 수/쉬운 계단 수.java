import java.io.*;

public class Main {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long[][] dp;
	public static void main(String[] args) throws IOException {
		
		int N = Integer.parseInt(br.readLine());
		
		dp = new long[N+1][10];
		
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) { // 끝나는 수 (0 ~ 9)
				dp[i][j] = (j != 0 ? dp[i-1][j-1] : 0) + (j != 9 ? dp[i-1][j+1] : 0);
				dp[i][j] %= 1_000_000_000;
			}
		}
		
		long result = 0;
		
		for (int i = 0; i <= 9; i++) {
			result += dp[N][i];
			result %= 1_000_000_000;
		}
		
		System.out.println(result);
	}
}
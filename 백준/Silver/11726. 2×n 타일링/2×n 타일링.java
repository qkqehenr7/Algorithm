import java.io.*;

public class Main {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dp;
	public static void main(String[] args) throws IOException {
		
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		
		for (int i = 0; i <= N; i++) {
			if (i == 0 || i == 1) dp[i] = 1;
			else dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
		
		System.out.println(dp[N]);
	}
}
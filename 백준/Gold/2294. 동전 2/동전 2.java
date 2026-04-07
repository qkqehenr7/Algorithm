import java.io.*;
import java.util.*;

public class Main {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dp;
	static int[] coins;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		dp = new int[k+1];
		Arrays.fill(dp, k+1);
		dp[0] = 0;
		
		coins = new int[n];
		for (int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		
		for (int i = 0; i < n; i++) {
			for (int j = coins[i]; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j - coins[i]] +1);
			}
		}
		
		System.out.println(dp[k] == k+1 ? -1 : dp[k]);
		
	}
}
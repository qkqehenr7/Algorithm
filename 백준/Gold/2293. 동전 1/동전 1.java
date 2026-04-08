import java.io.*;
import java.util.*;

public class Main {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, coin;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[K+1];
		dp[0] = 1;
		
		for (int i = 1; i <= N; i++) {
			coin = Integer.parseInt(br.readLine());
			
			for (int j = coin; j <= K; j++) {
				dp[j] += dp[j - coin];
			}
		}
		
		System.out.println(dp[K]);
	}
}
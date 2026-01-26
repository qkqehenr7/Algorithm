import java.io.*;

public class Main {
	
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nums = br.readLine().split(" ");
		int n = Integer.parseInt(nums[0]);
		int k = Integer.parseInt(nums[1]);
		
		dp = new int[n + 1][k + 1];
		
		for (int i = 1; i <= n; i++) {
			nums = br.readLine().split(" ");
			int weight = Integer.parseInt(nums[0]);
			int value = Integer.parseInt(nums[1]);
			
			for (int w = 1; w <= k; w++) {
				if (weight <= w) {
					dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight] + value);
				} else {
					dp[i][w] = dp[i-1][w];
				}
			}
		}
		
		System.out.println(dp[n][k]);
	}
}
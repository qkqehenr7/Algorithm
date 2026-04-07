import java.io.*;
import java.util.*;

public class Main {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] dp;
	static int[][] matrices;
	
	public static void main(String[] args) throws IOException {
		
		int N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1][N+1];
		
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], (int) Math.pow(2, 31));
			dp[i][i] = 0;
		}
		
		matrices = new int[N+1][2];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			matrices[i][0] = Integer.parseInt(st.nextToken());
			matrices[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int len = 2; len <= N; len++) {
			for (int i = 1; i+len-1 <= N; i++) {
				int j = i + len - 1;
				for (int k = i; k < j; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + matrices[i][0] * matrices[k][1] * matrices[j][1]);
				}
			}
		}
		
		System.out.println(dp[1][N]);
	}
}
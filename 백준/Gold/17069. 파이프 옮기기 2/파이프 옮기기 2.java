import java.io.*;
import java.util.*;

public class Main {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static long[][][] dp;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		
		N = Integer.parseInt(br.readLine());
		dp = new long[N+1][N+1][3];
		map = new int[N+1][N+1];		
		
		dp[1][2][0] = 1; // 초기값
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) continue;
				if (i == 1 && j == 2) continue;
				// 가로
				dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
				// 세로
				dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
				// 대각선
				if (map[i-1][j] != 1 && map[i][j-1] != 1) {
					dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}

			}
		}
		
		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);	
	}
}
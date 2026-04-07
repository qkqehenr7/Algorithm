import java.io.*;

public class Main {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1][3];
		
		dp[1][0] = 1; // 1번째 줄에 사자가 없는 경우.
		dp[1][1] = 1; // 1번째 줄의 오른쪽에 사자가 있는 경우
		dp[1][2] = 1; // 1번째 줄의 왼쪽에 사자가 있는 경우
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 0) { // 사자 없음
					dp[i][j] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2]; // 모두 가능.
				} else if (j == 1) { // 오른쪽
					dp[i][j] = dp[i-1][0] + dp[i-1][2];
				} else if (j == 2) { // 왼쪽
					dp[i][j] = dp[i-1][0] + dp[i-1][1];					
				}
				
				dp[i][j] %= 9901;
			}
		}
		
		System.out.println((dp[N][0] + dp[N][1] + dp[N][2]) % 9901);
	}
}
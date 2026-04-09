import java.io.*;
import java.util.*;

public class Main {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, P = 0;
	static int[][] cost;
	static int[] dp;
	static int INF = 1_000_000_000;
	
	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		cost = new int[N+1][N+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		String s = br.readLine();
		
		P = Integer.parseInt(br.readLine());
		
		int mask = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'Y') {
				mask |= (1 << i);
			}
		}
		
		dp = new int[1 << N];
		Arrays.fill(dp, -1);
		
		int result = solution(mask);
		System.out.println(result == INF ? -1 : result);
		
		
		
	}
	
	static int solution(int mask) {
		if (Integer.bitCount(mask) >= P) return 0; // 이미 만족햇으면
		if (dp[mask] != -1) return dp[mask]; // 메모이제이션 캐싱
		
		int ans = INF; // 리턴값 초기화
		
		for (int j = 0; j < N; j++) {
			if ((mask & (1 << j)) != 0) continue; // 이미 켜져있으면 패스
			
			int minCost = INF;
			// 이미 켜진 발전소(i)를 통해 꺼져있는 발전소(j)를 키는 경우
			for (int i = 0; i < N; i++) {
				if ((mask & (1 << i)) == 0) continue; // 꺼진 발전소는 패스.
				minCost = Math.min(minCost, cost[i][j]); // 최소비용 갱신
			}
			
			if (minCost != INF) {
				ans = Math.min(ans, minCost + solution(mask | (1 << j)));
			}
		}
		
		return dp[mask] = ans;
	}
}
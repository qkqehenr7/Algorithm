class Solution {
    public int solution(int m, int n, int[][] puddles) {
        // 인덱스 맞추기
        int[][] dp = new int[n + 1][m + 1];
        
        for (int[] puddle : puddles) {
            // 웅덩이 표시
            dp[puddle[1]][puddle[0]] = -1;
        }
        // dp의 필수요소 - 초기화를 잊지마라.
        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 웅덩이는 skippp
                if(dp[i][j] == -1) {
                    continue;
                }
                
                // 위의 칸까지의 경우의 수가 양수인 경우
                if (dp[i - 1][j] > 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j]) % 1_000_000_007;
                }
                // 왼쪽 칸까지의 경우의 수가 양수인 경우
                if(dp[i][j - 1] > 0) {
                    dp[i][j] = (dp[i][j] +dp[i][j - 1]) % 1_000_000_007;
                }
            }
        }
        return dp[n][m];
    }
}
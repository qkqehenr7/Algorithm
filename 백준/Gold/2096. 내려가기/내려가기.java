import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][][] dp = new int[2][n][3];
        int[][] map = new int[n][3];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            map[i][0] = Integer.parseInt(input[0]);
            map[i][1] = Integer.parseInt(input[1]);
            map[i][2] = Integer.parseInt(input[2]);
        }

        // dp의 필수템 초기화하기
        for (int i = 0; i < 3; i++) {
            dp[0][0][i] = map[0][i]; // 최소 점수
            dp[1][0][i] = map[0][i]; // 최대 점수
        }

        // dp 채우기.
        for (int i = 1; i < n; i++) {
            // 1번을 선택한 경우
            dp[0][i][0] = map[i][0] + Math.min(dp[0][i - 1][0], dp[0][i - 1][1]); // 최소
            dp[1][i][0] = map[i][0] + Math.max(dp[1][i - 1][0], dp[1][i - 1][1]); // 최대
            // 2번을 선택한 경우
            dp[0][i][1] = map[i][1] + Math.min(dp[0][i - 1][0], Math.min(dp[0][i - 1][1], dp[0][i - 1][2]));
            dp[1][i][1] = map[i][1] + Math.max(dp[1][i - 1][0], Math.max(dp[1][i - 1][1], dp[1][i - 1][2]));
            // 3번을 선택한 경우
            dp[0][i][2] = map[i][2] + Math.min(dp[0][i - 1][1], dp[0][i - 1][2]);
            dp[1][i][2] = map[i][2] + Math.max(dp[1][i - 1][1], dp[1][i - 1][2]);
        }
        int minValue = Math.min(dp[0][n-1][0], Math.min(dp[0][n-1][1], dp[0][n-1][2]));
        int maxValue = Math.max(dp[1][n-1][0], Math.max(dp[1][n-1][1], dp[1][n-1][2]));
        System.out.println(maxValue + " " + minValue);
    }
}
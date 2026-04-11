import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
    	
    	char[] str1 = br.readLine().toCharArray();
    	char[] str2 = br.readLine().toCharArray();
    	
    	N = str1.length;
    	M = str2.length;
    	
    	dp = new int[N+1][M+1];
    	
    	for (int i = 1; i <= N; i++) {
    		for (int j = 1; j <= M; j++) {
    			if (str1[i-1] == str2[j-1]) {
    				dp[i][j] = dp[i-1][j-1] + 1;
    			} else {
        			dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
    			}
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	if (dp[N][M] != 0) {
    		int i = N;
        	int j = M;
        	while(i > 0 && j > 0) {
        		if (str1[i-1] == str2[j-1]) {
    				sb.append(str1[i-1]);
    				i--;
    				j--;
    			}
        		
        		else if (dp[i][j] == dp[i-1][j]) {
        			i--;
    			}
    			
        		else if (dp[i][j] == dp[i][j-1]) {
    				j--;
    			}
        	}
    	}
    	
    	System.out.println(dp[N][M]);
    	sb.reverse();
    	System.out.println(sb);
        
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] trees;
	static int maxH;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			trees = new int[N];
			maxH = 0;
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, trees[i]);
			}
			
			int even = 0;
			int odd = 0;
			for (int i = 0; i < N; i++) {
				int diff = maxH - trees[i];
				even += diff/2;
				odd += diff%2;
			}
			
			if (even > odd) {
				while (Math.abs(even - odd) > 1) {
					even--;
					odd+=2;
				}
			}
			
			int answer;
			
			if (even >= odd) {
				answer = even*2;
			} else {
				answer = odd * 2 - 1;
			}
			
			if (t != T) sb.append("#" + t + " " + answer + "\n");
			else sb.append("#" + t + " " + answer);
		}
		System.out.println(sb);
	}
}
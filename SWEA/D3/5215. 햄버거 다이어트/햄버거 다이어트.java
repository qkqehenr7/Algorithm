import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] scores;
	static int[] calories;
	static int max_score;
	static int N;
	static int L;
	static int[] answer;

	public static void main(String[] args) throws IOException {
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			max_score = Integer.MIN_VALUE;
			scores = new int[N];
			calories = new int[N];
			
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				scores[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int bit = 0; bit < (1 << N); bit++) {
				int totalScore = 0;
				int totalCalorie = 0;
				for (int i = 0; i < N; i++) {
					if ((bit & (1 << i)) != 0) {
						totalScore += scores[i];
						totalCalorie += calories[i];
					}
				}
				
				max_score = totalCalorie <= L && max_score < totalScore ? totalScore : max_score;
			}
			answer[t] = max_score;
		}
		
		for (int t = 1; t <= T; t++) {
			System.out.println("#" + t + " " + answer[t-1]);
		}
	}
}
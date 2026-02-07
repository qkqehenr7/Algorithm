import java.io.*;
import java.util.*;

public class Solution {
	static int[][] honeyBox;
	static int[][] honeyMax;
	static int N;
	static int M;
	static int C;	
	static int max_value;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			honeyBox = new int[N][N];
			honeyMax = new int[N][N-M+1];
			max_value = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					honeyBox[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 각 벌통에 대한 최댓값을 미리 계산
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					honeyMax[i][j] = calMaxHoney(i, j);
				}
			}
			
			// 벌통 뽑기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N-M; j++) {
					// 일꾼 1이 i,j 선택
					for (int r = i; r < N; r++) { // 같은 행부터 시작
						int start = (r == i) ? j + M : 0; // 같은 행인 경우 일꾼 1의 벌통 다음부터, 아니라면 처음부터
						// 일꾼 2는 r,c 선택
						for (int c = start; c <= N-M; c++) {
							max_value = Math.max(max_value, honeyMax[i][j] + honeyMax[r][c]);
						}
					}
				}
			}
			
			System.out.println("#" + t + " " + max_value);
		}
	}

	static int calMaxHoney(int row, int col) {
		int answer = 0;
		for (int bit = 1; bit < 1 << M; bit++) {
			int total = 0;
			int squareSum = 0;
			for (int i = 0; i < M; i++) {
				if ((bit & (1 << i)) != 0) {
					total += honeyBox[row][col + i];
					squareSum += Math.pow(honeyBox[row][col + i], 2);
				}
			}
			
			if (total  <= C) {
				answer = Math.max(answer, squareSum);
			}
		}
		return answer;
	}
}
import java.io.*;
import java.util.*;

public class Main {

	static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int R;
	static int C; 
	static int T;
	static int[][] map;
	static int[] clean;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		clean = new int[2];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					clean[0] = i;
					clean[1] = j;
				}
			}
		}
		
		for (int t = 0; t < T; t++) {
			// 1. 미세먼지 확산
			spread();
			// 2. 회전
			rotate(clean[0]);
			// 3. 공기청정
			map[clean[0]][clean[1]] = -1;
			map[clean[0]-1][clean[1]] = -1;
			
			map[clean[0]][clean[1]+1] = 0;
			map[clean[0]-1][clean[1]+1] = 0;
		}
		// 4. 누적합 계산
		int total = 0;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) total += map[i][j];
			}
		}
		System.out.println(total);
	}
	
	static boolean check(int row, int col) {
		if (row < 0 || row >= R || col < 0 || col >= C) {
			return false;
		}
		if (map[row][col] == -1)
			return false;
		
		return true;
	}
	
	static void spread() {
		int[][] temp = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] > 0) {
					int amount = map[r][c] / 5;
					int cnt = 0;
					
					for (int i = 0; i < 4; i++) {
						int nr = r + move[i][0];
						int nc = c + move[i][1];
						
						if (check(nr, nc)) {
							temp[nr][nc] += amount;
							cnt++;
						}
					}
					
					temp[r][c] += map[r][c] - (amount * cnt);
				} else if (map[r][c] == -1) {
					temp[r][c] = -1;
				}
			}
		}
		
		map = temp;
	}
	
	static void rotate(int k) {
		// 위 남쪽
		for (int r = 0; r < k-1; r++) {
			map[k-1-r][0] = map[k-1-r-1][0];
		}
		
		// 아래 북쪽
		for (int r = k; r < R-1; r++) {
			map[r][0] = map[r+1][0];
		}
		
		// 서쪽
		for (int c = 0; c < C-1; c++) {
			map[0][c] = map[0][c+1];
			map[R-1][c] = map[R-1][c+1];
		}
		
		// 위 북쪽
		for (int r = 0; r < k-1; r++) {
			map[r][C-1] = map[r+1][C-1];
		}
		
		// 아래 남쪽
		for (int r = R-1; r > k; r--) {
			map[r][C-1] = map[r-1][C-1];
		}
		
		// 동쪽
		for (int c = C-1; c > 0; c--) {
			map[k-1][c] = map[k-1][c-1];
			map[k][c] = map[k][c-1];
		}
	}
}
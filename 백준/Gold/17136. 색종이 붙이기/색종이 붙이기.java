import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map = new int[10][10];
	static int[] squares = {5, 5, 5, 5, 5}; // 1, 2, 3, 4, 5 정사각형
	static int min_value = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0,0);
		
		System.out.println(min_value == Integer.MAX_VALUE ? -1 : min_value);
	}
	
	static boolean isPossible(int row, int col, int size) {
		// 1. 색종이가 밖으로 나가지 않는가?
		if (row + size > 10 || col + size > 10) {
			return false;
		}
		
		// 2. 색종이 내에 0이 있는가?
		for (int i = row; i < row + size; i++) { 
			for (int j = col; j < col + size; j++) {
				if (map[i][j] == 0) return false;
			}
		}
		
		return true;
	}
	
	static void paper(int row, int col, int size, int type) {
		
		if (type == 0) { // 색종이 붙이기
			for (int i = row; i < row + size; i++) {
				for (int j = col; j < col + size; j++) {
					map[i][j] = 0;
				}
			}
		}
		
		if (type == 1) { // 색종이 떼기
			for (int i = row; i < row + size; i++) {
				for (int j = col; j < col + size; j++) {
					map[i][j] = 1;
				}
			}
		}
	}

	static void dfs(int row, int col, int cnt) {
        
        if (cnt >= min_value) return; // pruning
		
		if (col == 10) {
			dfs(row+1, 0, cnt);
			return;
		}
		
		if (row == 10) {
			min_value = Math.min(min_value, cnt);
			return;
		}
		
		if (map[row][col] == 1) {
			// 모든 사이즈에 대해 탐색
			for (int size = 1; size <= 5; size++) {
				if (squares[size-1] > 0 && isPossible(row, col, size)) { // 해당 사이즈의 색종이가 남아있어야함
					paper(row, col, size, 0);
					squares[size-1]--; // 수량 감소
					
					dfs(row, col+1, cnt+1);
					
					squares[size-1]++; // 원복
					paper(row, col, size, 1);
				}
			}
			
		} else {
			dfs(row, col+1, cnt);
		}
	}
}
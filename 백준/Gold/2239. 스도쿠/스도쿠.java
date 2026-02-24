import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[][] sudoku = new int[9][9];
	static ArrayList<int[]> blanks = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		for (int i = 0; i < 9; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = ch[j] - '0';
				if (sudoku[i][j] == 0) blanks.add(new int[] {i, j});
			}
			
		}
		
		solution(0);
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(sudoku[i][j]);
			}
			if (i != 8) sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean solution(int idx) {
		
		if (idx == blanks.size()) {
			return true; // 스도쿠 완성
		}
		
		int[] curr = blanks.get(idx);
		int r = curr[0];
		int c = curr[1];
		
		for (int num = 1; num <= 9; num++) {
			if (!checkSquare(r, c, num) || !checkRow(r, num) || !checkCol(c, num)) {
				continue; // num을 사용할 수 없다면 다른 숫자로
			}
			
			sudoku[r][c] = num; // 숫자 채우기
			if (solution(idx+1)) { // 다음 칸 채우기
				return true; // 스도쿠 완성 후 재귀 탈출
			}; 
			sudoku[r][c] = 0; // 원복
		}
		return false;
		
	}

	static boolean checkSquare(int row, int col, int target) {
		int sr = 6;
		int sc = 6;
		if (row < 3) sr = 0;
		else if (row < 6) sr = 3;
		
		if (col < 3) sc = 0;
		else if (col < 6) sc = 3;
		
		for (int r = sr; r < sr + 3; r++) {
			for (int c = sc; c < sc + 3; c++) {
				if (sudoku[r][c] == target) {
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean checkCol(int col, int target) {
		for (int r = 0; r < 9; r++) {
			if (sudoku[r][col] == target) {
				return false;
			}
		}
		return true;
	}
	
	static boolean checkRow(int row, int target) {
		for (int c = 0; c < 9; c++) {
			if (sudoku[row][c] == target) {
				return false;
			}
		}
		return true;
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] map = new char[12][6];
	static Queue<int[]> queue;
	static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static boolean[][] visited, temp;
	static int cnt;
	static boolean globalFlag = true;
	
	public static void main(String[] args) throws IOException {
		
		// map 초기화
		for (int i = 0; i < 12; i++) {
			char[] arr = br.readLine().toCharArray();
			for (int j = 0; j < 6; j++) {
				map[i][j] = arr[j];
			}
		}
		
		while (globalFlag) {
			simulation();
			refresh();
		}
		System.out.println(cnt);
	}
	
	// 터진 맵 정리하기
	static void refresh() { 
		for (int j = 0; j < 6; j++) {
			List<Character> ch = new ArrayList<>();
			for (int i = 11; i >= 0; i--) {
				if (map[i][j] != '.' && !visited[i][j]) { // visited true면 .과 같음
					ch.add(map[i][j]);
				}
			}
			for (int i = 0; i < ch.size(); i++) {
				map[11-i][j] = ch.get(i);
			}
			for (int i = ch.size(); i < 12; i++) {
				map[11-i][j] = '.';
			}
		}
	}
	
	// 1회의 연쇄가 발생
	static void simulation() {
		visited = new boolean[12][6];
		boolean flag = false;
		// 시뮬레이션 맵이 있어야 할거 같은디?
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (map[i][j] != '.' && !visited[i][j]) {
					temp = new boolean[12][6];
					flag = boom(i, j) || flag; // 한번이라도 터지면 true
				}
			}
		}
		
		if (flag) { // 터지면
			cnt++;
		} else { // 안터지면
			globalFlag = false;
			return;
		}
	}
	
	
	// 이번에 터지면 true 반환.
	static boolean boom(int row, int col) {
		queue = new ArrayDeque<>();
		queue.offer(new int[] {row, col});
		temp[row][col] = true;
		int buyo = 1; // 시작 지점부터 세기
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (nr < 0 || nc < 0 || nr >= 12 || nc >= 6) continue;
				
				if (temp[nr][nc]) continue;
				
				if (map[nr][nc] != map[r][c]) continue; // 같은 뿌요가 아니면 안됨
				
				temp[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
				buyo++;
			}
		}
		
		// 4개 이상이 연결되면 visited에 반영
		if (buyo >= 4) {
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (temp[i][j]) visited[i][j] = true;
				}
			}
			return true;
		}
		return false;
	}
}
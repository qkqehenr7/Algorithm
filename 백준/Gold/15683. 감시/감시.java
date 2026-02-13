import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map;
	static List<CCTV> cctvs;
	static int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][][] arr = {
			{{0}, {1}, {2}, {3}},
			{{0, 2}, {1, 3}},
			{{0, 1}, {1, 2}, {2, 3}, {3, 0}},
			{{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},
			{{0, 1, 2, 3}}
			};
	
	static class CCTV {
		int type;
		int row;
		int col;
		
		CCTV(int type, int row, int col) {
			this.type = type;
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctvs = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] <= 5) {
					cctvs.add(new CCTV(map[i][j]-1, i, j));
				}
			}
		}

		dfs(0);
		System.out.println(answer);
	}
	
	static void watch(int row, int col, int dir) {
		int nr = row + moves[dir][0];
		int nc = col + moves[dir][1];
		
		if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 6) return;
		
		if (map[nr][nc] == 0) {
			map[nr][nc] = -1; // 감시 표시
			
		}
		watch(nr, nc, dir);
	}
	
	static void dfs(int idx) {
		if (idx == cctvs.size()) {
			answer = Math.min(answer, cntZero());
			return;
		}
		
		CCTV cctv = cctvs.get(idx);
		int type = cctv.type;
		int r = cctv.row;
		int c = cctv.col;
		
		for (int[] directions : arr[type]) {
			int[][] backup = copyMap();
			for (int d : directions) {
				watch(r, c, d);
			}
			
			dfs(idx+1);
			map = backup;
		}
	}
	
	static int[][] copyMap() {
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0 ; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}
	
	
	static int cntZero() {
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) total++;  
			}
		}
		return total;
	}
}
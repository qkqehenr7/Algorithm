import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static int[][] map;
	static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int max_area = Integer.MIN_VALUE;
	
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		wall(0);
		System.out.println(max_area);
	}
	
	static void bfs() {
		
		int[][] tempMap = new int[N][M];
		Queue<int[]> queue = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tempMap[i][j] = map[i][j];
				if (map[i][j] == 2) queue.offer(new int[] {i, j});
			}
		}
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if (tempMap[nr][nc] == 0) {
					queue.offer(new int[] {nr, nc});
					tempMap[nr][nc] = 2;
				}
			}
		}
		
		int area = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tempMap[i][j] == 0) area++;
			}
		}
		
		max_area = Math.max(max_area, area);
	}
	
	static void wall(int cnt) {
		if (cnt == 3) {
			bfs();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					wall(cnt+1);
					map[i][j] = 0;
				}
			}
		}
	}
}
import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int M;
	static int[][] map;
	static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int cheeze;
	static int time;

	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) cheeze++;
			}
		}
		
		while (cheeze > 0) {
			time++;
			bfs();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = map[i][j] == 2 ? 1 : map[i][j];  
				}
			}
		}
		System.out.println(time);

	}
	
	
	static void bfs() {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0});
		visited[0][0] = true;
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (!check(nr, nc) || visited[nr][nc]) continue;
				// 공기
				if (map[nr][nc] == 0) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}

				if (map[nr][nc] == 1 || map[nr][nc] == 2) {
					map[nr][nc]++;
				}

				if (map[nr][nc] == 3) {
					visited[nr][nc] = true;
					map[nr][nc] = 0;
					cheeze--;
				}
			}
		}
	}
	
	static boolean check(int row, int col) {
		if (row < 0 || col < 0 | row >= N || col >= M) {
			return false;
		}
		return true;
	}
}
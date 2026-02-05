import java.io.*;
import java.util.*;

public class Main {

	static int R;
	static int C;
	static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int[][] map;
	static int currCheeze;
	static int time;
	static int prevCheeze;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) currCheeze++;
			}
		}
		
		while (currCheeze > 0) {
			time++;
			prevCheeze = currCheeze;
			defineAir();

		}
		System.out.println(time);
		System.out.println(prevCheeze);
	}
	
	static void defineAir() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0});
		boolean[][] visited = new boolean[R][C];
		visited[0][0] = true;
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];

				// 맵을 벗어나거나 이미 방문한 경우 제외
				if (!check(nr, nc) || visited[nr][nc]) continue;
						
				// 인접한 구역이 공기인 경우 큐에 넣기
				if (map[nr][nc] == 0) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
				// 인접한 구역이 치즈인 경우 녹임
				if (map[nr][nc] == 1) {
					map[nr][nc] = 0;
					visited[nr][nc] = true;
					currCheeze--;
				}
			}
		}
	}
	
		
	static boolean check(int row, int col) {
		if (row < 0 || col < 0 || row >= R || col >= C) {
			return false;
		}
		return true;
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] moves = {
			{1, 0}, {0, 1}, {-1, 0}, {0, -1},
			{1, 1}, {1, -1}, {-1, -1}, {-1, 1}
	};
	static int w;
	static int h;
	static int[][] map;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			cnt = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) break;
			// map initialize
			map = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1) bfs(i,j);
				}
			}
			sb.append(cnt + "\n");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}
	
	
	static void bfs(int row, int col) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {row, col});
		map[row][col] = -1;
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			
			for (int i = 0; i < 8; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (!check(nr, nc)) continue;
				
				queue.offer(new int[] {nr, nc});
				map[nr][nc] = -1;

			}
		}
		cnt++;
	}
	
	static boolean check(int row, int col) {
		if (row < 0 || row >= h || col < 0 || col >= w) {
			return false;
		}
		if (map[row][col] != 1) {
			return false;
		}
		return true;
	}
}
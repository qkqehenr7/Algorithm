import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int K, W, H;
	static StringTokenizer st;
	static int[][] map;
	static boolean[][][] visited;
	static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1},
			{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {-2, -1}, {-1, -2}, {2, -1}, {1, -2}};
	
	public static void main(String[] args) throws IOException {
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[K+1][H][W];
		
		for (int h = 0; h < H; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < W; w++) {
				map[h][w] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0, 0, 0, 0});
		visited[0][0][0] = true;
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int k = curr[2];
			int d = curr[3];
			
			if (r == H-1 && c == W-1) return d;
			
			for (int i = 0; i < 12; i++) {
				if (i > 3) {
					if (k >= K) break;
				}

				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (nr < 0 || nc < 0 || nr >= H || nc >= W) continue;
				
				if (map[nr][nc] == 1) continue;
				
				
				
				if (i >= 4) {
					if (visited[k+1][nr][nc]) continue;
					queue.offer(new int[] {nr, nc, k+1, d+1});
					visited[k+1][nr][nc] = true;
					
				} else {
					if (visited[k][nr][nc]) continue;
					queue.offer(new int[] {nr, nc, k, d+1});
					visited[k][nr][nc] = true;
					
				}
			}
		}
		return -1;
	}
}
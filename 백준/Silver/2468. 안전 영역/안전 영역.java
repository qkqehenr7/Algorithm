import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int min_height = Integer.MAX_VALUE;
	static int max_height = Integer.MIN_VALUE;
	static int max_area = Integer.MIN_VALUE;
	static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min_height = Math.min(min_height, map[i][j]);
				max_height = Math.max(max_height, map[i][j]);
			}
		}
		
		for (int depth = min_height-1; depth <= max_height; depth++) {
			max_area = Math.max(max_area, simulation(depth));
		}
		
		System.out.println(max_area);
	}
	
	static int simulation(int depth) {
		int area = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > depth && !visited[i][j]) {
					bfs(i, j, depth);
					area++;
				}
			}
		}
		return area;
	}
	
	static void bfs(int row, int col, int depth) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {row, col});
		visited[row][col] = true;
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if (visited[nr][nc]) continue;
				if (map[nr][nc] <= depth) continue;
				
				visited[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
			}	
		}	
	}
}
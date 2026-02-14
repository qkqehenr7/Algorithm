import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, er, ec;
	static char[][] map;
	static int[][][] visited;
	static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static Queue<int[]> queue;
		
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new int[64][N][M]; // 64가지 열쇠 조합 표현
		queue = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			char[] arr = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = arr[j];
				if (map[i][j] == '0') {
					queue.offer(new int[] {i, j, 0});
					visited[0][i][j] = 1;
				}
			}
		}
		
		System.out.println(bfs());
	}

	static int bfs() {
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int key = curr[2];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				
				if (map[nr][nc] == '1') return visited[key][r][c]; // 목적지
				
				if (visited[key][nr][nc] > 0) continue; // 이 열쇠조합으로 방문한 적이 있는 경우
				
				if (map[nr][nc] == '#') continue; // 벽
				
				if (map[nr][nc] - 97 >= 0 && map[nr][nc] - 97 <= 5) { // 키 습득
					int num = map[nr][nc] - 97;
					visited[key | (1<<num)][nr][nc] = visited[key][r][c] + 1;
					queue.offer(new int[] {nr, nc, key | (1<<num)});
					continue;
				}
				
				if (map[nr][nc] - 65 >= 0 && map[nr][nc] - 65 <= 5) { // 문
					int num = map[nr][nc] - 65;
					if ((key & (1<<num)) == 0) { // 열쇠 보유 여부 체크
						continue;
					}					
				}
				// 빈칸 or 열쇠를 보유한 경우
				visited[key][nr][nc] = visited[key][r][c] + 1;
				queue.offer(new int[] {nr, nc, key});
			}
		}
		return -1;
	}
}
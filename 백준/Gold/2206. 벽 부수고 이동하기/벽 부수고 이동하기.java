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
	static int[][][] map; // 0: 폭탄 사용 전, 1: 폭탄 사용 후
	static Queue<int[]> queue;
	static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[2][N][M];
		
		for (int i = 0; i < N; i++) {
			char[] arr = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[0][i][j] = arr[j] - '0' == 1 ? -1 : 0; // 벽을 -1로 바꿔줌
				map[1][i][j] = map[0][i][j];
			}
		}
		
		queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0, 0});
		map[0][0][0] = 1;		
		
		bfs();
		
		int answer;
		
		if (map[0][N-1][M-1] == 0 && map[1][N-1][M-1] == 0) {
			answer = -1;
		} else if (map[0][N-1][M-1] == 0) {
			answer = map[1][N-1][M-1];
		} else if (map[1][N-1][M-1] == 0) {
			answer = map[0][N-1][M-1];
		} else {
			answer = Math.min(map[0][N-1][M-1], map[1][N-1][M-1]);
		}
		
		System.out.println(answer);		
	}
	
	static void bfs() {
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int boom = curr[2];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				
				if (map[boom][nr][nc] > 0) continue; // 이미 방문한 곳
				
				if (map[boom][nr][nc] == 0) { // 그냥 길
					map[boom][nr][nc] = map[boom][r][c] + 1;
					queue.offer(new int[] {nr, nc, boom});
				}
					
				if (boom == 0 && map[boom][nr][nc] == -1) { // 벽은 지나갈 수 없다.
					map[1][nr][nc] = map[0][r][c] + 1;
					queue.offer(new int[] {nr, nc, 1});
				}
			}
		}
	}
}
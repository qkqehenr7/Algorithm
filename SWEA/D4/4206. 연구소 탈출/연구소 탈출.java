import java.io.*;
import java.util.*;

public class Solution {
	
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, sr, sc;
	static int[][] map, visited, moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	static Queue<int[]> queue;
	static Queue<int[]> virusq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new int[N][M];
			
			queue = new ArrayDeque<>();
			virusq = new ArrayDeque<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int next = Integer.parseInt(st.nextToken());
					
					if (next == 1) map[i][j] = -1; // 벽 구분을 위해
					
					if (next == 2) {
						map[i][j] = 1; // 바이러스 시작점은 1로 초기화.
						virusq.offer(new int[] {i, j}); // 큐에 삽입
					}
					
					if (next == 3) { // 인디 좌표 저장
						// 맵에 저장은 굳이 안해도 될듯
						visited[i][j] = 1;
						queue.offer(new int[] {i, j});
						sr = i; sc = j; // 삼성이 시작점 저장
					}
				}
			}
			
			virus(); // 시간대 별 바이러스 정보 저장
			int result = bfs();
			sb.append("#").append(t).append(" ");
			if (result == -1) {
				sb.append("ZOMBIE").append("\n");
			} else if (result == -2) {
				sb.append("CANNOT ESCAPE").append("\n");
			} else {
				sb.append(result).append("\n");
			}
		}
		
		System.out.println(sb.toString().trim());
		

	}
	
	static void virus() { // 바이러스 퍼뜨리기
		while (!virusq.isEmpty()) {
			int[] curr = virusq.poll();
			int r = curr[0];
			int c = curr[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] > 0) {
					continue;
				}
				
				if (map[nr][nc] == -1) continue; // 벽은 못감
				
				map[nr][nc] = map[r][c] + 1; // 바이러스 도달 시간
				virusq.offer(new int[] {nr, nc});
			}
		}
	}
	
	static int bfs() { // 인디 이동
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) { // 맵 밖으로 탈출 성공
					return visited[r][c];
				}
				
				if (visited[nr][nc] > 0) {
					continue;
				}
				
				if (map[nr][nc] == -1) continue; // 벽은 못감
				if (map[nr][nc] != 0 && map[nr][nc] <= visited[r][c] + 1) continue; // 독가스가 이미 있거나, 동시에 도달할 경우 갈 수 없음
				
				visited[nr][nc] = visited[r][c] + 1; // 누적합
				queue.offer(new int[] {nr, nc});
			}
		}
		
		// 출발 위치에 바이러스가 갈 수 있으면 좀비가 될 거고
		// 못가면 안되고
		
		if (map[sr][sc] > 0) return -1;
		else return -2;
	}

}
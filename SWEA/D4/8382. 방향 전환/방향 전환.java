import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[][][] move = {{{0, 1}, {0, -1}}, {{1, 0}, {-1, 0}}}; // 0: 가로 방향, 1: 세로 방향
	static int x1;
	static int y1;
	static int x2;
	static int y2;
	static int[][][] visited;
	
	public static void main(String[] args) throws IOException {
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken()) + 100;
			y1 = Integer.parseInt(st.nextToken()) + 100;
			x2 = Integer.parseInt(st.nextToken()) + 100;
			y2 = Integer.parseInt(st.nextToken()) + 100;
			
			visited = new int[2][201][201];
			bfs();
			
			int ans;
			if (visited[0][x2][y2] == 0) {
				ans = visited[1][x2][y2];
			} else if (visited[1][x2][y2] == 0) {
				ans = visited[0][x2][y2];
			} else {
				ans = Math.min(visited[0][x2][y2], visited[1][x2][y2]);
			}

			if (t != T) sb.append("#" + t + " " + ans + "\n");
			else sb.append("#" + t + " " + ans);
		}
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x1, y1, 0}); // 가로 방향
		queue.offer(new int[] {x1, y1, 1}); // 세로 방향
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int x = curr[0];
			int y = curr[1];
			int dir = curr[2]; // 이번에 이동할 방향
			
			if (x == x2 && y == y2) { // 도착 시 정지
				break;
			}
			
			for (int i = 0; i < 2; i++) {
				int nx = x + move[dir][i][0];
				int ny = y + move[dir][i][1];
				
				if (nx < 0 || ny < 0 || nx > 200 || ny > 200) { // map 벗어나면 continue
					continue;
				}
				
				if (visited[dir][nx][ny] != 0) { // 다음 이동 좌표를 해당 방향으로 방문한 적이 있으면 pass
					continue;
				}
				
				queue.offer(new int[] {nx, ny, (dir+1)%2}); // 다음좌표와 다음 방향 큐에 삽입
				visited[dir][nx][ny] = visited[(dir+1)%2][x][y] + 1; // 이전 좌표값에 1을 더해 누적합 계산
			}
		}
	}
}
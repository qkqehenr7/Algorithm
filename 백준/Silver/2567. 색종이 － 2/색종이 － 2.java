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
	static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		map = new int[101][101];
		Queue<int[]> queue = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());

			// 색칠 및 색종이 부분 모두 큐에 넣기
			for (int r = sr; r < sr+10; r++) {
				for (int c = sc; c < sc+10; c++) {
					if (map[r][c] == 0) {
						map[r][c] = 1;
						queue.offer(new int[] {r,c});
					}
				}
			}
		}
		
		int cnt = 0;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (map[nr][nc] == 0) {
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}
}
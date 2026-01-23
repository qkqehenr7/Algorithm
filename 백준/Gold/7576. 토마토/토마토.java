import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] map;
	static int m;
	static int n;
	static int[][] moves = {
			{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static Queue<int[]> queue;
	
	
	static void bfs() {
		// 더 이상 익힐 수 없을 때까지
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int i = current[0];
			int j = current[1];
			// 상하좌우
			for (int k = 0; k < 4; k++) {
				int nr = i + moves[k][0];
				int nc = j + moves[k][1];
				// 맵을 벗어난 경우
				if (nr < 0 || nc < 0 || nr >= n || nc >= m) {
					continue;
				}
				// 다음 칸이 안 익은 토마토인 경우
				if (map[nr][nc] == 0) {
					map[nr][nc] = map[i][j] + 1;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		// map 초기화
		for (int i = 0; i < n; i++) {
			String[] nums = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(nums[j]);
			}
		}
		// queue 초기화
		queue = new ArrayDeque<>();
		// 익지 않은 토미토가 없으면 바로 0 출력
		boolean isExist = false; // 안익은 토마토 존재 체크
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 익은 토마토인 경우
				if (map[i][j] == 1) {
					queue.offer(new int[] {i, j});
				} else if (map[i][j] == 0) {
					isExist = true; // 안 익은게 하나라도 있으면 true
				}
			}
		}
		// 처음에 안 익은 토마토가 하나도 없다면
		if (!isExist) {
			System.out.println(0);
			return;
		}
		
		// 전염 시작
		bfs();
		
		int max_value = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				max_value = (max_value < map[i][j]) ? map[i][j] : max_value;
			}
		}
		System.out.println(max_value - 1);
	}
}
import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] map;
	static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			char[] nums = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = (nums[j] == '0') ? 0 : 1;
			}
		}
		
		List<Integer> villages = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					villages.add(bfs(i,j));
				}
			}
		}
		Collections.sort(villages);
		System.out.println(villages.size());
		for (int cnt : villages) {
			System.out.println(cnt);
		}
	}
	
	
	static int bfs(int stR, int stC) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {stR, stC});
		map[stR][stC] = 0;
		
		int cnt = 1;
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int row = curr[0];
			int col = curr[1];
			
			for (int i = 0; i < 4; i++) {
				int nrow = row + move[i][0];
				int ncol = col + move[i][1];
				
				if (nrow < 0 || nrow >= N || ncol < 0 || ncol >= N) {
					continue;
				}

				if (map[nrow][ncol] == 0) {
					continue;
				}
				
				map[nrow][ncol] = 0;
				queue.offer(new int[] {nrow, ncol});
				cnt++;
			}
		}
		return cnt;
	}
}
import java.io.*;

public class Main {
	
	static int[][] map;
	static int r;
	static int c;
	static int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int cnt = 1;
	static int n;
	static int target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		target = Integer.parseInt(br.readLine());
		map = new int[n][n];
		r = n/2;
		c = n/2;
		
		int dir = 0;
		for (int i = 1; i <= n; i++) {
			// 해당 방향으로 i만큼 전진 (이동을 i번 반복)
			if (cnt > n*n) break;
			for (int j = 0; j < i; j++) {
				map[r][c] = cnt++;
				r += moves[dir][0];
				c += moves[dir][1];				
				
			}
			// 이동 방향 변경
			dir += 1;
			dir %= 4;

			// 해당 방향으로 i만큼 이동
			if (cnt > n*n) break;
			for (int j = 0; j < i; j++) {
				map[r][c] = cnt++;
				r += moves[dir][0];
				c += moves[dir][1];
			}
			dir += 1;
			dir %= 4;
		}
		
		int[] ans = new int[2];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == target) {
					ans[0] = i+1;
					ans[1] = j+1;
				}
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(ans[0] + " " + ans[1]);
	}
}
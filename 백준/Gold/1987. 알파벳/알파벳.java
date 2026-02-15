import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int R, C, max_dist;
	static char[][] alpha;
	static boolean[] visited = new boolean[26];
	static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alpha = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				alpha[i][j] = ch[j];
			}
		}
		
		dfs(0, 0, 0);
		System.out.println(max_dist);
		
//		System.out.println((int) 'A');
//		System.out.println((int) 'Z');
	}
	
	static void dfs(int row, int col, int cnt) {
		
		if (row < 0 || col < 0 || row >= R || col >= C || visited[alpha[row][col] -65]) {
			max_dist = Math.max(max_dist, cnt);
			return;
		}
		
		visited[alpha[row][col] -65] = true;
		dfs(row+1, col, cnt+1);
		dfs(row, col+1, cnt+1);
		dfs(row-1, col, cnt+1);
		dfs(row, col-1, cnt+1);
		visited[alpha[row][col] -65] = false;
	}
}
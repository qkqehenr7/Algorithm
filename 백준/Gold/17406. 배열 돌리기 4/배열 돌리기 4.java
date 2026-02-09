import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int M;
	static int K;
	static int[][] map;
	static int[][] origin;
	static int[][] op;
	static int[] order;
	static int min_value = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		origin = new int[N][M];
		op = new int[K][3];
		order = new int[K];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				origin[i][j] = map[i][j];
			}
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			op[i][0] = Integer.parseInt(st.nextToken());
			op[i][1] = Integer.parseInt(st.nextToken());
			op[i][2] = Integer.parseInt(st.nextToken());
		}
		
		perm(0, 0);
		System.out.println(min_value);
	}
	
	static void perm(int cnt, int bit) {
		if (cnt == K) {
			simulation();
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if ((bit & (1<<i)) == 0) {
				order[cnt] = i;
				perm(cnt+1, bit |(1<<i));
			}
		}
	}
	
	static void simulation() {
		for (int i = 0; i < N; i++) {
			map[i] = origin[i].clone();
		}
		
		for (int i = 0; i < K; i++) {
			int r = op[order[i]][0] - 1;
			int c = op[order[i]][1] - 1;
			int s = op[order[i]][2];
			
			for (int j = 0; j < s; j++) {
				rotate(j, r-s, c-s, 2*s);
			}
		}
		
		for (int i = 0; i < N; i++) {
			int total = 0;
			for (int j = 0; j < M; j++) {
				total += map[i][j];
			}
			min_value = Math.min(min_value, total);
		}
	}

	static void rotate(int depth, int row, int col, int len) {
		// init
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			temp[i] = map[i].clone();
		}
		// right
		for (int j = col+depth; j < col+len-depth; j++) {
			temp[row+depth][j+1] = map[row+depth][j];
		}
		// left
		for (int j = col+len-depth; j > col+depth; j--) {
			temp[row+len-depth][j-1] = map[row+len-depth][j];
		}
		// down
		for (int i = row+depth; i < row+len-depth; i++) {
			temp[i+1][col+len-depth] = map[i][col+len-depth];
		}
		// up
		for (int i = row+len-depth; i > row+depth; i--) {
			temp[i-1][col+depth] = map[i][col+depth];
		}
		
		for (int i = 0; i < N; i++) {
			map[i] = temp[i].clone();
		}
	}
}
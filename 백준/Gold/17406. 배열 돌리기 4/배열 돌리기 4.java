import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K;
	static int[][] map, origin, op;
	static int[] order;
	static int min_value = Integer.MAX_VALUE;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
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
			
			for (int depth = 0; depth < s; depth++) {
				rotate(r-s+depth, c-s+depth, r+s-depth, c+s-depth);
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
	
	static void rotate(int sr, int sc, int er, int ec) {
		// 좌측 상단값 복사
		int temp = map[sr][sc];
		// 위
		for (int r = sr; r < er; r++) {
			map[r][sc] = map[r+1][sc];
		}
		// 왼쪽
		for (int c = sc; c < ec; c++) {
			map[er][c] = map[er][c+1];
		}
		// 아래
		for (int r = er; r > sr; r--) {
			map[r][ec] = map[r-1][ec];
		}
		// 오른쪽
		for (int c = ec; c > sc; c--) {
			map[sr][c] = map[sr][c-1];
		}
		map[sr][sc + 1] = temp;
	}
}
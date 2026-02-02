import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int M;
	static int[][] map;
	
	static int x1;
	static int y1;
	static int x2;
	static int y2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		// map 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i < N; i++) {
			map[0][i] += map[0][i-1];
			map[i][0] += map[i-1][0];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				map[i][j] += (map[i-1][j] + map[i][j-1] - map[i-1][j-1]);
			}
		}
		
		// 좌표 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken()) -1;
			y1 = Integer.parseInt(st.nextToken()) -1;
			x2 = Integer.parseInt(st.nextToken()) -1;
			y2 = Integer.parseInt(st.nextToken()) -1;
			
			if (i < M-1) sb.append(sliding() + "\n");
			else sb.append(sliding());
		}
		System.out.println(sb);
	}
	
	static int sliding() {
		if (x1 == 0 && y1 == 0) {
			return map[x2][y2];
		} else if (x1 == 0) {
			return map[x2][y2] - map[x2][y1-1];
		} else if (y1 == 0) {
			return map[x2][y2] - map[x1-1][y2];
		} else {
			return map[x2][y2] - map[x1-1][y2] - map[x2][y1-1] + map[x1-1][y1-1];
		}
	}
}
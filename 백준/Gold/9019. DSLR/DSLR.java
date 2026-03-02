import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, A, B;
	static boolean[] visited;
	static String[] op;
	
	public static void main(String[] args) throws IOException {
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
		
			visited = new boolean[10000];
			op = new String[10000];
			
			bfs();
			sb.append(op[B]).append("\n");
		}
		
		System.out.println(sb.toString().trim());
	}
	
	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(A);
		visited[A] = true;
		op[A] = "";
		
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			
			for (int i = 1; i <= 4; i++) {
				int next = 0;
				String DSLR = "";
				
				if (i == 1) {
					next = (2 * curr) % 10000; 
					DSLR = "D";
					
				} else if (i == 2) {
					next = (curr - 1 + 10000) % 10000;
					DSLR = "S";
					
				} else if (i == 3) {
					next = rotate(curr, 0);
					DSLR = "L";
					
				} else if (i == 4) {
					next = rotate(curr, 1);
					DSLR = "R";
				}
					
				if (visited[next]) continue;
				visited[next] = true;
				op[next] = op[curr].concat(DSLR); // 연산자 갱신
				queue.offer(next);
			}
		}
	}
	
	static int rotate(int num, int dir) { // 0: 왼쪽, 1: 오른쪽
		int d1 = num/1000;
		num%=1000;
		int d2 = num/100;
		num%=100;
		int d3 = num/10;
		num%=10;
		int d4 = num;
		
		if (dir == 0) return d2 * 1000 + d3 * 100 + d4 * 10 + d1;
		else return d4 * 1000 + d1 * 100 + d2 * 10 + d3; 
	}
}
import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, A, B;
	static boolean[] visited;
	
	static class Node {
		int num;
		String op;
		
		Node(int num, String op) {
			this.num = num;
			this.op = op;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
		
			visited = new boolean[10000];
			
			bfs();
		}
		
		System.out.println(sb.toString().trim());
	}
	
	static void bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(A, ""));
		visited[A] = true;
		
		while (!queue.isEmpty()) {
			Node curr = queue.poll();
			int num = curr.num;
			String op = curr.op;
			
			if (num == B) {
				sb.append(op).append("\n");
				return;
			}
			
			for (int i = 1; i <= 4; i++) {
				int next = 0;
				String DSLR = "";
				
				if (i == 1) {
					next = (2 * num) % 10000; 
					DSLR = "D";
					
				} else if (i == 2) {
					next = (num - 1 + 10000) % 10000;
					DSLR = "S";
					
				} else if (i == 3) {
					next = rotate(num, 0);
					DSLR = "L";
					
				} else if (i == 4) {
					next = rotate(num, 1);
					DSLR = "R";
				}
					
				if (visited[next]) continue;
				visited[next] = true;
				queue.offer(new Node(next, op.concat(DSLR)));
			}
		}
	}
	
	static int rotate(int num, int dir) { // 0: 왼쪽, 1: 오른쪽
		
		if (dir == 0) {
			return (num%1000) * 10 + num/1000;
		} else {
			return (num%10) * 1000 + num/10;
		}
	}
}
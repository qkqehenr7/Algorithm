import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static int[][] visited;
	static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static PriorityQueue<Node> pq;
	
	static class Node implements Comparable<Node>{
		int row;
		int col;
		int totalRupee;
		
		Node(int row, int col, int totalRupee) {
			this.row = row;
			this.col = col;
			this.totalRupee = totalRupee;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.totalRupee - o.totalRupee;
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		int cnt = 1;
		while (true) {
			map = new int[N][N];
			visited = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], 9*N*N);
			}

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			pq = new PriorityQueue<>();
			pq.offer(new Node(0, 0, map[0][0]));
			bfs();
			sb.append("Problem " + cnt + ": " + visited[N-1][N-1]);

			N = Integer.parseInt(br.readLine());
			
			if (N != 0) {
				sb.append("\n");
				cnt++;
			} else {
				break;
			}
		}
		
		System.out.println(sb);
		
	}
	
	static void bfs() {
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			int r = curr.row;
			int c = curr.col;
			int total = curr.totalRupee;
			
			for (int i = 0; i < 4; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				
				if (visited[nr][nc] > total + map[nr][nc]) {
					visited[nr][nc] = total + map[nr][nc];
					pq.offer(new Node(nr, nc, total + map[nr][nc])); // 다음 노드 추가
				}
			}
		}
	}
}
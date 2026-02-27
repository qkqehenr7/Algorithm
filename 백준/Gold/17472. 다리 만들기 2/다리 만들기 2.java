import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, island = 2;
	static int[][] map;
	static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int[][] minDist;
	static Queue<int[]> points = new ArrayDeque<>();
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;
		
		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		public int compareTo(Edge e) {
			return Integer.compare(this.weight, e.weight);
		}
	}
	
	static int find(int x) {
		if (x == parent[x]) return x;
		else return parent[x] = find(parent[x]);
	}
	
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y) return false;
		parent[x] = y;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					bfs(i, j);
					island++;
				}
			}
		}
		
//		System.out.println("섬의 개수: " + (island-2));
		
		parent = new int[island];
		for (int i = 0; i < island; i++) parent[i] = i;
		
		minDist = new int[island+1][island+1];
		for (int i = 0; i <= island; i++) {
			Arrays.fill(minDist[i], Integer.MAX_VALUE);
		}
		calDist();
		
//		// 색칠된 섬 출력
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		// 섬 간 거리 출력
		for (int i = 2; i < island-1; i++) {
			for (int j = i+1; j < island; j++) {
				pq.offer(new Edge(i, j, minDist[i][j]));
//				System.out.println(i + " " + j + " " + minDist[i][j]);
			}
		}
		
		int total = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			if (cnt == island-2) {
				System.out.println(total);
				return;
			}
			Edge edge = pq.poll();
			if (union(edge.from, edge.to)) {
				if (edge.weight == Integer.MAX_VALUE) {
					System.out.println(-1);
					return;
				}
				total += edge.weight;
//				System.out.println("거리: " + edge.weight);
				cnt++;
			}
		}
		System.out.println(total);
	}
	
	static void calDist() {
		while (!points.isEmpty()) {
			int[] curr = points.poll();
			int r = curr[0];
			int c = curr[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r;
				int nc = c;
				int dist = 0;
				while (true) {
					nr += moves[i][0];
					nc += moves[i][1];
					if (nr < 0 || nc < 0 || nr >= N || nc >= M) break; // 지도 밖으로 나가버리고..
					if (map[nr][nc] == map[r][c]) { // 이쪽 길은 아니다.
						break;
					}
					
					if (map[nr][nc] == 0) {
						dist++;
						continue;
					}
					if (map[nr][nc] != map[r][c]) { // 다른 섬 만나면
						if (dist != 1) { // 거리 1이면 안됨
							int a = map[nr][nc];
							int b = map[r][c];
							minDist[a][b] = Math.min(minDist[a][b], dist);
							minDist[b][a] = minDist[a][b];
						}
						break; // 아무튼 중지는 해야함
					}
				}
			}
		}
	}
	
	static void bfs(int row, int col) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {row, col});
		points.offer(new int[] {row, col});
		map[row][col] = island;
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + moves[i][0];
				int nc = c + moves[i][1];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if (map[nr][nc] == 0 || map[nr][nc] > 1) continue;
				
				map[nr][nc] = island;
				queue.offer(new int[] {nr, nc});
				points.offer(new int[] {nr, nc});
			}
		}
	}
}
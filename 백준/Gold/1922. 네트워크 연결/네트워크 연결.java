import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int[] parent, rank;
	
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
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (rank[x] <= rank[y]) {
			rank[x] += rank[y];
			parent[y] = x;
		} else {
			rank[y] += rank[x];
			parent[x] = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		
		rank = new int[N+1];
		Arrays.fill(rank, 1);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.offer(new Edge(a, b, c));
		}
		
		int total = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (find(edge.from) != find(edge.to)) { // 부모 노드가 다른 경우 (연결되지 않음)
				union(edge.from, edge.to); // union 연산 수행
				total += edge.weight; // 비용 누적합
			}
		}
		
		System.out.println(total);
	}
}
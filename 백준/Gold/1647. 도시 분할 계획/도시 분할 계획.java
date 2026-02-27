import java.util.*;
import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, A, B, C;
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
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for (int i = 0; i <= N; i++) parent[i] = i;
		rank = new int[N+1];
		Arrays.fill(rank, 1);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(A, B, C));
		}
		
		int total = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			if (cnt == N-2) break;
			Edge edge = pq.poll();
			if (find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				total += edge.weight;
				cnt++;
			}
		}
		System.out.println(total);
	}
}
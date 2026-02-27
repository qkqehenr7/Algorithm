import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double cost;
		
		Edge(int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		public int compareTo(Edge e) {
			return Double.compare(this.cost, e.cost);
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static double a, b;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static double[][] stars;
	
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

		n = Integer.parseInt(br.readLine());
		stars = new double[n+1][2];
		parent = new int[n+1];
		for (int i = 0; i <= n; i++) parent[i] = i;
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			a = Double.parseDouble(st.nextToken());
			b = Double.parseDouble(st.nextToken());
			stars[i][0] = a;
			stars[i][1] = b;
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = i+1; j <= n; j++) {
				double dist = Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2));
				pq.offer(new Edge(i, j, dist));
			}
		}
		
		double total = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			if (cnt == n-1) break;
			Edge edge = pq.poll();
			if (union(edge.from, edge.to)) {
				total += edge.cost;
				cnt++;
			}
		}
		System.out.println(Math.round(total * 100) / 100.0);
	}
}
import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static int[] indegree;
	static List<List<Integer>> adj;
//	static ArrayList[] adj;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N+1];
		adj = new ArrayList<>(N+1);
//		adj = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
//			adj[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
//			adj[A].add(B);
			adj.get(A).add(B);
			indegree[B]++;
		}
		
//		Queue<Integer> queue = new ArrayDeque<>();
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
//				sb.append(i + " ");
			}
		}
		
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			sb.append(curr + " ");
			
			for (int next : adj.get(curr)) {
				indegree[next]--; // 차수 1 감소
				
				if (indegree[next] == 0) {
					queue.offer(next);
//					sb.append(next + " ");
				}
			}
			
		}
		
		System.out.println(sb);

	}

}
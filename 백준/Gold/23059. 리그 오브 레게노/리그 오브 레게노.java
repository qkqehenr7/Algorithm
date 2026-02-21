import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		HashMap<String, Integer> indegree = new HashMap<>();
		HashMap<String, ArrayList<String>> adj = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String A = st.nextToken();
			String B = st.nextToken();
			
			adj.computeIfAbsent(A, k -> new ArrayList<>()).add(B);
			adj.put(B, adj.getOrDefault(B, new ArrayList<>()));
			
			indegree.put(A, indegree.getOrDefault(A, 0)); // 너는 차수 올리지마
			indegree.put(B, indegree.getOrDefault(B, 0) + 1); // 차수 증가
		}
		
		
		PriorityQueue<String> pq = new PriorityQueue<>();
		for (String key : indegree.keySet()) {
			if (indegree.get(key) == 0) {
				pq.offer(key);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while (true) {
			
			Queue<String> queue = new ArrayDeque<>();
			while (!pq.isEmpty()) {
				String curr = pq.poll();
				sb.append(curr).append("\n");
				cnt++;
				
				for (String next : adj.get(curr)) {
					indegree.put(next, indegree.get(next) -1); // 차수 1 낮춤
					
					if (indegree.get(next) == 0) {
						queue.offer(next);
					}
				}
			}
			
			while (!queue.isEmpty()) {
				pq.offer(queue.poll());
			}
			
			if (pq.isEmpty()) break;
		}
		
		if (cnt != indegree.size()) {
			System.out.println(-1);
		} else {
			System.out.println(sb.toString().trim());
		}
	}
}
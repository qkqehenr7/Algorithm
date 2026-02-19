import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, A, B;
	static StringTokenizer st;
	static List<List<Integer>> adj = new ArrayList<>(N+1);
	static int[] indegree;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		indegree = new int[N+1];
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			adj.get(A).add(B);
			indegree[B]++;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) queue.offer(i);
		}
		
		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			sb.append(curr).append(' ');
			
			for (int next : adj.get(curr)) {
				indegree[next]--;
				if (indegree[next] == 0) queue.offer(next);
			}
		}

		System.out.println(sb);
	}
}
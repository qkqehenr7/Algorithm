import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;
	static int[] visited;
	static int[] moves = {1, -1};
	static int min_value = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new int[100003];
		Arrays.fill(visited, 100003);
		
		bfs(N);
		System.out.println(visited[K]-1);
	}
	
	static void bfs(int idx) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(idx);
		visited[idx] = 1;
		
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			if (curr == K) {
				min_value = Math.min(min_value, visited[curr]);
				continue;
			}
			if (visited[curr] >= min_value) continue;
			
			for (int i = 0; i < 3; i++) {
				if (i != 2) { // 한 칸 이동
					int next = curr + moves[i];
					if (!check(next)) continue;
					if (visited[next] <= visited[curr] + 1) continue;
					visited[next] = visited[curr] + 1;
					queue.offer(next);
					
				} else { // 순간이동
					int next = curr * 2;
					if (!check(next)) continue;
					if (visited[next] <= visited[curr]) continue;
					visited[next] = visited[curr];
					queue.offer(next);
				}
			}
		}
	}
	
	static boolean check(int idx) {
		if (idx < 0 || idx > 100002) return false;
		return true;
	}

}
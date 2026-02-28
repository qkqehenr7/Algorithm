import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;
	static int[] visited;
	static int min_value = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new int[100003];
		Arrays.fill(visited, 100003); // 최대 100002회 이동 가능
		
		bfs(N);
		System.out.println(visited[K] - 1);
	}
	
	static void bfs(int idx) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(idx);
		visited[idx] = 1;
		
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			if (curr == K) {
				min_value = Math.min(min_value, visited[curr]); // 최단 시간 갱신
				continue;
			}
			if (visited[curr] >= min_value) continue; // 이미 현재 시점의 최단 시간을 넘은 경우
			
			for (int i = 0; i < 3; i++) {
				int next = 0, sec = 0;
				if (i == 0) { // 오른쪽
					next = curr + 1;
					sec = visited[curr] + 1;
					
				} else if (i == 1) { // 왼쪽
					next = curr - 1;
					sec = visited[curr] + 1;
				}
				else { // 순간이동
					next = curr * 2;
					sec = visited[curr];
				}
				
				if (next < 0 || next > 100002) continue; // 배열 밖으로 나간 경우
				if (visited[next] <= sec) continue; // 이미 더 빠른 방법이 있는 경우
				visited[next] = sec;
				queue.offer(next);
			}
		}
	}
}
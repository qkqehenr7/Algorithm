import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] population;
	static List<List<Integer>> adj = new ArrayList<>();
	static List<List<Integer>> subset = new ArrayList<>();
	static boolean[] visited;
	static int totalPop = 0;
	static int min_pop = Integer.MAX_VALUE;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N];
		visited = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			totalPop += population[i];
		}
		
		// 1. 인접 리스트 만들기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			List<Integer> nb = new ArrayList<>();
			int t = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < t; j++) {
				nb.add(Integer.parseInt(st.nextToken()));
			}
			adj.add(nb);
		}
		
		subset(0);
		
		// 2-3. 공집합을 제외한 진부분집합 만들기
		for (List<Integer> elements : subset) {
			// 2-4. 여집합 만들기
			List<Integer> comp = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				if (!elements.contains(i)) {
					comp.add(i);
				}
			}
			
			// 4-1. 두 선거구가 모두 연결된 상태인 경우 최솟값 갱신하기
			if (isConnected(elements) && isConnected(comp)) {
				int total = 0;
				for (int element : elements) {
					total += population[element-1];
				}
				int diff = Math.abs((totalPop - 2*total));
				min_pop = min_pop > diff ? diff : min_pop;
			}
		}
		System.out.println((min_pop == Integer.MAX_VALUE) ? -1 : min_pop);
	}
	
	// 2-1. 부분집합 만들기
	static void subset(int depth) {
		if (depth == N && check()) {
			return;
		}
		if (depth == N) {
			List<Integer> elements = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (visited[i]) elements.add(i+1);
			}
			subset.add(elements);
			return;
		}
		visited[depth] = true;
		subset(depth+1);
		visited[depth] = false;
		subset(depth+1);	
	}
	
	// 2-2. 모든 부분집합을 만들지 않도록 하기
	static boolean check() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i]) cnt++; 
		}
		return cnt > N/2 || cnt == 0;
	}
	
	// 4-2. 같은 선거구 내 노드들의 상호연결 여부 확인하기
	static boolean isConnected(List<Integer> elements) {
		boolean[] vis = new boolean[N];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(elements.get(0));
		vis[elements.get(0)-1] = true;
		
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			
			for (int node : adj.get(curr-1)) {
				// 방문하지 않았으면서 subset에 포함된 노드만 큐에 넣기
				if (!vis[node-1] && elements.contains(node)) {
					vis[node-1] = true;
					queue.offer(node);
				}
			}
		}
		
		for (int node : elements) {
			if(!vis[node-1]) {
				return false;
			}
		}
		return true;
	}
}
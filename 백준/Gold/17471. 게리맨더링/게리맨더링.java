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
	static int totalPop = 0;
	static int min_diff = Integer.MAX_VALUE;
	static int FULL;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N];
		
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
		
		FULL = (1 << N) - 1;

		// 2. 서브셋 구하기
		for (int mask = 1; mask < (1 << N) / 2; mask++) {

		    int comp = FULL ^ mask;

		    if (!isConnected(mask) || !isConnected(comp)) continue;

		    int sum = 0;
		    for (int i = 0; i < N; i++) {
		        if ((mask & (1 << i)) != 0) {
		            sum += population[i];
		        }
		    }

		    int diff = Math.abs(totalPop - 2 * sum);
		    min_diff = min_diff < diff ? min_diff : diff;
		}
		
		System.out.println((min_diff == Integer.MAX_VALUE) ? -1 : min_diff);
	}
	
	// 3. 같은 선거구 내 노드들의 상호연결 여부 확인하기
	static boolean isConnected(int mask) {
		boolean[] vis = new boolean[N];
		Queue<Integer> queue = new ArrayDeque<>();
		
		// 시작 노드 찾기
	    int start = -1;
	    for (int i = 0; i < N; i++) {
	        if ((mask & (1 << i)) != 0) {
	            start = i;
	            break;
	        }
	    }
	    
	    if (start == -1) return false;
	    
	    queue.offer(start);
	    vis[start] = true;
	    int cnt = 1;
		
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			
			for (int next : adj.get(curr)) {
				next--;
				// 방문하지 않았으면서 subset에 포함된 노드만 큐에 넣기
				if (!vis[next] && (mask & (1 << next)) != 0)  {
					vis[next] = true;
					queue.offer(next);
					cnt++;
				}
			}
		}
		return cnt == Integer.bitCount(mask);
	}
}
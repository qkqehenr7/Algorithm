import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, S, T;
	
	static class Session implements Comparable<Session>{
		int startTime;
		int endTime;
		
		Session(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Session o) {
			if (this.endTime == o.endTime) {
				return Integer.compare(this.startTime, o.startTime);
			}
			return Integer.compare(this.endTime, o.endTime);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Session> pq = new PriorityQueue<>();
		Session[] arr = new Session[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			arr[i] = new Session(S, T);
		}
		// 정렬은 시작 시간 순
		Arrays.sort(arr, (l1, l2) -> Integer.compare(l1.startTime, l2.startTime));
		
		for (int i = 0; i < N; i++) {
			Session curr = arr[i];
			if (!pq.isEmpty()) {
				Session prev = pq.poll();
				if (prev.endTime > curr.startTime) pq.offer(prev);
			}
			pq.offer(curr);
		}
		System.out.println(pq.size());

	}	
}
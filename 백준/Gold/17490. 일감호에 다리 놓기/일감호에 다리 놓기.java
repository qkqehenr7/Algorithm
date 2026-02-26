import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static long K;
	static int[] P, R, possible;
	static PriorityQueue<Room> pq = new PriorityQueue<>();
	
	static class Room implements Comparable<Room>{
		int from;
		int to;
		int weight;
		
		Room(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Room o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
        
        if (M <= 1) {
		    System.out.println("YES");
		    return;
		}
		
		P = new int[N+1];
		for (int i = 1; i <= N; i++) {
			P[i] = i;
		}
		
		R = new int[N+1];
		Arrays.fill(R, 1);
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			int weight = Integer.parseInt(st.nextToken());
			pq.offer(new Room(0, i, weight));
		}
		
		possible = new int[N+1];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			if (Math.min(i, j) == 1 && Math.max(i, j) == N) {
				possible[N] = 1;
			} else possible[Math.min(i, j)] = 1; // 다음으로 못가면 1 
		}
		
		for (int i = 1; i <= N; i++) {
			if (possible[i] == 0) {
				if (i == N) pq.offer(new Room(i, 1, 0));
				else pq.offer(new Room(i, i+1, 0));		
			}
		}
		
		long total = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			Room room = pq.poll();
			if (find(room.from) != find(room.to)) { // 부모가 다른 경우
				union(room.from, room.to);
				total += room.weight;
				cnt++;
			}
		}
		
		System.out.println(total<= K && cnt == N ? "YES" : "NO");

	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (R[x] >= R[y]) {
			R[x] += R[y]; // 카운트 증가
			P[y] = x; // 부모로 지정
		} else {
			R[y] += R[x];
			P[x] = y;
		}
	}
	
	static int find(int x) {
		if (x == P[x]) return x;
		else return P[x] = find(P[x]);
	}
}
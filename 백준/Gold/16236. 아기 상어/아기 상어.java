import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] moves = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int sr, sc;
	static int size = 2;
	static int cnt;
	static int seconds;
	
	static class Fish implements Comparable<Fish> {
		int distance;
		int row;
		int col;
		
		Fish(int distance, int row, int col) {
			this.distance = distance;
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(Fish o) {
			
			if (this.distance != o.distance) {
				return Integer.compare(this.distance, o.distance);
			}
			
			if (this.row != o.row) {
				return Integer.compare(this.row, o.row);
			}
			
			return Integer.compare(this.col, o.col);
		}
	}

	public static void main(String[] args) throws IOException  {
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					sr = i;
					sc = j;
				}
			}
		}
		
		bfs();
		System.out.println(seconds);
	}

	static void bfs() {
		// 가장 우선순위가 높은 먹이를 찾아 먹는 루프
		Queue<Fish> queue = new ArrayDeque<>(); // bfs
		queue.offer(new Fish(0, sr, sc)); // 출발 좌표
		map[sr][sc] = 0; // 기존 아기상어 위치 비우기
		visited = new boolean[N][N]; // 방문 기록 초기화
		visited[sr][sc] = true; // 방문 처리
		while (true) {
			
			List<Fish> fishes = new ArrayList<>(); // 먹을 수 있는 먹이 리스트 초기화
			
			while (!queue.isEmpty()) {
				Fish curr = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int nr = curr.row + moves[i][0];
					int nc = curr.col + moves[i][1];
					
					if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
					
					if (visited[nr][nc]) continue;
					
					if (map[nr][nc] <= size) {
						visited[nr][nc] = true;
						if (map[nr][nc] != 0 && map[nr][nc] < size) {
							fishes.add(new Fish(curr.distance+1, nr, nc));
						}
						queue.offer(new Fish(curr.distance+1, nr, nc));
					}
				}
			}
			
			if (fishes.isEmpty()) return; // 먹을 수 있는 먹이가 없으면 종료
			// 먹이들 정렬
			Collections.sort(fishes);
			Fish target = fishes.get(0); // 먹이 선정
			seconds += target.distance; // 이동 시간 누적
			
			cnt++; // 먹이 카운팅
			if (size == cnt) { // 크기만큼 먹으면 성장
				size++;
				cnt = 0;
			}
			// 다시 큐에 아기 상어 좌표 넣기
			queue.offer(new Fish(0, target.row, target.col));
			map[target.row][target.col] = 0; // 자리 비우기
			visited = new boolean[N][N]; // 방문 기록 초기화
			visited[target.row][target.col] = true; // 시작점 방문 처리
		}
	}
}
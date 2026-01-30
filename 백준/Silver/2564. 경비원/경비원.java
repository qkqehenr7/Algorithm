import java.io.*;

public class Main {

	static int[] WL;
	static int n;
	static int[][] map; // 위치 좌표
	static int total = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nums = br.readLine().split(" ");
		WL = new int[2];
		WL[0] = Integer.parseInt(nums[0]); // 가로
		WL[1] = Integer.parseInt(nums[1]); // 세로

		n = Integer.parseInt(br.readLine()); // 상점 수
		
		map = new int[n+1][2]; // n개 + 동근이 좌표
		
		// 방향과 거리를 행렬 좌표로 변환
		for (int i = 0; i < n+1; i++) {
			nums = br.readLine().split(" ");
			trans(i, nums[0], nums[1]);
		}
		
		// 최단 거리 누적 계산
		for (int i = 0; i < n; i++) {
			total += distance(map[i][0], map[i][1]);
		}
		
		System.out.println(total);
		
	}
	
	// 좌표 변환 메서드
	static void trans(int idx, String dir, String dist) {
		switch(dir) {
		// 북
		case "1":
			map[idx][0] = 0;
			map[idx][1] = Integer.parseInt(dist);
			return;
		// 남
		case "2":
			map[idx][0] = WL[1];
			map[idx][1] = Integer.parseInt(dist);
			return;
		// 서
		case "3":
			map[idx][0] = Integer.parseInt(dist);
			map[idx][1] = 0;
			return;
		// 동
		case "4":
			map[idx][0] = Integer.parseInt(dist);
			map[idx][1] = WL[0];
			return;
		}
	}
	
	// 동근이와의 최단 거리 계산 메서드
	static int distance(int row, int col) {		
		// 남북 마주볼 때
		if (Math.abs(map[n][0] - row) == WL[1]) {
			if (2*WL[0] - col - map[n][1] > col + map[n][1]) {
				return WL[1] + col + map[n][1];
			} else {
				return WL[1] + 2*WL[0] - col - map[n][1]; 
			}
		// 동서 마주볼 때
		} else if (Math.abs(map[n][1] - col) == WL[0]) {
			if (2*WL[1] - row - map[n][0] > row + map[n][0]) {
				return WL[0] + row + map[n][0];
			} else {
				return WL[0] + 2*WL[1] - row - map[n][0]; 
			}
		// 그 외는 다 좌우로 인접 맞제?
		} else {
			return Math.abs((map[n][0] - row)) + Math.abs((map[n][1] - col)); 
		}
	}
}
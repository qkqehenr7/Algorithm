import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static int[] arr;
	static int max_score = Integer.MIN_VALUE;
	static boolean[] field;
	static int score, order;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][9];
		
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		arr = new int[9];
		perm(0, 0, 0);
		System.out.println(max_score);
		
	}
	
	static void perm(int inning, int cnt, int bit) {
		if (cnt == 9) {
			score = 0; order = 0;
			calTotalScore();
			max_score = Math.max(max_score, score);
			return;
		}
		
		
		for (int i = 0; i < 9; i++) {
			if (cnt == 3 && i != 0) continue;
			if ((bit & (1<<i)) == 0) {
				arr[cnt] = i;
				perm(inning, cnt+1, bit | (1<<i));
				arr[cnt] = 0;
			}
		}
	}
	
	
	static void push(int res) {
		// 기존 주자 진루 먼저
		for (int i = 2; i >= 0; i--) {
			// i+1루에 주자가 있는 경우
			if (field[i]) {
				// 홈인하는 경우
				if (i + res > 2) {
					field[i] = false; // 기존 자리는 지우기
					score++; // 점수 증가
				// 홈인 X
				} else {
					field[i] = false;
					field[i+res] = true;
				}
			}			
		}
		if (res != 4) {
			field[res-1] = true; // 주자 진출
		} else {
			score++; // 본인이 홈런타자면 점수 획득
		}
	}
	
	static void calTotalScore() {
		for (int i = 0; i < N; i++) { // i번째 이닝에 대하여
			field = new boolean[3]; // 1,2,3루 주자 존재 여부
			int out = 0; // 아웃 카운트
			while (out < 3) {
				int result = map[i][arr[order]]; // 이번 타자의 결과
				if (result == 0) { // 아웃인 경우 아웃카운트와 타순 증가
					out++;
				} else if (result == 1) {
					push(1);
				} else if (result == 2) {
					push(2);
				} else if (result == 3) {
					push(3);
				} else {
					push(4);
				}
				order++;
				order %= 9; // 타선 순환
			}
		}
	}
}
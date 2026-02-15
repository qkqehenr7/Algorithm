import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, answer = Integer.MIN_VALUE;
	static int[][] eggs;

	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		eggs = new int[N][2];
		for (int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i][0] = Integer.parseInt(st.nextToken());
			eggs[i][1] = Integer.parseInt(st.nextToken());
		}
		
		eggBoom(0);
		System.out.println(answer);
	}
	
	static void eggBoom(int attackEgg) {
		
		if (attackEgg-1 == N-1) { // 이전 계란이 가장 오른쪽인 경우
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (eggs[i][0] <= 0) cnt++;
			}
			answer = Math.max(answer, cnt);
			return;
		}
		
		if (eggs[attackEgg][0] <= 0) { // 공격 계란이 깨진 경우
			eggBoom(attackEgg+1);

		} else { // 깨지지 않은 경우, 공격 대상 선택
			for (int i = 0; i < N; i++) {
				if (attackEgg == i) continue; // 자기자신 제외
				
				if (eggs[i][0] > 0) { // 깨지지 않은 경우
					eggs[i][0] -= eggs[attackEgg][1];
					eggs[attackEgg][0] -= eggs[i][1];
					eggBoom(attackEgg+1);
					eggs[i][0] += eggs[attackEgg][1];
					eggs[attackEgg][0] += eggs[i][1];
				}
			}
		}

		// 깨지지 않은 다른 계란이 없는 경우
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (eggs[i][0] <= 0) cnt++;
		}
		answer = Math.max(answer, cnt);
		return;
		
	}
}
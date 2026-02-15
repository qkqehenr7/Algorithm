import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int[] nums, operators;
	static int max_value = Integer.MIN_VALUE;
	static int min_value = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		operators = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		simulate(nums[0], 1);
		
		sb.append(max_value + "\n" + min_value);
		System.out.println(sb);
	}
	
	static int calculate(int a, int op, int b) {
		switch (op) {
		case 0:
			return a + b;
		case 1:
			return a - b;
		case 2:
			return a * b;
		default:
			return a / b;
		}
	}
	
	static void simulate(int prev, int idx) {
		
		if (idx == N) {
			max_value = Math.max(max_value, prev);
			min_value = Math.min(min_value, prev);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (operators[i] > 0) {
				operators[i]--;
				int next = calculate(prev, i, nums[idx]);
				simulate(next, idx+1);
				operators[i]++;
			}
		}
	}
}
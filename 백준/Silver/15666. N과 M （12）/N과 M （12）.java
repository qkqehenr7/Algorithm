import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static List<Integer> nums = new ArrayList<>();
	static int[] arr;
	
	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (!nums.contains(num)) {
				nums.add(num);
			}
		}
		Collections.sort(nums);
		arr = new int[M];
		subseq(0, 0);
		System.out.println(sb.toString().trim());
	}
	
	static void subseq(int idx, int cnt) {
		if (idx == nums.size()) {
			return;
		}
		
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				if (i != M-1) sb.append(arr[i]).append(" ");
				else sb.append(arr[i]).append("\n");
			}
			return;
		}
		arr[cnt] = nums.get(idx);
		subseq(idx, cnt+1);
		arr[cnt] = 0;
		subseq(idx+1, cnt);
	}
}
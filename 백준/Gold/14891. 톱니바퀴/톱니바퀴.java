import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] wheel = new int[4][8];
	static int[] pos = new int[4];
	static int K;
	static int n;
	static int dir;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			char[] nums = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = nums[j] - '0';
			}
		}
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken()); // 1: clockwise, 2: counterClockwise
			rotate(n, dir, 0);
		}
		
		int total = 0;
		for (int i = 0; i < 4; i++) {
			total += wheel[i][(8-pos[i])%8] * Math.pow(2, i);
		}
		System.out.println(total);
		

	}
	
	
	static void rotate(int n, int dir, int prev) {
		int prevPos = pos[n-1];
		pos[n-1] = (pos[n-1] + dir + 8)%8;
		
		if (n > 1 && prev != n-1 && wheel[n-1][(8-prevPos+6)%8] != wheel[n-2][(8-pos[n-2]+2)%8]) {
			rotate(n-1, dir*(-1), n);
		}
		
		if (n < 4 && prev != n+1 && wheel[n-1][(8-prevPos+2)%8] != wheel[n][(8-pos[n]+6)%8]) {
			rotate(n+1, dir*(-1), n);
		}
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, r, c, answer;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		Z(N, r, c, 0);
		System.out.println(answer);
	}
	
	static void Z(int n, int row, int col, int head) {
		if (n == 1) {
			if (row == 0 && col == 0) {
				answer = head;
			} else if (row == 0) {
				answer = head+1;
			} else if (col == 0) {
				answer = head+2;
			} else {
				answer = head+3;
			}	
			return;
		}
		
		int num = (int) Math.pow(2, n-1); // N=3, 4
		
		if (row < num && col < num) {
			Z(n-1, row, col, head);
		}
		
		if (row < num && col >= num) {
			Z(n-1, row, col - num, (int)Math.pow(num, 2) + head);
		}
		
		if (row >= num && col < num) {
			Z(n-1, row - num, col, (int)Math.pow(num, 2)*2 + head);
		}
		
		if (row >= num && col >= num) {
			Z(n-1, row - num, col - num, (int)Math.pow(num, 2)*3 + head);
		}
	}
}
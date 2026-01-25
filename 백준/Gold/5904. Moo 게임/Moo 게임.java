import java.io.*;

public class Main {
	
	static int n;
	static int[] len;
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
        
		len = new int[30];
		len[0] = 3;
		for (int k = 1; k < 30; k++) {
			len[k] = 2 * len[k-1] + k + 3;
			if (len[k] > n) {
				moo(n, k);
				break;
			}
		}
	}
	
	static void moo(int idx, int k) {
		
		if (idx == 1) {
			System.out.println('m');
			return;
		} else if (idx == 2 || idx == 3) {
			System.out.println('o');
			return;
		}
		
		if (idx <= len[k-1]) {
			moo(idx, k-1);
		} else if (idx <= len[k-1] + k+3) {
			if (idx == len[k-1] + 1) {
				System.out.println('m');
			} else {
				System.out.println('o');
			}
			return;
		} else {
			moo(idx - (len[k-1] + k+3), k-1);
		}
	}
}
import java.io.*;

public class Main {
	
	static long div = 1_000_000_007;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		long[][] mat = new long[][] {
			{1, 1}, {1, 0}
		};
		long[][] res = divConq(mat, n);
		System.out.println(res[1][0] % div);		
	}
	
	static long[][] times(long[][] matrixA, long[][] matrixB) {
		long[][] res = new long[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					res[i][j] += matrixA[i][k] * matrixB[k][j];
					res[i][j] %= div;
				}
			}
		}
		return res;
	}
	
	static long[][] divConq(long[][] mat, long exp) {
		if (exp == 1) {
			return mat;
		}
		long[][] res = divConq(mat, exp / 2);
		res = times(res, res);
		if (exp % 2 == 1) {
			res = times(res, mat);
		}
		return res;
	}
}
import java.io.*;

public class Main {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder(); 
	static long[] C = new long[31];
	
	public static void main(String[] args) throws IOException {
		
		C[0] = 1;
		
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			sb.append(catalan(N)).append("\n");	
		}
		
		System.out.println(sb.toString().trim());
	}
	
	static long catalan(int n) {
		if (C[n] != 0) return C[n];
		return C[n] = (4*n-2) * catalan(n-1) / (n+1);
	}
}
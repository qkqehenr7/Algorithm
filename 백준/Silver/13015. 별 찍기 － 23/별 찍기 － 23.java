import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < 2*N; i++) {
			star(i);
		}
		
		System.out.println(sb.toString().trim());
	}
	
	static void star(int depth) {
		if (depth > N) {
			depth = 2*N-1 - depth;
		}
		
		if (depth == 0) {
			for (int i = 0; i < 4*N - 3; i++) {
				if (i < N || i >= 3*N-3) {
					sb.append('*');
				} else sb.append(' ');
			}
			sb.append("\n");
		}
		
		else if (depth != N) {
			for (int i = 0; i < 4*N - 3; i++) {
				
				if (i > 4*N - 4 - depth) break;
				
				if (i == depth || i == 4*N - 4 - depth || i == depth + N-1 || i == 3*N - 3 - depth) {
					sb.append('*');
				} else sb.append(' ');
			}
			sb.append('\n');
		}
	}
}
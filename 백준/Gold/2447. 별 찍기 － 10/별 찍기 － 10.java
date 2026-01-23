import java.io.*;

public class Main {
	
	static int n;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				star(i, j);
			}
		}
		System.out.println(sb);
	}
	
	static void star(int r, int c) {
		int nRow = r;
		int nCol = c;
		// 둘 다 0이 되면 정지
		while (nRow > 0 || nCol > 0) {
			// 나머지가 모두 1인 칸인 경우 공백
			if (nRow%3 == 1 && nCol%3 == 1) {
				sb.append(" ");
				// 오른쪽 끝이었다면 줄바꿈
				if (c == n - 1) {
					sb.append("\n");
				}
				return;
			}
			// 1/3 사이즈에서 공백 찾기
			nRow /= 3;
			nCol /= 3;
		}
		sb.append("*");
		if (c == n - 1) sb.append("\n");
	}
}
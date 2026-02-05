import java.io.*;
import java.util.*;

public class Main {
	
	static boolean[][] map = new boolean[100][100];
	static int total;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		int cnt = Integer.parseInt(br.readLine());
		for (int i = 0; i < cnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			
			for (int r = row; r < row+10; r++) {
				for (int c = col; c < col+10; c++) {
					if (!map[r][c]) {
						map[r][c] = true;
						total++;
					}
				}
			}
		}
		System.out.println(total);	
	}
}
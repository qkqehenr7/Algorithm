import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int x = Math.abs(x2-x1);
			int y = Math.abs(y2-y1);
			int g = (x + y)/2;
			int ans = g*2 + Math.abs(g-x) + Math.abs(g-y);
			
			if (t != T) sb.append("#" + t + " " + ans + "\n");
			else sb.append("#" + t + " " + ans);
		}
		System.out.println(sb);
	}
}
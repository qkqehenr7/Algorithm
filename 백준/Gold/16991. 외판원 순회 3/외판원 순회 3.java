import java.io.*;
import java.util.*;

public class Main {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, done = 0;
	static int[][] pos;
	static double[][] moveCost, cost;
	
	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		pos = new int[N][2];
		moveCost = new double[N][N];
		cost = new double[N][100000];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
			
			done = done << 1;
			done++;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				moveCost[i][j] = Math.sqrt(Math.pow(pos[i][0] - pos[j][0], 2) + Math.pow(pos[i][1] - pos[j][1], 2));
				moveCost[j][i] = moveCost[i][j];
			}
		}		
		
		done -= 1;
		System.out.println(travel(0, 0));
	}
	
	
	
	static double travel(int idx, int visited) {
		if (cost[idx][visited] != 0) return cost[idx][visited];
		if (visited == done) {
			if (moveCost[idx][0] == 0) cost[idx][visited] = -1;
			else cost[idx][visited] = moveCost[idx][0];
			
			return cost[idx][visited];
		}
		
		double minCost = 0;
		double sum = 0;
		int v;
		
		for (int i = 1; i < N; i++) {
			if ((visited & (1<<i)) != 0) continue;
			if (moveCost[idx][i] == 0) continue;
			
			v = visited + (1 << i);
			if (travel(i, v) < 0) continue;
			
			sum = moveCost[idx][i] + travel(i,v);
			if (minCost == 0 || minCost > sum) minCost = sum;
		}
		
		if (minCost == 0) cost[idx][visited] = -1;
		else cost[idx][visited] = minCost;
		
		return cost[idx][visited];
	}
}
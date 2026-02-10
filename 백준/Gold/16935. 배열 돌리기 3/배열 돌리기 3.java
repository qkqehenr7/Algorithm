import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;
	static int N;
	static int M;
	static int R;
	static int[][] arr;
	static int[][] temp;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			rotate(Integer.parseInt(st.nextToken()));
		}
		br.close();
		
		sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (j != M-1) sb.append(arr[i][j] + " ");
				else sb.append(arr[i][j]);
			}
			if (i != N-1) sb.append("\n");
		}
		System.out.println(sb);

	}

	static void rotate(int op) {
		
		if (op == 1) {
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < M; j++) {
					arr[N-1 -i][j] = arr[N-1 -i][j] ^ arr[i][j] ^ (arr[i][j] = arr[N-1 -i][j]);
				}
			}
		} else if (op == 2) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M/2; j++) {
					arr[i][M-1 -j] = arr[i][M-1 -j] ^ arr[i][j] ^ (arr[i][j] = arr[i][M-1 -j]);
				}
			}
			
		} else if (op == 3) {
			temp = new int[M][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					temp[j][N-1 -i] = arr[i][j];
				}
			}
			arr = temp;
			N = N ^ M ^ (M = N);
						
		} else if (op == 4) {
			temp = new int[M][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					temp[M-1 -j][i] = arr[i][j];
				}
			}
			arr = temp;
			N = N ^ M ^ (M = N);
			
		} else if (op == 5) {
			temp = new int[N][M];
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < M/2; j++) {
					temp[i][j+M/2] = arr[i][j];
				}
				
				for (int j = M/2; j < M; j++) {
					temp[i+N/2][j] = arr[i][j];
				}
			}
			
			for (int i = N/2; i < N; i++) {
				for (int j = M/2; j < M; j++) {
					temp[i][j-M/2] = arr[i][j];
				}
				
				for (int j = 0; j < M/2; j++) {
					temp[i-N/2][j] = arr[i][j];
				}
			}
			arr = temp;
			
		} else if (op == 6) {
			temp = new int[N][M];
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < M/2; j++) {
					temp[i+N/2][j] = arr[i][j];
				}
				
				for (int j = M/2; j < M; j++) {
					temp[i][j-M/2] = arr[i][j];
				}
			}
			
			for (int i = N/2; i < N; i++) {
				for (int j = M/2; j < M; j++) {
					temp[i-N/2][j] = arr[i][j];
				}
				
				for (int j = 0; j < M/2; j++) {
					temp[i][j+M/2] = arr[i][j];
				}
			}
			arr = temp;
		}	
	}
}
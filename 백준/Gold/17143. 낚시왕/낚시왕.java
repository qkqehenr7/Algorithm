import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, M;
	static int r, c, s, d, z;
	static ArrayList<Shark> sharks = new ArrayList<>();
	static int[][] map;
	static int[][] moves = {{}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static int total;
	
	static class Shark {
		int row;
		int col;
		int velocity;
		int direction;
		int size;
		
		Shark(int row, int col, int velocity, int direction, int size) {
			this.row = row;
			this.col = col;
			this.velocity = velocity;
			this.direction = direction;
			this.size = size;
		}
		
		void setRow(int row) {
			this.row = row;
		}
		
		void setCol(int col) {
			this.col = col;
		}
		
		void setDirection(int direction) {
			this.direction = direction;
		}
	}
	
	static void fishing(int col) { // 상어  낚시
		Shark shark = new Shark(R+1, C+1, 0, 0, 0);
		for (Shark curr : sharks) { // 상어들 중 현재 컬럼에 있는 것들
			if (curr.col != col) continue; // 다른 컬럼의 상어는 안돼
			// 가장 땅에 가까운 것으로 갱신
			shark = shark.row > curr.row ? curr : shark; 
		}
		
		total += shark.size; // 크기 누적 합
		sharks.remove(shark); // 월척이오
	}
	
	static void moveShark() { // 1초간 상어 이동
		for (int i = 0; i < sharks.size(); i++) {
			Shark curr = sharks.get(i);
			int r = curr.row;
			int c = curr.col;
			int d = curr.direction;
			
			int period = 0;
			if (d == 1 || d == 2) {
				period = 2 * (R-1);
			} else {
				period = 2 * (C-1);
			}
			
			int move = curr.velocity % period;
			
			for (int s = 0; s < move; s++) {
				if (r == 1 && d == 1) {
					d = 2;
				}
				
				if (r == R && d == 2) {
					d = 1;
				}
				
				if (c == C && d == 3) {
					d = 4;
				}
				
				if (c == 1 && d == 4) {
					d = 3;
				}
				// 이동
				r += moves[d][0];
				c += moves[d][1];
			}
			// 이동 완료 후 좌표 수정
			curr.setRow(r);
			curr.setCol(c);
			curr.setDirection(d);
			sharks.set(i, curr);
		}
		
		// 이동 끝나고 먹방 시작
		Shark[][] newMap = new Shark[R+1][C+1];
		
		for (Shark curr : sharks) {
			
			int r = curr.row;
			int c = curr.col;
			
			if (newMap[r][c] == null) {
				newMap[r][c] = curr;
			} else {
				if (newMap[r][c].size < curr.size) {
					newMap[r][c] = curr;
				}
			}
		}
		
		// 리스트 재구성
		sharks.clear();
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (newMap[i][j] != null) {
					sharks.add(newMap[i][j]);
				}
			}
		}
	}
	
	

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			
			sharks.add(new Shark(r, c, s, d, z));
		}
		
		
		for (int c = 1; c <= C; c++) {
			fishing(c);
			moveShark();
		}
		
		System.out.println(total);
	}
}
import java.util.*;

class Solution {
    public int solution(String[] board) {
        int answer = -1;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++)
                if (board[i].charAt(j) == 'R') {
                    answer = bfs(board, i, j);
                }
        }
        return answer;
    }
    
    public int bfs(String[] board, int x, int y) {
        
        int n = board.length;
        int m = board[0].length();
        
        int[][] move = new int[][]{
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        
        // 누적 거리를 저장할 2차원 배열
        int[][] visited = new int[n][m];
        visited[x][y] = 1;
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            x = now[0];
            y = now[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = x;
                int ny = y;
                
                while (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx].charAt(ny) != 'D') {
                    // 가던 방향으로 계속 이동
                    nx += move[i][0];
                    ny += move[i][1];
                }
                
                // 장애물을 만나거나 맨 끝이라면 다시 뒤로
                nx -= move[i][0];
                ny -= move[i][1];
                
                // G에 도착한 경우 Stop. 그리고 누적 합 return
                if (board[nx].charAt(ny) == 'G') {
                    return visited[x][y];
                }
                
                // 방문하지 않은 곳이면 큐에 추가
                if (visited[nx][ny] == 0) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = visited[x][y] + 1;
                }
            }
        }
        return -1;
    }
}
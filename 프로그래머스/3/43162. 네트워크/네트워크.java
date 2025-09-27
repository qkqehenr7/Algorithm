import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        
        // 큐 생성
        Deque<Integer> queue = new ArrayDeque<>();
        // 방문 처리
        int[] visited = new int[n];
        // 네트워크 수 카운트
        int cnt = 0;
        
        
        // 묶이지 않은 네트워크 탐색
        for (int i = 0; i < n; i++){
            if (visited[i] == 0){
                // 새로운 네트워크 발견 시 cnt + 1
                cnt += 1;
                // 노드 초기화
                for (int j = 0; j < n; j++){
                    if (computers[i][j] == 1 && visited[j] == 0){
                        queue.offer(j);
                    }
                }
                
                // 탐색 시작 
                while(!queue.isEmpty()){
                    // 현재 탐색 노드
                    int node = queue.poll();
                    // 방문 처리
                    visited[node] = 1;
                    // 연결된 다른 노드 중 아직 방문하지 않은 노드 탐색
                    for (int j = 0; j < n; j++){
                        if (computers[node][j] == 1 && visited[j] == 0){
                            queue.offer(j);
                        }
                    }
                }
                
            }
        }
        
        return cnt;
    }
    
}
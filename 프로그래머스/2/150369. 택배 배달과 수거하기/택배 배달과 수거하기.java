import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        // 배달 및 수거 이동거리
        int distD = 0;
        int distP = 0;
        
        // 스택 초기화: 각 배달, 수거만큼 번호가 들어간다.
        Stack<Integer> stackD = new Stack<>();
        Stack<Integer> stackP = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            // 집에 배달 수량이 있는 경우
            if (deliveries[i] != 0) {
                stackD.push(i); // 집의 인덱스를 stack에 push
            }
            // 집에 수거 수량이 있는 경우
            if (pickups[i] != 0) {
                stackP.push(i); // 집의 인덱스를 push
            }
        }
        
        // 배달 및 수거 가능 횟수 초기화
        int capD = cap;
        int capP = cap;
        
        // 두 스택이 모두 빌 때까지(수거나 배달을 모두 완료할 때까지) 반복
        while (!stackD.isEmpty() ||!stackP.isEmpty()) {
            
            // 이번에 방문하는 집까지의 거리로 거리 갱신
            if (!stackD.isEmpty()) {
                distD = stackD.peek() + 1;
            }
            
            // 배달이 남아있다면
            while (!stackD.isEmpty()) {
                int currentD = stackD.pop();
                
                // 이번 집에 배달해도 수량이 남는 경우
                if (capD - deliveries[currentD] > 0) {
                    // 배달 가능 수량을 차감
                    capD -= deliveries[currentD];
                    continue; // 아직 배달이 더 가능하므로 다음 집으로 넘어감
                }
                // cap과 이 집의 배달 수량이 같다면
                if (capD - deliveries[currentD] == 0) {
                    capD = 0; // 더 이상 배달 불가. 창고로 돌아감
                    break;
                }
                
                // cap보다 배달해야할 수량이 더 많은 경우,
                // 배달한 수량만큼 집의 남은 배달 차감
                deliveries[currentD] -= capD;
                // 배달이 아직 남았으니 다시 스택에 넣기
                stackD.push(currentD);
                // 현재 배달 가능 수량은 0
                capD = 0;
                break;                
            }
            
            if (!stackP.isEmpty()) {
                distP = stackP.peek() + 1;
            }
            
            while (!stackP.isEmpty()){
                int currentP = stackP.pop();
                // 이번 집을 수거해도 여유가 있는 경우
                if(capP - pickups[currentP] > 0) {
                    // 수거 가능 수량 차감
                    capP -= pickups[currentP];
                    continue; // 다음 집으로
                }
                // 수거 수량과 capP가 정확히 일치하면
                if (capP - pickups[currentP] == 0) {
                    capP = 0;
                    break; // 더 이상 수거 불가. 창고로 돌아가기
                }
                
                // 이번에 모두 수거할 수 없는 경우
                pickups[currentP] -= capP;
                stackP.push(currentP); // 다시 와야하므로 stack에 push
                capP = 0; // 수거 가능 수량 0
                break; // 창고로 돌아가기
            }
            // 이번 반복에서 더 긴 거리로 왕복 거리 누적
            answer += 2 * Math.max(distD, distP);
            // 거리 초기화
            distD = 0;
            distP = 0;
            // 배달 및 수거 가능 수량 초기화
            capD = cap;
            capP = cap;
        }
        return answer;
    }
}
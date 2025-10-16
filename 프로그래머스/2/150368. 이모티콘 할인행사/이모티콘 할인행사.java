import java.util.*;

class Solution {
    
    int[] arr = new int[]{40, 30, 20, 10};
    int n;
    int m;
    // 가능한 이모티콘 판매액의 모든 조합
    List<int[]> combinations = new ArrayList<>();
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        n = users.length;
        m = emoticons.length;
        
        // 조합을 임시로 저장하는 배열
        int[] comb = new int[m];
        // 모든 조합 저장
        combination(comb, 0);
                
        // 이제 모든 조합들에 대해 판매량 계산
        for (int i = 0; i < combinations.size(); i++) {
            int[] current = combinations.get(i);
            
            int plus = 0; // 조합에 대한 플러스 가입자 수
            int purchaseSum = 0; // 조합에 대한 이모티콘 매출액  
        
            // 이모티콘 구입 여부 검증
            for (int k = 0; k < n; k++) {
                int purchase = 0;
                int discountLimit = users[k][0];
                int priceLimit = users[k][1];
                    
                for (int j = 0; j < m; j++) {
                    // 사용자 기준보다 할인율이 큰 경우
                    if (current[j] >= discountLimit) {
                        purchase += (100 - current[j]) * emoticons[j] / 100; // 누적합 계산
                    }
                }
                // 만약 누적합이 기준 가격을 넘으면
                if (purchase >= priceLimit) {
                    plus++; // 이모티콘 플러스 추가
                } else{
                    // 넘지 않으면 매출액에 반영
                    purchaseSum += purchase;       
                }
            }
            
            // 매출액 갱신: 플러스가 더 많은 경우
            if (answer[0] < plus) {
                answer[0] = plus;
                answer[1] = (int)purchaseSum;
                
            // 플러스는 같지만 매출액이 더 큰 경우 갱신
            } else if ((answer[0] == plus) && (answer[1] < purchaseSum)) {
                answer[1] = (int)purchaseSum;
            }
         }
        return answer;
    }
        
    void combination(int[] comb, int idx){
        
        // 모든 숫자를 뽑은 경우
        if (idx == m) {
            combinations.add(comb.clone());
            return;
        }
        
        for (int i = 0; i< 4; i++){
            comb[idx] = arr[i]; // 할인율 저장
            combination(comb, idx + 1);
        }
    }
}
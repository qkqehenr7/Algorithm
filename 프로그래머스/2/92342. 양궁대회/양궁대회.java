import java.util.*;

class Solution {
    
    int maxScore = -1;
    int[] answer = new int[11];
    
    public int[] solution(int n, int[] info) {
        // 1. 점수 별 화살 개수 결정
        int[] arrows = new int[11]; // 라이언의 info
        backtrack(arrows, 0, n, 0, info);
        
        // 라이언이 이기는 경우가 없을 수 있으므로 분기처리
        if (maxScore > 0) {
            return answer;
        } else {
            return new int[] {-1};
        }
    }
    
    // 백트래킹으로 결정: 결과배열, 사용 화살 수, 사용 가능 화살 수, 현재 과녁 인덱스
    void backtrack(int[] arrows, int cnt, int n, int idx, int[] info) {
        // 화살을 다 사용한 경우 누적합 게산
        if (idx == 11) {
            // 남은 화살은 0점에 몰아주기
            arrows[10] = n - cnt;
            
            int totalLion = 0;
            int totalPeach = 0;
            
            // 배열의 값이 음수인 경우만 
            for (int i = 0; i <= 10; i++) {
                if (info[i] - arrows[i] < 0) {
                    totalLion += (10 - i);
                // 아래 조건을 추가하지 않으면 둘다 화살을 맞추지 않아도 어피치가 점수 획득
                } else if (info[i] > 0) {
                    totalPeach += (10 - i);
                }
            }
            
            int diff = totalLion - totalPeach;
            
            if (diff > maxScore) {
                maxScore = diff;
                answer = arrows.clone();
                // 기존 최대 점수와 같은 경우 우선순위 비교
            } else if (diff > 0 && maxScore == diff) {
                // arrows가 더 낮은 점수를 많이 맞춘 경우
                if (isBetter(answer, arrows)) {
                    answer = arrows.clone();
                }
            }
            arrows[10] = 0;
            return;
        }     
        
        // 1. 어피치보다 1점 높게 쏘거나
        if (n - cnt > info[idx]) {
            arrows[idx] = info[idx] + 1;
            backtrack(arrows, cnt + arrows[idx], n, idx + 1, info);
            arrows[idx] = 0; // 복원
        }
        // 2. 안 쏘거나 둘 중 하나임
        arrows[idx] = 0;
        backtrack(arrows, cnt, n, idx + 1, info);
    }
    
    boolean isBetter(int[] arr, int[] currentArr) {
        
        for (int i = 0; i <= 10; i++) {
            // 새로운 조합이 가장 낮은 점수를 더 많이 맞춘 경우
            if (arr[10 - i] < currentArr[10 - i]) {
                return true;
                // 기존 조합이 가장 낮은 점수를 더 많이 맞춘 경우
            } else if (arr[10 - i] > currentArr[10 - i]){
                return false;
            } // 같은 경우, 다음 수를 비교
        }
        return false;
    }
}
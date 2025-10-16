import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        // 큐의 길이
        int n = queue1.length;
        
        // 모든 원소를 모을 배열 생성: 길이는 모든 원소 개수의 2배
        int[] numbers = new int[4 * n];
       
        // 모든 원소의 합
        long total = 0;
        long total1 = 0;
        long total2 = 0;
        
        // 큐 두 개를 붙인 정수 배열을 한 번 더 반복해서 붙임 > 양방향 큐
        // 배열 초기화
        for (int i = 0; i < n; i++) {
            numbers[i] = queue1[i];
            numbers[i + n] = queue2[i];
            numbers[i + 2 * n] = queue1[i];
            numbers[i + 3 * n] = queue2[i];
            // 총합 계산
            total1 += queue1[i];
            total2 += queue2[i];
            total = total1 + total2;
        }
        
        // 조기 중단 조건: 총합이 홀수
        if (total % 2 == 1) return -1;
        
        int limit = n * 4; // 이동 제한: 이 이상으로 이동하면 원점임
        int cnt = 0; // 작업 횟수
        long sum = total1; // queue1의 합으로 초기화
        long target = total / 2; // 목표 값
        
        // 투 포인터 초기화
        int st = 0; // queue1의 첫 원소
        int en = n; // queue2의 첫 원소
        
        // 최대 작업 횟수까지 반복
        while(cnt <= limit && st < limit && en < limit) {
            // 타겟 만족 시 종료    
            if (sum == target) {
                return cnt;
            }
            // 합이 타겟보다 크다면
            if (sum > target) {
                // 큐의 원소를 빼서 다른 큐에 넣는다.
                sum -= numbers[st];
                st++;
            } else {
                // 합이 타겟보다 작다면 다른 큐의 원소를 빼서 큐에 넣는다.
                sum += numbers[en];
                en++;
            }
            cnt++;
        }
        // 최대 작업 횟수를 넘었을 경우
        return -1;
    }
}
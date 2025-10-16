// 누적 주차시간에 따라 요금 부과
// 단위 시간 나누어 떨어지지 않는 경우 올림
// 기본시간 기본요금 단위시간 단위요금
import java.util.*;
import java.math.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        HashMap<Integer, int[]> map = new HashMap<>();
        
        // 1. 각 차량의 누적 주차시간 계산
        for (String record : records) {
            String[] str = record.split(" ");
            String[] hhmm = str[0].split(":");
            // 시간 차로 계산
            // 시간 저장할 때, IN이면 음수로 저장.
            int time = Integer.parseInt(hhmm[0]) * 60 + Integer.parseInt(hhmm[1]);
            int number = Integer.parseInt(str[1]);
            // System.out.println(time);
            int[] current = map.getOrDefault(number, new int[]{0,0});
            // current[1]가 홀수면 출차하지 않은 차량
            if (str[2].equals("IN")) {
                map.put(number, new int[]{current[0] - time, current[1] + 1});
            } else {
                map.put(number, new int[]{current[0] + time, current[1] + 1});
            } 
        }
        
        // 2. 차량번호에 따라 오름차순 정렬
        List<Integer> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet);
        
        int len = keySet.size();
        int[] answer = new int[len];
        
        // 3. 주차 시간에 따른 요금 계산
        for (int i = 0; i < len; i++) {
            int[] temp = map.get(keySet.get(i));
            // 누적시간
            int totalTime = 0;
            // 출차 내역이 없는 경우
            if (temp[1] % 2 == 1) {
                totalTime = (23 * 60 + 59 + temp[0]);
            } else {
                totalTime = temp[0];
            }
            // 기본시간 이하라면 기본요금 청구
            if (totalTime <= fees[0] ) {
                answer[i] = fees[1];
            } else {
                // 아니라면 기본요금 + 초과시간에 대한 추가요금 청구
                answer[i] = fees[1] + (int)(Math.ceil((double)(totalTime - fees[0]) / fees[2])) * fees[3];
            } 
        }
        
        return answer;
    }
}
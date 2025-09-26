import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> hm = new HashMap<>();
        
        int answer = 1;
        
        // 의상의 종류를 Hashmap에 넣기
        for (String[] cloth : clothes) {
            hm.put(cloth[1], hm.getOrDefault(cloth[1], 0) + 1);
        }
        // System.out.println(hm);
        // 모든 경우의 수 세기
        for (String type : hm.keySet()) {
            answer *= hm.get(type) + 1;
        }
        
        return answer - 1;
    }
}
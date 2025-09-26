import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        HashMap<String, Integer> map = new HashMap<>();
        
        // 참가자 리스트 저장
        for (String person : participant) {
            map.put(person, map.getOrDefault(person, 0) + 1);
        }
        
        // 완주자 리스트 순회 및 제거
        for (String person : completion) {
            map.put(person, map.get(person) - 1);
        }
        
        // 남은 참가자 return
        for (String person : map.keySet()) {
            if (map.get(person) > 0) {
                return person;
            }
        }
        return null;
    }
}
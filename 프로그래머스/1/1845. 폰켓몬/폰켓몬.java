import java.util.*;
import java.math.*;

class Solution {
    public int solution(int[] nums) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        // 포켓몬 해시맵에 넣기
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        
        return Math.min(hm.size(), nums.length / 2);
        
    }
}
import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long st = 0;
        long end = times[times.length - 1] * (long)n;
        
        while (st <= end) {
            long mid = (st + end)/2;
            long total = 0;
            
            for (int i = 0; i < times.length; i++){
                total += mid / times[i];
            }
            
            if (total >= n) {
                end = mid - 1;
                answer = mid;
            } else {
                st = mid + 1;
            }
        }
        
        return answer;
        
    }
}
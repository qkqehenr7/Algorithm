import java.util.*;
class Solution {
    
    static List<int[]> arr = new ArrayList<>();
    
    public int[][] solution(int n) {
        hanoi(n, 1, 2, 3);
        int[][] answer = arr.stream().toArray(int[][]::new);
        return answer;
    }
    
    public void hanoi(int n, int start, int mid, int end) {
        if (n == 0) {
            return;
        }
        hanoi(n-1, start, end, mid);
        arr.add(new int[]{start, end});
        hanoi(n-1, mid, start, end);
    }
}

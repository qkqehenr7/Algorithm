import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String, Integer> hm = new HashMap<>();
        
        int n = friends.length;
        
        // 해시맵 초기화
        for (int i = 0; i < n; i++) {
            hm.put(friends[i], i);
        }
        
        // 선물내역을 기록하는 2차원 배열
        int[][] map = new int[n][n];
        
        // 선물내역 읽기
        for (String gift : gifts) {
            // gift는 공백으로 구분됨. A = gift[0], B = gift[1]
            // 선물개수 + 1
            String[] name = gift.split(" ");
            map[hm.get(name[0])][hm.get(name[1])]++;
        }
        
        int[] pt = new int[n];
        // 다시 map을 읽으며 선물 지수를 기록하자.
        for (int i = 0; i < n; i++) {
            // 준건 더하고(내가 A인 배열의 원소)
            for (int j = 0; j < n; j++) {
                pt[i] += map[i][j]; // 더하고
                pt[i] -= map[j][i]; // 빼고
            }
        }
        
        // 각 친구들이 받는 선물의 개수 기록
        int[] answer = new int[n];
        //
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                if (i == j) {
                    continue;
                }
                
                // 선물을 더 많이 보냈는가? Yes.
                if (map[i][j] > map[j][i]) {
                    answer[i] ++;
                    continue;
                }
                
                if (map[i][j] == map[j][i]) {
                    // 선물 지수가 더 큰가? Yes
                    if (pt[i] > pt[j]) {
                        answer[i] ++;
                        continue;
                    }
                }
            }
        }
        Arrays.sort(answer);
        return answer[answer.length - 1];
    }

    
}
import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        
        HashMap<Integer, Integer> out = new HashMap<>();
        HashMap<Integer, Integer> in = new HashMap<>();
        
        int[] answer = new int[4];
        
        for (int[] edge : edges) {
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
            in.put(edge[1], out.getOrDefault(edge[1], 0) + 1);
        }
        
        for (int node : out.keySet()) {
            // 나가는 간선이 2개 이상이면서
            if (out.get(node) > 1) {
                // 들어오는 간선이 없다면 생성 노드
                if (!in.containsKey(node)) {
                    answer[0] = node;
                // 있다면 8자 그래프의 노드
                } else {
                    answer[3] ++;
                }
            }
        }
        
        // 들어오는 간선이 있는 노드 중 나가는 간선이 없으면 막대의 노드
        for (int node : in.keySet()) {
            if (!out.containsKey(node)) {
                answer[2] ++;
            }
        }
        
        answer[1] = out.get(answer[0]) - answer[2] - answer[3];
        
        return answer;
    }
}
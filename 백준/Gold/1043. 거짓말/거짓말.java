import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        int result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        // 만약 인원 수가 0이라면 그냥 파티 수 출력하고 종료
        if (cnt == 0) {
            System.out.println(m);
            return;
        }
        // 진실 노드를 체크하는 배열 (방문 여부와 기능적으로 같음)
        boolean[] truth = new boolean[n+1];

        // 0명이 아니라면,
        // 처음 진실 노드를 넣을 큐 생성
        Queue<Integer> queue = new ArrayDeque<>();
        while (st.hasMoreTokens()) {
            // 진실 노드를 저장
            int truthNode = Integer.parseInt(st.nextToken());
            queue.offer(truthNode);
            // 배열에 진실 노드 체크
            truth[truthNode] = true;
        }

        // 인접 리스트
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        // 인접 리스트 초기화
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 파티 수만큼 반복 입력
        // 첫 번째 수는 필요없는 것 같고, 두 번째부터 저장해보자.

        // 파티들을 저장할 리스트 (전체 파티)
        List<List<Integer>> parties = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            // 각 파티 리스트 생성
            List<Integer> partyMembers = new ArrayList<>();
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            st1.nextToken(); // 첫 번째는 버리고
            while (st1.hasMoreTokens()) {
                // 파티 멤버를 리스트에 추가
                partyMembers.add(Integer.parseInt(st1.nextToken()));
            }
            // 전체 파티 리스트에 이번 파티 리스트 추가
            parties.add(partyMembers);

            // 인접 리스트 저장하기
            for (int j = 0; j < partyMembers.size(); j++) {
                for (int k = j + 1; k < partyMembers.size(); k++) {
                    int p1 = partyMembers.get(j);
                    int p2 = partyMembers.get(k);
                    // 양방향 연결
                    graph[p1].add(p2);
                    graph[p2].add(p1);
                }

            }
        }
        // 큐 순회
        while (!queue.isEmpty()) {
            // 큐에서 진실 노드를 하나 꺼냄
            int next = queue.poll();
            // 진실 노드와 연결된 노드 순회
            for (int node : graph[next]) {
                // 연결된 노드가 진실 노드가 아니라면
                if (!truth[node]) {
                    truth[node] = true;
                    queue.offer(node); // 큐에 추가
                }
            }
        }

        // 이제 전체 파티를 순회하면서, 각 파티에 진실 노드가 있는지 체크
        for (List<Integer> party : parties) {
            // 파티의 각 멤버가 진실 노드인지 체크
            boolean check = false; // false 로 초기화
            for (int member : party) {
                // 진실 노드가 하나라도 있다면
                if (truth[member]) {
                    check = true; // 이 파티는 진실 멤버가 있다고 표시
                    break; // 반복문 탈출
                }
            }
            if (!check) {
                result ++; // 이번 파티에서 모두 진실을 모르면 ++
            }
        }
        System.out.println(result);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    // 최종 결과
    static int result;
    // 상하좌우 이동 좌표
    static int[][] move = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 빙산 지도
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 계산 메서드
        cal();
        System.out.println(result);

    }

    private static void cal() {
        // 서브맵
        int[][] subMap;
        // 덩어리 수 (매년 초기화)
        int cnt;
        // 경과 햇수
        int year = 0;
        while (true) {
            year ++;
            // 서브맵 초기화
            subMap = new int[n][m];
            // 방문 여부 초기화
            visited = new boolean[n][m];
            // 덩어리 초기화
            cnt = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] > 0) {
                        int zeroCnt = 0;
                        for (int k = 0; k < 4; k++) {
                            int row = i + move[k][0];
                            int col = j + move[k][1];
                            if (row < 0 || row >= n || col < 0 || col >= m) continue;
                            if (map[row][col] == 0) zeroCnt++;
                        }
                        int height = map[i][j] - zeroCnt;
                        subMap[i][j] = Math.max(height, 0);

                    }
                }
            }
            // 지도 업데이트
            map = subMap;

            // 덩어리 카운팅
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // 방문하지 않은 빙산이면
                    if (map[i][j] > 0 && !visited[i][j]) {
                        bfs(i,j);
                        cnt++;
                    }
                }
            }
            // 종료 조건 1: 빙산이 2개 이상인 경우
            if (cnt >= 2) {
                result = year;
                break;
            }
            // 종료 조건 2: 빙산이 없는 경우
            if (cnt == 0) {
                result = 0;
                break;
            }
        }
    }
    private static void bfs(int row, int col){
        Queue<int[]> queue = new ArrayDeque<>();
        visited[row][col] = true; // 방문 처리
        queue.offer(new int[]{row, col}); // 시작점 큐에 넣기

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            int[] pos = queue.poll(); // 현재 빙산의 좌표 꺼내기
            int r = pos[0];
            int c = pos[1];
            // 상하좌우 좌표 생성
            for (int i = 0; i < 4; i++) {
                int dr = r + move[i][0];
                int dc = c + move[i][1];
                // 지도 넘어가지 않는지 확인
                if (dr < 0 || dr >= n || dc < 0 || dc >= m) continue;
                // 방문하지 않은 빙산을 큐에 추가
                if (map[dr][dc] > 0 && !visited[dr][dc]) {
                    visited[dr][dc] = true; // 방문 처리
                    queue.offer(new int[]{dr, dc});
                }
            }
        }
    }
}
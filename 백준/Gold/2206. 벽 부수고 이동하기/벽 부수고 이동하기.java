import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int[][] map;
    static int[][][] visited;
    static int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nums = br.readLine().split(" ");
        n = Integer.parseInt(nums[0]);
        m = Integer.parseInt(nums[1]);

        map = new int[n][m];
        visited = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            nums = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(nums[j]);
            }
        }
        br.close();
        System.out.println(bfs());
    }

    private static int bfs() {

        Queue<int[]> queue = new ArrayDeque<>();
        // 첫 시작에는 벽을 부시지 않은 상태
        // 초기값 큐에 넣기
        queue.add(new int[]{0, 0, 0});
        // 우선 시작점은 방문 처리
        visited[0][0][0] = 1;
        int row;
        int col;
        int wallBreak; // 0: 아직 부수기 사용가능, 1: 부수기 이미 사용

        while (!queue.isEmpty()) {
            // 큐에서 하나 꺼내자
            int[] nums = queue.poll();
            row = nums[0];
            col = nums[1];
            wallBreak = nums[2];

            // 마지막 지점에 도착했다면
            // 도착했다는 것은 갈 수 있다는 뜻
            if (row == n - 1 && col == m - 1) {
                // 부시지 않고 이동이 불가능하다면, 부셔서 이동한 거리 return
                if (visited[row][col][0] == 0){
                    return visited[row][col][1];
                // 부실 벽이 아예 없으면 이런 경우가 생김
                } else if (visited[row][col][1] == 0) {
                    return visited[row][col][0];
                }
                // 두 가지 경우 모두 도착이 가능하면, 최솟값을 return
                return Math.min(visited[row][col][0], visited[row][col][1]);
            }

            // 상하좌우
            for (int i = 0; i < 4; i++) {
                // 상,하,좌,우 중 하나로 이동한 좌표
                int nextRow = row + moves[i][0];
                int nextCol = col + moves[i][1];

                // 지도 밖으로 나가면 일단 아웃
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) {
                    continue;
                }

                // 이미 방문한 곳이라면 아웃
                if (visited[nextRow][nextCol][wallBreak] != 0) {
                    continue;
                }

                // 분기를 묶어보면서 단순화하자.
                // 벽을 만났는데,
                if (map[nextRow][nextCol] == 1) {
                    // 부수기를 이미 사용한 경우
                    if (wallBreak == 1){
                        continue;
                    // 부수기를 쓸 수 있는데 아직 방문하지 않은 칸인 경우
                    }else if (visited[nextRow][nextCol][1] == 0){
                        // 큐에 다음 좌표를 넣으면서 부수기 금지
                        queue.offer(new int[]{nextRow, nextCol, 1});
                        // 원래 부수기 가능 세계에서의 최단거리 값을 불가능 세계로 가져오자.
                        visited[nextRow][nextCol][1] = visited[row][col][0] + 1;
                    } else continue;

                }

                // 이동할 수 있는 곳인데, 이미 방문은 안한걸로 검증되었으니 이동하자.
                if (map[nextRow][nextCol] == 0) {
                    // 다음 칸의 값을 큐에 넣어준다.
                    queue.offer(new int[]{nextRow, nextCol, wallBreak});
                    // 방문 처리
                    visited[nextRow][nextCol][wallBreak] = visited[row][col][wallBreak] + 1;
                }
            }
        }
        // 목적지에 도착할 수 없는 경우
        return -1;
    }
}
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        recursion(n, r, c, 0);
    }

    // 재귀적으로 사각형을 분할하는 메서드
    private static void recursion(int n, int row, int col, int start) {
        // 변의 길이가 2가 되면, 방문 순서 출력
        if (n == 1) {
            if (row % 2 == 0 && col % 2 == 0) {
                // 1번째
                System.out.println(start);
            } else if (row % 2 == 0 && col % 2 == 1) {
                // 2번째
                System.out.println(start + 1);
            } else if (row % 2 == 1 && col % 2 == 0) {
                // 3번째
                System.out.println(start + 2);
            } else if (row % 2 == 1 && col % 2 == 1) {
                // 4번째
                System.out.println(start + 3);
            }
        }
        // 변의 길이가 4 이상인 경우, 분할
        else {
            // 작은 사각형 한 변의 길이:
            int length = (int) Math.pow(2, n-1);
            // 작은 사각형의 넓이
            int size = length * length;
            // 가로 세로 길이에 따라 사각형 선택
            if (row < length && col < length) { // 1번째 칸
                recursion(n - 1, row, col, start);
            } else if (row < length) {
                recursion(n - 1, row, col - length, size + start); // 2번째 칸
            } else if (col < length) {
                recursion(n - 1, row - length, col, 2 * size + start); // 3번째 칸
            } else {
                recursion(n - 1, row - length, col - length, 3 * size + start); // 4번째 칸
            }
        }
    }
}
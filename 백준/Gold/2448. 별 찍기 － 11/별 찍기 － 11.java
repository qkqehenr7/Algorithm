import java.io.*;
import java.util.*;

public class Main {

    static char[][] stars;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        stars = new char[n][2 * n - 1];

        // 공백으로 초기화
        for (int i = 0; i < n; i++) {
            Arrays.fill(stars[i], ' ');
        }

        star(n,0, n - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(stars[i]);
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void star(int n, int row, int col) {

        if (n == 3) {
            stars[row][col] = '*';
            stars[row + 1][col - 1] = '*';
            stars[row + 1][col + 1] = '*';
            stars[row + 2][col - 2] = '*';
            stars[row + 2][col - 1] = '*';
            stars[row + 2][col] = '*';
            stars[row + 2][col + 1] = '*';
            stars[row + 2][col + 2] = '*';
            return;
        }

        int half = n / 2;
        star(half, row, col);
        star(half, row + half, col - half);
        star(half, row + half, col + half);
    }
}
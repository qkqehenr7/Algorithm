import java.io.*;

public class Main {

    static int n;
    static int[] arr;
    static int cnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];

        nQueen(0);
        System.out.println(cnt);


    }

    private static void nQueen(int col) {
        if (col == n) {
            cnt ++;
            return;
        }

        for (int i = 0; i < n; i++) {
            arr[col] = i;

            if (check(col)) {
                nQueen(col + 1);
            }
        }
    }

    private static boolean check(int col) {
        for (int i = 0; i < col; i++) {
            if (arr[col] == arr[i]) {
                return false;
            }

            else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }
        }
        return true;
    }
}
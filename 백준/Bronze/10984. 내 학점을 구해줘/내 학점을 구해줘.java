import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int[] totalC = new int[t];
		double[] totalG = new double[t];
		String[] str;
		
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			for (int j = 0; j < n; j++) {
				str = br.readLine().split(" ");
				totalC[i] += Integer.parseInt(str[0]);
				totalG[i] += Double.parseDouble(str[1]) * Integer.parseInt(str[0]);
			}
			totalG[i] = (totalG[i] == 0) ? 0 : (1.0) * totalG[i] / totalC[i]; 
		}
		
		for (int i = 0; i < t; i++) {
			System.out.printf("%d %.1f \n", totalC[i], totalG[i]);
		}

	}
}
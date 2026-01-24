import java.io.*;

public class Main {
	
	static long c;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nums = br.readLine().split(" ");
		long a = Long.parseLong(nums[0]);
		long b = Long.parseLong(nums[1]);
		c = Long.parseLong(nums[2]);
		
		System.out.println(divConq(a, b));
		
	}
	
	static long divConq(long a, long exp) {
		// 지수가 1인 경우 그냥 출력
		if (exp == 1) {
			return a % c;
		}
		// 이전 단계의 계산을 재귀 호출
		long res = divConq(a, exp / 2);
		res = (res * res) % c;
		// 지수가 홀수인 경우, 이전 단계 결과를 제곱하고 a를 한번 더 곱한다.
		if (exp % 2 == 1) {
			res = (res * a) % c;
		}		
		return res;
	}
}
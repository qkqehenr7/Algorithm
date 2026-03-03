import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int L;
	static int[] leds;
	
	static int stuNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		L = Integer.parseInt(br.readLine());
		leds = new int[L+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i =1; i<=L; i++) {
			leds[i] = Integer.parseInt(st.nextToken());
		}
		
		stuNum = Integer.parseInt(br.readLine());
		
		for(int i=0; i<stuNum; i++) {
			st = new StringTokenizer(br.readLine());
			int stu = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if(stu == 1) {
				toggleM(num);
			}else {
				toggleF(num);
			}
		}
		for(int i=1; i<=L; i++) {
			System.out.print(leds[i] + " ");
			if(i%20 == 0) System.out.println();
		}
		
	}
	
	static void toggleM(int num) {
		int newNum = num;
		while(newNum <= L) {
			leds[newNum] = (leds[newNum]+1)%2;
			newNum += num;
		}
	}
	
	static void toggleF(int num) {
		int n = 0;
		while(1<=num-n && num+n<=L && leds[num+n] == leds[num-n]) {
			n++;
		}
		
		leds[num] = (leds[num]+1)%2;
		for(int i =1; i<n; i++) {
			leds[num+i] = (leds[num+i]+1)%2;
			leds[num-i] = (leds[num-i]+1)%2;
		}
	}

}

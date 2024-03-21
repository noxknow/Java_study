import java.util.*;
import java.math.*;
public class Main {
	
	static int dp[],cost[] ;
	
	public static void main(String[] args)   {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		dp = new int[n+1];
		cost = new int[n+1];
        
		for(int i=1;i<=n;i++) {
			cost[i] = sc.nextInt();
		}
		
		dp[1] = cost[1];
		
		for(int i=2;i<=n;i++) { 
			dp[i] = cost[i];
			for(int j=1;j<i;j++) { 
				if(cost[i]>cost[j]) {
					dp[i] = Math.max(dp[j]+cost[i],dp[i]);
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for(int i=1;i<=n;i++) {
			if(dp[i]>max) {
				max = dp[i];
			}
		}
		System.out.println(max);
	}
	
}

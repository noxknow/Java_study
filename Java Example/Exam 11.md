```java
public class Exam11 {

	public static void main(String[] args) {
		int[][] score = {
				{100,100,100},
				{20,20,20},
				{30,30,30},
				{40,40,40},
				{50,50,50}	
		};
		
		int[][] res = new int[score.length+1][score[0].length+1];
		
		for(int i=0; i<score.length; i++) {
			for(int j=0; j<score[i].length; j++) {
				res[i][j] = score[i][j];
				res[i][res[i].length-1] += score[i][j];
				res[res.length-1][j] += score[i][j];
				res[res.length-1][res[i].length-1] += score[i][j]; 
			}
		}
		
		for(int i=0; i<res.length; i++) {
			for(int j=0; j<res[i].length; j++) {
				System.out.printf("%4d", res[i][j]);
			}
			System.out.println();
		}
	}
}
```

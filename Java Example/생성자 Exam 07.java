public class Mypoint {
	int x;
	int y;
	
	Mypoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	double getDistance(int x1, int y1) {
		return Math.sqrt((Math.pow(x1-x,2) + Math.pow(y1-y,2))); // 위에 있는 값에서 x,y를 받아오는 경우
	}
	
}

// 다른 클래스

public class Exam07 {

	public static void main(String[] args) {
		Mypoint p = new Mypoint(1,1);
		
		System.out.println(p.getDistance(2,2));

	}

}

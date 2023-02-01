public class Student {
	String name;
	int ban;
	int no;
	int kor;
	int eng;
	int math;
	
	public Student() {}
	
	public Student(String name, int ban, int no, int kor, int eng, int math) {
		this.name = name;
		this.ban = ban;
		this.no = no;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	public int getTotal() {
		return kor+eng+math;
	}
	
	public float getAverage() {
		return (kor+eng+math) / (float)3;
	}
	
	
	public String info() {
		return name + "," + ban + "," + no + "," + kor + "," + eng + "," + math;
	}
}

// 다른 클래스

public class Exam04 {

	public static void main(String[] args) {
		Student s = new Student();
		s.name = "홍길동";
		s.ban = 1;
		s.no = 1;
		s.kor = 100;
		s.eng = 60;
		s.math = 76;
		
		System.out.println("이름 : " + s.name);
		System.out.println("총점 : " + s.getTotal());
		System.out.println("평균 : " + String.format("%.1f", s.getAverage())); // 포맷팅 방식
		
		Student c = new Student("홍길동", 1, 1, 100, 60, 76);
		System.out.println(c.info());
	}

}

public class Exam18 {
	
		int iv = 10;
		static int cv = 20;

		int iv2 = cv;
//		static int cv2 = iv; // 라인 A 정적 변수 초기화에 인스턴스 변수 사용 불가

		static void staticMethod1() {
			System.out.println(cv);
//			System.out.println(iv); // 라인 B 정적메서드에서 인스턴스 변수 사용 불가
		}

		void instanceMethod1() { 
			System.out.println(cv); 
			System.out.println(iv);  //라인 C 
		} 

		static void staticMethod2() { 
			staticMethod1(); 
//			instanceMethod1();  //라인 D static 메서드에서는 인스턴스 메서드를 사용할 수 없다.
		} 

		void instanceMethod2() { 
			staticMethod1();  //라인 E 
			instanceMethod1(); 
		} 

}

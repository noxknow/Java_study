package sec02;

public class ServiceExample {

	public static void main(String[] args) {
		
		Service service = new ServiceImpi();
		
		service.defaultMethod1(); // 얘는 객체를 이용해서 선언함 service 소문자 객체
		System.out.println();
		service.defaultMethod2();
		System.out.println();
		
		Service.staticMethod1(); // 얘는 객체없이 선언하고 호출한 것 Service 대문자 객체가 아님
		System.out.println();
		Service.staticMethod2();
		System.out.println();
		
	}

}

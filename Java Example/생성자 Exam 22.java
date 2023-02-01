public class Exam22 {
	
	public static boolean isNumber(String str) {
		for(int i =0;i < str.length(); i++) {
			if(!Character.isDigit(str.charAt(i)) || str == null || str.equals("")) {
				return false;
			} // Character.isDigit 안쓰고 싶으면 str.charAt(i)>='0' && str.charAt(i)<='9' 
		}
		
		return true;
	}

/*
	public static boolean isNumber(String str) {
		for(int i =0;i < str.length(); i++) {
			if(Character.isDigit(str.charAt(i))) {
				continue;
			} 
			else {
				return false;
			}
		}
		
		return true;
	}
*/

	public static void main(String[] args) {
		String str = "123";
		System.out.println(str + "는 숫자 입니까? " + isNumber(str));
		
		str = "1234o";
		System.out.println(str + "는 숫자 입니까? " + isNumber(str));
	}

}

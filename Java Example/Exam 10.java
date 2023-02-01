public class Exam10 {

	public static void main(String[] args) {
		char[] abcCode = {'`','~','!','@','#','$','%','%','^','&','*','(',')','-','_','+','=','|','[',']','{','}',';',':',',','.','/'};
		char[] numCode = {'q','w','e','r','t','y','u','i','o','p'};
		
		String src = "abc123";
		String res = "";
				
		for(int i=0; i<src.length(); i++) {
			char ch = src.charAt(i);
			if(Character.isLetter(ch)) {
				res += abcCode[(int)ch-97];
			}
			else {
				res += numCode[(int)ch-48];
			}
			
		}
		System.out.println("src : " + src);
		System.out.println("res : " + res);

	}

}

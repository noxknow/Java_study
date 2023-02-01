public class SutdaCard {
	int num;
	boolean isKwang;
	
	public SutdaCard() {
		this.num = 1;
		this.isKwang = true;
	}
	
	public SutdaCard(int num, boolean isKwang) {
		this.num = num;
		this.isKwang = isKwang;
	}
	
	public String info() {
		if(isKwang) {
			return num + "k";
		}
		else {
			return num + "";
		} // return num + (isKwang ? "k" : ""); 똑같다. 삼항연산자
	}
	
}

// 다른 클래스

public class Exam02 {

	public static void main(String[] args) {
		SutdaCard card1 = new SutdaCard(3, false);
		SutdaCard card2 = new SutdaCard();

		System.out.println(card1.info());
		System.out.println(card2.info());
	}

}

public class PlayingCard {
	int kind;
	int num;
	
	static int width;
	static int height;
	
	PlayingCard(int k, int n) {
		kind = k;
		num = n;
	}

	public static void main(String[] args) {
		PlayingCard card = new PlayingCard(1,1);
	}

}

/*
 * 클래스 변수 : width, height
 * 인스턴스 변수 : kind, num
 * 지역 변수 : k, n, args, card
 */

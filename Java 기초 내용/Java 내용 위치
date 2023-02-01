```java
* static, main, void 관련 내용, static이 없을 때 호출방법 (Practice -- make.java)
* 2차원 배열 출력하는 방법, 배열 복사, 배열 출력하는 방법 (Practice --> dimension2.java)
* 소문자 -> 대문자, 대문자 -> 소문자, 반환값 2개인 경우 (Practice --> change.java)
* 메서드 만들기 (Practice --> summethod.java)
* 자바 입출력 하는 방법 (Practice --> forloop.java)
* 배열의 길이와 문자열의 길이, 문자열의 몇번째 인덱스 값 출력 (Practice --> strcharat.java)
* 문자열 --> 숫자로 변환해서 연산, 진수 변환 (Practice --> ParseInt.java)
* 클래스 생성 예제, 포맷팅 방식 (Practice --> oop.java, constructor.java)
* 상속에 대한 설명, 오버라이드 (Practice --> inheritance.java)
* 추상 클래스 + 상속 클래스 설명 (Practice --> Abstract.java)
* 인터페이스 개념(유일하게 추상과 인터페이스만 작동중) (Practice --> Interface.java)
** 다형성, 배열 선언 한줄로 하는 방법, merge 알고리즘에서 나온 for문
System.out.println(); 이것이 어떤 타입, 객체를 매개변수 받더라도 에러가 없는이유가 다형성.
위의 문장의 실제 메서드의 API는 public void println(object x)로 되어 있는데
object는 가장 최상위 이므로 어떤 객체들 보다 상위 타입이 된다.
(Practice --> polymorphism.java, polyabstract.java)

* 형 변환, 최상위 타입 Object (Collections --> Generic.java)
* 제네릭 사용 방법 (Collections --> Generic_ex.java)
* printf 하는 방법, ArrayList 제네릭으로 사용하는 방법 (Collections --> ArrayList_.java)
* 2차원 배열 ArrayList (Collections --> ArrayList_2.java)
* iterator, while문 사용하는 방법, if while 쓰는 방법 (Collections --> iterator_.java)
* 랜덤 숫자 출력 (Collections --> random_.java)
   
* 포맷팅 방식 (Algorithms --> RankAlgorithm)
* for (int item : merge) 파이썬이랑 조금 다른 적용 방식 (Algorithms --> MergeAlgorithm)
* 자바 조건문 let result = condition ? value1 : value2; (삼항연산자) (Algorithms --> NearAlgorithm)
* search, sort 알고리즘들은 보기 (Algorithms)

* int max는 초기화 값을 제일 작은 값, int min은 초기화 값을 제일 큰 값

* and -> &&, or -> ||, 비트 연산자는 &, | 하나씩

* 자바는 문자 하나라면 'A' 이렇게 문자열 이라면 "age" 이런 식으로 해야한다.

* System.exit(0); --> 바로 종료해준다

* int[] index = new int[5+1]; 뒤에 new int 구문은 배열의 개수를 말해주는 의미.

* equals 메소드는 비교하고자 하는 대상의 내용 자체를 비교하지만,    (즉, String은 str1.equals("AA") 혹은 !str1.equals("AA"))
  == 연산자는 비교하고자 하는 대상의 주소값을 비교합니다            ( == 연산자는 문자(char), 정수형(int) 형)

	String a = "aaa";                                      --> 그림에서는 a, b, c 모두 같은 aaa라는 문자열 내용을 가지고 있지만, 
	String b = a; // b에 a대입 이니 b = "aaa"이다.              a, b는 500이라는 임의의 주소값을 할당 받았으며, c는 600이라는 임의의 주소값을 할당받았습니다.
	String c = new String ("aaa");

	System.out.println(a.equals(b));               --> 첫번째는 a와 b가 가지고 있는 내용을 비교하였으므로 true
	System.out.println(a==b);                          두번째는 a와 b가 가지고 있는 주소값을 비교하고 있으므로 true
	System.out.println(a==c);                          세번째는 a와 c가 가지고 있는 주소값을 비교하고 있으므로 false
	System.out.println(a.equals(c));                   네번째는 a와 c가 가지고 있는 내용을 비교하였으므로 true

	String strVar1 = "홍길동"; --> 얘네 둘을 strVar1 == strVar2를 이용해 값을 확인해보면 원래는 주소값을 참조해야해서 다르다고 나와야 하지만, 문자열 안의 내용이 같기 때문에
	String strVar2 = "홍길동";     둘은 참조가 같다고 나온다.

	String name1 = new String("홍길동"); --> 얘네 둘이 name1 == name2할 경우 객체도 다르고 주소값도 다르기 때문에 다른경우이다. 이때 .equals()하면 내용만 보기 때문에 같다고 나온다.
	String name2 = new String("홍길동");

* Character.isLetter(ch) --> 문자인지 아닌지. Character.isDigit(ch) --> 숫자인지 아닌지, Character.isLetterOrDigit(ch) --> 둘다 확인 가능

* str.charAt(i)의 경우 str = "12345" 라면 str 문자열의 i번째 인덱스를 문자형인 char형으로 바꿔준다는 의미인데 이것을 int형으로 바꾸려고 한다면 int i 를 하면 i가
	1인 경우 문자 "1"로 인식되어 아스키코드로 49의 int값을 얻는다. 그렇기 때문에 int형을 구하기 위해서는 -48해줘야 한다.
	
* String형의 경우 str.length()로 길이를 구하고, 배열은 aa.length로 길이를 구하고, 숫자의 경우 (int)( Math.log10(num)+1 )로 길이를 구한다.	

* int sum = 0; int num = 1; int cnt = 0; 이런 식으로 이어쓰던가, int sum = 0, num = 1, cnt = 0; 이런식으로 하던가 둘 중 하나

* String replace(기존 문자열, 대체 문자열)  ex) String newStr = subject.replace("자바", "java");

* indexOf() 특정 문자나 문자열이 앞에서부터 처음 발견되는 인덱스를 반환하며, 만약 찾지 못했을 경우 "-1"을 반환합니다
	lastindexOf() 는 특정 문자나 문자열이 뒤에서부터 처음 발견되는 인덱스를 반환하며, 만약 찾지 못했을 경우 "-1"을 반환합니다.
	
	ex) String indexOfTestOne = "Hello world";  indexOfTestOne.indexOf("o")  // 4
																							indexOfTestOne.lastIndexOf("o")  // 7

* substring() 메소드는 문자열의 특정 부분을 잘라내는 데 사용합니다.

	ex) String str = "Hello";    str.substring(2) // "llo",        str.substring(2, 4) // "ll"

* boolean contains(CharSequence s)  -> contains() 함수는 대상 문자열에 특정 문자열이 포함되어 있는지 확인하는 함수이다. 대/소문자를 구분한다.

	ex) String str = "my java test";   str.contains("java") // true,  str.contains(" my") // false (공백 생각하기)

* boolean 타입의 기본형은 false, int arr[5]; 하고 int[] arr = new int[5] {1,2,3,4,5}; 틀린 선언 방식.

* System.out.println(counter[i] + "*".repeat(counter[i])); 이런식으로 반복시키는것도 가능하다.

* public class StringExample { 

	  public static void main(String [] args) {
	    String name1 = new String("nroo");
	    String name2 = "nroo";
	    String name3 = "nroo";
	  } 
}

 -> name1 은 heap 메모리에 개별 객체가 만들어지고, name2와 name3은 String Constant Pool에 만들어진 하나의 객체를 참조한다. 따라서 총 2개의 String 객체가 생성된다.
```

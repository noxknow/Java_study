# Object 클래스

```java
package sec01;

public class Member {
	public String id;

	public Member(String id) {
		this.id = id;
	}
	
	public boolean equals (Object obj) {
		if(obj instanceof Member target) {
			if(id.equals(target.id)) {
				return true;
			}
		}
		return false;
	}

}

//

package sec01;

public class EqualsExample {

	public static void main(String[] args) {
		Member obj1 = new Member("blue");
	    Member obj2 = new Member("blue");
	    Member obj3 = new Member("red");
	    
	    if(obj1.equals(obj2)) {
	    	System.out.println("obj1과 obj2는 동등합니다.");
	    }
	    else {
	    	System.out.println("obj1과 obj2는 동등하지 않습니다.");
	    }
	    
	    if(obj1.equals(obj3)) {
	    	System.out.println("obj1과 obj3는 동등합니다.");
	    }
	    else {
	    	System.out.println("obj1과 obj3는 동등하지 않습니다.");
	    }
	}

}

obj1과 obj2는 동등합니다.
obj1과 obj3는 동등하지 않습니다.
```

---

## HashCode 객체

> 객체 해시코드란 객체를 식별하는 정수를 말한다. Object의 hashCode () 메소드는 객체의 메모리
번지를 이용해서 해시코드를 생성하기 때문에 객체마다 다른 정수값을 리턴한다
> 

```java
package sec01;

public class Student {
	private int no;
	private String name;
	
	public Student (int no, String name) {
		this.no = no;
		this.name = name;
	}
	
	public int getNo() { return no;}
	public String getName() {return name;}
	
	public int hashCode() {
		int hashCode = no + name.hashCode();
		System.out.println(name + "의 해쉬코드 : " + getNo());
		return hashCode;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Student target) {
			if(no == target.getNo() && name.equals(target.getName())) {
				return true;
			}
		}
		return false;
	}
}

//

package sec01;

public class HashCodeExample {

	public static void main(String[] args) {
		Student s1 = new Student(1, "홍길동");
		Student s2 = new Student(1, "홍길동");
		
		if(s1.hashCode() == s2.hashCode()) {
			if(s1.equals(s2)) {
				System.out.println("동등 객체입니다.");
			}
			else {
				System.out.println("데이터가 다르므로 동등객체가 아닙니다.");
			}
		}
		else {
			System.out.println("해시코드가 다르므로 동등 객체가 아닙니다.");
		}
	}

}

홍길동의 해쉬코드 : 1
홍길동의 해쉬코드 : 1
동등 객체입니다.
```

---

# 객체 문자 정보

> System.out.println() 메서드는 매개값이 기본 타입이거나 문자열일 경우 해당 값을 그대로 출력한다. 매개값이 객체가 되면 객체의 toString() 메소드를 호출해서 리턴값을 출력한다.
> 

# 롬복 사용하기

- 표준 라이브러리는 아니지만, 개발자들이 자주 사용하는 자동 코드 생성 라이브러리
- Getter, Setter, hashCode(), equals(), toString()를 자동으로 생성
- 필드는 final이 아니고 getter는 접두가 get 또는 is를 붙이고, setter는 접두사 set을 붙여서 메서드를 생성시켜 준다.

## 사용 설정

1)  [https://projectlombok.org/download](https://projectlombok.org/download) 롬복 다운로드

2) 명령 프롬포트를 관리자 모드로 열기

3) cd C:\Users\pc26\Documents\이치왕(풀스텍_0111)\유틸리티 (다운로드 폴더로 이동)

4) java -jar lombok.jar

5) 이클립스 하고있는 프로젝트에 lib 폴더 만들고 거기에 복사 붙여넣기 후 build path > Add to build 

---

# System 클래스

## 콘솔 출력

> 운영체제 기능을 일부 사용
> 
- out : 콘솔 출력 standard output stream
- err : 콘솔 출력 (에러 출력 용도, 빨간 글자)

```java
package sec04;

public class ErrExample {
	public static void main(String[] args) {
		try {
			int value = Integer.parseInt("1oo");
		}
		catch (NumberFormatException e) {
			System.err.println("[에러 내용]");
			System.err.println(e.getMessage());
		}
	}

}

[에러 내용]
For input string: "1oo"
```

## 키보드 입력

- in : standard input stream
- in.read() : 키보드 입력값을 정수값으로 반환

### 실행 순서

1. 콘솔창에 문자들을 입력
2. Enter key를 누르면 1번 입력 내용이 input buffer로 전송
3. 전송된 내용중 1번째 글자를 정수로 출력
4. 나머지 문자들은 buffer에 대기상태로 남아있는다.

```java
package sec04;

public class InExample {
	public static void main(String[] args) throws Exception {
		int speed = 0;
		int keycode = 0;
		
		while(true) {
			if(keycode != 13 && keycode != 10) {
				if(keycode == 49) { 
					speed++;
				}
				else if (keycode == 50) {
					speed--;
				}
				else if (keycode == 51) {
					break;
				}
				System.out.println("-----------------------");
				System.out.println("1. 증속 | 2. 감속 | 3. 중지");
				System.out.println("-----------------------");
				System.out.println("현재 속도 : " + speed);
				System.out.println("선택 : ");
			}
			keycode = System.in.read();
		}
		
		System.out.println("프로그램 종료");
	}
}

-----------------------
1. 증속 | 2. 감속 | 3. 중지
-----------------------
현재 속도 : 0
선택 : 
1212121113
-----------------------
1. 증속 | 2. 감속 | 3. 중지
-----------------------
현재 속도 : 1
선택 : 
-----------------------
1. 증속 | 2. 감속 | 3. 중지
-----------------------
현재 속도 : 0
선택 : 
-----------------------
1. 증속 | 2. 감속 | 3. 중지
-----------------------
현재 속도 : 1
선택 : 
-----------------------
1. 증속 | 2. 감속 | 3. 중지
-----------------------
현재 속도 : 0
선택 : 
-----------------------
1. 증속 | 2. 감속 | 3. 중지
-----------------------
현재 속도 : 1
선택 : 
-----------------------
1. 증속 | 2. 감속 | 3. 중지
-----------------------
현재 속도 : 0
선택 : 
-----------------------
1. 증속 | 2. 감속 | 3. 중지
-----------------------
현재 속도 : 1
선택 : 
-----------------------
1. 증속 | 2. 감속 | 3. 중지
-----------------------
현재 속도 : 2
선택 : 
-----------------------
1. 증속 | 2. 감속 | 3. 중지
-----------------------
현재 속도 : 3
선택 : 
프로그램 종료

입력을 한번에 넣어도 read는 맨 앞 1글자만 읽어서 인식하고 나머지는 버퍼에 남아있게 된다.
그래서 한번에 입력해도 다 나온다.
```

---

# 문자열 클래스

## StringBuilder 클래스

- 내부 문자열을 수정할 수 있다.
- 주요 메서드
    - StringBuilder append (문자열) : 맨 뒤에 문자열 추가
    - StringBuilder insert (위치, 문자열) : 위치에 문자열 추가
    - StringBuilder delete (시작위치, 끝 위치) : 시작 위치 ~ 끝 위치-1 의 문자열 제거
    - StringBuilder replace (시작위치, 끝 위치, 문자열) : 시작위치 ~ 끝 위치-1 의 문자열을 교체
    - String toString() : 문자열값을 리턴

```java
package sec04;

public class StringBuilderExample {

	public static void main(String[] args) {
		StringBuilder data = new StringBuilder();
		data = data.append("DEF");
		data = data.insert(0,  "ABC");
		data = data.delete(3, 4);
		
		System.out.println(data);
	}

}

ABCEF

//

package sec04;

public class StringBuilderExample {

	public static void main(String[] args) {
		StringBuilder data = new StringBuilder();
		data = data.append("DEF").insert(0,  "ABC").delete(3, 4);
		
		System.out.println(data);
	}

}

위 아래 모두 다 가능하다.

//

package sec04;

public class StringBuilderExample {

	public static void main(String[] args) {
		String data = new StringBuilder()
			.append("DEF")
			.insert(0,  "ABC")
			.delete(3, 4)
			.toString();
		
		System.out.println(data);
	}

}

// 메서드 체이닝 패턴
```

---

## StringTokenizer 클래스

- 구분자로 구분된 문자열을 분리하는 역할
- 생성자 StringTokenizer(문자열, 구분자)
- 주요 메서드
    - int countTokens() : 분리한 문자열 수
    - String nextToken() : 분리한 문자열을 하나씩 순서대로 가져옴
    - boolean hasMoreTokens() :

```java
package sec04;

import java.util.StringTokenizer;

public class StringTokenizerExample {

	public static void main(String[] args) {
		String data1 = "홍길동&이수홍,박연수";
		String[] arr = data1.split("&|,");
		for(String token : arr) {
			System.out.println(token);
		}
		System.out.println();
		
		String data2 = "홍길동/이수롱/박연수";
		StringTokenizer st = new StringTokenizer(data2, "/");
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			System.out.println(token);
		}
	}

}
```

---

# 포장 클래스

> 기본 타입의 값을 포장 객체로 만드는 과정을 `박싱` 이라고 하고. 반대로 포장 객체에서 기본 타
입의 값을 얻어내는 과정을 `언박싱`이라고 한다
> 

```java
package sec04;

public class BoxingUnBoxingExample {

	public static void main(String[] args) {
		Integer obj = 100;
		System.out.println("value : " + obj.intValue());
		
		int value = obj;
		System.out.println("value : " + value);
		
		int res = obj + 100;
		System.out.println("res : " + res);
		
		Integer obj1 = 50;
		Integer obj2 = 250;
		int res1 = obj1 + obj2;
		System.out.println("res1 : " + res1);
	}

}

value : 100
value : 100
res : 200
res1 : 300

박싱은 포장 클래스 변수에 기본 타입 값이 대입될 때 발생한다. 반대로 언박싱은 기본 타입 변수에
포장 객체가 대입될 때 발생한다
```

---

# JAVA.UTIL.RANDOM 클래스

## 생성자

- Random() : 현재시간을 종자값으로 난수 발생
- Random(long seed) : 주어진 종자값으로 난수 발생

## 메서드

- boolean nextBoolean()
- double nextDouble()
- int nextInt() : int 표현 범위 이내 난수 발생
- int nextInt(int k) : 0 ≤ n < k 인 난수 발생

```java
package sec07;

import java.util.Arrays;
import java.util.Random;

public class RandomExample {

	public static void main(String[] args) {
		int[] selectNumber = new int[6];
		Random random = new Random(System.currentTimeMillis());
		System.out.print("선택번호 : ");
		for(int i = 0; i<6; i++) {
			selectNumber[i] = random.nextInt(45) + 1;
			System.out.print(selectNumber[i] + " ");
		}
		System.out.println();
		
		int[] winningNumber = new int[6];
		random = new Random(5);
		System.out.print("당첨번호 : ");
		for(int i = 0; i<6; i++) {
			winningNumber[i] = random.nextInt(45) + 1;
			System.out.print(winningNumber[i] + " ");
		}
		System.out.println();
		
		Arrays.sort(selectNumber);
		Arrays.sort(winningNumber);
		boolean res = Arrays.equals(selectNumber,  winningNumber);
		System.out.print("당첨여부 : ");
		if(res) {
			System.out.println("1등에 당첨되셨습니다.");
		}
		else {
			System.out.println("당첨되지 않으셨습니다.");
		}
	}

}

선택번호 : 5 4 10 21 11 2 
당첨번호 : 18 38 45 15 22 36 
당첨여부 : 당첨되지 않으셨습니다.
```

---

# 날짜와 시간 클래스

## java.util.Date()

> toString()으로 얻은 결과는 영문형식이기 때문에 원하는 형식으로 변환하려면 java.text.SimpleDateFormat 클래스를 이용한다.
> 

```java
package sec07;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataExample {

	public static void main(String[] args) {
		Date now = new Date();
		String strNow1 = now.toString();
		System.out.println(strNow1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String strNow2 = sdf.format(now);
		System.out.println(strNow2);
	}

}

Thu Feb 16 15:47:47 KST 2023
2023.02.16 15:47:47
```

## Calendar 클래스

```java
package sec07;

import java.util.Calendar;

public class CalendarExample {

	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1;
		int day = now.get (Calendar.DAY_OF_MONTH);
		int week = now.get (Calendar.DAY_OF_WEEK);
		String strWeek = null;
		
		switch(week) {
			case Calendar.MONDAY: strWeek = "월"; break;
			case Calendar.TUESDAY: strWeek = "화"; break;
			case Calendar.WEDNESDAY: strWeek = "수"; break;
			case Calendar.THURSDAY: strWeek = "목"; break;
			case Calendar.FRIDAY: strWeek = "금"; break;
			case Calendar.SATURDAY: strWeek = "토"; break;
			default: strWeek = "일"; 
		}
		
		int amPm = now.get(Calendar.AM_PM);
		String strAmPm = null;
		if(amPm == Calendar.AM) {
			strAmPm = "오전";
		} else {
			strAmPm = "오후";
		}
		
		int hour = now.get(Calendar.HOUR);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
	
		System.out.print(year + "년");
		System.out.print(month + "월");
		System.out.println(day + "일");
		System.out.print(strWeek + "요일");
		System.out.println(strAmPm + " ");
		System.out.print(hour + "시");
		System.out.print(minute + "분");
		System.out.println(second + "초");
	}

}
```

---

# 형식 클래스

## DecimalFormat

> DecimalFormat df = new DecimalFormat(”#,###.0”);
> 
> 
> String res = df.format(1234567.89); // 1,234,567.9
> 

## SimpleDateFormat

> SimpleDateFormat sdf = SimpleDateFormat(”yyyy년 MM월 dd일”);
> 
> 
> String res = sdf.format(new Date());
> 

---

# 정규 표현식 클래스

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fa06bd8d-2ecb-46f4-99e4-f071d4be1d1b/Untitled.png)

```java
ex) (02|010)-\d{3,4}-\d{4}

(02|010) : 02 또는 010, - : 그대로 표시
\d{3,4} : 숫자 3 ~ 4자리, \d{4} : 숫자 4자리

ex) \w+@\w+(\.\w+)?

\w : 1개의 문자, + : 한개 이상, \w+ : 문자가 1자 이상
@ : 그대로 표시, \. : .로 표시, ? : 없음 또는 한개
(\.\w+)? : .과 1자 이상의 문자가 없거나 또는 1번
```

---

# Pattern 클래스 검증

> java.util.regex.Pattern : 정규 표현식으로 문자열을 검증한다.
> 

```java
package sec07;

import java.util.regex.Pattern;

public class PatternExample {

	public static void main(String[] args) {
		String regExp = "(02|010)-\\d{3,4}-\\d{4}";
		String data = "010-123-4567";
		boolean res = Pattern.matches(regExp, data);
		if (res) {
			System.out.println("정규식과 일치합니다.");
		}
		else {
			System.out.println("정규식과 일치하지 않습니다.");
		}
		
		regExp = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		data = "angel@mycompanycom"; // 만약 "angel@mycompany.com" 이였다면 정규식과 일치
		res = Pattern.matches(regExp, data);
		if (res) {
			System.out.println("정규식과 일치합니다.");
		}
		else {
			System.out.println("정규식과 일치하지 않습니다.");
		}

	}

}
```

---

# 어노테이션

- 컴파일 시 사용하는 정보 전달.
- 빌드 툴이 코드를 자동으로 생성할 때 사용하는 정보 전달.
- 실행 시 특정 기능을 처리할 때 사용하는 정보 전달.

```java
package sec08;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface PrintAnnotation {
	String value() default "-";
	int number() default 15;
}

//

package sec08;

public class Service {
	@PrintAnnotation
	public void method1() {
		System.out.println("실행 내용1");
	}
	
	@PrintAnnotation("*")
	public void method2() {
		System.out.println("실행 내용2");
	}
	
	@PrintAnnotation(value = "#", number = 20)
	public void method3() {
		System.out.println("실행 내용3");
	}

}

//

package sec08;

import java.lang.reflect.Method;

public class PrintAnnotationExample {

	public static void main(String[] args) throws Exception {
		Method[] declareMethods = Service.class.getDeclaredMethods();
		for(Method method : declareMethods) {
			PrintAnnotation printAnnotation = method.getAnnotation(PrintAnnotation.class);
			
			printLine(printAnnotation);
			
			method.invoke(new Service());
		}
		
	}
	
	public static void printLine(PrintAnnotation printAnnotation) {
		if(printAnnotation != null) {
			int number = printAnnotation.number();
			String value = printAnnotation.value();
			
			for(int i=0; i<number; i++) {
				System.out.print(value);
			}
			System.out.println();
		}
	}

}
```

---

# Exam

## Exam 05

```java
package Example;

import java.util.HashSet;

public class StudentExample {

	public static void main(String[] args) {
		HashSet<Student> hashSet = new HashSet<Student>();
		
		hashSet.add(new Student("1"));
		hashSet.add(new Student("1"));
		hashSet.add(new Student("2"));
		
		System.out.println("저장된 Student 수: " + hashSet.size());
	}
}

//

package Example;

public class Student {
	private String studentNum;
	
	public Student(String studentNum) {
		this.studentNum = studentNum;
	}
	
	public String getStudentNum() {
		return studentNum;
	}
	
	public int hashCode() {
		return studentNum.hashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Student student) {
			if(this.studentNum.equals(student.getStudentNum())) {
				return true;
			}
		}
		return false;
	}

}
```

## Exam 06

```java
package Example;

public class MemberExample {

	public static void main(String[] args) {
		Member member = new Member("blue", "이파란");
		System.out.println(member);
	}
}

//

package Example;

public class Member {
	private String id;
	private String name;
	
	public Member(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String toString() {
		return id + " : " + name;
	}

}
```

## Exam 08

```java
package Example;

public class Exam08 {

	public static void main(String[] args) {
		long start = System.nanoTime();
		
		int[] scores = new int[1000];
		
		for(int i=0; i<scores.length; i++) {
			scores[i] = i;
		}
		
		int sum = 0;
		for(int score : scores) {
			sum += score;
		}
		
		double avg = (double)sum / scores.length;
		System.out.println(avg);
		
		long end = System.nanoTime();
		
		System.out.println((end - start) + "ns");
	}

}

499.5
1547000ns
```

## Exam 09

```java
package Example;

public class DecodingExample {

	public static void main(String[] args) throws Exception {
		byte[] bytes = {-20, -107, -120, -21, -123, -107 };
		String str = new String(bytes, "UTF-8");
		System.out.println("str : " + str);
	}
}

str : 안녕
```

## Exam 10

```java
package Example;

public class StringBuilderExample {

	public static void main(String[] args) {
		long start = System.nanoTime();
		
		String str = "";
		
		for(int i=1; i<=100; i++) {
			str += i;
		}
		
		System.out.println(str);
		
		long end = System.nanoTime();
		
		System.out.println((end - start) + "ns");
		
		start = System.nanoTime();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=100; i++) {
			sb.append(i);
		}
		
		System.out.println(sb);
		
		end = System.nanoTime();
		
		System.out.println((end - start) + "ns");
	}
}

123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
233400ns
123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
47300ns

약 5배정도 StringBuilder가 빠르다.
```

## Exam 11

```java
StringTokenizer도 가능하고 split도 가능하다.

package Example;

import java.util.StringTokenizer;

public class Exam11 {

	public static void main(String[] args) {
		String str = "아이디,이름,패스워드";
		
		String[] strlist = str.split(",");
		
		for (int i=0; i<strlist.length; i++) {
			System.out.println(strlist[i]);
		}
//		StringTokenizer st = new StringTokenizer(str, ",");
		
//		while(st.hasMoreTokens()) {
//			System.out.println(st.nextToken());
//		}

	}
}

아이디
이름
패스워드
```

## Exam 17

```java
package Example;

import java.util.regex.Pattern;

public class PatternMatcherExample {

	public static void main(String[] args) {
		String id = "5Angel1004"; // 5가 빠진다면 사용할 수 있다.
		String regExp = "[a-zA-Z][a-zA-Z0-9]{7,11}";
		boolean isMatch = Pattern.matches(regExp, id);
		
		if(isMatch) {
			System.out.println("ID로 사용할 수 있습니다.");
		} 
		else {
			System.out.println("ID로 사용할 수 없습니다.");
		}

	}

}

ID로 사용할 수 없습니다.
```

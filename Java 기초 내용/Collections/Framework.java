// package Collections;
// /*
//  * collection framework : 데이터 값들을 담는 여러 그릇.
//  * 크게 봤을 때 collection과 map으로 나뉘고 --> 이 둘은 모두 인터페이스로 되어 있다.
//  * collection은 또 list와 set으로 나뉘고 이 둘도 인터페이스 이다.
//  * 
//  * List 계열 구현 클래스 --> ArrayList, Linked list, vector, stack
//  * Set 계열 구현 클래스 --> HashSet, SortedSet, TreeSet
//  * 
//  * List 인터페이스를 상속하는 클래스들 특징 --> (1) 인덱스가 있고, (2) 저장순서가 유지되고, (3) 데이터 중복이 허용.
//  * Set 인터페이스를 상속하는 클래스들 특징 --> (1) 데이터 중복이 허용 안됨.
//  * 
//  * 먼저 자바의 배열은 크기를 미리 지정하고 사용했다. --> 그러다보니 넉넉하게 크기를 지정해놓고 사용한다.
//  * 반면, ArratList는 필요시 언제든 추가, 삭제가 가능하다. 또한 제네릭 문법 사용 가능
//  * --> 만약, 제네릭을 사용하지 않는다면 내부적으로 Object 타입으로 처리된다. (형변환의 불편함)
//  * 
//  * 사용을 위해서는 상단에 import 해줘야한다. --> import java.util.ArrayList; 또는 import java.util.*;
//  */
// import java.util.ArrayList;

// public class Framework {
//     public static void main(String[] args) {
//         // Generic 아닌 Object 일 때 (Generic 안써서 노란줄)
//         ArrayList list1 = new ArrayList();

//         list1.add("홍길동"); // 문자열 저장
//         list1.add(20); // 정수형 저장
//         list1.add(20); // 데이터 중복 가능

//         System.out.println(list1.get(0)); // 홍길동
//         String str = (String)list1.get(0); // Object 타입이라 (String)로 형 변환 해줘야 한다.
//         System.out.println(str.length()); // 3

//         System.out.println(list1.size()); // 3
//         for (int i = 0; i < list1.size(); i++) {
//             System.out.println(list1.get(i));
//         }
//     }
// }

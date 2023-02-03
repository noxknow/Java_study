package Collections;
/*
 * Iterator : 컬렉션에 대한 반복자.
 * 컬렉션 프레임워크내에는 다양한 컬렉션들이 있는데 요소(=원소)를 읽어올 때 Iterator인터페이스로 표준화 한다.
 * for 반복문보다 조금 더 편리하게 순회할 수 있다.
 * Iterator는 인터페이스다. --> 그래서 인터페이스내 선언된 메서드들이 있다 
 * 메서드 종류 : hasNext(), next(), remove() --> 반환 타입 boolean, Object(혹은 제네릭), void   
 * hasNext() : 다음 요소가 있는지를 검사하여 true를 리턴(boolean)
 * next() : 다음 요소를 리턴 --> 다음 위치로 커서 이동 --> Iterator에서는 삭제하지 않고, 커서만 이동
 * remove() : 제거
 * 
 * next()의 경우 배열에 값이 없을 때 사용하면 당연히 오류 발생
 * 따라서 사전에 hasnext()를 사용하여 다음요소 있는지 확인 후 next()로 요소를 가져온다.
 * import java.util.Iterator;
 * 
 * 컬렉션 프레임워크를 쓸 때 한번 이상은 꼭 만나게 되는 에러 메시지 --> java.util.ConcurrentModificationException
 * list 요소를 반복문 안에서 돌리면서 값을 제거할 때 발생. 보통 remove() 메서드 호출 시 발생.
 * 그 외에도 iterator 객체의 생성 순서에 따라 발생.
 * 
 * 반복문 들어가기 전에 size나 index가 변환되면서 순회시 정보가 맞지않아 발생하는 오류
 * 해결하기 위해서는 iterator 반복자를 순회하고 그 후에 remove() 해줘야 한다.
 */
import java.util.ArrayList;
import java.util.Iterator;

public class Iterator_ {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();

        list.add("Allgator");
        list.add("Hippo");
        list.add("Ostrich");
        list.add("Donkey");

        Iterator<String> iter = list.iterator();
 
        // System.out.println(iter.hasNext()); // True
        // System.out.println(iter.next()); // Allgator
        // System.out.println(iter.hasNext()); // True
        // System.out.println(iter.next()); // Hippo
        // System.out.println(iter.hasNext()); // True
        // System.out.println(iter.next()); // Ostrich
        // System.out.println(iter.hasNext()); // True
        // System.out.println(iter.next()); // Donkey
        // System.out.println(iter.hasNext()); // False 이 다음에 next하면 Err

        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("------------------------------------");

        // 전체 출력하는 경우
        // while (iter.hasNext()) {
        //     System.out.println(iter.next());
        // }
        // System.out.println("------------------------------------");
        
        // 하마만 출력하는 경우
        // while (iter.hasNext()) {
        //     String str = iter.next();
        //     if ("Hippo".equals(str)) {
        //         System.out.println(str);
        //     }
        // }
        // System.out.println("------------------------------------");

        // 하마만 삭제하는 경우
        while (iter.hasNext()) {
            String str = iter.next();
            if ("Hippo".equals(str)) {
                iter.remove(); // 이걸 list.remove()를 사용하면 위의 오류가 발생하므로 반복자를 이용한다.
                System.out.println("하마삭제");
            }
        }
        System.out.println("------------------------------------");

        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("------------------------------------");
    }
}

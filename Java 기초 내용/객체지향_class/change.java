package Practice;
import java.util.Arrays;

public class change {
    
    public static String[] changed(String a, String b) {
        String aa = a.toUpperCase();
        String bb = b.toLowerCase();

        return new String[] {aa,bb};
    }
    public static void main(String[] args) {
        String[] res = changed("korea", "USA");
        
        System.out.println(res); // 주소 값
        System.out.println(res[0] + " " + res[1]); // KOREA usa
        System.out.println(Arrays.toString(res)); // [KOREA, usa]
    }
}

/*
위의 문제는 호출값을 반환해서 받는경우
반환값이 있다? --> 호출에 따른 리턴이 있다는 거다.

아래 문제는 static옆을 void로 받아서 호출만 하는 경우

public class change {
    
    public static void changed(String a, String b) {
        String aa = a.toUpperCase();
        String bb = b.toLowerCase();

        System.out.println(aa);
        System.out.println(bb);
    }

    public static void main(String[] args) {
        changed("korea", "USA");
        
    }
}
 */

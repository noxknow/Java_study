package Collections;

class Sample<T> {
    private T obj;

    Sample(T x) {this.obj = x;}

    T getObj() {return obj;}

    void printinfo() {
        System.out.println(obj.getClass().getName());
    }
}

public class Generic_ex {
    public static void main(String[] args) {
        Sample<String> s1 = new Sample<String>("안녕하세요~");
        System.out.println(s1.getObj()); // 안녕하세요~
        s1.printinfo(); // java.lang.String
        System.out.println("------------------------------------");

        Sample<Integer> s2 = new Sample<Integer>(100);
        System.out.println(s2.getObj()); // 100
        s2.printinfo(); // java.lang.Integer
        System.out.println("------------------------------------");

        // String str = s1.getObj(); // generic에서는 (String)s1.getObj(); 이렇게 형변환 안해도 된다.
        // System.out.println(str.length()); // 6
        System.out.println(s1.getObj().length()); // 6 위 두줄 한줄로 가능
        System.out.println(s2.getObj() + 100); // 200
    }
}

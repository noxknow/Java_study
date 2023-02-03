package Practice;

public class make {

    public void hello() {
        System.out.println("Hello World~");
    }
    public static void main(String[] args) {

        make obj = new make();
        obj.hello();

        // 만약에 위에가 static void 라면 hello(); 하면 불러올 수 있다.
    }
}

/* 
static(접근 제한자)이 있으면 객체생성없이 선언 가능 (없으면 객체생성해서 접근해라) 
위에서는 obj가 객체가 되어서 make라는 클래스 안에서 메서드를 호출할 수 있는 권한을 얻는거다.

main 메서드 안에서는 static 메서드만 호출할 수 있다.
static 아닌거 호출하기 위해서는 객체를 만들어야 한다.

void 는 반환 타입, public(공용), private(개인), protected(비밀번호)

new라는 것은 객체를 생성해서 주소값을 반환해주는 역할.
*/




package Practice;

public class strcharat {
    public static void main(String[] args) {
        String[] arr = {"hong", "kim", "park"};
        System.out.println(arr[0].charAt(2)); // 0번째 문자열의 2번째 인덱스 값 반환 n
        System.out.println(arr[1].charAt(2)); // m

        for(int i = 0; i < arr.length; i++){
            for(int j=0; j < arr[i].length(); j++){
              System.out.print(arr[i].charAt(j) + "");
            }
            System.out.println();
          }
    }
}

/*
배열의 경우 arr.length 하면 배열의 길이가 3으로 잘 나오지만
arr[0].length 하면 에러가 나오게 된다 ("hong"은 문자열 이기 때문에)
그래서 저걸 하기위해서는 arr[0].length()를 하면 4로 잘 출력이 된다.
즉, length : 배열의 길이, length() : 문자열의 길이
 */
package Algorithms.NearAlgorithm;

//[?] 원본 데이터 중에서 대상 데이터와 가장 가까운 값
// 근삿값 알고리즘

public class NearAlgorithm {
    // 절댓값 구하는 함수
    public static int Abs(int number) {
        return (number < 0) ? - number : number;
    }
    public static void main(String[] args) {
        int min = Integer.MAX_VALUE;

        int[] numbers = { 10, 20, 30, 27, 17 };
        int target = 25;
        int near = 0;

        for (int i = 0; i < numbers.length; i++) {
            int abs = Abs(numbers[i] - target);
            if (abs < min) {
                min = abs;
                near = numbers[i];
            }
        }

        System.out.println(target + "와/과 가장 가까운 값 : " + near + "(차이 : " + min + ")");

    }
}

/*
자바 조건문 let result = condition ? value1 : value2; (삼항연산자)
-> 평가 대상인 condition이 truth라면 value1이, 그렇지 않으면 value2가 반환됩니다.
-> ? 사용하는 방식은 if 보다 가독성이 떨어질 때가 있어 반환문(return)일때 쓰는게 좋다

let message = (age < 3) ? '아기야 안녕?' :
(age < 18) ? '안녕!' :
(age < 100) ? '환영합니다!' :
'나이가 아주 많으시거나, 나이가 아닌 값을 입력 하셨군요!';

             =

if (age < 3) {
    message = '아기야 안녕?';
  } else if (age < 18) {
    message = '안녕!';
  } else if (age < 100) {
    message = '환영합니다!';
  } else {
    message = '나이가 아주 많으시거나, 나이가 아닌 값을 입력 하셨군요!';
  }
 */
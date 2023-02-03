package Algorithms.SumAlgorithm;

// 등차수열(ArithmeticSequence) : 연속하는 두 수의 차이가 일정한 수열
//[?] 1 부터 20까지의 정수 중 홀수의 합을 구하는 프로그램

public class ArithmeticSequence {

    public static void main(String[] args) {
        int sum = 0;

        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 1) {
                sum += i;
                System.out.print(i + " ");
            }
        }

        System.out.println("\n1부터 20까지 홀수의 합 : " + sum);
    }
}

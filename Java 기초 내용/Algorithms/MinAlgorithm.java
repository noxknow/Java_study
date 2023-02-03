package Algorithms.MinAlgorithm;

// [?] 주어진 데이터 중에서 짝수이면서 가장 작은 값
// 최솟값 알고리즘 

public class MinAlgorithm {
    public static void main(String[] args) {
        int min = Integer.MAX_VALUE;

        int[] numbers = { 2, 5, 3, 7, 1 };

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min && numbers[i] % 2 == 0) {
                min = numbers[i];
            }
        }

        System.out.println("짝수 최솟값 : " + min);
    }
}

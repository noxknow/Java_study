package Algorithms.MaxAlgorithm;

// [?] 주어진 데이터 중에서 가장 큰 값
// 최댓값 알고리즘

public class MaxAlgorithm {
    public static void main(String[] args) {
        int max = Integer.MIN_VALUE; // max는 정수 형 데이터 중 가장 작은값으로 초기화
        
        int[] numbers = { -2, -5, -3, -7, -1};

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] >= max) {
                max = numbers[i];
            }
        }

        System.out.println("최댓값 : " + max);
    }
}

package Algorithms.CountAlgorithm;

// [?] n개의 정수 중 13의 배수의 개수(건수, 횟수)
// 개수 알고리즘 : 주어진 범위에 주어진 조건에 해당하는 자료들의 개수

public class CountAlgorithm {

    public static void main(String[] args) {
        int[] numbers = { 11, 12, 13, 13, 15, 13 };
        int count = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 13) {
                count += 1;
            }
        }
        
        System.out.println(numbers.length + "개의 정수 중 13의 배수의 개수 : " + count);
    }
}

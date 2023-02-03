package Algorithms.ModeAlgorithm;

// [?] 주어진 데이터에서 가장 많이 나타난 중복된 값
// 최빈값 알고리즘

public class ModeAlgorithm {

    public static void main(String[] args) {
        int[] scores = {1,3,4,3,5};
        int mode = 0;
        int[] index = new int[5+1];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < scores.length; i++) {
            index[scores[i]] += 1;
        }
        for (int i = 0; i < index.length; i++) {
            if (index[i] > max) {
                max = index[i]; // 인덱스의 최대 크기
                mode = i; // 가장 많이 나온 최빈값
            }
        }

        System.out.println("최빈값 : " + mode + " - " + max + "번");
    }
}
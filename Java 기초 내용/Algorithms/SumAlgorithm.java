package Algorithms.SumAlgorithm;
// [?] n명의 국어점수 중에서 80점 이상인 점수의 합계

/**
 * SumAlgorithm
 */
public class SumAlgorithm {

    public static void main(String[] args) {
        int[] scores = { 100, 75, 50, 37, 90, 95 };
        int sum = 0;

        for (int i = 0; i < scores.length; i++) {
            if (scores[i] >= 80) {
                sum += scores[i];
            }
        }

        System.out.println(scores.length + "명의 점수 중 80점 이상의 총점 : " + sum);
    }
}
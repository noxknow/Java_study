package Algorithms.RankAlgorithm;

// [?] 주어진 데이터의 순위
// 순위 알고리즘

public class RankAlgorithm {
    public static void main(String[] args) {
        int[] scores = { 90, 87, 100, 95, 80};
        int[] rankings = {1,1,1,1,1};

        for (int i = 0; i < scores.length; i++) {
            rankings[i] = 1;
            for (int j = 0; j < scores.length; j++) {
                if (scores[i] < scores[j]) {
                    rankings[i] += 1;
                }
            }
        }

        for (int j = 0; j < rankings.length; j++) {
            System.out.println(String.format("%3d점 : %d등", scores[j], rankings[j]));
        }
    }
}

/*
System.out.println(String.format("%3d점 : %1d등", scores[j], rankings[j]));
%3d의 경우 정수를 출력할 때 자릿수가 3보다 작으면 출력이 왼쪽에 채워진다.(자릿수 정렬 하는 느낌) 
 */
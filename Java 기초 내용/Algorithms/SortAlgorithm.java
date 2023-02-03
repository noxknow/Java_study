package Algorithms.SortAlgorithm;

// [?] 무작위 데이터를 순서대로 정렬
// 정렬 알고리즘

public class SortAlgorithm {

    public static void main(String[] args) {
        int[] data = {3,2,1,5,4};
        int N = data.length; // 의사코드(슈도코드) 형태로 알고리즘을 표현하기 위함.

        for (int i = 0; i < N-1; i++) {
            for (int j = i+1; j < N; j++) {
                if (data[i] > data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.print(data[i] + "\t");
        }
        System.out.println();
    }
}
package Algorithms.MergeAlgorithm;

// [?] 2개의 정수 배열 합치기 (단, 2개의 배열은 오름차순으로 정렬)
// 병합 알고리즘

public class MergeAlgorithm {
    public static void main(String[] args) {
        int[] first = {1,3,5};
        int[] second = {2,4};
        int M = first.length;
        int N = second.length;

        int[] merge = new int[M+N];
        int i = 0; int j = 0; int k = 0;

        while(i < M && j < N) { // 둘중 하나라도 배열의 끝에 도달할 때 까지
            if (first[i] < second[j]) { 
                merge[k] = first[i];  // merge[k++] = first[i++]; 랑 똑같은 의미인듯 (한줄이랑 세줄)
                k++;
                i++;
            }
            else {
                merge[k++] = second[j++];
            }
        }
        while (i < M) { // 첫 번째 배열이 끝까지 도달할 때 까지
            merge[k++] = first[i++];
        }
        while (j < N) { // 두 번째 배열이 끝까지 도달할 때 까지
            merge[k++] = second[j++];
        }

        for (int item : merge) { // 약간 파이썬에서는 for i in merge: 느낌.
            System.out.print(item + " ");
        }
        System.out.println();

        // for (int ii = 0; ii < M+N; ii++) {
        //     System.out.print(merge[ii] + " ");
        // }

    }
}

/*
-> merge[k] = first[i];    =    merge[k++] = first[i++];
   k++;
   i++;
 */

package Algorithms.SearchAlgorithm;

// [?] 정렬 되어있는 데이터를 이진 검색을 사용하여 반씩 나눠서 검색
// 0번째 인덱스 부터 순차적으로 탐색하는 순차 탐색보다 low와 high의 중간값인 mid부터 찾아가는
// 이분 탐색 방식이 더 빠르다. (찾고자 하는 값이 mid값보다 크다면 mid값 왼쪽을 버리고)
// (low를 mid+1로, 반대면 mid값 오른쪽 버리고 high를 mid - 1로)

public class SearchAlgorithm {
    public static void main(String[] args) {
        int[] data = {1,3,5,7,9};
        int N = data.length;
        int search = 7;
        boolean flag = false;
        int index = 0;

        int low = 0;
        int high = N-1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (data[mid] == search) {
                flag = true;
                index = mid;
                break;
            }
            if (data[mid] < search) {
                low = mid + 1; // 찾을 데이터 크면 오른쪽 영역으로 이동(왼쪽 영역 삭제)
            }
            else{
                high = mid - 1; // 찾을 데이터 작으면 왼쪽 영역으로 이동
            }
        }
        
        if (flag) {
            System.out.println(search + "를 " + index + "위치에서 찾았습니다.");
        }
        else{
            System.out.println("찾지 못했습니다.");
        }
    }
}

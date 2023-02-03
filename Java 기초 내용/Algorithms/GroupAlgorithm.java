package Algorithms.GroupAlgorithm;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// [?] 컬렉션 형태의 데이터를 특정 키 값으로 그룹화
// 그룹 알고리즘

public class GroupAlgorithm {

    public static class Record{ // 테스트용 레코드 클래스
        private final String name; // 상품명
        private final int quantity; // 수량

        public Record(String name, int quantity) { // 생산자 (넘겨준다)
            this.name = name; // 넘겨진 상품명으로 초기화
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public int getQunatity() {
            return quantity;
        }
    }

    // 테스트용 데이터 채우기용 로컬 함수
    public static List<Record> getAll() {
        return Arrays.asList(
            new Record("RADIO", 3),
            new Record("TV", 1),
            new Record("RADIO", 2),
            new Record("DVD", 4)
        );
    }

    // 컬렉션 데이터 출력용 로컬 함수
    public static void printData(String message, List<Record> data) {
        System.out.println(message);
        for (Record item : data) {
            System.out.println(String.format("%5s - %d", item.getName(), item.getQunatity()));
        }
    }

    public static void main(String[] args) {
        List<Record> records = getAll(); // 입력 데이터
        List<Record> groups = new ArrayList<Record>(); // 출력 데이터
        int n = records.size();

        // [1]
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (records.get(i).getName().compareTo(records.get(j).getName()) > 0) { // i번째와 j번째를 비교해서 0보다 크다면 둘을 바꿔라
                    Record temp = records.get(i);
                    records.set(i, records.get(j)); // i번째에 j번째 값 넣기
                    records.set(j, temp); // j번째에 t값 넣기
                } // 즉 정렬을 한 것 이다. 
            }
        }

        // [2]
        int subtotal = 0;
        for (int i = 0; i < n; i++) {
            subtotal += records.get(i).getQunatity();
            if ((i+1) == n || (records.get(i).getName() != records.get(i+1).getName())) {
                Record r = new Record(records.get(i).getName(), subtotal);
                groups.add(r);

                subtotal = 0;
            }
        }

        printData("[1] 정렬된 원본 데이터 : ", records);
        printData("[2] 이름으로 그룹화 된 데이터", groups);
    }
}
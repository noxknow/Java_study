# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [3691] 컴퓨터 조립 

📌 → https://www.acmicpc.net/problem/3691 <br/><br/>

**주의할 점**

1. 가장 낮은 성능을 올려주는 것이 최우선이기 때문에 결과값을 담는 큐는 성능 기준 오름차순으로 정렬한다. <br/><br/>

**핵심 로직**

1. 각 부품을 hashmap에 제품의 이름을 key로 arraylist에 저장할 위치번호를 value로 저장한다.
2. arraylist의 속성을 우선순위 큐로 해서 각각의 부품을 가격순으로 정렬한다.
3. 모든 부품을 선택하기 위해 각각의 arraylist에 꺼내어 새로운 우선순위 큐에 저장한다.
    1. 이때는 성능이 낮은 부품을 꺼내야 하기 때문에 성능 순으로 정렬되게 한다.
4. 성능이 낮은 부품을 꺼내 해당 부품에서 가격이 낮고 자신보다 성능이 높은 것이 나올 때까지 진행한다.
5. 성능이 높은 것이 존재하지 않으면 현재 낮은 것을 결과로 저장한다. <br/><br/>

```
1
18 800
processor 3500_MHz 66 5
processor 4200_MHz 103 7
processor 5000_MHz 156 9
processor 6000_MHz 219 12
memory 1_GB 35 3
memory 2_GB 88 6
memory 4_GB 170 12
mainbord all_onboard 52 10
harddisk 250_GB 54 10
harddisk 500_FB 99 12
casing midi 36 10
monitor 17_inch 157 5
monitor 19_inch 175 7
monitor 20_inch 210 9
monitor 22_inch 293 12
mouse cordless_optical 18 12
mouse microsoft 30 9
keyboard office 4 10
```

### 최종 결과 (480 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static class Product implements Comparable<Product> {

        int num, price, value;

        Product (int num, int price, int value) {
            this.num = num;
            this.price = price;
            this.value = value;
        }

        @Override
        public int compareTo(Product o) {

            int cmp = Integer.compare(this.price, o.price);
            if (cmp == 0) {
                return Integer.compare(o.value, this.value);
            }

            return cmp;
        }
    }

    static class SelectProduct implements Comparable<SelectProduct> {

        int num, price, value;

        SelectProduct (int num, int price, int value) {
            this.num = num;
            this.price = price;
            this.value = value;
        }

        @Override
        public int compareTo(SelectProduct o) {

            int cmp = Integer.compare(this.value, o.value);

            return cmp;
        }
    }

    static BufferedReader br;
    static int n, b, sum, ans;
    static HashMap<String, Integer> types;
    static List<PriorityQueue<Product>> products;
    static PriorityQueue<SelectProduct> results;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));

        int t = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= t; testCase++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st1.nextToken());
            b = Integer.parseInt(st1.nextToken());

            sum = 0;
            types = new HashMap<>();
            products = new ArrayList<>();
            results = new PriorityQueue<>();

            for (int i = 0; i < n; i++) {
                insertData();
            }

            init();
            calculate();
            System.out.println(ans);
        }

        br.close();
    }

    private static void insertData() throws Exception {

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        String pc = st2.nextToken();
        st2.nextToken();
        int price = Integer.parseInt(st2.nextToken());
        int value = Integer.parseInt(st2.nextToken());

        if (types.get(pc) != null) {
            int num = types.get(pc);
            products.get(num).add(new Product(num, price, value));
        } else {
            int num = types.size();
            types.put(pc, num);
            products.add(new PriorityQueue<>());
            products.get(num).add(new Product(num, price, value));
        }
    }

    private static void init() {

        for (PriorityQueue<Product> product : products) {
            Product next = product.remove();
            results.add(new SelectProduct(next.num, next.price, next.value));
            sum += next.price;
        }
    }

    private static void calculate() {

        while (true) {
            SelectProduct selectProduct = results.remove();
            sum -= selectProduct.price;
            boolean flag = false;

            while (!products.get(selectProduct.num).isEmpty()) {
                Product next = products.get(selectProduct.num).remove();
                if (sum + next.price <= b && next.value > selectProduct.value) {
                    sum += next.price;
                    results.add(new SelectProduct(next.num, next.price, next.value));
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                ans = selectProduct.value;
                return;
            }
        }
    }
}
```

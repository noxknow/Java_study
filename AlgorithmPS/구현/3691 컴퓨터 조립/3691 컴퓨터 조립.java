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

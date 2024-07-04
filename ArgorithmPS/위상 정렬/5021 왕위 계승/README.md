# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [5021] 왕위 계승

📌 → https://www.acmicpc.net/problem/5021 <br/><br/>

**주의할 점**

1. `*bloodTree*.put(rootNode, 1d);`  의 위치가 for문보다 전에 나오는 경우 rootNode의 값이 -1로 갱신되기 때문에 for문의 뒤에 둬야한다. <br/><br/>

**핵심 로직**

1. 자식과 부모의 관계를 저장할 familyTree 맵과 혈통의 정도를 나타내는 bloodTree 맵을 만들어준다.
2. 자식과 부모의 경우는 -1로 초기화 해주고, rootNode의 경우는 1로 초기화 해준다.
3. dfsAll 메서드를 활용해서 위상정렬을 해준다.
4. dfs 메서드 내부에서는 부모의 혈통을 활용해서 자식의 혈통을 구해 반환한다.
    1. 이때, bloodTree에 이미 -1이 아닌 다른 값이 저장되어 있다면 반환한다.
    2. 또한, familyTree가 null 즉, 왕족이 아니고 부모가 없는 경우 0으로 값을 넣고 반환한다.
5. main 메서드로 돌아와 successor와 competitor의 혈통값을 비교해 혈통이 더 큰 경우를 출력한다. <br/><br/>

```
9 2
edwardi
charlesi edwardi diana
philip charlesi mistress
wilhelm mary philip
matthew wilhelm helen
edwardii charlesi laura
alice laura charlesi
helen alice bernard
henrii edwardii roxane
charlesii elizabeth henrii
charlesii
matthew
```

### 최종 결과 (128 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static HashMap<String, ArrayList<String>> familyTree;
    static HashMap<String, Double> bloodTree;
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        String rootNode = br.readLine();
        familyTree = new HashMap<>();
        bloodTree = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String son = st.nextToken();

            if (familyTree.get(son) == null) {
                familyTree.put(son, new ArrayList<>());
            }

            String father = st.nextToken();
            familyTree.get(son).add(father);
            String mother = st.nextToken();
            familyTree.get(son).add(mother);

            bloodTree.put(son, -1d);
            bloodTree.put(father, -1d);
            bloodTree.put(mother, -1d);
        }

        bloodTree.put(rootNode, 1d);

        dfsAll();

//        for (String name : bloodTree.keySet()) {
//            System.out.println(String.format("name : %s\t\tvalue : %f", name,
//                    bloodTree.get(name)));
//        }

        String successor = br.readLine();
        for (int i = 1; i < m; i++) {
            String competitor = br.readLine();

            if (bloodTree.getOrDefault(successor, 0d) < bloodTree.getOrDefault(competitor, 0d)) {
                successor = competitor;
            }
        }

        System.out.println(successor);

        br.close();
    }

    private static void dfsAll() {

        for (String name : bloodTree.keySet()) {
            dfs(name);
        }
    }

    private static double dfs(String name) {

        if (bloodTree.get(name) != -1) {
            return bloodTree.get(name);
        }

        // 부모가 존재하지 않는 즉, 왕족이 아닌 0의 경우
        if (familyTree.get(name) == null) {
            bloodTree.put(name, 0d);
            return bloodTree.get(name);
        }

        double fatherBlood = dfs(familyTree.get(name).get(0));
        double motherBlood = dfs(familyTree.get(name).get(1));

        bloodTree.put(name, (fatherBlood + motherBlood) / 2);

        return bloodTree.get(name);
    }
}
```

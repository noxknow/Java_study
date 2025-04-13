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

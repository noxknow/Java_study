import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt")); 

        int n = Integer.parseInt(br.readLine());
        List<String> wordGroup = new ArrayList<>();
        for (int i=0; i<n; i++) {
            wordGroup.add(br.readLine());
        }

        List<String> newWord = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        for (int i=0; i<n-1; i++) {
            if (wordGroup.get(0).compareTo(wordGroup.get(wordGroup.size() - 1)) > 0) {
                newWord.add(wordGroup.get(wordGroup.size() - 1));
                wordGroup.remove(wordGroup.size() - 1);
            }
            else if (wordGroup.get(0).compareTo(wordGroup.get(wordGroup.size() - 1)) < 0) {
                newWord.add(wordGroup.get(0));
                wordGroup.remove(0);
            }
            else {
                int j = 1;
                while (j < wordGroup.size() && wordGroup.get(j).compareTo(wordGroup.get(wordGroup.size() - 1 - j)) == 0) {
                    j++;
                }

                if (j == wordGroup.size()) {
                    newWord.add(wordGroup.get(0));
                    wordGroup.remove(0);
                } else if (wordGroup.get(j).compareTo(wordGroup.get(wordGroup.size() - 1 - j)) > 0) {
                    newWord.add(wordGroup.get(wordGroup.size() - 1));
                    wordGroup.remove(wordGroup.size() - 1);
                } else {
                    newWord.add(wordGroup.get(0));
                    wordGroup.remove(0);
                }
            }

            if (newWord.size() == 80) {
                result.append(String.join("", newWord)).append("\n");
                newWord.clear();
            } 
        }

        result.append(String.join("", newWord)).append(wordGroup.get(0));

        System.out.println(result.toString());
    }
}

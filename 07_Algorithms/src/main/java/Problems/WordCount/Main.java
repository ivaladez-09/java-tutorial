package Problems.WordCount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        var inputs = List.of(
                "hello world",
                "world hello",
                "hello",
                "howdy"
        );

        countWords(inputs);
    }

    public static void countWords(List<String> inputs) {
        Map<String, Integer> map = new HashMap<>();

        for (String input: inputs) {
            String[] words = input.split(" ");
            for (String word: words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        final List<String> list = new ArrayList<>();

        map.forEach((key, value) -> list.add(key));

        list.stream().sorted().forEach(key -> System.out.println(key + " " + map.get(key)));
    }
}

package Problems.Anagrams;

import java.io.*;
import java.util.*;

/*
 * Anagrams
 * 1. Create method that detects when 2 Strings are anagrams between them.
 *    - "eat" -> "tea" --> True
 *    - "eat" -> "eato" --> False
 *    - "eat" -> "" --> False
 *    - null -> "eat" --> False
 * 2. Given an array of Strings, return a List of List grouping the anagrams by list
 *    Input = ["eat", "tea", "col", "a", "loc", "ate"]
 *    Output = [
 *               ["eat", "tea", "ate"],
 *               ["col", "loc"],
 *               ["a"]
 *    ]
 */

public class Main {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("eat");
        strings.add("tea");
        strings.add("col");
        strings.add("a");
        strings.add("loc");
        strings.add("ate");

        System.out.println(isAnagram(strings.get(3), strings.get(5)) + "--- \n");
        var gropus = getAnagramGroups(strings);
        gropus.forEach(System.out::println);
        System.out.println("\n --- \n");

        var groups2 = getAnagramGroups2(strings);
        groups2.forEach(System.out::println);

    }

    public static boolean isAnagram(String a, String b) {
        if(a == null || b == null || a.isBlank() || b.isBlank()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for(Character character: a.toCharArray()) {
            map.put(character, map.getOrDefault(character, 0) + 1);
        }

        for(Character character: b.toCharArray()) {
            map.put(character, map.getOrDefault(character, 0) - 1);
        }

        for(Map.Entry<Character, Integer> set: map.entrySet()) {
            if(set.getValue() != 0) {
                return false;
            }
        }

        return true;
    }

    public static List<List<String>> getAnagramGroups(List<String> strings) {
        List<List<String>> groups = new ArrayList<>();
        Set<String> set = new HashSet<>();

        if(strings == null || strings.isEmpty()) return groups;

        for(int i = 0; i < strings.size(); i++) {
            List<String> subGroup = new ArrayList<>();

            if(set.contains(strings.get(i))) {
                continue;
            }

            subGroup.add(strings.get(i));
            //set.add(strings.get(i));

            for(int j = i + 1; j < strings.size(); j++) {
                if(isAnagram(strings.get(i), strings.get(j))) {
                    subGroup.add(strings.get(j));
                    set.add(strings.get(j));
                }
            }

            groups.add(subGroup);
        }

        return groups;
    }

    public static String generateHash(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for(Character character: s.toCharArray()) {
            map.put(character, map.getOrDefault(character, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            sb.append(entry.getValue() + entry.getKey());
        }

        return sb.toString();
    }

    public static List<List<String>> getAnagramGroups2(List<String> strings) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> groups = new ArrayList<>();

        for(String s: strings) {
            String hash = generateHash(s);

            if(!map.containsKey(hash)) {
                List<String> subGroup = new ArrayList<>();
                subGroup.add(s);
                map.put(hash, subGroup);

            } else {
                map.get(hash).add(s);
            }
        }

        map.forEach((k, v) -> groups.add(v));

        return groups;
    }
}

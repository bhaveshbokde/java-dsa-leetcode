package com.bhavesh.dsa.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 49 - Group Anagrams (Medium).
 *
 * <p>Partitions the input strings into groups that are anagrams of one another.
 *
 * <p>Comparing every pair is O(n^2 * k). The key idea is that "is an anagram
 * of" is an equivalence relation, so each class needs a <em>canonical
 * representative</em> that is identical for all its members and different for
 * every other class. Then one hash-map pass groups them.
 *
 * <p>Two canonical forms are reasonable:
 * <ul>
 *   <li>the sorted characters - O(k log k) per word, works for any alphabet;</li>
 *   <li>a 26-slot character-count signature - O(k) per word, but only valid for
 *       the lowercase-English constraint the problem states.</li>
 * </ul>
 * The count signature is used here because it is asymptotically better in k,
 * and the sorted variant is kept alongside it for the unconstrained case.
 *
 * <p>Time O(n * k), auxiliary space O(n * k), for n words of length up to k.
 */
public final class GroupAnagrams {

    private static final int ALPHABET = 26;

    private GroupAnagrams() {
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String word : strs) {
            groups.computeIfAbsent(countSignature(word), key -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }

    /**
     * Builds a signature such as {@code "1#0#2#..."} from character counts.
     *
     * <p>The separator matters. Concatenating raw counts would make a word with
     * 11 of one letter collide with a word having 1 then 1 of two letters.
     */
    private static String countSignature(String word) {
        int[] counts = new int[ALPHABET];
        for (int i = 0; i < word.length(); i++) {
            counts[word.charAt(i) - 'a']++;
        }
        StringBuilder signature = new StringBuilder(ALPHABET * 2);
        for (int count : counts) {
            signature.append(count).append('#');
        }
        return signature.toString();
    }

    /**
     * Alphabet-independent variant keyed on the sorted characters.
     *
     * <p>Time O(n * k log k), auxiliary space O(n * k).
     */
    public static List<List<String>> groupAnagramsBySorting(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            groups.computeIfAbsent(new String(chars), key -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }
}

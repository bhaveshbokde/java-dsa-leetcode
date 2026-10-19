package com.bhavesh.dsa.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GroupAnagramsTest {

    /**
     * Grouping has no defined output order, so comparisons normalise: sort
     * within each group, then sort the groups themselves.
     */
    private static List<List<String>> normalise(List<List<String>> groups) {
        List<List<String>> copy = new ArrayList<>();
        for (List<String> group : groups) {
            List<String> sorted = new ArrayList<>(group);
            sorted.sort(Comparator.naturalOrder());
            copy.add(sorted);
        }
        copy.sort(Comparator.comparing(Object::toString));
        return copy;
    }

    @Test
    @DisplayName("LeetCode example")
    void leetCodeExample() {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> expected = List.of(
                List.of("ate", "eat", "tea"),
                List.of("bat"),
                List.of("nat", "tan"));
        assertEquals(normalise(expected), normalise(GroupAnagrams.groupAnagrams(input)));
    }

    @Test
    @DisplayName("empty string forms its own group")
    void emptyString() {
        assertEquals(normalise(List.of(List.of(""))), normalise(GroupAnagrams.groupAnagrams(new String[] {""})));
    }

    @Test
    @DisplayName("single word")
    void singleWord() {
        assertEquals(normalise(List.of(List.of("a"))), normalise(GroupAnagrams.groupAnagrams(new String[] {"a"})));
    }

    @Test
    @DisplayName("no anagrams: every word is its own group")
    void allDistinct() {
        String[] input = {"abc", "de", "fgh"};
        assertEquals(3, GroupAnagrams.groupAnagrams(input).size());
    }

    @Test
    @DisplayName("repeated identical words stay in one group")
    void repeatedWords() {
        String[] input = {"ab", "ab", "ba"};
        List<List<String>> result = GroupAnagrams.groupAnagrams(input);
        assertEquals(1, result.size());
        assertEquals(3, result.get(0).size());
    }

    @Test
    @DisplayName("counts above nine do not collide: 'aaaaaaaaaaab' versus 'aab'")
    void multiDigitCountsDoNotCollide() {
        // Without a separator in the signature these two would share a key.
        String many = "a".repeat(11) + "b";
        String few = "ab".repeat(1) + "a";
        List<List<String>> result = GroupAnagrams.groupAnagrams(new String[] {many, few});
        assertEquals(2, result.size(), "11 a's must not group with 1 a + 1 b");
    }

    @Test
    @DisplayName("count signature and sorting agree on randomised inputs")
    void bothStrategiesAgree() {
        Random random = new Random(86420L);
        for (int trial = 0; trial < 300; trial++) {
            String[] words = new String[1 + random.nextInt(8)];
            for (int i = 0; i < words.length; i++) {
                int length = random.nextInt(5);
                StringBuilder word = new StringBuilder();
                for (int c = 0; c < length; c++) {
                    word.append((char) ('a' + random.nextInt(3)));
                }
                words[i] = word.toString();
            }
            assertEquals(normalise(GroupAnagrams.groupAnagramsBySorting(words)),
                    normalise(GroupAnagrams.groupAnagrams(words)),
                    () -> "strategies disagreed on " + Arrays.toString(words));
        }
    }

    @Test
    @DisplayName("every input word appears exactly once across all groups")
    void partitionIsComplete() {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<String> flattened = GroupAnagrams.groupAnagrams(input).stream()
                .flatMap(List::stream)
                .sorted()
                .toList();
        List<String> expected = Arrays.stream(input).sorted().collect(
                java.util.stream.Collectors.collectingAndThen(java.util.stream.Collectors.toList(),
                        Function.identity()));
        assertEquals(expected, flattened);
    }
}

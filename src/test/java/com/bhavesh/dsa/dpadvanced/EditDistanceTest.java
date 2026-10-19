package com.bhavesh.dsa.dpadvanced;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EditDistanceTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertEquals(3, EditDistance.minDistance("horse", "ros"));
        assertEquals(5, EditDistance.minDistance("intention", "execution"));
    }

    @Test
    @DisplayName("identical words need no edits")
    void identical() {
        assertEquals(0, EditDistance.minDistance("same", "same"));
        assertEquals(0, EditDistance.minDistance("", ""));
    }

    @Test
    @DisplayName("converting to or from the empty string costs one edit per character")
    void emptyString() {
        assertEquals(4, EditDistance.minDistance("word", ""));
        assertEquals(4, EditDistance.minDistance("", "word"));
        assertEquals(3, EditDistance.minDistance(null, "abc"));
        assertEquals(3, EditDistance.minDistance("abc", null));
    }

    @Test
    @DisplayName("a single operation of each kind")
    void singleOperations() {
        assertEquals(1, EditDistance.minDistance("cat", "cut"));    // replace
        assertEquals(1, EditDistance.minDistance("cat", "cats"));   // insert
        assertEquals(1, EditDistance.minDistance("cats", "cat"));   // delete
    }

    @Test
    @DisplayName("completely different words of equal length cost one replace each")
    void allReplacements() {
        assertEquals(3, EditDistance.minDistance("abc", "xyz"));
    }

    @Test
    @DisplayName("the distance is symmetric")
    void symmetric() {
        Random random = new Random(7272L);
        for (int trial = 0; trial < 400; trial++) {
            String a = randomWord(random, 8);
            String b = randomWord(random, 8);
            assertEquals(EditDistance.minDistance(a, b), EditDistance.minDistance(b, a),
                    () -> "asymmetric for '" + a + "' and '" + b + "'");
        }
    }

    @Test
    @DisplayName("matches a full-table reference on randomised inputs")
    void matchesFullTable() {
        Random random = new Random(720720L);
        for (int trial = 0; trial < 600; trial++) {
            String a = randomWord(random, 9);
            String b = randomWord(random, 9);
            assertEquals(fullTable(a, b), EditDistance.minDistance(a, b),
                    () -> "mismatch for '" + a + "' and '" + b + "'");
        }
    }

    @Test
    @DisplayName("the distance never exceeds the longer length")
    void boundedByLongerLength() {
        Random random = new Random(111L);
        for (int trial = 0; trial < 300; trial++) {
            String a = randomWord(random, 10);
            String b = randomWord(random, 10);
            int distance = EditDistance.minDistance(a, b);
            org.junit.jupiter.api.Assertions.assertTrue(distance <= Math.max(a.length(), b.length()),
                    () -> "distance exceeded the longer length for '" + a + "' and '" + b + "'");
            org.junit.jupiter.api.Assertions.assertTrue(distance >= Math.abs(a.length() - b.length()),
                    () -> "distance below the length difference for '" + a + "' and '" + b + "'");
        }
    }

    private static String randomWord(Random random, int maxLength) {
        int length = random.nextInt(maxLength + 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(4)));
        }
        return sb.toString();
    }

    private static int fullTable(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= b.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                dp[i][j] = a.charAt(i - 1) == b.charAt(j - 1)
                        ? dp[i - 1][j - 1]
                        : 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[a.length()][b.length()];
    }
}

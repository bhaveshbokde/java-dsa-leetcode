package com.bhavesh.dsa.strings;

/**
 * LeetCode 242 - Valid Anagram (Easy).
 *
 * <p>Decides whether two strings are permutations of one another.
 *
 * <p>Sorting both and comparing is correct and O(n log n). Counting is O(n),
 * and for the stated constraint of lowercase English letters the counter is a
 * fixed 26-element array rather than a hash map - no boxing, no hashing, and a
 * cache-friendly contiguous scan.
 *
 * <p>The single-pass form increments for {@code s} and decrements for {@code t}
 * simultaneously, so a non-anagram leaves a non-zero residue in some slot. The
 * leading length check is what makes that single loop legal at all: it lets one
 * index walk both strings, and it rejects differing lengths without scanning.
 *
 * <p>Time O(n), auxiliary space O(1) - the 26 counters do not grow with input.
 */
public final class ValidAnagram {

    private static final int ALPHABET = 26;

    private ValidAnagram() {
    }

    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] counts = new int[ALPHABET];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Unicode-safe variant. The fixed 26-slot array above assumes lowercase
     * ASCII, which is what the problem guarantees; this version handles any
     * code point at the cost of a hash map and boxing.
     *
     * <p>Time O(n), auxiliary space O(k) for k distinct code points.
     */
    public static boolean isAnagramUnicode(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        java.util.Map<Integer, Integer> counts = new java.util.HashMap<>();
        s.codePoints().forEach(cp -> counts.merge(cp, 1, Integer::sum));
        for (int cp : t.codePoints().toArray()) {
            Integer remaining = counts.get(cp);
            if (remaining == null || remaining == 0) {
                return false;
            }
            counts.put(cp, remaining - 1);
        }
        return true;
    }
}

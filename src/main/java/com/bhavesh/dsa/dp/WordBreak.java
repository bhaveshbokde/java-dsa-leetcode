package com.bhavesh.dsa.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 139 - Word Break (Medium).
 *
 * <p>Decides whether a string can be segmented into a sequence of dictionary
 * words, reusing words freely.
 *
 * <p>Greedily taking the longest matching prefix fails: for {@code "aaaaab"}
 * with dictionary {@code ["aaaa","aaa"]}, taking "aaaa" first strands "ab",
 * while "aaa" + "aa" would also fail - but the general shape is that an early
 * choice can make a later segmentation impossible, so every split point must
 * stay available.
 *
 * <p>The state that works is a single index: can the prefix of length {@code i}
 * be segmented? Then {@code breakable[i]} is true when some {@code j < i} has
 * {@code breakable[j]} true and {@code s[j..i)} is a dictionary word. Each
 * prefix is solved once, so the overlapping subproblems collapse.
 *
 * <p>Two practical details. The dictionary is copied into a {@link HashSet} so
 * membership is O(1) rather than a list scan. And the inner loop is bounded by
 * the longest dictionary word - without that bound it examines substrings that
 * cannot possibly match, which on long strings with short words is most of the
 * work.
 *
 * <p>Time O(n * L) substring checks where L is the longest word length, each
 * costing O(L) to hash - so O(n * L^2) overall. Auxiliary space O(n + total
 * dictionary characters).
 */
public final class WordBreak {

    private WordBreak() {
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        if (wordDict == null || wordDict.isEmpty()) {
            return false;
        }
        Set<String> dictionary = new HashSet<>(wordDict);
        int longestWord = 0;
        for (String word : wordDict) {
            longestWord = Math.max(longestWord, word.length());
        }

        // breakable[i] == "the first i characters can be segmented".
        boolean[] breakable = new boolean[s.length() + 1];
        breakable[0] = true;   // the empty prefix, segmented by taking nothing

        for (int end = 1; end <= s.length(); end++) {
            // Only split points within a word's length of `end` can matter.
            int earliestStart = Math.max(0, end - longestWord);
            for (int start = end - 1; start >= earliestStart; start--) {
                if (breakable[start] && dictionary.contains(s.substring(start, end))) {
                    breakable[end] = true;
                    break;
                }
            }
        }
        return breakable[s.length()];
    }
}

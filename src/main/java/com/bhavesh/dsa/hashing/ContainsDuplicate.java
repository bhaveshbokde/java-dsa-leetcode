package com.bhavesh.dsa.hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 217 - Contains Duplicate (Easy).
 *
 * <p>Reports whether any value appears more than once.
 *
 * <p>The useful detail is that {@link Set#add} already answers the question: it
 * returns {@code false} when the element was present. So detection needs one
 * pass and no second lookup, and it short-circuits on the first repeat rather
 * than building the whole set first.
 *
 * <p>Sorting then scanning adjacent pairs is the O(1)-extra-space alternative
 * at O(n log n) time; it is provided separately because that trade is the point
 * of the problem.
 *
 * <p>Time O(n) expected, auxiliary space O(n).
 */
public final class ContainsDuplicate {

    private ContainsDuplicate() {
    }

    public static boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Set<Integer> seen = new HashSet<>(nums.length * 2);
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sort-based variant: O(n log n) time but O(1) auxiliary space for
     * primitives, and it short-circuits on the first adjacent pair.
     *
     * <p>Sorts a copy, so the caller's array is untouched.
     */
    public static boolean containsDuplicateBySorting(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] == sorted[i - 1]) {
                return true;
            }
        }
        return false;
    }
}

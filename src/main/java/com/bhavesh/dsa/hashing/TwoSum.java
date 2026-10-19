package com.bhavesh.dsa.hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1 - Two Sum (Easy).
 *
 * <p>Returns the indices of the two numbers that add to {@code target}. Exactly
 * one such pair is guaranteed.
 *
 * <p>The brute-force double loop is O(n^2). The reframing that removes the
 * inner loop: at index {@code i} the question is not "which later element pairs
 * with me" but "have I already seen {@code target - nums[i]}". A hash map of
 * value to index answers that in O(1), so one pass suffices.
 *
 * <p>Checking the map <em>before</em> inserting the current element is what
 * prevents an element from pairing with itself when {@code target} is exactly
 * {@code 2 * nums[i]}.
 *
 * <p>Sorting and using two pointers is O(n log n) and destroys the original
 * indices, which the problem asks for - recovering them costs another pass and
 * careful duplicate handling. Hashing is both faster and simpler here.
 *
 * <p>Time O(n), auxiliary space O(n).
 */
public final class TwoSum {

    private TwoSum() {
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // The complement is computed in long deliberately. In int
            // arithmetic `target - nums[i]` can wrap: for target = -2 and
            // nums[i] = Integer.MAX_VALUE it wraps back to MAX_VALUE, which
            // then "matches" another MAX_VALUE and reports a pair that only
            // sums correctly under silent two's-complement overflow. Widening
            // first and range-checking makes that case a genuine no-match.
            long complement = (long) target - nums[i];
            if (complement >= Integer.MIN_VALUE && complement <= Integer.MAX_VALUE) {
                Integer partnerIndex = seen.get((int) complement);
                if (partnerIndex != null) {
                    return new int[] {partnerIndex, i};
                }
            }
            seen.put(nums[i], i);
        }
        throw new IllegalArgumentException("no pair sums to " + target);
    }
}

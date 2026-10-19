package com.bhavesh.dsa.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 560 - Subarray Sum Equals K (Medium).
 *
 * <p>Counts the contiguous subarrays summing to exactly {@code k}.
 *
 * <p>A sliding window is the instinctive choice and is wrong here: the values
 * may be negative, so extending the window does not monotonically increase the
 * sum and there is no shrink rule that preserves correctness.
 *
 * <p>Prefix sums fix that. With {@code P[i]} the sum of the first {@code i}
 * elements, the sum of the subarray ending at {@code i} and starting after
 * {@code j} is {@code P[i] - P[j]}. Asking for that to equal {@code k} is
 * asking how many earlier prefixes equal {@code P[i] - k} - a hash-map lookup.
 *
 * <p>Seeding the map with {@code {0: 1}} accounts for subarrays that start at
 * index 0, whose prefix predecessor is the empty prefix.
 *
 * <p>Time O(n), auxiliary space O(n).
 */
public final class SubarraySumEqualsK {

    private SubarraySumEqualsK() {
    }

    public static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Long keys: prefix sums of int values can exceed int range.
        Map<Long, Integer> prefixCounts = new HashMap<>();
        prefixCounts.put(0L, 1);

        long prefix = 0;
        int count = 0;
        for (int num : nums) {
            prefix += num;
            count += prefixCounts.getOrDefault(prefix - k, 0);
            prefixCounts.merge(prefix, 1, Integer::sum);
        }
        return count;
    }
}

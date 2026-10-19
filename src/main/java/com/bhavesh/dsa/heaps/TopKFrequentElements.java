package com.bhavesh.dsa.heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 347 - Top K Frequent Elements (Medium).
 *
 * <p>Returns the {@code k} most frequent values.
 *
 * <p>Counting with a hash map is O(n) and unavoidable. The question is how to
 * extract the top k from the counts:
 *
 * <ul>
 *   <li>sort the distinct values by frequency - O(d log d) for d distinct;</li>
 *   <li>a min-heap of size k - O(d log k), better when k is small;</li>
 *   <li><b>bucket sort by frequency</b> - O(n) overall, used here.</li>
 * </ul>
 *
 * <p>Bucket sort applies because frequencies are bounded: no value can occur
 * more than {@code n} times, so an array indexed by frequency has exactly
 * {@code n + 1} slots. Walking it from the high end collects the answer without
 * any comparison sorting, which is what makes the whole algorithm linear - and
 * strictly better than the O(n log n) the problem's follow-up asks you to beat.
 *
 * <p>Time O(n), auxiliary space O(n).
 */
public final class TopKFrequentElements {

    private TopKFrequentElements() {
    }

    public static int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums must be non-empty");
        }
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.merge(num, 1, Integer::sum);
        }
        if (k < 1 || k > counts.size()) {
            throw new IllegalArgumentException("k must be within 1.." + counts.size() + " but was " + k);
        }

        // buckets[f] holds every value occurring exactly f times.
        List<List<Integer>> buckets = new ArrayList<>(nums.length + 1);
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(null);
        }
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int frequency = entry.getValue();
            if (buckets.get(frequency) == null) {
                buckets.set(frequency, new ArrayList<>());
            }
            buckets.get(frequency).add(entry.getKey());
        }

        int[] answer = new int[k];
        int filled = 0;
        for (int frequency = nums.length; frequency >= 1 && filled < k; frequency--) {
            List<Integer> bucket = buckets.get(frequency);
            if (bucket == null) {
                continue;
            }
            for (int value : bucket) {
                if (filled == k) {
                    break;
                }
                answer[filled++] = value;
            }
        }
        return answer;
    }
}

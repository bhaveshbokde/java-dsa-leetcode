package com.bhavesh.dsa.twopointers;

/**
 * LeetCode 11 - Container With Most Water (Medium).
 *
 * <p>Two lines and the x-axis form a container; returns the largest area.
 *
 * <p>Area is {@code (right - left) * min(height[left], height[right])}. Checking
 * all pairs is O(n^2). Starting from the widest pair and closing inwards is
 * O(n), and the move rule is what makes it correct: always advance the pointer
 * at the <em>shorter</em> line.
 *
 * <p>Why discarding the shorter line is safe: any other container using that
 * shorter line has width no greater than the current one (the pointers only
 * close) and height no greater than that same shorter line (the minimum caps
 * it). So every remaining pairing involving it is dominated by the area just
 * measured, and it can be dropped without losing the optimum.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class ContainerWithMostWater {

    private ContainerWithMostWater() {
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int best = 0;
        while (left < right) {
            int shorter = Math.min(height[left], height[right]);
            best = Math.max(best, (right - left) * shorter);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return best;
    }
}

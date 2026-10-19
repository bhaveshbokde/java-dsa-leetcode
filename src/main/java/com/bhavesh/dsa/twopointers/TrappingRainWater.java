package com.bhavesh.dsa.twopointers;

/**
 * LeetCode 42 - Trapping Rain Water (Hard).
 *
 * <p>Returns the total water trapped between bars of the given heights.
 *
 * <p>Per-column reasoning: the water standing above column {@code i} is
 * {@code min(tallestToTheLeft, tallestToTheRight) - height[i]}, floored at
 * zero. Computing those two arrays explicitly is O(n) time and O(n) space, and
 * is a perfectly good answer.
 *
 * <p>The two-pointer form removes the arrays. It keeps running maxima from both
 * ends and always processes the side whose running maximum is smaller. That
 * side's answer is already determined: if {@code leftMax <= rightMax}, then the
 * true right-hand maximum for the left column is at least {@code rightMax},
 * hence at least {@code leftMax}, so the binding constraint is {@code leftMax}
 * regardless of what lies between. The uncertainty on the far side cannot
 * change the result, so that column can be settled immediately.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class TrappingRainWater {

    private TrappingRainWater() {
    }

    public static int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int water = 0;
        while (left < right) {
            if (leftMax <= rightMax) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left];
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
            }
        }
        return water;
    }

    /**
     * Prefix/suffix maxima variant, kept as an executable reference for the
     * two-pointer version and as the form that is easier to derive under
     * pressure.
     *
     * <p>Time O(n), auxiliary space O(n).
     */
    public static int trapWithPrefixArrays(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return water;
    }
}

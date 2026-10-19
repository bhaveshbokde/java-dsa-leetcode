package com.bhavesh.dsa.greedy;

/**
 * LeetCode 55 - Jump Game (Medium), with the LeetCode 45 minimum-jump variant.
 *
 * <p>Each value is the maximum jump length from that index; decide whether the
 * last index is reachable.
 *
 * <p>Dynamic programming over "is index i reachable" is O(n^2) and correct.
 * The greedy solution is O(n), and the reason it is safe to be greedy is that
 * reachability is an interval property: if index {@code i} is reachable then so
 * is every index before it, so tracking a single furthest-reachable position
 * loses no information.
 *
 * <p>The loop fails fast when {@code i} exceeds that furthest position, which
 * means a zero-valued cell blocked all progress.
 *
 * <p>Time O(n), auxiliary space O(1).
 */
public final class JumpGame {

    private JumpGame() {
    }

    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int furthest = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > furthest) {
                return false;
            }
            furthest = Math.max(furthest, i + nums[i]);
            if (furthest >= nums.length - 1) {
                return true;
            }
        }
        return true;
    }

    /**
     * LeetCode 45 - fewest jumps to reach the last index, assuming it is
     * reachable.
     *
     * <p>This is a breadth-first search over the array collapsed into a scan.
     * Indices are processed in "levels": every index within the current jump
     * range belongs to the same jump count, and the furthest position any of
     * them can reach becomes the next level's boundary. Incrementing the
     * counter only when the current boundary is crossed is what makes the
     * result a minimum.
     *
     * <p>Time O(n), auxiliary space O(1).
     */
    public static int minJumps(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums must be non-empty");
        }
        if (nums.length == 1) {
            return 0;
        }
        int jumps = 0;
        int currentEnd = 0;
        int furthest = 0;
        // Stops before the last index: arriving there ends the process.
        for (int i = 0; i < nums.length - 1; i++) {
            furthest = Math.max(furthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = furthest;
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }
        if (currentEnd < nums.length - 1) {
            throw new IllegalArgumentException("the last index is not reachable");
        }
        return jumps;
    }
}

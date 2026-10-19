package com.bhavesh.dsa.greedy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JumpGameTest {

    @Test
    @DisplayName("LeetCode examples")
    void leetCodeExamples() {
        assertTrue(JumpGame.canJump(new int[] {2, 3, 1, 1, 4}));
        assertFalse(JumpGame.canJump(new int[] {3, 2, 1, 0, 4}));
    }

    @Test
    @DisplayName("single element is already at the end")
    void singleElement() {
        assertTrue(JumpGame.canJump(new int[] {0}));
        assertEquals(0, JumpGame.minJumps(new int[] {0}));
    }

    @Test
    @DisplayName("a leading zero blocks everything except a single-element array")
    void leadingZero() {
        assertFalse(JumpGame.canJump(new int[] {0, 1}));
    }

    @Test
    @DisplayName("empty and null inputs")
    void degenerate() {
        assertFalse(JumpGame.canJump(new int[] {}));
        assertFalse(JumpGame.canJump(null));
        assertThrows(IllegalArgumentException.class, () -> JumpGame.minJumps(new int[] {}));
    }

    @Test
    @DisplayName("minimum jumps for the LeetCode 45 examples")
    void minimumJumps() {
        assertEquals(2, JumpGame.minJumps(new int[] {2, 3, 1, 1, 4}));
        assertEquals(2, JumpGame.minJumps(new int[] {2, 3, 0, 1, 4}));
    }

    @Test
    @DisplayName("minJumps rejects an unreachable last index")
    void unreachable() {
        assertThrows(IllegalArgumentException.class, () -> JumpGame.minJumps(new int[] {1, 0, 1}));
    }

    @Test
    @DisplayName("canJump matches a dynamic-programming reference on randomised inputs")
    void canJumpMatchesDp() {
        Random random = new Random(5555L);
        for (int trial = 0; trial < 800; trial++) {
            int[] nums = new int[1 + random.nextInt(12)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(4);
            }
            assertEquals(dpCanJump(nums), JumpGame.canJump(nums.clone()),
                    () -> "mismatch for " + Arrays.toString(nums));
        }
    }

    @Test
    @DisplayName("minJumps matches a breadth-first reference on randomised reachable inputs")
    void minJumpsMatchesBfs() {
        Random random = new Random(45045L);
        for (int trial = 0; trial < 800; trial++) {
            int[] nums = new int[1 + random.nextInt(10)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(4);
            }
            if (!dpCanJump(nums)) {
                continue;
            }
            assertEquals(bfsMinJumps(nums), JumpGame.minJumps(nums.clone()),
                    () -> "mismatch for " + Arrays.toString(nums));
        }
    }

    private static boolean dpCanJump(int[] nums) {
        boolean[] reachable = new boolean[nums.length];
        reachable[0] = true;
        for (int i = 0; i < nums.length; i++) {
            if (!reachable[i]) {
                continue;
            }
            for (int step = 1; step <= nums[i] && i + step < nums.length; step++) {
                reachable[i + step] = true;
            }
        }
        return reachable[nums.length - 1];
    }

    private static int bfsMinJumps(int[] nums) {
        int[] distance = new int[nums.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        java.util.Deque<Integer> queue = new java.util.ArrayDeque<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int at = queue.poll();
            for (int step = 1; step <= nums[at] && at + step < nums.length; step++) {
                if (distance[at + step] == Integer.MAX_VALUE) {
                    distance[at + step] = distance[at] + 1;
                    queue.add(at + step);
                }
            }
        }
        return distance[nums.length - 1];
    }
}

package com.bhavesh.dsa.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 54 - Spiral Matrix (Medium).
 *
 * <p>Returns every element in clockwise spiral order.
 *
 * <p>There is no clever asymptotic win here - every cell must be visited, so
 * O(m * n) is optimal. The difficulty is purely about bookkeeping, and tracking
 * four shrinking boundaries is the formulation least prone to off-by-one
 * errors: walk the top row, the right column, then the bottom row and left
 * column, contracting the relevant boundary after each.
 *
 * <p>The two guards before the bottom row and left column are essential rather
 * than defensive. In a single remaining row, the top and bottom boundaries
 * coincide, and walking the bottom row would emit that row a second time in
 * reverse. The same applies to a single remaining column.
 *
 * <p>Time O(m * n), auxiliary space O(1) beyond the output list.
 */
public final class SpiralMatrix {

    private SpiralMatrix() {
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> out = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return out;
        }
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int col = left; col <= right; col++) {
                out.add(matrix[top][col]);
            }
            top++;

            for (int row = top; row <= bottom; row++) {
                out.add(matrix[row][right]);
            }
            right--;

            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    out.add(matrix[bottom][col]);
                }
                bottom--;
            }

            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    out.add(matrix[row][left]);
                }
                left++;
            }
        }
        return out;
    }
}

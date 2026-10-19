package com.bhavesh.dsa.matrix;

/**
 * LeetCode 48 - Rotate Image (Medium).
 *
 * <p>Rotates an n x n matrix 90 degrees clockwise, in place.
 *
 * <p>Copying into a fresh matrix with {@code out[j][n-1-i] = in[i][j]} is the
 * obvious O(n^2) time solution, but costs O(n^2) extra space, which the problem
 * forbids.
 *
 * <p>The in-place route decomposes the rotation into two involutions:
 * transpose, then reverse each row. Transposing maps {@code (i, j)} to
 * {@code (j, i)}; reversing each row then maps {@code (j, i)} to
 * {@code (j, n-1-i)} - precisely a clockwise quarter turn. Both steps are
 * trivially in place.
 *
 * <p>The transpose loop must start at {@code j = i}, not {@code j = 0}.
 * Swapping the full square would swap every pair twice and leave the matrix
 * unchanged.
 *
 * <p>Time O(n^2) - unavoidable, since every cell moves. Auxiliary space O(1).
 */
public final class RotateImage {

    private RotateImage() {
    }

    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length < 2) {
            return;
        }
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        for (int[] row : matrix) {
            for (int left = 0, right = n - 1; left < right; left++, right--) {
                int tmp = row[left];
                row[left] = row[right];
                row[right] = tmp;
            }
        }
    }
}

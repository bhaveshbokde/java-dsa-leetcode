package com.bhavesh.dsa.unionfind;

/**
 * LeetCode 547 - Number of Provinces (Medium).
 *
 * <p>Counts connected components in an undirected graph given as an adjacency
 * matrix.
 *
 * <p>Depth-first or breadth-first search solves this equally well in
 * O(n^2) - the matrix must be read in full either way, so that bound is
 * optimal. Union-Find is used here because it extends to the incremental case
 * that search does not: if edges arrive over time and the component count is
 * queried after each, the DSU answers in near-constant time per edge, whereas
 * a traversal would re-run from scratch.
 *
 * <p>Only the strict upper triangle is scanned. The matrix is symmetric, so the
 * lower half would repeat every union, and the diagonal is a self-edge that
 * carries no information.
 *
 * <p>Time O(n^2 * alpha(n)), auxiliary space O(n).
 */
public final class NumberOfProvinces {

    private NumberOfProvinces() {
    }

    public static int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        int n = isConnected.length;
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    dsu.union(i, j);
                }
            }
        }
        return dsu.componentCount();
    }
}

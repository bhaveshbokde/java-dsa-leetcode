package com.bhavesh.dsa.unionfind;

/**
 * Disjoint Set Union with union by size and path compression.
 *
 * <p>Maintains a partition of {@code 0..n-1} under two operations: merge two
 * sets, and ask whether two elements share a set.
 *
 * <p>Both optimisations are needed, and they address different failures:
 *
 * <ul>
 *   <li><b>Union by size</b> attaches the smaller tree under the larger, which
 *       bounds the height at O(log n). Without it, repeatedly unioning in the
 *       wrong order builds a linked list and every find becomes O(n).</li>
 *   <li><b>Path compression</b> re-points every node visited during a find
 *       directly at the root, so the work is paid once rather than on every
 *       subsequent query.</li>
 * </ul>
 *
 * <p>Together they give an amortised cost of O(alpha(n)) per operation, where
 * alpha is the inverse Ackermann function - at most 4 for any input that fits
 * in memory, hence "effectively constant". Claiming literal O(1) would be
 * wrong, even though the distinction never matters in practice.
 *
 * <p>The find is iterative in two passes rather than recursive, so a degenerate
 * chain cannot overflow the stack before compression has had a chance to run.
 */
public final class DisjointSetUnion {

    private final int[] parent;
    private final int[] size;
    private int componentCount;

    public DisjointSetUnion(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        componentCount = n;
    }

    /** Representative of {@code x}'s set, compressing the path on the way. */
    public int find(int x) {
        checkBounds(x);
        int root = x;
        while (parent[root] != root) {
            root = parent[root];
        }
        // Second pass: point everything on the path straight at the root.
        int current = x;
        while (parent[current] != root) {
            int next = parent[current];
            parent[current] = root;
            current = next;
        }
        return root;
    }

    /** Merges the two sets; returns false if they were already joined. */
    public boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return false;
        }
        // Attach the smaller tree beneath the larger one.
        if (size[rootA] < size[rootB]) {
            int tmp = rootA;
            rootA = rootB;
            rootB = tmp;
        }
        parent[rootB] = rootA;
        size[rootA] += size[rootB];
        componentCount--;
        return true;
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }

    /** Number of disjoint sets remaining. */
    public int componentCount() {
        return componentCount;
    }

    /** Size of the set containing {@code x}. */
    public int componentSize(int x) {
        return size[find(x)];
    }

    private void checkBounds(int x) {
        if (x < 0 || x >= parent.length) {
            throw new IndexOutOfBoundsException("element " + x + " is outside 0.." + (parent.length - 1));
        }
    }
}

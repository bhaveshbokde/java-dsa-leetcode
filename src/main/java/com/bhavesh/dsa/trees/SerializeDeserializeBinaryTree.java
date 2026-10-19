package com.bhavesh.dsa.trees;

import com.bhavesh.dsa.support.TreeNode;
import java.util.Iterator;
import java.util.List;

/**
 * LeetCode 297 - Serialize and Deserialize Binary Tree (Hard).
 *
 * <p>Converts a tree to a string and back, such that the reconstruction is
 * structurally identical to the original.
 *
 * <p>The central fact: <b>a traversal alone does not identify a tree</b>.
 * Pre-order {@code 1,2} could mean 2 is a left child or a right child. Two
 * traversals resolve it (that is LeetCode 105), but a single traversal
 * <b>does</b> suffice once <b>explicit null markers</b> are included - the
 * markers record precisely the shape information a bare traversal discards.
 *
 * <p>Pre-order is the natural choice because the root comes first, so
 * deserialisation can build top-down by consuming tokens in the order they were
 * written, with no index arithmetic and no lookahead.
 *
 * <p>Two format decisions that look cosmetic and are not:
 *
 * <ul>
 *   <li>A <b>delimiter</b> between tokens is mandatory. Without it {@code 1,2}
 *       and {@code 12} are indistinguishable, and negative values make it
 *       worse.</li>
 *   <li>The <b>null marker</b> must be a token that cannot be confused with a
 *       value. {@code "#"} is safe; an empty token between delimiters would
 *       also work but is easy to mishandle.</li>
 * </ul>
 *
 * <p>Deserialisation consumes tokens through a shared iterator rather than an
 * index, for the same reason the preorder cursor is shared in LeetCode 105:
 * consumption is strictly sequential, and recomputing positions from subtree
 * sizes is where off-by-one errors appear.
 *
 * <p>Time O(n) each way; space O(n) for the string plus O(h) recursion.
 */
public final class SerializeDeserializeBinaryTree {

    private static final String NULL_MARKER = "#";
    private static final String DELIMITER = ",";

    private SerializeDeserializeBinaryTree() {
    }

    public static String serialize(TreeNode root) {
        StringBuilder out = new StringBuilder();
        writePreorder(root, out);
        return out.toString();
    }

    private static void writePreorder(TreeNode node, StringBuilder out) {
        if (node == null) {
            out.append(NULL_MARKER).append(DELIMITER);
            return;
        }
        out.append(node.val).append(DELIMITER);
        writePreorder(node.left, out);
        writePreorder(node.right, out);
    }

    public static TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        // -1 keeps the trailing empty token out of the stream; split's default
        // would silently drop it and misalign nothing here, but being explicit
        // avoids depending on that behaviour.
        List<String> tokens = List.of(data.split(DELIMITER, -1));
        Iterator<String> cursor = tokens.iterator();
        return readPreorder(cursor);
    }

    private static TreeNode readPreorder(Iterator<String> cursor) {
        if (!cursor.hasNext()) {
            return null;
        }
        String token = cursor.next();
        if (token.isEmpty() || NULL_MARKER.equals(token)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.left = readPreorder(cursor);
        node.right = readPreorder(cursor);
        return node;
    }
}

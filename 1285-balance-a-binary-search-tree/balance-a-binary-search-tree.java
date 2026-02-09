/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 class Solution {

    private List<Integer> inorderList = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        // Step 1: Store inorder traversal
        inorder(root);

        // Step 2: Build balanced BST
        return buildTree(0, inorderList.size() - 1);
    }

    // Inorder traversal (BST → sorted array)
    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);
        inorderList.add(root.val);
        inorder(root.right);
    }

    // Build balanced BST from sorted array
    private TreeNode buildTree(int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(inorderList.get(mid));

        node.left = buildTree(left, mid - 1);
        node.right = buildTree(mid + 1, right);

        return node;
    }
}


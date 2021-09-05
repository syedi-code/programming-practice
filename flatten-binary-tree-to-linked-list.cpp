/****
** Problem: Flatten Binary Tree to Linked List (https://leetcode.com/problems/flatten-binary-tree-to-linked-list/)
** Runtime: 4 ms, faster than 86.41% of C++ online submissions for Flatten Binary Tree to Linked List.
** Memory Usage: 13.1 MB, less than 7.08% of C++ online submissions for Flatten Binary Tree to Linked List.
** Time Complexity: O(n)
** Space Complexity: O(n)
****/

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

class Solution {
public:    
    void preorder(queue<int>& queue, TreeNode* root) {
        if (root == nullptr) {
            return;
        }
        
        queue.push(root->val);
        preorder(queue, root->left);
        preorder(queue, root->right);
    }
    
    void flatten(TreeNode* root) {
        TreeNode* head = root;
        queue<int> queue;
        
        if (!root) return;
        
        preorder(queue, root);
        queue.pop();
        while (queue.size() > 0) {
            root->left = nullptr;
            root->right = new TreeNode(queue.front());
            queue.pop();
            root = root->right;
        }
        root = head;
    }
};


/****
** Problem: Remove Nth Node From End of List (https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
** Runtime: 4 ms, faster than 80.47% of C++ online submissions for Remove Nth Node From End of List.
** Memory Usage: 10.6 MB, less than 96.39% of C++ online submissions for Remove Nth Node From End of List.
****/

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */

class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        int size = 0;
        ListNode* curr = head;
        ListNode* deletePtr = head;
        
        if (head->next == nullptr) {
            head = nullptr;
            return head;
        }
        
        while (curr != nullptr) {
            size++;
            curr = curr->next;
        }
        
        for (int i = 0; i < size - n; i++) {
            deletePtr = deletePtr->next;
        }
        
        // 3 cases: deletePtr is head, deletePtr is tail, deletePtr is in middle of list
        if (deletePtr == head) {
            head = deletePtr->next;
            delete deletePtr;
        } else if (deletePtr->next == nullptr) {
            curr = head;
            for (int i = 0; i < size - n - 1; i++) {
                curr = curr->next;
            }
            curr->next = nullptr;
            delete deletePtr;
        } else {
            curr = head;
            for (int i = 0; i < size - n - 1; i++) {
                curr = curr->next;
            }
            curr->next = deletePtr->next;
            delete deletePtr;
        }
        
        return head;
    }
};

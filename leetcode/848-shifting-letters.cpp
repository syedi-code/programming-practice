/****
** Problem: Shifting Letters (https://leetcode.com/problems/shifting-letters/)
** Runtime: Runtime: 132 ms, faster than 38.95% of C++ online submissions for Shifting Letters.
** Memory Usage: 67.9 MB, less than 21.28% of C++ online submissions for Shifting Letters
** Time Complexity: O(n)
** Space Complexity: O(1)
****/

class Solution {
public:
    char offsetChar(char c, int offset) {
        int distToZ = 122 - int(c);
        
        if (distToZ < offset) {
            int index = 96 + (offset - distToZ);
            return char(index);
        } else {
            return char(int(c) + offset);
        }
    }
    
    string shiftingLetters(string s, vector<int>& shifts) {
        int sum = 0;
        
        for (int i = shifts.size() - 1; i >= 0; i--) {
            sum += shifts[i];
            sum = sum % 26;
            s[i] = offsetChar(s[i], sum);
        }
        
        return s;
    }
};

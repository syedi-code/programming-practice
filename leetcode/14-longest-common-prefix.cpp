/****
** Problem: Longest Common Prefix (https://leetcode.com/problems/longest-common-prefix/)
** Runtime: 4 ms, faster than 72.53% of C++ online submissions for Longest Common Prefix.
** Memory Usage: 9.2 MB, less than 76.09% of C++ online submissions for Longest Common Prefix.
****/

class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        string prefix = "";
        bool endCommonPrefix = false;
        int i = 0;
        
        if (strs.size() == 1) {
            return strs[0];
        }
        
        while(strs[0][i] != NULL) {
            char c = strs[0][i];
            for (int j = 0; j < strs.size(); j++) {
                if (strs[j][i] != c) {
                    endCommonPrefix = true;
                    break;
                }
            }
            
            if (endCommonPrefix == true) {
                break;
            } else {
                prefix.push_back(c);
            }
            
            i++;
        }
        
        return prefix;
    }
};

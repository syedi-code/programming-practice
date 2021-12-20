/****
** Problem: ZigZag Conversion (https://leetcode.com/problems/zigzag-conversion/submissions/)
** Runtime: 8 ms, faster than 87.73% of C++ online submissions for ZigZag Conversion.
** Memory Usage: 10.8 MB, less than 40.68% of C++ online submissions for ZigZag Conversion.
****/

class Solution {
public:
    string convert(string s, int numRows) {
        vector<string> outs(numRows);
        string answer = "";
        int i = 1;
        int n = (2 * numRows) - 2;
        
        if (s.size() < 2 || numRows == 1) {
            return s;
        }
        
        outs[0].push_back(s[0]);
        while (i < s.size()) {
            int a = i % n;
            if (a <= n/2) {
                outs[a].push_back(s[i]);
            } else if (a > n/2) {
                outs[n - a].push_back(s[i]);
            }            
            
            i++;
        }
        
        for (int i = 0; i < outs.size(); i++) {
            answer.append(outs[i]);
        }
        
        return answer;
    }
};

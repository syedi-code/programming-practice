/****
** Problem: Count and Say (https://leetcode.com/problems/count-and-say/)
** Runtime: Runtime: 8 ms, faster than 70.71% of C++ online submissions for Count and Say.
** Memory Usage: 6.6 MB, less than 53.52% of C++ online submissions for Count and Say.
** Complexity: N/A
****/

class Solution {
public:    
    string countAndSay(int n) {
        string s = "1";
        
        for (int i = 1; i < n; i++) {
            int j = 0;
            string temp = "";
            
            while(j < s.size()) {
                int prefix = 0;
                char prev = s[j];
                while (s[j] == prev) {
                    prefix++;
                    j++;
                }
                
                temp.append(to_string(prefix));
                temp.push_back(prev);
            }
            
            s = temp;
        }
        
        return s;
    }
};

/****
** Problem: Container With Most Water (https://leetcode.com/problems/container-with-most-water/submissions/)
** Runtime: 84 ms, faster than 60.98% of C++ online submissions for Container With Most Water.
** Memory Usage: 59 MB, less than 79.95% of C++ online submissions for Container With Most Water.
****/

// lol
class Solution {
public:
    int maxArea(vector<int>& height) {
        int area = 0;
        int begin = 0;
        int end = height.size() - 1;
        
        while (begin < end) {
            int h = min(height[begin], height[end]);
            area = max(area, h * (end - begin));
            
            while (height[begin] <= h && begin < end) begin++;
            while (height[end] <= h && begin < end) end--;
        }
        
        return area;
    }
};

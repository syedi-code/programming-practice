/****
** Problem: 3Sum Closest (https://leetcode.com/problems/3sum-closest/)
** Runtime: 4 ms, faster than 97.71% of C++ online submissions for 3Sum Closest.
** Memory Usage: 9.7 MB, less than 92.49% of C++ online submissions for 3Sum Closest.
** Complexity: O(n*n)
****/

class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        int sum = 0;
        int distance = INT_MAX;
        
        sort(nums.begin(), nums.end());
        for (int i = 0; i < nums.size(); i++) {
            int begin = i + 1;
            int end = nums.size() - 1;
            
            while (begin < end) {
                int s = nums[i] + nums[begin] + nums[end];
                int d = abs(target - s);
                
                if (s == target) return s;
                if (s < target) begin++;
                if (s > target) end--;
                
                if (d < distance) {
                    distance = d;
                    sum = s;
                }
            }
        }
        
        return sum;
    }
};

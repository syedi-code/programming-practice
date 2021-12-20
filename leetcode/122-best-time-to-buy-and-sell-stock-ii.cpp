/****
** Problem: Best Time to Buy and Sell Stock II (https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)
** Runtime: 4 ms, faster than 91.20% of C++ online submissions for Best Time to Buy and Sell Stock II.
** Memory Usage: 13 MB, less than 45.80% of C++ online submissions for Best Time to Buy and Sell Stock II.
** Time Complexity: O(n)
** Space Complexity: O(1)
****/

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int start = 0;
        int end = 0;
        int profit = 0;
        
        while (start < prices.size() - 1) {
            end++;
            int currProfit = 0;
            while (end < prices.size() && prices[end] > prices[end - 1]) {
                currProfit = prices[end] - prices[start];
                end++;
            }
            profit += currProfit;
            start = end;
        }
        
        return profit;
    }
};

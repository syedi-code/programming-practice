/****
** Problem: Number of Islands (https://leetcode.com/problems/number-of-islands/)
** Runtime: 16 ms, faster than 61.01% of C++ online submissions for Number of Islands.
** Memory Usage: 9.5 MB, less than 66.53% of C++ online submissions for Number of Islands.
** Time Complexity: O(m*n), m = grid.length, n = grid[i].length
****/

class Solution {
public:
    void DFS(vector<vector<char>>& grid, int i, int j) {
        if (i < 0 || i >= grid.size() || j < 0 || j >= grid[i].size() || grid[i][j] == '0') {
            return;
        }
        
        grid[i][j] = '0';
        DFS(grid, i, j - 1); // left
        DFS(grid, i, j + 1); // right
        DFS(grid, i + 1, j); // up
        DFS(grid, i - 1, j); // down
    }
    
    int numIslands(vector<vector<char>>& grid) {
        int c = 0;
        
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid[i].size(); j++) {
                if (grid[i][j] == '1') {
                    c++;
                    DFS(grid, i, j);
                }
            }
        }
        
        return c;
    }
};

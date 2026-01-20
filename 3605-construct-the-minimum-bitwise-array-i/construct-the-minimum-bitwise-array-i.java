import java.util.*;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int p = nums.get(i);
            ans[i] = -1;

            // Try all possible x
            for (int x = 0; x <= p; x++) {
                if ((x | (x + 1)) == p) {
                    ans[i] = x;
                    break; // smallest found
                }
            }
        }
        return ans;
    }
}

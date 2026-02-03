
class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        int i = 0;

        // 1️⃣ Strictly increasing
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }

        // must have at least one increasing step
        if (i == 0) return false;

        // 2️⃣ Strictly decreasing
        int p = i;
        while (i + 1 < n && nums[i] > nums[i + 1]) {
            i++;
        }

        // must have at least one decreasing step
        if (i == p) return false;

        // 3️⃣ Strictly increasing again
        int q = i;
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }

        // must have at least one increasing step
        if (i == q) return false;

        // must reach the end
        return i == n - 1;
    }
}

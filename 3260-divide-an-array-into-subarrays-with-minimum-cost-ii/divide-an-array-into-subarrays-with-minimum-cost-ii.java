class Solution {

    TreeMap<Integer, Integer> small = new TreeMap<>();
    TreeMap<Integer, Integer> large = new TreeMap<>();

    int smallSize = 0, largeSize = 0;

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        int need = k - 1;

        long sumSmall = 0;
        long ans = Long.MAX_VALUE;

        int L = 1;

        for (int R = 1; R < n; R++) {
            addSmall(nums[R]);
            sumSmall += nums[R];

            sumSmall = rebalance(sumSmall, need);

            if (R - L > dist) {
                if (small.containsKey(nums[L])) {
                    removeSmall(nums[L]);
                    sumSmall -= nums[L];
                } else {
                    removeLarge(nums[L]);
                }
                L++;
                sumSmall = rebalance(sumSmall, need);
            }

            if (smallSize == need) {
                ans = Math.min(ans, nums[0] + sumSmall);
            }
        }
        return ans;
    }

    private long rebalance(long sumSmall, int need) {
        while (smallSize > need) {
            int x = small.lastKey();
            removeSmall(x);
            sumSmall -= x;
            addLarge(x);
        }

        while (smallSize < need && largeSize > 0) {
            int x = large.firstKey();
            removeLarge(x);
            addSmall(x);
            sumSmall += x;
        }
        return sumSmall;
    }

    // -------- helper methods --------

    private void addSmall(int x) {
        small.put(x, small.getOrDefault(x, 0) + 1);
        smallSize++;
    }

    private void removeSmall(int x) {
        small.put(x, small.get(x) - 1);
        if (small.get(x) == 0) small.remove(x);
        smallSize--;
    }

    private void addLarge(int x) {
        large.put(x, large.getOrDefault(x, 0) + 1);
        largeSize++;
    }

    private void removeLarge(int x) {
        large.put(x, large.get(x) - 1);
        if (large.get(x) == 0) large.remove(x);
        largeSize--;
    }
}

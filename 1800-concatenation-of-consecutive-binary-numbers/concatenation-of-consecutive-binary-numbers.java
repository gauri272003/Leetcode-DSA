
class Solution {
    public int concatenatedBinary(int n) {
        long mod = 1_000_000_007;
        long result = 0;

        for (int i = 1; i <= n; i++) {
            int bits = 32 - Integer.numberOfLeadingZeros(i);
            result = ((result << bits) % mod + i) % mod;
        }

        return (int) result;
    }
}
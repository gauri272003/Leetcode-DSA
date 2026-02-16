
class Solution {
    public int reverseBits(int n) {
        int result = 0;
        
        for (int i = 0; i < 32; i++) {
            result = result << 1;     // Make space for next bit
            result = result | (n & 1); // Add last bit of n
            n = n >>> 1;              // Unsigned right shift
        }
        
        return result;
    }
}

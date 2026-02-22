
class Solution {
    public int binaryGap(int n) {
        int lastPosition = -1;  // stores position of previous 1
        int maxDistance = 0;
        int position = 0;       // current bit position
        
        while (n > 0) {
            if ((n & 1) == 1) {   // if current bit is 1
                if (lastPosition != -1) {
                    maxDistance = Math.max(maxDistance, position - lastPosition);
                }
                lastPosition = position;
            }
            n = n >> 1;   // right shift
            position++;
        }
        
        return maxDistance;
    }
}
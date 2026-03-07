
class Solution {
    public int minFlips(String s) {
        
        int n = s.length();
        String t = s + s;

        int ans = Integer.MAX_VALUE;
        int diff1 = 0, diff2 = 0;

        for(int i = 0; i < 2*n; i++) {

            char expected1 = (i % 2 == 0) ? '0' : '1';
            char expected2 = (i % 2 == 0) ? '1' : '0';

            if(t.charAt(i) != expected1) diff1++;
            if(t.charAt(i) != expected2) diff2++;

            if(i >= n) {
                int j = i - n;

                char prev1 = (j % 2 == 0) ? '0' : '1';
                char prev2 = (j % 2 == 0) ? '1' : '0';

                if(t.charAt(j) != prev1) diff1--;
                if(t.charAt(j) != prev2) diff2--;
            }

            if(i >= n - 1) {
                ans = Math.min(ans, Math.min(diff1, diff2));
            }
        }

        return ans;
    }
}

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        final int MOD = 1_000_000_007;

   
        int[] h = new int[hFences.length + 2];
        int[] v = new int[vFences.length + 2];

        h[0] = 1;
        h[1] = m;
        for (int i = 0; i < hFences.length; i++) {
            h[i + 2] = hFences[i];
        }

        v[0] = 1;
        v[1] = n;
        for (int i = 0; i < vFences.length; i++) {
            v[i + 2] = vFences[i];
        }

        Arrays.sort(h);
        Arrays.sort(v);

        
        Set<Integer> horizontalDiffs = new HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                horizontalDiffs.add(h[j] - h[i]);
            }
        }

        long maxSide = -1;

        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                int diff = v[j] - v[i];
                if (horizontalDiffs.contains(diff)) {
                    maxSide = Math.max(maxSide, diff);
                }
            }
        }

        if (maxSide == -1) return -1;

        return (int)((maxSide * maxSide) % MOD);
    }
}

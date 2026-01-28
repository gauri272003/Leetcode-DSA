
class Solution {

    static class State {
        int i, j, t;
        long cost;
        State(int i, int j, int t, long cost) {
            this.i = i;
            this.j = j;
            this.t = t;
            this.cost = cost;
        }
    }

    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        long INF = Long.MAX_VALUE / 4;

        long[][][] dist = new long[m][n][k + 1];
        for (long[][] a : dist)
            for (long[] b : a)
                Arrays.fill(b, INF);

        // collect cells sorted by value
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                cells.add(new int[]{grid[i][j], i, j});
        cells.sort(Comparator.comparingInt(a -> a[0]));

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));
        dist[0][0][0] = 0;
        pq.add(new State(0, 0, 0, 0));

        int[] dx = {1, 0};
        int[] dy = {0, 1};

        int[] ptr = new int[k + 1]; // teleport unlock pointers

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int i = cur.i, j = cur.j, t = cur.t;
            long cost = cur.cost;

            if (cost != dist[i][j][t]) continue;

            // normal moves
            for (int d = 0; d < 2; d++) {
                int ni = i + dx[d], nj = j + dy[d];
                if (ni < m && nj < n) {
                    long nc = cost + grid[ni][nj];
                    if (nc < dist[ni][nj][t]) {
                        dist[ni][nj][t] = nc;
                        pq.add(new State(ni, nj, t, nc));
                    }
                }
            }

            // teleport
            if (t < k) {
                while (ptr[t] < cells.size() && cells.get(ptr[t])[0] <= grid[i][j]) {
                    int[] c = cells.get(ptr[t]++);
                    int x = c[1], y = c[2];
                    if (cost < dist[x][y][t + 1]) {
                        dist[x][y][t + 1] = cost;
                        pq.add(new State(x, y, t + 1, cost));
                    }
                }
            }
        }

        long ans = INF;
        for (int t = 0; t <= k; t++)
            ans = Math.min(ans, dist[m - 1][n - 1][t]);

        return (int) ans;
    }
}

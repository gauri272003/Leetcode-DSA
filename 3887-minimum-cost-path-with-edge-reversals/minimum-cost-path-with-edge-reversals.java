class Solution {
    public int minCost(int n, int[][] edges) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new int[]{v, w});        // normal
            graph[v].add(new int[]{u, 2 * w});    // reversed
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
            new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));

        dist[0] = 0;
        pq.add(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int) cur[0];
            long d = cur[1];

            if (d > dist[u]) continue;

            for (int[] e : graph[u]) {
                int v = e[0], w = e[1];
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.add(new long[]{v, dist[v]});
                }
            }
        }

        return dist[n - 1] == Long.MAX_VALUE ? -1 : (int) dist[n - 1];
    }
}

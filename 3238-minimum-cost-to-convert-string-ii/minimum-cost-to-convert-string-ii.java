
class Solution {

    static final long INF = (long)1e18;

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int n = source.length();

        // Build graph
        Map<String, Map<String, Long>> graph = new HashMap<>();
        for (int i = 0; i < original.length; i++) {
            graph
                .computeIfAbsent(original[i], k -> new HashMap<>())
                .merge(changed[i], (long) cost[i], Math::min);
        }

        // Cache shortest paths from a given string
        Map<String, Map<String, Long>> distCache = new HashMap<>();

        // DP array
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {

            // Case 1: no operation needed
            if (source.charAt(i) == target.charAt(i)) {
                dp[i] = dp[i + 1];
            }

            // Case 2: try all substring operations
            for (int k = 0; k < original.length; k++) {
                String from = original[k];
                int len = from.length();

                if (i + len > n) continue;
                if (!source.startsWith(from, i)) continue;

                String to = target.substring(i, i + len);

                long convCost = getMinCost(from, to, graph, distCache);
                if (convCost == INF) continue;

                dp[i] = Math.min(dp[i], convCost + dp[i + len]);
            }
        }

        return dp[0] >= INF ? -1 : dp[0];
    }

    // Dijkstra on string graph
    private long getMinCost(
            String start,
            String end,
            Map<String, Map<String, Long>> graph,
            Map<String, Map<String, Long>> cache
    ) {
        if (start.equals(end)) return 0;

        if (cache.containsKey(start)) {
            return cache.get(start).getOrDefault(end, INF);
        }

        Map<String, Long> dist = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(start, 0));
        dist.put(start, 0L);

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            if (cur.cost > dist.get(cur.str)) continue;

            if (!graph.containsKey(cur.str)) continue;

            for (Map.Entry<String, Long> e : graph.get(cur.str).entrySet()) {
                String nxt = e.getKey();
                long newCost = cur.cost + e.getValue();

                if (newCost < dist.getOrDefault(nxt, INF)) {
                    dist.put(nxt, newCost);
                    pq.add(new Pair(nxt, newCost));
                }
            }
        }

        cache.put(start, dist);
        return dist.getOrDefault(end, INF);
    }

    static class Pair implements Comparable<Pair> {
        String str;
        long cost;

        Pair(String s, long c) {
            str = s;
            cost = c;
        }

        public int compareTo(Pair o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}

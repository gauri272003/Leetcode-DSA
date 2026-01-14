import java.util.*;

class Solution {

    static class Event {
        double y;
        int l, r;
        int type; // +1 start, -1 end
        Event(double y, int l, int r, int type) {
            this.y = y;
            this.l = l;
            this.r = r;
            this.type = type;
        }
    }

    static class SegmentTree {
        int[] count;
        double[] cover;
        double[] xs;
        int n;

        SegmentTree(double[] xs) {
            this.xs = xs;
            n = xs.length - 1;
            count = new int[4 * n];
            cover = new double[4 * n];
        }

        void update(int node, int l, int r, int ql, int qr, int val) {
            if (ql >= r || qr <= l) return;
            if (ql <= l && r <= qr) {
                count[node] += val;
            } else {
                int m = (l + r) / 2;
                update(node * 2, l, m, ql, qr, val);
                update(node * 2 + 1, m, r, ql, qr, val);
            }

            if (count[node] > 0) {
                cover[node] = xs[r] - xs[l];
            } else if (l + 1 == r) {
                cover[node] = 0;
            } else {
                cover[node] = cover[node * 2] + cover[node * 2 + 1];
            }
        }
    }

    public double separateSquares(int[][] squares) {

        List<Event> events = new ArrayList<>();
        Set<Double> xSet = new HashSet<>();

        for (int[] s : squares) {
            double x1 = s[0], y1 = s[1], l = s[2];
            xSet.add(x1);
            xSet.add(x1 + l);
        }

        double[] xs = xSet.stream().sorted().mapToDouble(Double::doubleValue).toArray();
        Map<Double, Integer> index = new HashMap<>();
        for (int i = 0; i < xs.length; i++) index.put(xs[i], i);

        for (int[] s : squares) {
            double x1 = s[0], y1 = s[1], l = s[2];
            int L = index.get(x1);
            int R = index.get(x1 + l);
            events.add(new Event(y1, L, R, +1));
            events.add(new Event(y1 + l, L, R, -1));
        }

        events.sort(Comparator.comparingDouble(e -> e.y));

        SegmentTree st = new SegmentTree(xs);

        double totalArea = 0;
        double prevY = events.get(0).y;

        // First pass: compute total area
        for (Event e : events) {
            double dy = e.y - prevY;
            totalArea += st.cover[1] * dy;
            st.update(1, 0, st.n, e.l, e.r, e.type);
            prevY = e.y;
        }

        double half = totalArea / 2.0;

        // Reset for second pass
        Arrays.fill(st.count, 0);
        Arrays.fill(st.cover, 0);

        double currArea = 0;
        prevY = events.get(0).y;

        // Second pass: find split y
        for (Event e : events) {
            double dy = e.y - prevY;
            double stripArea = st.cover[1] * dy;

            if (currArea + stripArea >= half) {
                double remaining = half - currArea;
                return prevY + remaining / st.cover[1];
            }

            currArea += stripArea;
            st.update(1, 0, st.n, e.l, e.r, e.type);
            prevY = e.y;
        }

        return prevY;
    }
}


class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Set<Integer> set = new HashSet<>();

        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){

                // area 0 rhombus
                set.add(grid[r][c]);

                for(int k = 1; r-k >= 0 && r+k < m && c-k >= 0 && c+k < n; k++){
                    int sum = 0;

                    int i = r-k, j = c;

                    // top -> right
                    while(i < r && j < c+k){
                        sum += grid[i][j];
                        i++; j++;
                    }

                    // right -> bottom
                    while(i < r+k && j > c){
                        sum += grid[i][j];
                        i++; j--;
                    }

                    // bottom -> left
                    while(i > r && j > c-k){
                        sum += grid[i][j];
                        i--; j--;
                    }

                    // left -> top
                    while(i > r-k && j < c){
                        sum += grid[i][j];
                        i--; j++;
                    }

                    set.add(sum);
                }
            }
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list, Collections.reverseOrder());

        int size = Math.min(3, list.size());
        int[] res = new int[size];

        for(int i = 0; i < size; i++)
            res[i] = list.get(i);

        return res;
    }
}
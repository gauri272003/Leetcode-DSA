
class Solution {
    public String getHappyString(int n, int k) {
        List<String> result = new ArrayList<>();
        char[] chars = {'a', 'b', 'c'};
        
        backtrack(n, "", chars, result);
        
        if (k > result.size()) return "";
        return result.get(k - 1);
    }
    
    private void backtrack(int n, String curr, char[] chars, List<String> result) {
        if (curr.length() == n) {
            result.add(curr);
            return;
        }
        
        for (char c : chars) {
            if (curr.length() == 0 || curr.charAt(curr.length() - 1) != c) {
                backtrack(n, curr + c, chars, result);
            }
        }
    }
}
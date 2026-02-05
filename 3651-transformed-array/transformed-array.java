class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for(int i=0; i<n; i++){
            if(nums[i] == 0){
                result[i] = nums[i];
            } else {
                int steps = nums[i];
                int targetIndex = (i + steps % n + n) %n;
                result[i] = nums[targetIndex];
            }
        }

        return result;
    }
}
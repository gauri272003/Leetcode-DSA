
class Solution {
    public int minOperations(String s) {
        int startWith0 = 0;
        int startWith1 = 0;
        
        for(int i = 0; i < s.length(); i++){
            
            // pattern: 010101
            if(i % 2 == 0){
                if(s.charAt(i) != '0') startWith0++;
                if(s.charAt(i) != '1') startWith1++;
            }
            else{
                if(s.charAt(i) != '1') startWith0++;
                if(s.charAt(i) != '0') startWith1++;
            }
        }
        
        return Math.min(startWith0, startWith1);
    }
}
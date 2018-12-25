//Runtime: 76 ms, faster than 21.32% of Java online submissions for Integer to Roman.
//How to Improvement? 

class Solution {
    int[] VALUES = {1,5,10,50,100,500,1000};
    String[] SYMS = {"I","V","X","L","C","D","M"};
    int maxLength = VALUES.length;

    public String intToRoman(int num) {

        return recToRoman(maxLength-1, num);

    }
    private String recToRoman(int i, int num){        
        if(i < 0 || i > maxLength-1){
            return "";
        }
        if(num < VALUES[i]){
            return recToRoman(i-1,num);
        }
        if(i+1<maxLength && i-1>=0 && i%2==1){
            if(num/VALUES[i-1] == 9){
                return SYMS[i-1]+ SYMS[i+1] + recToRoman(i-2, num%VALUES[i-1]);
            }else{
                return SYMS[i] + recToRoman(i-1, num-VALUES[i]);
            }
        }else if( i%2 == 0){
            if(num/VALUES[i] == 4 && i+1 <maxLength){
                return SYMS[i] + SYMS[i+1] + recToRoman(i-1, num-4*VALUES[i]);                    
            }else{
                String tempCat = "";
                for(int j=0; j< num/VALUES[i]; j++){                    
                    tempCat = tempCat + SYMS[i];
                }
                return tempCat + recToRoman(i-1, num%VALUES[i]);

            }
        }else
            return "";
    }
}

class RegularExpressionMatching {
    enum Result{
        TRUE, FALSE
    }
    Result[][] memo;
    public boolean isMatch(String s, String p) {
        // Due to star symbol existence, hard to regularly check from left to right 
        // since we are not sure how many indefinite numbers of characters to be matched
        // By divide-and-conquer, and narrow down the problem, recursive might be the best solution
        // since 1) it has multiple branches of cases 2) dynamic programming is needed       
	// Note about Java array need explicit memory allocation, otherwise it cannot be accessed at all (itself is null)
	// Time complexity, is S*P, (times of recursive calling), space complexity is the stacks when doing recursive calls, S*P

Space Complexity: The only memory we use is the O(TP)O(TP) boolean entries in our cache. Hence, the space complexity is O(TP)O(TP).
        
        memo = new Result[s.length()+1][p.length()+1];
        return recIsMatch(0,0,s,p);        
    }                
    private boolean recIsMatch(int i, int j, String s, String p){
        if(memo[i][j]!=null){
            return (memo[i][j]==Result.TRUE);
        }
        boolean ans;

        if(j<p.length()){
            if(i<s.length()){
                if(j+1<p.length() && p.charAt(j+1)=='*'){
                    ans =(charMatch(s.charAt(i),p.charAt(j)) && recIsMatch(i+1,j+1,s,p)) 
                        || recIsMatch(i,j+2,s,p);
                }else if(p.charAt(j) == '*'){
                    ans = charMatch(s.charAt(i), p.charAt(j-1)) && recIsMatch(i+1,j,s,p) 
                        || recIsMatch(i,j+1,s,p);
                }else{
                    ans = charMatch(s.charAt(i),p.charAt(j)) && recIsMatch(i+1,j+1,s,p);
                }
            }else{
                ans = (p.charAt(j) =='*' && recIsMatch(i,j+1,s,p)) || 
                    (j+1 < p.length() && p.charAt(j+1) == '*' && recIsMatch(i,j+2,s,p));
            }           
        }else{
            if(i==s.length()) ans = true;
            else ans = false;
        }
        
        memo[i][j] = ans?Result.TRUE:Result.FALSE;
        return (memo[i][j]==Result.TRUE);
    }
    
    private boolean charMatch(char s, char p){
        if(p == '.' || s == p){
            return true;
        }else{
            return false;
        }
    }
}

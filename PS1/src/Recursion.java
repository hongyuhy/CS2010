import java.util.*;

public class Recursion {
    
    public static void main(String args[]){
     int n = testFunction(6,1);
     
     System.out.println(n);
    }

static int testFunction (int n, int k){
    if(k==0 || k ==n ) return 1;
    return testFunction(n-1,k-1) + testFunction(n-1,k);
 }



static int test(int n){
    if(n<=1) return n;
    return test(n-1) + test(n-2);
    }
}
package practice;

import java.util.HashMap;
import java.util.Map;

public class Pract_III {
	//1
	public static int solutions (int a,int b,int c) {
		if (b*b - 4*a*c == 0)
			return 1;
		else if(b*b - 4*a*c > 0)
				return 2;
		else
			return 0;
					
	}
	//2
	public static int findZip(String s) {
		int ind = s.indexOf("zip");
		if(ind == -1)
			return ind;
		int res = s.indexOf("zip",ind+1);
		return res;
	}//3
	public static boolean checkPerfect(int n){
        int sum=0;
        for(int i=1;i<=n/2;i++){
            if (n%i==0)sum+=i;
        }
        return n==sum;
    }//4
	public static String  flipEndChars(String s) {
		if (s.length() < 2)
			return "Incompatible";
		else if (s.charAt(0) == s.charAt(s.length()-1))
				return "two is a pair";
		else 
		return s.charAt(s.length()-1) + s.substring(1, s.length()-2) + s.charAt(0); 
		
	}
	 public static boolean isValidHexCode(String s){
	     					
		 return s.matches("#[a-zA-z0-9{6}");
	    }
	
	 public static boolean same(int[] mas1, int[] mas2){
	        Map<Integer, String> map1 = new HashMap<Integer, String>();
	        for (int i=0;i<mas1.length;i++)
	        	map1.put(mas1[i],"");
	        Map<Integer, String> map2 = new HashMap<Integer, String>();
	        for (int i=0;i<mas2.length;i++) 
	        	map2.put(mas2[i],"");
	        return map1.size()==map2.size();
	    }
	 public static boolean isKaprekar(int n){
	     if(n==0 || n==1)
	    	 return true;
	     String str=String.valueOf(n*n);
	     return Integer.parseInt(str.substring(0,str.length()/2))+
	                +Integer.parseInt(str.substring(str.length()/2))==n;
	    }
	 public static String longestZero(String str){
	        String sum="";
	        String max="";
	        if (str.indexOf('0') == -1)
	            return sum;
	        for (int i=0;i<str.length();i++){
	            if (str.charAt(i)=='0')
	            	sum+="0";
	            else {
	                if (max.length()<sum.length())
	                	max=sum;
	                sum="";
	            }
	        }
	        if (max.length()<sum.length())
	        	max=sum;
	        return max;
	    }
	 public static Boolean nextPrimes(int chislo){
	        for (int i = 2; i < chislo; i++)
	        {
	            if ((chislo%i == 0) && (chislo != i))
	            	return false;              
	        }
	        return true;
	    }
	 public static boolean rigthTriangle( int x, int y, int z){
	        if ( x < 0 || y < 0 || z < 0 )
	            return false;
	        return  ( x*x+y*y==z*z || x*x==y*y+z*z || x*x+z*z == y*y);
	 }
}

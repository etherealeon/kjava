package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Pract_VI {
	private static int bell(int n) {
        int[][] bell = new int[n+1][n+1];
        bell[0][0] = 1;

        for (int i=1; i<=n; i++)
        {
            bell[i][0] = bell[i-1][i-1];

            for (int j=1; j<=i; j++)
                bell[i][j] = bell[i-1][j-1] + bell[i][j-1];
        }

        return bell[n][0];
    }
	  public static String translateWord (String str) {
		  if (str.isBlank())
			  return str;
		   if  (String.valueOf(str.charAt(0)).matches("[aeyuioAEYUIO]")) 
			   return str + "yay";
		   else {
			  while(!String.valueOf(str.charAt(0)).matches("[aeyuioAEYUIO]")){
			   String sym = str.substring(0, 1);
			   str = str.substring(1) + sym.toLowerCase();
		        }
			  return str + "ay";
			  }
}
	  public static String translateSentence (String str) {	
			boolean flag = false;
			String res = "";
			String sym = "";
			  if  (String.valueOf(str.charAt(str.length()-1)).matches("[.!?]")) {
				 sym = String.valueOf(str.charAt(str.length()-1));
				  str = str.substring(0, str.length()-2);
				  }
			String[] each = str.split(" ");
			for (String word : each) {
				 word = translateWord(word);
				 if (!word.toLowerCase().equals(word)) {
		                word = word.toLowerCase();
		                word = word.substring(0,1).toUpperCase() + word.substring(1);
		            }
		            if (flag) word+=sym;
		            res += word + " ";
		        }
		        return res;
		    }
	  public static boolean validColor(String str) {
	        String[] wp = str.substring(str.indexOf('(')+1, str.indexOf(')')).split(",");
	        if (str.contains("rgba") && wp.length != 4 || (str.contains(",,"))) return false;
	        for (int i = 0; i < wp.length ; i++) {
	            double num = Double.parseDouble(wp[i]);
	            if (i==3) {
	                if (!(num >= 0 && num <= 1.0)) return false;
	            }
	            else if (!(num >= 0 && num <= 255)) return false;
	        }
	        return true;
	    }
	  public static String stripUrlParams(String url, String ...param) {
	        String res = "", args = "";
	        Map<String, String> map=new HashMap<>();
	        String[] wp = url.split("\\?");
	        res += wp[0];
	        if (wp.length!=1) {
	            wp = wp[1].split("&");
	            for (int i = 0; i<wp.length; i++) {
	                String[] pair = wp[i].split("=");
	                if (map.containsKey(pair[0])) {
	                    map.replace(pair[0], pair[1]);
	                } else map.put(pair[0], pair[1]);
	            }
	            res+="?";
	        }
	        for (String pam : param) {
	            if (map.containsKey(pam)) map.remove(pam);
	        }
	        for (String key : map.keySet()) {
	            res += key + "=" + map.get(key) + "&";
	        }
	        return res.substring(0,res.length()-1);
	    }
	  private static int getMaxLenght(String[] m){
	        int k = m[0].length();
	        for(int i=1;i<m.length;i++)
	            if(m[i].length() > k ) k = m[i].length();
	        return k;
	    }

	  private static String[] getHashTags(String s){
	        int size = 0;
	        String[] a;
	        for(int i=0;i<s.length();i++){
	            if(s.charAt(i) == ' ') size++;
	        }
	        int m = 0;
	        if(size == 1 || size == 0){
	            a = new String[size+1];
	            Arrays.fill(a,"#");
	            for(int i=0;i<s.length();i++){
	                if(s.charAt(i) == ' ') m++;
	                else if(!(s.charAt(i) == ',')) a[m] += Character.toLowerCase(s.charAt(i));
	            }
	            if(size == 1){
	                if(a[0].length() < a[1].length()){
	                    String e = a[0];
	                    a[0] = a[1];
	                    a[1] = e;
	                }
	            }
	            return a;
	        }
	        else {
	            a = new String[size+1];
	            Arrays.fill(a,"#");
	            for(int i=0;i<s.length();i++){
	                if(s.charAt(i) == ' ' ) m++;
	                else if(!(s.charAt(i) == ',')) a[m] += Character.toLowerCase(s.charAt(i));
	            }
	            String rez = "";
	            String[] b = new String[3];
	            Arrays.fill(b,"");
	            int x = 0;
	            for(int j=0;j<a.length;j++){
	            for(int i=0;i<a.length;i++){
	                if(x == 3) break;
	                if(a[i].length() == getMaxLenght(a)) {
	                    b[x] += a[i];
	                    a[i] = "";
	                    x++;
	                    break;
	                }
	            }}
	            return b;
	        }
}
	  public static int ulam(int n) {
	        Vector<Integer> seq = new Vector<Integer>();
	        seq.add(1); seq.add(2);

	        for (int i = 3; i < 2000; i++) {
	            int count = 0;
	            for (int j = 0; j < seq.size()-1; j++) {
	                for (int k = j + 1; k < seq.size(); k++) {
	                    if (seq.get(j) + seq.get(k) == i) {
	                        count++;
	                    }
	                    if (count > 1) break;
	                }
	                if (count > 1) break;
	            }
	            if (count == 1) seq.add(i);
	        }
	    return seq.get(n-1);
	    }  
	  private static boolean someS(String str){
	        for(int i=0;i<str.length();i++){
	            for(int j=0;j<str.length();j++){
	                if(i == j) continue;
	                if(str.charAt(i) == str.charAt(j)) return false;
	            }
	        }
	        return true;
	    }
	    private static String longestNonrepeatingSubstring(String str){
	        String rez = "";
	        String rez2 = "";
	        for(int j=0;j<str.length();j++) {
	            rez = "";
	            for (int i = j; i < str.length(); i++) {
	                if (i == 0) rez += str.charAt(i);
	                else {
	                    while (someS(rez)) {
	                        rez += str.charAt(i);
	                        if(i != str.length()-1) i++;
	                        String r = rez;
	                        r += str.charAt(i);
	                        if (!someS(r)) break;
	                    }
	                    if(rez.length() > rez2.length()) rez2 = rez;
	                    break;
	                }
	            }
	        }
	        return rez2;
	    }
	    public static String convertToRoman(int num) {
	        String res = "";
	        while (num >= 1000) {
	            res += "M";
	            num -= 1000;        }
	        while (num >= 900) {
	            res += "CM";
	            num -= 900;
	        }
	        while (num >= 500) {
	            res += "D";
	            num -= 500;
	        }
	        while (num >= 400) {
	            res += "CD";
	            num -= 400;
	        }
	        while (num >= 100) {
	            res += "C";
	            num -= 100;
	        }
	        while (num >= 90) {
	            res += "XC";
	            num -= 90;
	        }
	        while (num >= 50) {
	            res += "L";
	            num -= 50;
	        }
	        while (num >= 40) {
	            res += "XL";
	            num -= 40;
	        }
	        while (num >= 10) {
	            res += "X";
	            num -= 10;
	        }
	        while (num >= 9) {
	            res += "IX";
	            num -= 9;
	        }
	        while (num >= 5) {
	            res += "V";
	            num -= 5;
	        }
	        while (num >= 4) {
	            res += "IV";
	            num -= 4;
	        }
	        while (num >= 1) {
	            res += "I";
	            num -= 1;
	        }
	        return res;
	    }
	    private static boolean formula(String str){
	        int size = 0;
	        for(int i=0;i<str.length();i++){
	            if(str.charAt(i) == ' ') size++;
	        }
	        String a[] = new String[size+1];
	        Arrays.fill(a,"");
	        size = 0;
	        for(int i=0;i<str.length();i++){
	            if(str.charAt(i) == ' ') size++;
	            else a[size] += str.charAt(i);
	        }
	        if(size > 4 || !(a[3].equals("="))) return false;
	        try {
	            int a1 = Integer.parseInt(a[0]);
	            int a2 = Integer.parseInt(a[2]);
	            int a3 = Integer.parseInt(a[4]);
	            if (a[1].equals("+")) {
	                return (a1 + a2) == a3;
	            } else if (a[1].equals("-")) {
	                return (a1 - a2) == a3;
	            } else if (a[1].equals("*")) {
	                return (a1 * a2) == a3;
	            } else if (a[1].equals("/")) {
	                return (a1 / a2) == a3;
	            }
	            else return false;
	        } catch (Throwable e){
	            return false;
	        } }
	        public static String revers(String s,  int posit) {
	   		 if (posit != s.length() / 2) {
	             char[] word = s.toCharArray();
	             char c = word[posit];
	             word[posit] = word[s.length() - 1 - posit];
	             word[s.length() - 1 - posit] = c;
	              return revers(new String(word), ++posit);
	         } else {
	             return s;
	         }
	     }
	 	 public static boolean isPalindrome (String s) {
	 		return  s.equals((revers(s,0)));
	 	 }
	        public static boolean palindromeDescendant(int num) {
	            String wp = String.valueOf(num);
	            while (wp.length()%2==0) { //
	                String decoy = "";
	                if (isPalindrome(wp)) return true;
	                else {
	                    for (int i=0; i < wp.length()-1; i=+2) {
	                        decoy += Character.getNumericValue(wp.charAt(i)) + Character.getNumericValue(wp.charAt(i+1));
	                    }
	                    wp = decoy;
	                }
	            }
	            return false;
	        }
	    
	}    



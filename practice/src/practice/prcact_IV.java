package practice;

import java.util.HashMap;
import java.util.Map;

public class prcact_IV {
	 public static String Essay(int n, int k, String s){
	        String[] text=s.split(" ");
	        s="";
	        String Curr="";
	        for(int i=0;i<n;i++){
	            if (Curr.length()+text[i].length()>k){
	               s=s.trim()+"\r\n"+text[i]+" ";
	                Curr=text[i];
	            }
	            else{
	               s+=text[i]+" ";
	               Curr+=text[i];
	            }
	        }
	        return s.trim();
	    }
	 public static String split(String s)   
	    {
	        int cnt1 = 0;
	        int cnt2 = 0; 
	        String rez = "";
	       
	        for (int i = 0; i < s.length(); i++)
	        {
	            if (s.charAt(i) == ' ')
	                continue;
	            if (s.charAt(i) == '(')
	            {    
	                cnt1 += 1;
	                rez += "(";
	                
	            }  
	            else if (s.charAt(i) == ')')
	            {
	                cnt2 += 1;
	                rez += ")";
	            }
	            if (cnt1 == cnt2)
	            {
	                if (i == s.length()-1)
	                    break;
	                rez += ", ";
	                cnt1 = 0;
	                cnt2 = 0;
	            }
	        }
	        return rez;
	    }
	 public static String toCamelCase(String s) {
	        for (int i = 1; i < s.length(); i++) {
	            if (s.charAt(i) == '_')
	                s = s.substring(0, i) + s.substring(i + 1, i + 2).toUpperCase() + s.substring(i + 2);
	        }
	        return s;
	    }

	    public static String toSnakeCase(String s) {
	        return s.replaceAll("([A-Z])", "_$0").toLowerCase();
	    }
	    public static String overTime(double[] work) {
	        double sum = 0;
	        if (17 - work[0] >= 0)
	            sum += (17 - work[0]) * work[2];
	        if (work[1] - 17 >= 0)
	            sum += (work[1] - 17) * work[2] * work[3];
	        return ('$' + String.valueOf(sum));
	    }
	    public static String BMI(String[] data) {
	        double weight = Double.parseDouble(data[0].split(" ")[0]);
	        double height = Double.parseDouble(data[1].split(" ")[0]);
	        String rez = "";
	        if (data[0].contains("pounds"))
	            weight = weight * 0.45;
	        if (data[1].contains("inches"))
	            height *= 0.0254;
	        double BMI = Math.round((weight / (height * height)) * 10.0) / 10.0;
	        if (BMI < 18.5)
	            rez = BMI + " Underweight";
	        if (BMI >= 18.5 && BMI <= 24.9)
	            rez = BMI + " Normal weight";
	        if (BMI > 25)
	            rez = BMI + " Overweight";
	        return rez;
	    }
	    public static int bugger(int num){
	        int i=0;
	        int mul=num;
	        while(mul>9){
	            mul=1;
	            do{
	                mul*=num%10;
	                num=num/10;
	            }while (num!=0);
	            num=mul;
	            i++;
	        }
	        return i;
	    }
	    public static String toStarShorthand(String str){
	        if (str.isEmpty()) return "";
	        int j=1;
	        char c=str.charAt(0);
	        String text="";
	        for(int i=1;i<str.length();i++){
	            if (str.charAt(i)==c) j++;
	            else {if (j>1)
	                text+=c+"*"+j;
	            else
	                text+=c;
	            c=str.charAt(i);
	            j=1;
	            }
	        }

	        if (j>1) text+=c+"*"+j;
	        else text+=c;

	        return text;
	    }
	    public static boolean doesRhyme(String str1, String str2){
	        str1 = str1.substring(str1.lastIndexOf(" ")).toLowerCase();
	        str2 = str2.substring(str2.lastIndexOf(" ")).toLowerCase();

	        for (int i=0;i<str1.length();i++){
	            if (String.valueOf(str1.charAt(i)).matches("[aeyuio]"))
	                if (!str2.contains(String.valueOf(str1.charAt(i)))) 
	                	return false;
	        }
	        for (int i=0;i<str2.length();i++){
	            if (String.valueOf(str2.charAt(i)).matches("[aeyuio]"))
	                if (!str1.contains(String.valueOf(str2.charAt(i)))) 
	                	return false;
	        }
	        return true;
	    }
	        public static boolean trouble(String str1, String str2){
	        	int num = 0;
	        	for (int i=0;i<str1.length();i++){
	        		 if (str1.charAt(i) == str1.charAt(i+1) && str1.charAt(i) == str1.charAt(i+2)) 
	        			 num = str1.charAt(i);
	        		 }
	        		 for (int i = 0 ; i < str2.length(); i++){
	        	            if (str2.charAt(i)==num && str2.charAt(i+1) == num)
	        	                return true;
	        	        }
	        	        return false;
	        	 }
	        public static int countUniqueBooks(String str, char c){
	            Map<Character, Integer> values = new HashMap<>();
	            boolean start = true;
	            for (int i = 0; i < str.length(); i++){
	                if (str.charAt(i) == c && start) {
	                    i++;
	                    while (str.charAt(i) != c){
	                        Integer n = values.get(str.charAt(i));
	                        if (n == null)
	                            values.put(str.charAt(i), 1);
	                        else
	                            values.put(str.charAt(i), ++n);
	                        i++;
	                    }
	                    start = false;
	                }
	                if (str.charAt(i) == c)
	                    start = true;
	            }
	            return values.size();
	        }
	        	
	        }
	        
	        	    
	    
	 


package practice;

public class Pract_II {


	    //1
	    public static String repeat(String str, int n){
	        String rez="";
	        for (int i=0;i<str.length();i++){
	            for(int j=0; j<n; j++)
	                rez+=str.charAt(i);
	            
	        }
	        return rez;
	    }

	    //2
	    public static int differenceMaxMin(int[] mas){
	        int max=Integer.MIN_VALUE;
	        int min=Integer.MAX_VALUE;
	        for (int i=0;i<mas.length;i++){
	            max=Integer.max(max,mas[i]);
	            min=Integer.min(min,mas[i]);
	        }
	        return max-min;
	    }

	    //3
	    public static boolean isAvgWhole(int[] mas){
	        int sum=0;
	        for (int i=0;i<mas.length;i++){
	            sum+=mas[i];
	        }
	        return (((double)sum)/mas.length)%2==0;
	    }

	    //4
	    public static int[] cumulativeSum(int[] mas){
	        int[] newMas= new int[mas.length];
	        for (int i=0;i<mas.length;i++){
	            int sum=0;
	            for(int j=0;j<=i;j++){
	                sum+=mas[j];
	            }
	            newMas[i]=sum;
	        }
	        return newMas;
	    }

	    //5
	    public static int getDecimalPlaces(String str){
	        if (!str.contains(".")) return 0;
	        else
	            return str.split(".")[1].length();
	    }

	    //6
	    public static int Fibonacci(int n){
	        int[] mas = new int[n];
	        mas[0]=1; mas[1]=2;
	        for (int i=2;i<n;i++){
	            mas[i]=mas[i-1]+mas[i-2];
	        }
	        return mas[n-1];
	    }

	    //7
	    public static boolean isValid(String str){
	        if (str.length()==5)
	        	if(!str.contains("+"))
	        		if(!str.contains("-"))
	            try{
	               Integer.parseInt(str);
	               return true;
	            }
	            catch (Exception e){
	                return false;
	            }
	            return false;
	    }

	    //8
	    public static boolean isStrangePair(String s1, String s2){
	        return (s1.charAt(0)==s2.charAt(s2.length()-1) && s2.charAt(0)==s1.charAt(s1.length()-1));
	    }
	    //9
	    public static boolean isPrefix(String str1, String str2){
	        str2=str2.substring(0,str2.length()-1);
	        return str1.startsWith(str2);
	    }

	    public static boolean isSuffix(String str1, String str2){
	        str2=str2.substring(1);
	        return str1.endsWith(str2);
	    }

	    //10
	    public static int boxSeq(int a){
	        if (a%2==0) return a;
	        else return a+2;
	    }
	}

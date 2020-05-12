package practice;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Pract_V {
	public static int[] encrypt(String str) {
        char[] fst = str.toCharArray();
        int[] res = new int[str.length()];
        res[0] = fst[0];
        for (int i = 1; i<fst.length; i++) {
            res[i] = fst[i]-fst[i-1];
        }
        return res;
    }
    public static String decrypt(int[] arr) {
        String res = "";
        res += (char) arr[0];
        int ch =  arr[0];
        for (int i = 1; i<arr.length; i++) {
            ch += arr[i];
            res += (char) ch;
        }
        return res;
    }
    public static boolean canMove(String figure,String pos, String aim){
        char pL = pos.charAt(0);
        int pN = Integer.parseInt(String.valueOf(pos.charAt(1)));
        char aL = aim.charAt(0);
        int aN = Integer.parseInt(String.valueOf(aim.charAt(1)));
        if (pL==aL && pN==aN) return false;
        switch (figure){
            case "Pawn": {     
                if (pL==aL && pN==2 && aN==4) return true;
                if (pL==aL && pN==(aN-1)) return true;
                return false;
            }
            case "Knight": {  
                if ((Math.abs(pL-aL)==2 && Math.abs(pN-aN)==1)||(Math.abs(pL-aL)==1 && Math.abs(pN-aN)==2)) return true;
                return false;
            }
            case "Bishop": {  
                if (Math.abs(pL-aL)==Math.abs(pN-aN))return true;
                return false;
            }
            case "Rook": {    
                if ((pL==aL && pN!=aN) || (pL!=aL && pN==aN)) return true;
                return false;
            }
            case "Queen": {    
                if ((pL==aL && pN!=aN) || (pL!=aL && pN==aN)) return true;
                if (Math.abs(pL-aL)==Math.abs(pN-aN))return true;
                break;
            }
            case "King": {     
                if (Math.abs(pL-aL)<2 && Math.abs(pN-aN)<2) return true;
                return false;
            }
            default: return false;
        }
        return false;
    }
    public static boolean canComplete(String a, String b)
    {
        char[] chars = a.toCharArray();
        int k=0;
        for (int i = 0; i<chars.length;i++)
        {
            if(b.indexOf(String.valueOf(chars[i]),k)!=-1)
                k=b.indexOf(String.valueOf(chars[i]),k)+1;
            else return false;
        }
        return true;
    }
    public static int sumDigProd(int... number){
        int res=0;
        for (int i : number) {
            res+=i;
        }

        int mul=res;
        while(mul>9){
            mul=1;
            do{
                mul*=res%10;
                res=res/10;
            }while (res!=0);
            res=mul;
        }
        return res;
    }
    public static String[] sameVowelGroup(String[] str){
        List<String> list=new ArrayList<String>();
        list.add(str[0]);
        String vowel="aeiyou";

        for(int i=1;i<str.length;i++){
            boolean flag=true;
            for(char c: vowel.toCharArray()){
                if (str[0].contains(String.valueOf(c))!=str[i].contains(String.valueOf(c))) flag=false;
            }
            if (flag) list.add(str[i]);
        }
        return list.toArray(new String[list.size()]);
    }
    public static boolean validateCard (long num){
        if (!String.valueOf(num).matches("[0-9]{14,19}")) return false;
        long cntrl = num%10;
        num=num/10;
        int sum=0;
       String s = Long.toString(num);
        StringBuffer buffer = new StringBuffer(s);
        buffer.reverse();
        for (int i=0;i<s.length();i++){
        	 if (i%2==0) {
                 int odd = Character.getNumericValue(s.charAt(i)) * 2;
                 sum += odd / 10;
                 sum += odd % 10;
             }
             else
                 sum += Character.getNumericValue(s.charAt(i));
         }
  
         if (10-sum%10==cntrl)
             return true;
         return false;
     }
    
    private static String numToEng(int a){
        if(!(a >= 0 && a <= 999)) return "Error";
        String s = "";
        String[] one = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        String[] tens = {"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen","twenty"};
        String[] two = {"","","twenty","thirty","forty","fifty","sixty","six eventy","eighty","ninety"};
        String[] three = {"","one hundred","two hundred","three hundred","four hundred","five hundred",
                "six hundred","seven hundred","eight hundred","nine hundred"};
        if(a < 10) return one[a];
        else if(a>= 10 && a <= 20) return tens[a-10];
        else if(a>20 && a<100) {
            String b = Integer.toString(a); 
            int fi = Character.getNumericValue(b.charAt(0));
            int tw = Character.getNumericValue(b.charAt(1));
            s += two[fi] + " " + one[tw];
        }
        else if(a % 100 == 0){
            String b = Integer.toString(a);
            int fi = Character.getNumericValue(b.charAt(0));
            s += three[fi];
        }
        else {
            String b = Integer.toString(a);
            int fi = Character.getNumericValue(b.charAt(0));
            int tw = Character.getNumericValue(b.charAt(1));
            int th = Character.getNumericValue(b.charAt(2));
            s += three[fi] + " " + two[tw] + " " + one[th];
        }
        return s;
    }
    private static String getSha256Hash(String s) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[]hashInBytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    public static String correctTitle(String str) {
		String[] words = str.split(" ");
		String output = "";
		for (int i = 0; i < words.length; i++) {
			if (i>0) {
				output += " ";
			}
			String[] wordsNoHyphens = words[i].split("-");
			for (int j = 0; j < wordsNoHyphens.length; j++) {
				if(j>0) {
					output += "-";
				}
				if (wordsNoHyphens[j].equalsIgnoreCase("and")
					 || wordsNoHyphens[j].equalsIgnoreCase("the")
					 || wordsNoHyphens[j].equalsIgnoreCase("of")
					 || wordsNoHyphens[j].equalsIgnoreCase("in")) {
					output += wordsNoHyphens[j].toLowerCase();
				}
				else {
					output += wordsNoHyphens[j].substring(0,1).toUpperCase();
					output += wordsNoHyphens[j].substring(1).toLowerCase();
				}
			}
		}
		
		return output;
	}
    public static String hexLattice(int n) {
        String upHalf = "", middle = "", full = "", lowHalf = "";
        int count = 1, i = 0, j =0;
        for (i = 1; count < n; i++) {
            count += i*6;
        }
        if (n!=count) {
            return "Invalid";
        }
        for (j = 0; j < i*2-1; j++) {
            middle += "o ";
        }
        upHalf = middle;
        for (j = 1; j < i; j++) {
            upHalf = " " + upHalf.substring(0, upHalf.length()-2);
            middle = upHalf  + "\n" +  middle + "\n" + upHalf;
        }
        return middle;
    }
    
        }

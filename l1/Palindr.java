
public class Palindr {
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
	 public static void main(String[] args) 
		{ 

			for( int i =0; i < args.length; i++ ) {
			String s = args[i];
			if (isPalindrome(s)) {
				System.out.println(s +' ' + "is Palindrome");
			} 
			else {
				System.out.println(s  + ' ' + "isn't Palindrome");
			}
			}
		}

}

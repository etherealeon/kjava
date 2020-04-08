
public class Prime {
	public static void main(String [] args) {
		for (int i =2; i <= 100; i++) {
			if (isPrime(i)) {
				System.out.println(i);
			}
		}
}
	public static boolean isPrime (int n) {
		boolean Prime = true;
		for (int i = 3; i < n; i++) {
			if ((n%i) == 0) {
				Prime = false;
			}
		}
		return Prime;
	}
}

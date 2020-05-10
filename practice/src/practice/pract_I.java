package practice;

public class pract_I {

//1
	    public int remainder(int a, int b) {return a%b;}

	    //2
	    public double triArea(double a, int h) {return a*h/2;}

	    //3
	    public int animals(int chickens, int cows, int pigs) {
	        return chickens*2+(pigs+cows)*4;
	    }

	    //4
	    public boolean profitableGamble(double prob, double prize, double pay){
	        return prob * prize > pay;
	    }

	    //5
	    public String operation(int a, int b, int N){
	        if (a+b==N) return "added";
	        else if (a-b==N) return "subtracted";
	        else if (a*b==N) return "multiplied";
	        else if (a/b==N) return "divided";
	        else return "none";
	    }

	    //6
	    public static int ctoa(char ch){return ch;}

	   //7
	    public int addUpTo(int a){
	        int sum=0;
	        for (int i=1;i<=a;i++){
	            sum+=i;
	        }
	        return sum;
	    }

	    //8
	    public int nextEdge(int a, int b){return a+b-1;}

	    //9
	    public int sumOfCubes(int[] mas){
	        int sum=0;
	        for(int i=0;i<mas.length;i++){
	            sum+=mas[i]*mas[i]*mas[i];
	        }
	        return sum;
	    }

	    //10
	    public boolean abcmath(int a, int b, int c){
	        for(int i=0;i<b;i++){
	            a+=a;
	        }
	        return a%c==0;
	    }
	}


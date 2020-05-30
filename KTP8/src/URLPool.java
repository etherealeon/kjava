import java.util.LinkedList;

public class URLPool {

    private LinkedList<URLDepthPair> Ulink= new LinkedList<URLDepthPair>();
    private LinkedList<URLDepthPair> Nlink = new LinkedList<URLDepthPair>();
    private int Depth;
    private int Waiting;
    private int Threads;

    public URLPool(String url, int depth, int threads) {
        Nlink.add(new URLDepthPair(url, depth));
        Depth = depth;
        Threads = threads;
    }

    public synchronized URLDepthPair get() throws InterruptedException {
        if (isEmpty()) {
            Waiting++;
            if (Waiting == Threads) {
                getSites();
                System.exit(0);
            }
            wait();
        }
        return Nlink.removeFirst();
    }
    public synchronized void addNotProcessed(URLDepthPair pair) {
        Nlink.add(pair);
        if (Waiting > 0) {
            Waiting--;
            notify();
        }
    }

    private boolean isEmpty() {
        if (Nlink.size() == 0) return true;
        return false;
    }

    public void getSites() {
        System.out.println("Depth: " + Depth);
        for (int i = 0; i < Ulink.size(); i++) {
            System.out.println( Depth - Ulink.get(i).getDepth() + " " +  Ulink.get(i).getURL());
        }
        System.out.println("Links visited: " + Ulink.size());
    }


    public void addProcessed(URLDepthPair pair) {
        Ulink.add(pair);
    }

    public LinkedList<URLDepthPair> getProcessed()
    {
        return Ulink;
    }

    public LinkedList<URLDepthPair> getNotProcessed()
    {
        return Nlink;
    }

}

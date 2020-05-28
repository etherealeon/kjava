import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Crawler {

    static LinkedList <URLDepthPair> Nlink = new LinkedList <URLDepthPair>();
    static LinkedList <URLDepthPair> Ulink = new LinkedList <URLDepthPair>();


    public static void showResult(LinkedList<URLDepthPair> viewedLink) {
        for (URLDepthPair c : viewedLink)
            System.out.println("Depth : "+c.getDepth() + "\tLink : "+c.getURL());
    }


    public static void request(PrintWriter out,URLDepthPair pair) throws MalformedURLException {
        out.println("GET " + pair.getPath() + " HTTP/1.1");
        out.println("Host: " + pair.getHost());
        out.println("Connection: close");
        out.println();
        out.flush();
    }

    public static void Scan(String pair, int maxDepth) throws IOException {
        Nlink.add(new URLDepthPair(pair, 0));
        while (!Nlink.isEmpty()) {
            URLDepthPair currentPair = Nlink.removeFirst();
            if (currentPair.depth<maxDepth){
                Socket my_socket = new Socket(currentPair.getHost(), 80);
                my_socket.setSoTimeout(70000);
                BufferedReader in = new BufferedReader(new InputStreamReader(my_socket.getInputStream()));
                PrintWriter out = new PrintWriter(my_socket.getOutputStream(), true);
                request(out, currentPair);
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.indexOf(currentPair.URL_PREFIX)!=-1) {
                        StringBuilder currentLink = new StringBuilder();
                        for (int i = line.indexOf(currentPair.URL_PREFIX) + 9; line.charAt(i) != '"'; i++) {
                            currentLink.append(line.charAt(i));
                        }
                        URLDepthPair newPair = new URLDepthPair(currentLink.toString(), currentPair.depth + 1);
                        if (currentPair.check(Nlink, newPair) && currentPair.check(Ulink, newPair) && !currentPair.URL.equals(newPair.URL))
                            Nlink.add(newPair);

                    }
                }
                my_socket.close();
            }

            Ulink.add(currentPair);
        }
        showResult(Ulink);
    }
    
    public static void main(String[] args) throws IOException  {

            Scan(args[0], Integer.parseInt(args[1]));
            System.out.println("usage: java Crawler <URL><depth>");
        }
    }
    
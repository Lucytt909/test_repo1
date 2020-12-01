package edu.cmu.cs.cs214.hw1;

import java.io.IOException;

/**
 * Takes a list of URLs on the command line and prints the two URL whose web
 * pages have the highest cosine similarity. Prints a stack trace if any of the
 * URLs are invalid, or if an exception occurs while reading data from the URLs.
 */
public class FindClosestMatch {
  /**
   * Method that is called when this program is run (the 'main' method)
   * @param args command line arguments, urls list
   * @throws IOException some problems when reading url
   */
  public static void main(String[] args) throws IOException {
    // Replace with your implementation:
    String uRL1 = "";
    String uRL2 = "";
    double sim = 0;
    for (int i = 0; i < args.length; i++) {
//      System.out.println(args[i]);
      String url1 = args[i];
      Document doc1 = new Document(url1);
      for (int j = i + 1; j < args.length; j++) {
        String url2 = args[j];
        Document doc2 = new Document(url2);
        double tmp = doc1.calSimilarity(doc2);
//        System.out.println(tmp);
        if (tmp > sim) {
          sim = tmp;
          uRL1 = url1;
          uRL2 = url2;
        }
      }
    }
    System.out.println("Two most closest urls:");
    System.out.println(uRL1);
    System.out.println(uRL2);
  }
}

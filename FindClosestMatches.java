package edu.cmu.cs.cs214.hw1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Sitian Shen -- sitians@andrew.cmu.edu
 * @date: 9/3/20
 * @time: 10:36 上午
 */
public class FindClosestMatches {
    /**
     * private instance variable used to store the most similar pairs
     */
    class Pair {
        private String url;
        private double similar;
        Pair(String url, double similar) {
            this.url = url;
            this.similar = similar;
        }
        public String getUrl() {
            return url;
        }
        public double getSimilar() {
            return similar;
        }
    }
    private Map<String, Pair> map = new HashMap<>();

    /**
     * Empty constructor
     */
    public FindClosestMatches() {

    }

    /**
     * find matches for each url
     * @param args url pairs
     * @throws IOException some problems when reading url
     */
    public void findMatches(String[] args) throws IOException {
        for (int i = 0; i < args.length; i++) {
            String url1 = args[i];
            Document doc1 = new Document(url1);
            for (int j = i + 1; j < args.length; j++) {
                String url2 = args[j];
                Document doc2 = new Document(url2);
                double tmp = doc1.calSimilarity(doc2);
                // whether contains url1
                if (!map.containsKey(url1) || (map.containsKey(url1) && tmp > map.get(url1).similar)) {
                    map.put(url1, new Pair(url2, tmp));
                }
                // whether contains url2
                if (!map.containsKey(url2) || (map.containsKey(url2) && tmp > map.get(url2).similar)) {
                    map.put(url2, new Pair(url1, tmp));
                }

            }
        }
        for (String url: map.keySet()) {
            System.out.println("Two matched URL:");
            System.out.println(url);
            System.out.println(map.get(url).url);
        }
    }

    /**
     * Method that is called when this program is run (the 'main' method)
     * @param args command line arguments, urls list
     * @throws IOException some problems when reading url
     */
    public static void main(String[] args) throws IOException {
        new FindClosestMatches().findMatches(args);
    }
}

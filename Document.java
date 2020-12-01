package edu.cmu.cs.cs214.hw1;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: Sitian Shen -- sitians@andrew.cmu.edu
 * @date: 2020-09-02
 * @time: 10:17
 */
public class Document {
    private String uRLString;
    private Map<String, Integer> map = new HashMap<>();
    private double sumOfSquares = 0;

    /**
     * Constructor
     * @param urlString url string to read from
     * @throws IOException meet problems when read url
     */
    public Document(String urlString) throws IOException {
        this.uRLString = urlString;
        genMap(urlString);
        this.sumOfSquares = calSquares(map);
    }

    /**
     * generate word map for url
     * @param urlString url to read from
     * @throws IOException meet problems when read url
     */
    private void genMap(String urlString) throws IOException {

        URL url = new URL(urlString);
        Scanner in = new Scanner(url.openStream());
        while (in.hasNext()) {
//                String[] strings = in.next().split("[\\W]+");
            String string = in.next();
//                for (String string: strings) {
//                    if (string.trim().equals(""))
//                        continue;
//                    map.put(string, map.getOrDefault(string, 0) + 1);
//                }
//                if (string.trim().equals())
            map.put(string, map.getOrDefault(string, 0) + 1);
        }
    }

    /**
     * calculate sum of squares, and cache it
     * @param input the word map
     * @return sum of squares
     */
    private double calSquares(Map<String, Integer> input) {
        double sum = 0;
        for (int val: input.values()) {
            sum += val * val;
        }
        return sum;
    }


    /**
     * calculate similarity with another doc
     * @param document another doc
     * @return return similarity
     */
    public double calSimilarity(Document document) {
        Map<String, Integer> customMap = document.getMap();
        double customSquares = document.getSumOfSquares();
        double numerate = 0;
        for (String string: customMap.keySet()) {
            if (map.containsKey(string)) {
                numerate += map.get(string) * customMap.get(string);
            }
        }
        double similarity = numerate / Math.sqrt(sumOfSquares * customSquares);
        return similarity;
    }

    /**
     * get word map
     * @return return the map
     */
    public Map<String, Integer> getMap() {
        return map;
    }

    /**
     * get cached sum of squares
     * @return sum of squares
     */
    public double getSumOfSquares() {
        return sumOfSquares;
    }

    @Override
    public String toString() {
        return uRLString;
    }
}

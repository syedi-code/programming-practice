import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        partOne();
        partTwo();
    }

    public static void partTwo() throws Exception {
        File file = new File("E:\\Coding\\programming-practice\\advent-of-code-2021\\day3\\input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> table = new ArrayList<String>();

        String s;
        while ((s = br.readLine()) != null) {
            table.add(s);
        }

        String oxygenGeneratorRating = findOxygenGeneratorRating(table, 0);
        String CO2ScrubberRating = findCO2ScrubberRating(table, 0);

        int oxygenValue = Integer.parseInt(oxygenGeneratorRating, 2);
        int scrubberValue = Integer.parseInt(CO2ScrubberRating, 2);
        int answer = oxygenValue * scrubberValue;
        
        System.out.println("Part two: " + answer);

        br.close();
    }

    public static String findOxygenGeneratorRating(ArrayList<String> table, int position) throws Exception {
        if (position == table.get(0).length() || table.size() == 1) {
            return table.get(0);
        } else {
            ArrayList<String> zeroes = new ArrayList<String>();
            ArrayList<String> ones = new ArrayList<String>();

            for (int i = 0; i < table.size(); i++) {
                if (table.get(i).charAt(position) == '0') {
                    zeroes.add(table.get(i));
                } else {
                    ones.add(table.get(i));
                }
            }

            if (zeroes.size() > ones.size()) {
                return findOxygenGeneratorRating(zeroes, position + 1);
            } else if (ones.size() > zeroes.size()) {
                return findOxygenGeneratorRating(ones, position + 1);
            } else {
                return findOxygenGeneratorRating(ones, position + 1);
            }
        }
    }

    public static String findCO2ScrubberRating(ArrayList<String> table, int position) throws Exception {
        if (position == table.get(0).length() || table.size() == 1) {
            return table.get(0);
        } else {
            ArrayList<String> zeroes = new ArrayList<String>();
            ArrayList<String> ones = new ArrayList<String>();

            for (int i = 0; i < table.size(); i++) {
                if (table.get(i).charAt(position) == '0') {
                    zeroes.add(table.get(i));
                } else {
                    ones.add(table.get(i));
                }
            }

            if (zeroes.size() > ones.size()) {
                return findCO2ScrubberRating(ones, position + 1);
            } else if (ones.size() > zeroes.size()) {
                return findCO2ScrubberRating(zeroes, position + 1);
            } else {
                return findCO2ScrubberRating(zeroes, position + 1);
            }
        }
    }

    public static void partOne() throws Exception {
        File file = new File("E:\\Coding\\programming-practice\\advent-of-code-2021\\day3\\input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder gamma = new StringBuilder();
        int tableSize = 0;
        int[] frequencies = new int[12];

        String s;
        while ((s = br.readLine()) != null) {
            tableSize++;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    frequencies[i]++;
                }
            }
        }

        for (int i = 0; i < frequencies.length; i++) {
            gamma.append(findMostCommonDigit(tableSize, frequencies[i]));
        }
        
        String epsilon = gamma.toString();
        epsilon = epsilon.replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1");

        int gammaValue = Integer.parseInt(gamma.toString(), 2);
        int epsilonValue = Integer.parseInt(epsilon, 2);
        int answer = gammaValue * epsilonValue;

        System.out.println("Part one: " + answer);
        br.close();
    }

    public static char findMostCommonDigit(int size, int numZeroes) {
        if (numZeroes > (size/2)) {
            return '0';
        } else {
            return '1';
        }
    }
}
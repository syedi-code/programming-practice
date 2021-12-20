import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        File file = new File("E:\\Coding\\advent-of-code-2021\\day2\\input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int position = 0;
        int depth = 0;
        int aim = 0;

        String s;
        while ((s = br.readLine()) != null) {
            char lastChar = s.charAt(s.length() - 1);
            char firstChar = s.charAt(0);
            int value = Character.getNumericValue(lastChar);

            if (firstChar == 'f') { // forward
                position += value;
                depth += (aim * value);
            } else if (firstChar == 'd') { // down
                aim += value;
            } else if (firstChar == 'u') { // up
                aim -= value;
            } else {
                System.out.println("Unexpected first character encountered");
            }
        }

        System.out.println(position * depth);
        br.close();
    }
}
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        File file = new File("E:\\Coding\\advent-of-code-2021\\day1\\input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<Integer> nums = new ArrayList<Integer>();
        int answer = 0;
        int c = 0;
        int lastSum = 0;

        String s;
        while ((s = br.readLine()) != null) {
            int curr = Integer.parseInt(s);

            if (nums.size() == 3) {
                nums.set(c % 3, curr);
                int secondSum = nums.get(0) + nums.get(1) + nums.get(2);
                if (secondSum > lastSum) {
                    answer++;
                }

                lastSum = secondSum;
            } else {
                nums.add(curr);
            }

            c++;
        }

        System.out.println(answer);
        br.close();
    }
}
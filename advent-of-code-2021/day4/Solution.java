import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        partOne();
        partTwo();
    }

    public static void partOne() throws Exception {
        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<Integer> callouts = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Integer>>> boards = new ArrayList<>();
        int c = -1;

        // Save first line into callouts List
        String s = br.readLine();
        List<String> numbers = Arrays.asList(s.split(","));
        for (String number : numbers) {
            callouts.add(Integer.valueOf(number));
        }

        // Save all values into boards 3D ArrayList
        while ((s = br.readLine()) != null) {
            if (!s.isEmpty()) {
                numbers = Arrays.asList(s.trim().split("\\s+"));
                ArrayList<Integer> rowValues = new ArrayList<>();
                for (String number : numbers) {
                    rowValues.add(Integer.valueOf(number));
                }
                boards.get(c).add(rowValues);
            } else {
                boards.add(new ArrayList<ArrayList<Integer>>());
                c++;
            }            
        }

        // Finding the best winning row
        int winningRowBoard = -1;
        int winningRowScore = Integer.MAX_VALUE;
        for (int i = 0; i < boards.size(); i++) {
            for (int j = 0; j < boards.get(i).size(); j++) {
                int rowCounter = 0;
                int rowScore = Integer.MIN_VALUE;
                for (int k = 0; k < boards.get(i).get(j).size(); k++) {
                    int curr = boards.get(i).get(j).get(k);
                    if (callouts.contains(curr)) {
                        rowCounter++;
                        rowScore = Math.max(rowScore, callouts.indexOf(curr));

                        if (rowCounter == 5) {
                            winningRowScore = Math.min(rowScore, winningRowScore);
                            if (winningRowScore == rowScore) {
                                winningRowBoard = i;
                                // System.out.println("Winning coords (board, row): " + i + ", " + j);
                            }
                        }
                    }
                }
            }
        }

        // Finding the best winning column
        int winningColumnBoard = -1;
        int winningColumnScore = Integer.MAX_VALUE;
        for (int i = 0; i < boards.size(); i++) {
            for (int j = 0; j < boards.get(i).size(); j++) {
                int columnCounter = 0;
                int columnScore = Integer.MIN_VALUE;

                for (int k = 0; k < boards.get(i).get(j).size(); k++) {
                    int curr = boards.get(i).get(k).get(j);
                    if (callouts.contains(curr)) {
                        columnCounter++;
                        columnScore = Math.max(columnScore, callouts.indexOf(curr));

                        if (columnCounter == 5) {
                            winningColumnScore = Math.min(columnScore, winningColumnScore);
                            if (winningColumnScore == columnScore) {
                                winningColumnBoard = i;
                                // System.out.println("Winning coords (board, column): " + i + ", " + k);
                            }
                        }
                    }
                }
            }
        }

        // Identify whether to use winning column or row data and slice callouts List after last called number
        int winningBoardIdx = Integer.MAX_VALUE;
        if (winningColumnScore < winningRowScore) {
            winningBoardIdx = winningColumnBoard;
            callouts.subList(winningColumnScore + 1, callouts.size()).clear();
        } else if (winningRowScore < winningColumnScore) {
            winningBoardIdx = winningRowBoard;
            callouts.subList(winningRowScore + 1, callouts.size()).clear();
        } else {
            System.out.println("Winning row and column scores are equal?");
        }

        // Calculate winning board sum
        ArrayList<ArrayList<Integer>> winningBoard = boards.get(winningBoardIdx);
        int boardSum = 0;
        // System.out.println("Winning board: " + winningBoardIdx + ", last called number: " + callouts.get(callouts.size() - 1));
        for (int i = 0; i < winningBoard.size(); i++) {
            for (int j = 0; j < winningBoard.get(i).size(); j++) {
                int curr = winningBoard.get(i).get(j);
                if (!callouts.contains(curr)) {
                    boardSum += curr;
                }
            }
        }

        int lastCalledNumber = callouts.get(callouts.size() - 1);
        int answer = boardSum * lastCalledNumber;
        System.out.println("Part one: " + answer);
        br.close();
    }

    public static void partTwo() throws Exception {
        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<Integer> callouts = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Integer>>> boards = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Integer>>> boardMap = new ArrayList<>();
        HashMap<Integer, Integer> completedBoards = new HashMap<Integer, Integer>();
        int c = -1;

        // Save first line into callouts List
        String s = br.readLine();
        List<String> numbers = Arrays.asList(s.split(","));
        for (String number : numbers) {
            callouts.add(Integer.valueOf(number));
        }

        // Save all values into boards 3D ArrayList
        while ((s = br.readLine()) != null) {
            if (!s.isEmpty()) {
                numbers = Arrays.asList(s.trim().split("\\s+"));
                ArrayList<Integer> rowValues = new ArrayList<>();
                for (String number : numbers) {
                    rowValues.add(Integer.valueOf(number));
                }
                boards.get(c).add(rowValues);
            } else {
                boards.add(new ArrayList<ArrayList<Integer>>());
                c++;
            }            
        }

        // Create a board map mirroring 'boards' that contains only 0s
        // Also instantiates hash map of completed boards
        for (int i = 0; i < boards.size(); i++) {
            boardMap.add(new ArrayList<ArrayList<Integer>>());
            completedBoards.put(i, 0);
            for (int j = 0; j < boards.get(i).size(); j++) {
                boardMap.get(i).add(new ArrayList<Integer>());
                for (int k = 0; k < boards.get(i).get(j).size(); k++) {
                    boardMap.get(i).get(j).add(0);
                }
            }
        }

        // Calculating board states after each callout
        int winningBoardIdx = Integer.MAX_VALUE;
        for (int i = 0; i < callouts.size(); i++) {
            int callout = callouts.get(i);

            int uncompleted = 0;
            int lastUncompleted = Integer.MAX_VALUE;
            for (Integer key : completedBoards.keySet()) {
                if (completedBoards.get(key) == 0) {
                    uncompleted++;
                    lastUncompleted = key;
                }
            }
            if (uncompleted == 1) {
                winningBoardIdx = lastUncompleted;
                callouts.subList(i+1, callouts.size()).clear();
                break;
            }

            handleCallout(callout, boards, boardMap, completedBoards);
        }

        // Calculate board sum
        ArrayList<ArrayList<Integer>> winningBoard = boards.get(winningBoardIdx);
        int boardSum = 0;
        for (int i = 0; i < winningBoard.size(); i++) {
            for (int j = 0; j < winningBoard.get(i).size(); j++) {
                int curr = winningBoard.get(i).get(j);
                if (!callouts.contains(curr)) {
                    boardSum += curr;
                }
            }
        }

        int lastCalledNumber = callouts.get(callouts.size() - 1);
        int answer = boardSum * lastCalledNumber;
        System.out.println("Part two: " + answer);
        br.close();
    }

    public static void handleCallout(int callout, ArrayList<ArrayList<ArrayList<Integer>>> boards, ArrayList<ArrayList<ArrayList<Integer>>> boardMap, HashMap<Integer, Integer> completedBoards) throws Exception {        
        // Update board map
        for (int i = 0; i < boards.size(); i++) {
            for (int j = 0; j < boards.get(i).size(); j++) {
                for (int k = 0; k < boards.get(i).get(j).size(); k++) {
                    int curr = boards.get(i).get(j).get(k);
                    if (curr == callout) {
                        boardMap.get(i).get(j).set(k, 1);
                    }
                }
            }
        }

        // Looking for winning rows/columns and if a win is found, adding to completed list
        for (int i = 0; i < boardMap.size(); i++) {
            if (completedBoards.get(i) == 0) {
                for (int j = 0; j < boardMap.get(i).size(); j++) {
                    int rowCounter = 0;
                    int colCounter = 0;
                    for (int k = 0; k < boardMap.get(i).get(j).size(); k++) {
                        int rowCurr = boardMap.get(i).get(j).get(k);
                        int colCurr = boardMap.get(i).get(k).get(j);
                        if (rowCurr == 1) {
                            rowCounter++;
                            if (rowCounter == 5) {
                                completedBoards.put(i, 1);
                            }
                        }
                        if (colCurr == 1) {
                            colCounter++;
                            if (colCounter == 5) {
                                completedBoards.put(i, 1);
                            }
                        }
                    }
                }
            }
        }
    }
}
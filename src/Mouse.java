import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/*  E = saída 
    # = parede
    C = queijo
    S = entrada 
    M = mouse */

public class Mouse {

    private static Map<String, Integer> findRownsAndColunsToDescoveryStartAndExitPoint(String[][] maze) {
        Map<String, Integer> result = new HashMap<>(); 

        int originRow = 0;
        int originColumn = 0;
        int destineRow = 0;
        int destColumn = 0;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[1].length; j++) {
                if (maze[i][j].equals("S")){
                    originRow = i;
                    originColumn = j;
                }
                if (maze[i][j].equals("E")){
                    destineRow = i;
                    destColumn = j;
                }
            }
        }

        result.put("originRow", originRow);
        result.put("originColumn", originColumn);
        result.put("destineRow", destineRow);
        result.put("destineColumn", destColumn);

        return result;
    }

    private static void findWay(String[][] maze, boolean[][] possibleMoves, int originRow, int originColumn, int destineRow, int destColumn) {
        
        maze[originRow][originColumn] = "M";
        int currentPositionOnRow = originRow, currentPositionOnColunm = originColumn;
        
        boolean isPossibleToWalk = walking(currentPositionOnRow, currentPositionOnColunm, possibleMoves, maze);
        System.out.println(isPossibleToWalk);
    
    }
    
    private static boolean walking(int currentPositionOnRow, int currentPositionOnColunm, boolean [][]possibleMoves, String [][]maze) {
             if(possibleMoves[currentPositionOnRow][currentPositionOnColunm] == true && maze[currentPositionOnRow][currentPositionOnColunm].equals("E")){
                 return true;

             } else if (possibleMoves[currentPositionOnRow - 1][currentPositionOnColunm] == true && !maze[currentPositionOnRow -1][currentPositionOnColunm].equals("M")) {

                maze[currentPositionOnRow - 1][currentPositionOnColunm] = "M";
                printMaze(maze);
                walking(currentPositionOnRow -1, currentPositionOnColunm, possibleMoves, maze);

             } else if (possibleMoves[currentPositionOnRow][currentPositionOnColunm - 1] == true && !maze[currentPositionOnRow][currentPositionOnColunm -1].equals("M")) {

                maze[currentPositionOnRow][currentPositionOnColunm -1] = "M";
                printMaze(maze);
                walking(currentPositionOnRow, currentPositionOnColunm - 1, possibleMoves, maze);

             } else if (possibleMoves[currentPositionOnRow + 1][currentPositionOnColunm] == true && !maze[currentPositionOnRow + 1][currentPositionOnColunm].equals("M")) {

                maze[currentPositionOnRow + 1][currentPositionOnColunm] = "M";
                printMaze(maze);
                walking(currentPositionOnRow + 1, currentPositionOnColunm, possibleMoves, maze);

             } else if (possibleMoves[currentPositionOnRow][currentPositionOnColunm + 1] == true && !maze[currentPositionOnRow][currentPositionOnColunm + 1].equals("M")) {

                maze[currentPositionOnRow][currentPositionOnColunm + 1] = "M";
                printMaze(maze);
                walking(currentPositionOnRow, currentPositionOnColunm + 1, possibleMoves, maze);
             }
            return false;
    }

    private static void printMaze(String maze[][]) {
        for(int i = 0; i < maze.length; i ++) {
            for(int j = 0; j < maze[1].length; j++){
                System.out.print(maze[i][j]);
            }
            System.out.println(); 
        }
    }

    private static String[][] createMaze() throws FileNotFoundException {
        
        Scanner scanner = new Scanner(new File("C:\\maze.txt"));
        List<String []> list = new ArrayList<>();

        while (scanner.hasNext()) {
            String [] ar = scanner.nextLine().split(" ");
            String[] parsed = new String[ar.length];
            for (int i = 0; i<ar.length; i++) parsed[i] = String.valueOf(ar[i]);
            list.add(parsed);
        }
        
        String[][]maze = list.toArray(new String[0][]);

        return maze; 
    }

    public static void main(String[] args) throws FileNotFoundException {

        String maze[][] = createMaze(); 
        boolean possibleMoves[][];

        printMaze(maze);              
        Map<String, Integer> rowsAndColumns = findRownsAndColunsToDescoveryStartAndExitPoint(maze);
        int originRow = rowsAndColumns.get("originRow");
        int destineRow = rowsAndColumns.get("destineRow");
        int originColumn = rowsAndColumns.get("originColumn");
        int destColumn = rowsAndColumns.get("destineColumn");
        
        possibleMoves = discoveryPossibleMoves(maze);

        findWay(maze, possibleMoves, originRow, originColumn, destineRow, destColumn);
        
      /*   int originRow = rowsAndColumns.get("originRow");
        int originColumn = rowsAndColumns.get("originColumn");
        int destineRow = rowsAndColumns.get("destineRow");
        int destColumn = rowsAndColumns.get("destColumn"); */


    }

    private static boolean[][] discoveryPossibleMoves(String[][] maze) {
        boolean[][] possibleMoves = new boolean[maze.length][maze[1].length];

        for(int i = 0; i < maze.length; i ++) {
            for(int j = 0; j < maze[1].length; j++){
                if(maze[i][j].equals("#")) {
                   possibleMoves[i][j] = false;
                } else {
                    possibleMoves[i][j] = true;
                }
            }
        }

        /* System.out.println("Possible moves");

        for(int i = 0; i < possibleMoves.length; i ++) {
            for(int j = 0; j < possibleMoves[1].length; j++){
                System.out.print(" " + possibleMoves[i][j] + " ");
            }
            System.out.println(); 
        } */
        return possibleMoves;        
    }

}

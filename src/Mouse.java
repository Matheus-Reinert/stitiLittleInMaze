import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Mouse {

    static boolean isSafe(int[][] arr, int row, int col) {
        return(row < arr.length && col < arr.length && col>=0 && row>=0 && arr[row][col] == 1);            
    }

    static boolean findPathUtil(int[][] arr, int[][] sol, int row, int col) {

        if(row == arr.length - 1 && col == arr.length - 1 && arr[row][col] == 1) {
            sol[row][col] = 1;
            return true;
        }

        if(isSafe(arr, row, col)) {
            sol[row][col] = 1;

            if(findPathUtil(arr, sol, row + 1, col )) {
                return true;
            }

            if(findPathUtil(arr, sol, row, col + 1)) {
                return true;
            }

            sol[row][col] = 0;
            return false;
        }

        return false;   
    }    
    
    static void findPath(int[][] arr, int n) {
        int[][] solution = new int[n][n];
        
        if(findPathUtil(arr, solution, 0, 0)) {
            printArray(solution);     
        } else {
            System.out.println("Sem solução");
        }
    }

    static void printArray(int[][] arr) {
        for(int i = 0; i < arr.length; i ++) {
            for(int j = 0; j < arr.length; j++){
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        int maze[][] = {{ 1, 1, 0, 0 },
                        { 1, 1, 0, 0 },
                        { 1, 1, 0, 0 },
                        { 0, 1, 1, 1 }};        

        int n = maze.length;    
        findPath(maze, n);
    }


/*     Scanner scanner = new Scanner(new File("C:\\maze.txt"));
        List<String []> list = new ArrayList<>();

        while (scanner.hasNext()) {
            String [] ar = scanner.nextLine().split(" ");
            String[] parsed = new String[ar.length];
            for (int i = 0; i<ar.length; i++) parsed[i] = String.valueOf(ar[i]);
            list.add(parsed);
        }

        String[][]matrix = list.toArray(new String[0][]);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[1].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        } */
}

package chatGPT;

import java.util.Arrays;

public class NQueens {

    public static boolean check(int[][] board, int row, int col, int n) {
        // Check this row on left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal on left side
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // If it passes all checks, return true
        return true;
    }

    public static boolean solve(int[][] board, int col, int n) {
        // Base case: if all queens are placed, return true
        if (col >= n) {
            return true;
        }

        // Recursive case: try to place a queen in this column
        for (int i = 0; i < n; i++) {
            if (check(board, i, col, n)) {
                // Place the queen in this cell
                board[i][col] = 1;

                // Recursively place queens in the remaining columns
                if (solve(board, col + 1, n)) {
                    return true;
                }

                // If placing the queen here doesn't lead to a solution, backtrack
                board[i][col] = 0;
            }
        }

        // If all rows have been tried and nothing worked, return false
        return false;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] board = new int[n][n];
        if (solve(board, 0, n)) {
            // Print the solution
            for (int[] row : board) {
                System.out.println(Arrays.toString(row));
            }
        } else {
            System.out.println("No solution found.");
        }
    }

}

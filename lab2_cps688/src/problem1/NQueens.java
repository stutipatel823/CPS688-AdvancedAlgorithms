/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem1;

/**
 *
 * @author s364pate
 */
// //No two queens can lie in the same row, coloumn or diagonal
import java.util.Scanner;

public class NQueens {
    private int n;
    private int[][] board;

    //constructor
    public NQueens(int n){
        this.n = n;
        this.board = new int[n][n];
    }

    // method to solve the n-queens problem recursively for a given column
    private boolean solve(int col){
        //base case: all queens have been placed successfully
        if(col == n){
            return true;
        }
        // loop through each row of the current column
        for(int row = 0; row<n; row++){
            //check if the current position is safe to place a queen
            if(check(row, col)){
                //place queen at the current position
                board[row][col] = 1;
                //try placing another queen starting from the next column recursively
                if(solve(col+1)){
                    return true;
                }
                //if not successful, backtrack and remove the previously placed queen
                board[row][col] = 0; 
            }
        }
        //if no solution found in this column, return false
        return false;
    }


    private boolean check(int row, int col){
        //check horizontal
        for(int i = 0; i<n; i++){
            if(board[row][i]==1){return false;}
        }
        
        //check vertical
        for(int j = 0; j<n; j++){
            if(board[j][col]==1){return false;}
        }
        
        //check upper left diagonal
        for(int r=row, c=col; c>=0 && r>=0; c--, r--){
            if(board[r][c]==1){return false;}
        }
        
        //check upper right diagonal
        for(int r=row, c=col; c<n && r>=0; c++, r--){
            if(board[r][c]==1){return false;}
        }
        
        //check bottom left diagonal
        for(int r=row, c=col; c>=0 && r<n; c--, r++){
            if(board[r][c]==1){return false;}
        }
        
        //check bottom right diagonal
        for(int r=row, c=col; c<n && r<n; c++, r++){
            if(board[r][c]==1){return false;}
        }

        return true;
    }

    public void printBoard(){
        for(int row=0; row<n; row++){
            for(int col=0; col<n; col++){
                if(board[row][col]==0){
                    System.out.print("0 ");
                } else {
                    System.out.print("1 ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the size of the board? ");
        int n = scan.nextInt();
        NQueens queens = new NQueens(n);

        if (queens.solve(0)) {
            System.out.println("Solution found:");
            queens.printBoard();
        } 
        else {
            System.out.println("No solution found.");
        }
    }
}

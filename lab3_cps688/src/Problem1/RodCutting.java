/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem1;

/**
 *
 * @author s364pate
 */
import java.util.*;
import java.io.*;

public class RodCutting{
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("input.txt")); // Open the file "input.txt" for input
            int n = scan.nextInt(); // Read the first integer from the file, which represents the length of the rod
            int[] price = new int[n];
            
            
            
            for(int i=0; i<n;i++){
                price[i] = scan.nextInt(); // Read the price of each segment from the file and store it in the array price
            }

            int[] r = rodCutting(n, price); // Solve the rod cutting problem using the input from the file
            System.out.println(r[n]); // Print the maximum revenue for a rod of length n
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
        }
    }

    public static int[] rodCutting(int n, int[] price){
        // Initialize an array revenue to store the maximum revenue for each rod length from 0 to n
        int[] revenue = new int[n+1];
        revenue[0]=0; // The maximum revenue for a rod of length 0 is 0
        for(int j=1; j<=n; j++){
            int max_value = -1;
            int value = 0;
            // Iterate over all possible cut positions from 1 to j
            for(int i=1; i<=j; i++){
                if(j-i>=0){
                    // Calculate the revenue for the current cut position
                    value = price[i-1]+revenue[j-i];
                    if (value > max_value){
                        max_value = value;
                    }
                }
            }
            // Store the maximum revenue for the current rod length in revenue[j]
            revenue[j] = max_value;
        }
        // Return the array revenue containing the maximum revenue for each rod length from 0 to n
        return revenue;
    }
}





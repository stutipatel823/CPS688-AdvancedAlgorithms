/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem4;

/**
 *
 * @author s364pate
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CandyShop {
    public static void main(String[] args) {
        try {
            // Scanner sc = new Scanner(new File("/home/student1/s364pate/CPS688/lab2/src/problem4/input.txt"));
            Scanner sc = new Scanner(new File("input.txt"));
            int n = sc.nextInt(); // number of candies
            int[] value = new int[n+1]; // sentimental value of candies
            int[] weight = new int[n+1]; // weight of candies
            for (int i = 1; i <= n; i++) {
                value[i] = sc.nextInt();
            }
            for (int i = 1; i <= n; i++) {
                weight[i] = sc.nextInt();
            }
            int W = sc.nextInt(); // maximum weight of the bag

            // 2D array to store the maximum sentimental value for each subproblem
            int[][] dp = new int[n+1][W+1];

            // fill the first row and the first column with zeros
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }
            for (int j = 0; j <= W; j++) {
                dp[0][j] = 0;
            }

            // fill the rest of the array using the Knapsack formula
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= W; j++) {
                    if (weight[i] <= j) {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i]);
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }

            // the maximum sentimental value is stored in the last cell of the array
            int maxSentimentalValue = dp[n][W];
            System.out.println(maxSentimentalValue);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

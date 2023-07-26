/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem5;

/**
 *
 * @author s364pate
 */
import java.util.*;
public class LIS_ScannerInput {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements in the sequence: ");
        int n = sc.nextInt();
        int arr[] = new int[n];
        System.out.print("Enter the elements of the sequence: ");
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int lis[] = new int[n];
        int prev[] = new int[n];
        int maxLength = 0;
        int maxIndex = 0;
        for(int i = 0; i < n; i++) {
            lis[i] = 1;
            prev[i] = -1;
            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i] && lis[j] + 1 > lis[i]) {
                    lis[i] = lis[j] + 1;
                    prev[i] = j;
                }
            }
            if(lis[i] > maxLength) {
                maxLength = lis[i];
                maxIndex = i;
            }
        }
        System.out.println("LIS = " + maxLength);
        System.out.print("LIS is: ");
        int lisArr[] = new int[maxLength];
        int index = maxLength - 1;
        while(maxIndex != -1) {
            lisArr[index] = arr[maxIndex];
            index--;
            maxIndex = prev[maxIndex];
        }
        for(int i = 0; i < maxLength; i++) {
            System.out.print(lisArr[i] + " ");
        }
    }
}
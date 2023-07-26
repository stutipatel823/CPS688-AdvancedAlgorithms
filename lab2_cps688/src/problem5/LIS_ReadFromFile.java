// /*
//  * To change this license header, choose License Headers in Project Properties.
//  * To change this template file, choose Tools | Templates
//  * and open the template in the editor.
//  */
// package problem5;

// /**
//  *
//  * @author s364pate
//  */
// import java.util.*;
// import java.io.*;
// public class LIS_ReadFromFile {
//     public static void main(String args[]) throws FileNotFoundException {
//         File file = new File("/home/student1/s364pate/CPS688/lab2/src/problem5/input.txt");
//         Scanner sc = new Scanner(file);
//         int n = 0;
//         while(sc.hasNextInt()) {
//             n++;
//             sc.nextInt();
//         }
//         sc = new Scanner(file);
//         int arr[] = new int[n];
//         for(int i = 0; i < n; i++) {
//             arr[i] = sc.nextInt();
//         }
//         int lis[] = new int[n];
//         int prev[] = new int[n];
//         int maxLength = 0;
//         int maxIndex = 0;
//         for(int i = 0; i < n; i++) {
//             lis[i] = 1;
//             prev[i] = -1;
//             for(int j = 0; j < i; j++) {
//                 if(arr[j] < arr[i] && lis[j] + 1 > lis[i]) {
//                     lis[i] = lis[j] + 1;
//                     prev[i] = j;
//                 }
//             }
//             if(lis[i] > maxLength) {
//                 maxLength = lis[i];
//                 maxIndex = i;
//             }
//         }
//         System.out.println("LIS = " + maxLength);
//         System.out.print("LIS is: ");
//         int lisArr[] = new int[maxLength];
//         int index = maxLength - 1;
//         while(maxIndex != -1) {
//             lisArr[index] = arr[maxIndex];
//             index--;
//             maxIndex = prev[maxIndex];
//         }
//         for(int i = 0; i < maxLength; i++) {
//             System.out.print(lisArr[i] + " ");
//         }
//     }
// }
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class LIS_ReadFromFile {
    public static void main(String[] args) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
            System.exit(0);
        }
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] lis = new int[n];
        Arrays.fill(lis, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (lis[i] > lis[maxIndex]) {
                maxIndex = i;
            }
        }

        int maxLength = lis[maxIndex];
        System.out.println("Length of LIS: " + maxLength);

        int[] lisArray = new int[maxLength];
        int k = maxLength - 1;
        lisArray[k] = arr[maxIndex];
        k--;
        for (int i = maxIndex - 1; i >= 0; i--) {
            if (arr[i] < arr[maxIndex] && lis[i] == lis[maxIndex] - 1) {
                maxIndex = i;
                lisArray[k] = arr[maxIndex];
                k--;
            }
        }

        System.out.print("Elements of LIS: ");
        for (int i = 0; i < maxLength; i++) {
            System.out.print(lisArray[i] + " ");
        }
    }
}

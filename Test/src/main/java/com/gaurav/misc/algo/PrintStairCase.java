package com.gaurav.misc.algo;

import java.util.Scanner;
import java.util.StringTokenizer;

public class PrintStairCase {
    public static void main(final String[] args) {
        final Scanner scanIn = new Scanner(System.in);
        final String nextLine = scanIn.nextLine();
        final StringTokenizer stringTokenizer = new StringTokenizer(nextLine);
        final int x = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = x; i > 0; i--) {
            for (int j = i - 1; j > 0; j--) {
                System.out.print(" ");
            }
            for (int j = x; j >= i; j--) {
                System.out.print("#");
            }
            System.out.println();
        }
    }
}

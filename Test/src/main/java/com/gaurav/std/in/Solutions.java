package com.gaurav.std.in;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class Solutions {

    public static void main(final String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        try (final InputStream in = System.in; final Scanner sc = new Scanner(in);) {
            final int n = sc.nextInt();
            final BigDecimal[] ar = new BigDecimal[n];
            BigDecimal sum = BigDecimal.ZERO;
            for (int i = 0; i < n; i++) {
                ar[i] = sc.nextBigDecimal();
            }
            for (int i = 0; i < n; i++) {
                sum = sum.add(ar[i]);
            }
            System.out.println(sum);
        } catch (final Exception e) {
            System.err.println(e);
        }
    }
}
package com.nimbuzz.algo;

/**
 * https://www.careercup.com/question?id=5677751244685312
 * 
 * @author gkushwaha
 *
 */
public class CountNumberOfOneFlippingK {

    public static void main(final String args[]) {
        final int k = 1;
        final int[] arr = new int[] { 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0 };
        int zeroCount = 0;
        int oneCount = 0;
        int result = 0;
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }
            if (zeroCount == k) {
                result = zeroCount + oneCount;
                max = result > max ? result : max;
            } else if (zeroCount > k) {
                zeroCount = zeroCount - k;
                if (arr[i - 1] == 0) {
                    oneCount = 0;
                    result = 0;
                }
            }
            System.out.println("zeroCount " + zeroCount + " oneCount " + oneCount + " result " + result);
        }
        System.out.println(" max " + max);

    }

}

package com.gaurav.recurrsion;

/**
 * https://www.tutorialspoint.com/data_structures_algorithms/tower_of_hanoi.htm
 * 
 * @author gkushwaha
 *
 */
public class TowerOfHanoi {

    public static void main(final String args[]) {
        new TowerOfHanoi().tower(3, "A", "B", "C");
    }
    public void tower(final int height, final String from, final String to, final String intermediate) {
        if (height >= 1) {
            tower(height - 1, from, intermediate, to);
            //tower(1, from, to, intermediate);
            System.out.println("disc " + height + " " + from + " " + to);
            tower(height - 1, intermediate, to, from);
        }
    }
}

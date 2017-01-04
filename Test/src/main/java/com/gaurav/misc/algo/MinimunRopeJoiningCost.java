package com.gaurav.misc.algo;

import java.util.PriorityQueue;

/**
 * 
 * USE OF HEAP
 * 
 * amazon-interview-questions
 * 
 * Given N ropes of lengths L1, L2, L3, L4, …, LN. I had to join every rope to get a final rope of length L1 + L2 + … +
 * LN. However, I can join only two ropes at a time and the cost of joining the two ropes is L1 + L2. I was supposed to
 * join ropes in such a way that the cost is minimum. https://www.careercup.com/question?id=5159122468077568
 * 
 * @author gkushwaha
 */
public class MinimunRopeJoiningCost {
    static public void main(final String[] args) throws Exception{
        //final BufferedReader br = new BufferedReader( new InputStreamReader( System.in));
        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        //        final String[] temp = br.readLine().trim().split(" ");
        final String[] temp = "1 2 3 4 5 6 7 8 9 10 11 12".split(" ");
        for ( final String s: temp){
            System.out.println(s);
            pq.add(Integer.parseInt(s));
        }
        Integer result = new Integer(0);
        while (pq.size() > 1) {
            final Integer a = pq.remove();
            final Integer b = pq.remove();
            result = result + a+b;
            pq.add(a+b);
        }

    }
}

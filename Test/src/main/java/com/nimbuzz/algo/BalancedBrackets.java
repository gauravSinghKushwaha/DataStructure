package com.nimbuzz.algo;

import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.hackerrank.com/challenges/ctci-balanced-brackets
 * 
 * @author gkushwaha
 *
 */
public class BalancedBrackets {
    public static boolean isBalanced(final String expression) {
        final int length = expression.length();
        final Stack<Character> stack = new Stack<Character>();
        if (!(length >= 1 && length <= 1000) || !(length % 2 == 0)) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            final char startChar = expression.charAt(i);
            if (isClosingBracket(startChar)) {
                final Character topChar = stack.size() > 0 ? stack.pop() : 'N';
                if (!doComplement(topChar, startChar)) {
                    return false;
                } else {
                    continue;
                }
            }
            stack.push(startChar);
        }
        return stack.size() == 0;
    }

    private static boolean isClosingBracket(final char startChar) {
        return startChar == ']' || startChar == '}' || startChar == ')';
    }

    private static boolean doComplement(final char startChar, final char endChar) {
        if (startChar == '{' && endChar == '}') {
            return true;
        } else if (startChar == '[' && endChar == ']') {
            return true;
        } else if (startChar == '(' && endChar == ')') {
            return true;
        } else {
            return false;
        }
    }

    public static void main(final String[] args) {
        @SuppressWarnings("resource")
        final Scanner in = new Scanner(System.in);
        final int t = in.nextInt();
        for (int a0 = 0; a0 < t && a0 < 1000; a0++) {
            final String expression = in.next();
            final boolean answer = isBalanced(expression);
            if (answer) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}

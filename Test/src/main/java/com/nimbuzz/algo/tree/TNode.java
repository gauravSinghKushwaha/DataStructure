package com.nimbuzz.algo.tree;

import java.util.ArrayList;
import java.util.List;

public final class TNode<E> {
    private final E value;
    private TNode<E> left;
    private TNode<E> right;

    public TNode(final E value, final TNode<E> left, final TNode<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public E value() {
        return value;
    }

    public TNode<E> left() {
        return left;
    }

    public TNode<E> right() {
        return right;
    }

    public void setLeft(final TNode<E> left) {
        this.left = left;
    }

    public void setRight(final TNode<E> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "\n Node [value=" + value + ", left=" + left + ", right=" + right + "]";
    }

    public void printTree() {
        final List<List<String>> lines = new ArrayList<List<String>>();
        List<TNode<E>> level = new ArrayList<TNode<E>>();
        List<TNode<E>> next = new ArrayList<TNode<E>>();

        level.add(this);

        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            final List<String> line = new ArrayList<String>();

            nn = 0;

            for (final TNode<E> n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    final E aa = n.value();
                    final String temp = "" + aa;
                    line.add(temp);
                    if (temp.length() > widest) {
                        widest = temp.length();
                    }

                    next.add(n.left());
                    next.add(n.right());

                    if (n.left() != null) {
                        nn++;
                    }
                    if (n.right() != null) {
                        nn++;
                    }
                }
            }

            if (widest % 2 == 1) {
                widest++;
            }
            lines.add(line);
            final List<TNode<E>> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            final List<String> line = lines.get(i);
            final int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = line.get(j) != null ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) {
                                c = '└';
                            }
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) {
                    f = "";
                }
                final int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                final int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }

}

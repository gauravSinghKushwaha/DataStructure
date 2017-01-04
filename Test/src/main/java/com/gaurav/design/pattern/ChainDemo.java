package com.gaurav.design.pattern;

class Handler {
    private static java.util.Random s_rn = new java.util.Random();
    private static int s_next = 1;
    private final int m_id = s_next++;
    private Handler m_next;

    public void add(final Handler next) {
        if (m_next == null) {
            m_next = next;
        } else {
            m_next.add(next);
        }
    }

    public void wrap_around(final Handler root) {
        if (m_next == null) {
            m_next = root;
        } else {
            m_next.wrap_around(root);
        }
    }

    public void handle(final int num) {
        if (s_rn.nextInt(4) != 0) {
            System.out.print(m_id + "-busy  ");
            m_next.handle(num);
        } else {
            System.out.println(m_id + "-handled-" + num);
        }
    }
}

public class ChainDemo {
    public static void main(final String[] args) {
        final Handler chain_root = new Handler();
        chain_root.add(new Handler());
        chain_root.add(new Handler());
        chain_root.add(new Handler());
        chain_root.wrap_around(chain_root);
        for (int i = 1; i < 10; i++) {
            chain_root.handle(i);
        }
    }
}

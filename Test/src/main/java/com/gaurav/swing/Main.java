package com.gaurav.swing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
    public static int x = 5;
    public static int y = 5;
    public static final Set<String> set = new HashSet<String>();

    public static void main(final String args[]) {
        final Box[][] arr = new Box[x][y];
        final JFrame frame = new JFrame("PLAY");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        final JPanel comp = new JPanel(new GridLayout(x, y));
        final MouseListener listener = new MouseListenerAdapter(arr);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                final Box button = createButton(listener, i, j);
                arr[i][j] = button;
                comp.add(button);
            }
        }
        frame.add(comp);
        frame.pack();
    }

    private static Box createButton(final MouseListener listener, final int i, final int j) {
        Box button = null;
        if (i % 2 == 0 && j % 2 == 1) {
            button = new Box(i, j, false, false, false, 0);
        } else {
            button = new Box(i, j, true, false, false, 0);
            button.setForeground(Color.red);
        }
        // final Box button = new Box();
        button.setForeground(Color.yellow);
        button.setSize(50, 50);
        button.addMouseListener(listener);
        return button;
    }
}

class MouseListenerAdapter implements MouseListener {
    final Box[][] arr;

    public MouseListenerAdapter(final Box[][] arr) {
        super();
        this.arr = arr;
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        // System.out.println("event e " + e);

    }

    @Override
    public void mousePressed(final MouseEvent e) {
        // System.out.println("event e " + e);

    }

    @Override
    public void mouseExited(final MouseEvent e) {
        // System.out.println("event e " + e);

    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        // System.out.println("event e " + e);
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        final Box component = (Box) e.getComponent();
        if (SwingUtilities.isLeftMouseButton(e)) {
            System.out.println("left event e " + e + " Component " + component);
            if (component.isEnabled() && component.isMine()) {
                youLoose();
                clearAll(arr);
            } else {
                clearNonMineNonNumber(component);
            }
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            System.out.println("right event e " + e + " Component " + component);
        }

    }

    private void clearAll(final Box[][] arr2) {
        for (final Box[] boxs : arr2) {
            for (final Box box : boxs) {
                if (box.isNumbered()) {
                    box.setText(box.getNumber() + "");
                }
                if (box.isMine()) {
                    box.setForeground(Color.red);
                }
                box.setEnabled(false);
            }
        }

    }

    private void youLoose() {
        System.out.println("You loose");
    }

    private void clearNonMineNonNumber(final Box component) {
        System.out.println("componet " + component);
        final int x = component.getxCordinate();
        final int y = component.getyCordinate();
        Main.set.add(x + "," + y);
        clear(component);

        int newX = x - 1;
        int newY = y;
        if (!Main.set.contains(newX + "," + newY) && newX >= 0 && newX < Main.x && newY >= 0 && newY < Main.y) {
            clearNonMineNonNumber(arr[newX][newY]);
        }

        newX = x + 1;
        newY = y;
        if (!Main.set.contains(newX + "," + newY) && newX >= 0 && newX < Main.x && newY >= 0 && newY < Main.y) {
            clearNonMineNonNumber(arr[newX][newY]);
        }

        newX = x;
        newY = y - 1;
        if (!Main.set.contains(newX + "," + newY) && newX >= 0 && newX < Main.x && newY >= 0 && newY < Main.y) {
            clearNonMineNonNumber(arr[newX][newY]);
        }

        newX = x;
        newY = y + 1;
        if (!Main.set.contains(newX + "," + newY) && newX >= 0 && newX < Main.x && newY >= 0 && newY < Main.y) {
            clearNonMineNonNumber(arr[newX][newY]);
        }
    }

    private void clear(final Box component) {
        if (!component.isEnabled()) {
            return;
        }
        if (component.isNumbered()) {
            component.setText(component.getNumber() + "");
        }
        if (!component.isMine()) {
            arr[component.getxCordinate()][component.getyCordinate()].setEnabled(false);
        }
    }
}

class Box extends JButton {
    private static final long serialVersionUID = 1L;

    private int xCordinate;
    private int yCordinate;
    private boolean isMine;
    private boolean isSweeped;
    private boolean isNumbered;
    private int number;

    public Box(final int xCordinate, final int yCordinate, final boolean isMine, final boolean isSweeped,
               final boolean isNumbered, final int number) {
        super();
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
        this.isMine = isMine;
        this.isSweeped = isSweeped;
        this.isNumbered = isNumbered;
        this.number = number;
    }

    public Box() {
        super();
    }

    @Override
    public String toString() {
        return "Box [x=" + xCordinate + ", y=" + yCordinate + ", isMine=" + isMine + ", isSweeped=" + isSweeped
                + ", isNumbered=" + isNumbered + ", number=" + number + "]";
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getxCordinate() {
        return xCordinate;
    }

    public int getyCordinate() {
        return yCordinate;
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isSweeped() {
        return isSweeped;
    }

    public boolean isNumbered() {
        return isNumbered;
    }

    public int getNumber() {
        return number;
    }

}
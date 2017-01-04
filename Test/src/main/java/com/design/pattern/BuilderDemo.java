package com.design.pattern;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

class Reader {
    private final Builder m_builder;

    public Reader(final Builder b) {
        m_builder = b;
    }

    public void construct(final String file_name) {
        try {
            final BufferedReader br = new BufferedReader(new FileReader(file_name));
            String line, cell = "";
            String[] tokens;
            boolean first_line = true;
            while ((line = br.readLine()) != null) {
                tokens = line.split("\\s");
                int i = 0;
                if (first_line) {
                    m_builder.set_width_and_height(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                    i = 2;
                    first_line = false;
                }
                for (; i < tokens.length; ++i) {
                    if (tokens[i].equals("")) {
                        m_builder.build_cell(cell);
                        cell = "";
                        m_builder.start_row();
                    } else if (tokens[i].equals("")) {
                        m_builder.build_cell(cell);
                        cell = "";
                    } else {
                        cell += " " + tokens[i];
                    }
                }
            }
            m_builder.build_cell(cell);
            br.close();
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
}

interface Builder {
    void set_width_and_height(final int width, final int height);

    void start_row();

    void build_cell(final String value);

    Component get_result();
}

class JTable_Builder implements Builder {
    private JTable m_table;
    private TableModel m_model;
    private int i = 0, j = 0;

    @Override
    public void set_width_and_height(final int width, final int height) {
        m_table = new JTable(height, width);
        m_model = m_table.getModel();
    }

    @Override
    public void start_row() {
        ++i;
        j = 0;
    }

    @Override
    public void build_cell(final String value) {
        m_model.setValueAt(value, i, j++);
    }

    @Override
    public Component get_result() {
        return m_table;
    }
}

class GridLayout_Builder implements Builder {
    private final JPanel m_panel = new JPanel();

    @Override
    public void set_width_and_height(final int width, final int height) {
        m_panel.setLayout(new GridLayout(height, width));
        m_panel.setBackground(Color.white);
    }

    @Override
    public void start_row() {
    }

    @Override
    public void build_cell(final String value) {
        m_panel.add(new Label(value));
    }

    @Override
    public Component get_result() {
        return m_panel;
    }
}

class GridBagLayout_Builder implements Builder {
    private final JPanel m_panel = new JPanel();
    private final GridBagConstraints c = new GridBagConstraints();
    private int i = 0, j = 0;

    @Override
    public void set_width_and_height(final int width, final int height) {
        m_panel.setLayout(new GridBagLayout());
        m_panel.setBackground(Color.white);
    }

    @Override
    public void start_row() {
        ++i;
        j = 0;
    }

    @Override
    public void build_cell(final String value) {
        c.gridx = j++;
        c.gridy = i;
        m_panel.add(new Label(value), c);
    }

    @Override
    public Component get_result() {
        return m_panel;
    }
}

public class BuilderDemo {
    public static void main(final String[] args) {
        Builder target = null;
        try {
            target = (Builder) Class.forName(args[0]).newInstance();
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        final Reader parser = new Reader(target);
        parser.construct("BuilderDemo.dat");

        final JFrame frame = new JFrame("BuilderDemo - " + args[0]);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(target.get_result());
        frame.pack();
        frame.setVisible(true);
    }
}

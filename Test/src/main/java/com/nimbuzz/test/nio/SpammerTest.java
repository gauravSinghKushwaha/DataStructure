package com.nimbuzz.test.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SpammerTest {

    public static void main(final String args[]) throws IOException {
        final File file = new File("/Users/gkushwaha/Documents/dhines/spammers3.txt");
        final File file1 = new File("/Users/gkushwaha/Documents/dhines/spammers1.txt");
        final FileReader reder = new FileReader(file);
        final BufferedReader reader = new BufferedReader(reder);

        final FileWriter writer = new FileWriter(file1);
        final BufferedWriter bufWriter = new BufferedWriter(writer);

        try {
            while (reader.ready()) {
                final String user = reader.readLine();
                final String userName = user.substring(0, user.lastIndexOf(','));
                String appended = userName;
                for (int i = 0; i < 25; i++) {
                    appended = "\\n" + appended;
                    if (i > 9) {
                        bufWriter.write(appended + "XXX");
                        bufWriter.newLine();
                    }
                }
            }
        } finally {
            bufWriter.close();
            reader.close();
        }

    }

}

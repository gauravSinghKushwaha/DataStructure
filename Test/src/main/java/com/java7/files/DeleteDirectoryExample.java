package com.java7.files;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class DeleteDirectoryExample {
    private static final String SRC_FOLDER = "/Users/gkushwaha/Documents/Nimuzz_Server_SVN";

    public static void main(final String[] args) throws URISyntaxException {

        final File directory = FileSystems.getDefault().getPath(SRC_FOLDER).toFile();

        // make sure directory exists
        if (!directory.exists()) {

            System.out.println("Directory does not exist.");
            System.exit(0);

        } else {
            try {
                delete(directory);
            } catch (final IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        System.out.println("Done");
    }

    public static void delete(final File file) throws IOException {

        if (file.isDirectory() && file.getName().contains("tags")) {
            System.out.println("DELETE :: " + file.getAbsolutePath());
            file.listFiles();
            for (final File temp : file.listFiles()) {
                if (temp.isFile()) {
                    temp.delete();
                } else {
                    delete(temp);
                }
            }
            if (file.list().length <= 0) {
                System.out.println("DELETE :: " + file.getAbsolutePath());
                Files.delete(file.toPath());
            }
        } else if (file.isDirectory()) {

            // list all the directory contents
            final File[] files = file.listFiles();
            for (final File temp : files) {
                // construct the file structure
                if (temp.isDirectory()) {
                    // recursive delete
                    delete(temp);
                }
            }
        } else {
            file.delete();
            System.out.println("It's file : " + file.getAbsolutePath());
        }
    }
}
package com.nimbuzz.test.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RecursiveDeleteUsingFileTree {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final Path directoryToDelete = Paths.get("D:\\Deepak\\WebSite\\DeepakGaikwadNet\\Resources");
        final DeletingFileVisitor delFileVisitor = new DeletingFileVisitor();
        try {
            Files.walkFileTree(directoryToDelete, delFileVisitor);
        } catch (final IOException ioe) {
            ioe.printStackTrace();
        }

    }

}
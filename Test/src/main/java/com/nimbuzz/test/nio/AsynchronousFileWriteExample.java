package com.nimbuzz.test.nio;

import static java.nio.file.Paths.get;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;

public class AsynchronousFileWriteExample {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final String filePath = "/Users/gkushwaha/Documents/temp/AsynchNIODataFile.txt";
        final Path file = get(filePath);
        try (AsynchronousFileChannel asynchFileChannel = AsynchronousFileChannel.open(file, WRITE, CREATE);) {

            final CompletionHandler<Integer, Object> handler = new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(final Integer result, final Object attachment) {
                    System.out.println("Thread: " + Thread.currentThread().getName()
                            + " File Write Completed with Result:" + result);
                }

                @Override
                public void failed(final Throwable e, final Object attachment) {
                    System.err.println("File Write Failed Exception:");
                    e.printStackTrace();
                }
            };
            System.out.println("Thread: " + Thread.currentThread().getName() + " Before write call");
            asynchFileChannel.write(ByteBuffer.wrap("I am writing using Asynchronous NIO.".getBytes()), 0,
                    "File Write", handler);
            System.out.println("Thread: " + Thread.currentThread().getName() + " After write call");
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
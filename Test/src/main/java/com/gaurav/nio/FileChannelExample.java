package com.gaurav.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChannelExample {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        RandomAccessFile file = null;
        try {
            final Path path = Paths.get("D:\\Deepak\\WebSite\\DeepakGaikwadNet\\Resources\\NIODataFile.txt");
            file = new RandomAccessFile(path.toFile(), "r");
            final FileChannel fileChannel = file.getChannel();
            final ByteBuffer buffer = ByteBuffer.allocate(24);
            while (fileChannel.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
            }

        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (final IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }

}

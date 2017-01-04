package com.nimbuzz.test.nio;

import static java.lang.Integer.parseInt;
import static java.lang.System.err;
import static java.lang.System.exit;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiPortEcho {
    private final int ports[];
    private final ByteBuffer echoBuffer = ByteBuffer.allocate(1024);

    public MultiPortEcho(final int ports[]) throws IOException {
        this.ports = ports;
        configure_selector();
    }

    private void configure_selector() throws IOException {
        // Create a new selector
        final Selector selector = Selector.open();

        // Open a listener on each port, and register each one
        // with the selector
        for (int i = 0; i < ports.length; ++i) {
            final ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            final ServerSocket ss = ssc.socket();
            final InetSocketAddress address = new InetSocketAddress(ports[i]);
            ss.bind(address);

            final SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Going to listen on " + ports[i]);
        }

        while (true) {
            final int num = selector.select();
            final Set<SelectionKey> selectedKeys = selector.selectedKeys();
            final Iterator<SelectionKey> it = selectedKeys.iterator();
            System.out.println(selectedKeys);
            while (it.hasNext()) {
                final SelectionKey key = it.next();

                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    // Accept the new connection
                    final ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    final SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);

                    // Add the new connection to the selector
                    final SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
                    it.remove();

                    System.out.println("Got connection from " + sc);
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    // Read the data
                    final SocketChannel sc = (SocketChannel) key.channel();

                    // Echo data
                    int bytesEchoed = 0;
                    while (true) {
                        echoBuffer.clear();

                        final int number_of_bytes = sc.read(echoBuffer);

                        if (number_of_bytes <= 0) {
                            break;
                        }

                        echoBuffer.flip();

                        sc.write(echoBuffer);
                        bytesEchoed += number_of_bytes;
                    }

                    System.out.println("Echoed " + bytesEchoed + " from " + sc);

                    it.remove();
                }

            }
        }
    }

    static public void main(final String args[]) throws Exception {
        if (args.length <= 0) {
            err.println("Usage: java MultiPortEcho port [port port ...]");
            exit(1);
        }
        final int ports[] = new int[args.length];
        for (int i = 0; i < args.length; ++i) {
            ports[i] = parseInt(args[i]);
        }
        new MultiPortEcho(ports);
    }
}

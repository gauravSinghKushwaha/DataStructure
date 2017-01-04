package com.gaurav.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class DirectoryWatcherExample {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final Path directory = Paths.get("D:\\Deepak\\WebSite\\DeepakGaikwadNet\\Resources\\");

        try {
            final WatchService fileSystemWatchService = FileSystems.getDefault().newWatchService();
            final WatchKey watchKey =
                    directory.register(fileSystemWatchService, StandardWatchEventKinds.ENTRY_CREATE,
                            StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
            while (true) {
                final WatchKey watchKeyActual = fileSystemWatchService.take();
                for (final WatchEvent<?> event : watchKeyActual.pollEvents()) {
                    final WatchEvent.Kind<?> eventKind = event.kind();

                    if (eventKind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }

                    final WatchEvent<Path> eventPath = (WatchEvent<Path>) event;
                    final Path fileName = eventPath.context();
                    System.out.println("Event " + eventKind + " occurred on " + fileName);
                }
                final boolean isReset = watchKeyActual.reset();
                if (!isReset) {
                    break;
                }
            }
        } catch (final IOException ioe) {
            ioe.printStackTrace();
        } catch (final InterruptedException ie) {
            ie.printStackTrace();
        }

    }

}

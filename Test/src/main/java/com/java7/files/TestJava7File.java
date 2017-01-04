package com.java7.files;

import static java.lang.System.out;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import static java.nio.file.attribute.PosixFilePermission.GROUP_WRITE;
import static java.nio.file.attribute.PosixFilePermission.OTHERS_EXECUTE;
import static java.nio.file.attribute.PosixFilePermission.OTHERS_READ;
import static java.nio.file.attribute.PosixFilePermissions.fromString;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.attribute.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

public class TestJava7File {
    public static void main(final String args[]) throws IOException {
        // File creation

        final Path path = Files.createTempFile(null, ".txt");
        Files.write(path, "Hello\n".getBytes());
        final Path link = path.getParent().resolve("link");
        Files.deleteIfExists(link);
        final Path symlink = Files.createSymbolicLink(link, path);
        out.println("Real file: " + path);
        out.println("Link file: " + symlink);
        out.println("Is link? " + Files.isSymbolicLink(symlink));
        out.println("Link target: " + Files.readSymbolicLink(symlink));
        out.println("Content: " + Files.readAllLines(path, Charset.defaultCharset()));
        out.println("Content type: " + Files.probeContentType(path));

        // FILE Attributes

        // classic command line
        final Path onlyForMe = path;
        final Set<PosixFilePermission> permissions = fromString("rwxrwxrwx");
        PosixFilePermission.values();
        // permissions = Arrays.asList(values);
        Files.createFile(onlyForMe, PosixFilePermissions.asFileAttribute(permissions));
        final Set<PosixFilePermission> freeAccess = Files.getPosixFilePermissions(onlyForMe);
        out.println(freeAccess);
        final EnumSet<PosixFilePermission> of = EnumSet.of(GROUP_WRITE, OTHERS_READ, OTHERS_EXECUTE);
        freeAccess.removeAll(of);
        final PosixFileAttributeView attributeView =
                Files.getFileAttributeView(onlyForMe, PosixFileAttributeView.class);
        attributeView.setPermissions(freeAccess);
        final PosixFileAttributes fileAttributes = attributeView.readAttributes();
        System.out.println(fileAttributes.permissions());
        out.println("Current owner: " + attributeView.getOwner());
        final BasicFileAttributeView basic = Files.getFileAttributeView(onlyForMe, BasicFileAttributeView.class);
        // prints device id and inode on Linux
        System.out.println(basic.readAttributes().fileKey());

        // Directory Stream

        // simple name based filter
        final Path dir = path;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{tmp,test}")) {
            for (final Path path2 : stream) {
                System.out.println(path2);
            }
        }
        // filter based on file matchers and file attributes
        final FileSystem fs = FileSystems.getDefault();
        final PathMatcher regexMatcher = fs.getPathMatcher("regex:.*7\\.\\d+.*");
        final PathMatcher globMatcher = fs.getPathMatcher("glob:/tmp/*.*");
        final Filter<? super Path> filter =
                path1 -> globMatcher.matches(path1) && regexMatcher.matches(path1) && !Files.isSymbolicLink(path1);
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter)) {
                    for (final Path path3 : stream) {
                        System.out.println(path3);
                    }
                }

                // Walk file tree

                final FileVisitor<? super Path> visitor = new MySimpleFileVisitor();
                try {
                    Files.walkFileTree(path, visitor);
                } catch (final IOException e) {
                    System.out.println("Failed to walk: " + e.getMessage());
                }

                // watch service

                final Path path3 = Paths.get(System.getProperty("java.io.tmpdir"));
                final WatchService watchService = path3.getFileSystem().newWatchService();
                final WatchKey watchKey = path3.register(watchService, OVERFLOW, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
                final Path tempFile = Files.createTempFile(path3, "", ".tmp");
                final Path path2 = Paths.get(tempFile + "_moved");
                Files.move(tempFile, path2);
                Files.write(path2, "Hello".getBytes());
                Files.deleteIfExists(path2);
                printEvents(watchKey);
                watchKey.cancel();

                printEvents(watchKey);

                // file system provider

                final List<FileSystemProvider> providers = FileSystemProvider.installedProviders();
                for (final FileSystemProvider fsProvider : providers) {
                    System.out.println("sheme: '" + fsProvider.getScheme() + "', provider: " + fsProvider.getClass());
                }
                final Path tmpFile = Files.createTempFile("", ".tmp");
                Files.write(tmpFile, "Hello".getBytes());
                final Path jarFile = Files.createTempFile("", ".jar");
                try (JarOutputStream outputStream = new JarOutputStream(Files.newOutputStream(jarFile))) {
                    outputStream.putNextEntry(new ZipEntry(tmpFile.getFileName().toString()));
                    Files.copy(tmpFile, outputStream);
                }
                try (FileSystem fileSystem = createVirtualFS(jarFile)) {
                    final Iterable<Path> directories = fileSystem.getRootDirectories();
                    for (final Path dir2 : directories) {
                        System.out.println("Reading dir: " + dir2.toUri());
                        final DirectoryStream<Path> stream = Files.newDirectoryStream(dir2);
                        for (final Path file : stream) {
                            System.out.println("Reading file: " + file.toUri());
                            System.out.println("\tfrom " + file.getFileSystem().provider().getClass());
                            System.out.print("Content: ");
                            Files.copy(file, System.out);
                        }
                    }
                }

    }

    static FileSystem createVirtualFS(final Path jarFile) throws IOException {
        return FileSystems.newFileSystem(jarFile, FileSystemProvider.class.getClassLoader());
    }

    static void printEvents(final WatchKey watchKey) {
        final List<WatchEvent<?>> events = watchKey.pollEvents();
        for (final WatchEvent<?> event : events) {
            System.out.println("-> " + event.count() + " event(s):");
            final Object context = event.context();
            if (context instanceof Path) {
                final Path path = (Path) context;
                System.out.print("\tPath: " + path);
            }
            System.out.println("\tKind: " + event.kind());
        }
    }

    private static class MySimpleFileVisitor implements FileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(final Path file, final IOException exc) throws IOException {
            return FileVisitResult.SKIP_SUBTREE;
        }

        @Override
        public FileVisitResult postVisitDirectory(final Path dir, final IOException exc) throws IOException {
            return FileVisitResult.TERMINATE;
        }
    }
}

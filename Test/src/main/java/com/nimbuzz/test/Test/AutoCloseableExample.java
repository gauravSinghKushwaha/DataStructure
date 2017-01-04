package com.nimbuzz.test.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;

public class AutoCloseableExample {

    public static final void main(final String args[]) throws IOException {

        final Set<PosixFilePermission> pers = new HashSet<PosixFilePermission>();
        pers.add(PosixFilePermission.OWNER_WRITE);
        pers.add(PosixFilePermission.OWNER_READ);
        pers.add(PosixFilePermission.OWNER_EXECUTE);
        try (InputStream is = new FileInputStream(Files.createTempFile("abc", "txt",
                                                                       PosixFilePermissions.asFileAttribute(pers))
                                                       .toFile())) {

        } catch (final IOException | RuntimeException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}

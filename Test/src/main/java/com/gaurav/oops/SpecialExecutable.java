package com.gaurav.oops;

public class SpecialExecutable implements Executable {

    @Override
    public void execute() {

        new Executable.DefaultExecutable().execute();
    }

    public static void main(final String args[]) {
        final Executable ex = new SpecialExecutable();
        ex.execute();
    }
}

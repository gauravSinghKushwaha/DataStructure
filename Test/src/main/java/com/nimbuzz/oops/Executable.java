package com.nimbuzz.oops;

public interface Executable {
    void execute();

    static class DefaultExecutable implements Executable {

        @Override
        public void execute() {
            System.out.println("Default execution");
        }

    }
}

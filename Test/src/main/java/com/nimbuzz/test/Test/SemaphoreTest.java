package com.nimbuzz.test.Test;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    static Semaphore semaphore;

    public static void main(final String args[]) throws InterruptedException {
        semaphore = new Semaphore(5);
        System.out.println("available permits ::" + semaphore.availablePermits());
        // SAME THREAD ACCQUIRING PERMITS AGAIN AND AGAIN
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();
        System.out.println("available permits ::" + semaphore.availablePermits());
        semaphore.release(10);
        System.out.println("available permits ::" + semaphore.availablePermits());
    }
}

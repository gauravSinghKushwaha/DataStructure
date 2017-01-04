package com.nimbuzz.test.Thread;

import static java.util.concurrent.Executors.newScheduledThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class BeeperControl {
    private final ScheduledExecutorService scheduler = newScheduledThreadPool(1);

    public void beepForAnHour() {
        final Runnable beeper = () -> System.out.println("beep");
        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
        scheduler.schedule(() -> beeperHandle.cancel(true), 60, SECONDS);
    }
}
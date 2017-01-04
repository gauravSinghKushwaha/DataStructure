package com.jmx;

import static java.lang.Thread.currentThread;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

public class ByteWatcher {
    private static final String GET_THREAD_ALLOCATED_BYTES = "getThreadAllocatedBytes";
    private static final MBeanServer mBeanServer;
    private static final ObjectName name;
    private static final String[] SIGNATURE = new String[] { long.class.getName() };
    static {
        try {
            name = new ObjectName(ManagementFactory.THREAD_MXBEAN_NAME);
            mBeanServer = ManagementFactory.getPlatformMBeanServer();
        } catch (final MalformedObjectNameException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    private long allocated = 0;
    private final long MEASURING_COST_IN_BYTES; // usually 336
    private final Object[] PARAMS;
    private final long tid;

    public ByteWatcher() {
        tid = currentThread().getId();
        PARAMS = new Object[] { tid };
        long calibrate = threadAllocatedBytes();
        // calibrate
        for (int repeats = 0; repeats < 10; repeats++) {
            for (int i = 0; i < 10_000; i++) {
                // run a few loops to allow for startup anomalies
                calibrate = threadAllocatedBytes();
            }
            try {
                Thread.sleep(50);
            } catch (final InterruptedException e) {
                currentThread().interrupt();
                break;
            }
        }
        MEASURING_COST_IN_BYTES = threadAllocatedBytes() - calibrate;
        reset();
    }

    /**
     * Calculates the number of bytes allocated since the last reset().
     */
    public long calculateAllocations() {
        final long mark1 = threadAllocatedBytes() - MEASURING_COST_IN_BYTES - allocated;
        return mark1;
    }

    public void reset() {
        allocated = threadAllocatedBytes();
    }

    private long threadAllocatedBytes() {
        try {
            return (long) mBeanServer.invoke(name, GET_THREAD_ALLOCATED_BYTES, PARAMS, SIGNATURE);
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }
}

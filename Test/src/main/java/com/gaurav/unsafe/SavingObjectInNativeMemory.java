package com.gaurav.unsafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import sun.misc.Unsafe;

//(Outside heap)
public class SavingObjectInNativeMemory {

    Unsafe unsafe;

    public SavingObjectInNativeMemory() throws Exception {
        final Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        unsafe = unsafeConstructor.newInstance();
    }

    public static void main(final String args[]) throws Exception {
        final SavingObjectInNativeMemory obj = new SavingObjectInNativeMemory();
        final long containerSize = obj.sizeOf(Container.class);
        final long address = obj.unsafe.allocateMemory(containerSize);
        final Container c1 = new Container(10, 1000L);
        final Container c2 = new Container(5, -10L);
        obj.place(c1, address);
        obj.place(c2, address + containerSize);
        final Container newC1 = (Container) obj.read(Container.class, address);
        final Container newC2 = (Container) obj.read(Container.class, address + containerSize);
        assertEquals(c1, newC1);
        assertEquals(c2, newC2);
    }

    public long sizeOf(Class<?> clazz) {
        long maximumOffset = 0;
        do {
            for (final Field f : clazz.getDeclaredFields()) {
                if (!Modifier.isStatic(f.getModifiers())) {
                    maximumOffset = Math.max(maximumOffset, unsafe.objectFieldOffset(f));
                }
            }
        } while ((clazz = clazz.getSuperclass()) != null);
        return maximumOffset + 8;
    }

    public void place(final Object o, final long address) throws Exception {
        Class clazz = o.getClass();
        do {
            for (final Field f : clazz.getDeclaredFields()) {
                if (!Modifier.isStatic(f.getModifiers())) {
                    final long offset = unsafe.objectFieldOffset(f);
                    if (f.getType() == long.class) {
                        unsafe.putLong(address + offset, unsafe.getLong(o, offset));
                    } else if (f.getType() == int.class) {
                        unsafe.putInt(address + offset, unsafe.getInt(o, offset));
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
            }
        } while ((clazz = clazz.getSuperclass()) != null);
    }

    public Object read(Class clazz, final long address) throws Exception {
        final Object instance = unsafe.allocateInstance(clazz);
        do {
            for (final Field f : clazz.getDeclaredFields()) {
                if (!Modifier.isStatic(f.getModifiers())) {
                    final long offset = unsafe.objectFieldOffset(f);
                    if (f.getType() == long.class) {
                        unsafe.putLong(instance, offset, unsafe.getLong(address + offset));
                    } else if (f.getType() == int.class) {
                        unsafe.putLong(instance, offset, unsafe.getInt(address + offset));
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
            }
        } while ((clazz = clazz.getSuperclass()) != null);
        return instance;
    }

    // park/unpark ...threads
    // The park and unpark methods allow you to pause a thread for a certain amount of time and to resume it:
    public void testPark() throws Exception {
        final boolean[] run = new boolean[1];
        final Thread thread = new Thread() {
            @Override
            public void run() {
                unsafe.park(true, 100000L);
                run[0] = true;
            }
        };
        thread.start();
        unsafe.unpark(thread);
        thread.join(100L);
        assertTrue(run[0]);
    }

    public void testCopy() throws Exception {
        final long address = unsafe.allocateMemory(4L);
        unsafe.putInt(address, 100);
        final long otherAddress = unsafe.allocateMemory(4L);
        unsafe.copyMemory(address, otherAddress, 4L);
        assertEquals(100, unsafe.getInt(otherAddress));
    }

    private static class Container {
        private final int i;
        private final long j;

        Container(final int i, final long j) {
            this.i = i;
            this.j = j;
        }
    }
}

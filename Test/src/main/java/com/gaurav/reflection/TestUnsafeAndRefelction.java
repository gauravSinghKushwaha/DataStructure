package com.gaurav.reflection;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import sun.misc.Unsafe;
import sun.reflect.ReflectionFactory;

// class creates an int array in native memory (outside heap) ,array size more than max int
class DirectIntArray {

    private final static long INT_SIZE_IN_BYTES = 4;
    private final Unsafe unsafe;
    private final long startIndex;

    public DirectIntArray(final Unsafe unsafe, final long size) {
        this.unsafe = unsafe;
        // allocating memory
        startIndex = unsafe.allocateMemory(size * INT_SIZE_IN_BYTES);
        // setting every byte to zero
        unsafe.setMemory(startIndex, size * INT_SIZE_IN_BYTES, (byte) 0);
    }

    public void setValue(final long index, final int value) {
        unsafe.putInt(index(index), value);
    }

    public int getValue(final long index) {
        return unsafe.getInt(index(index));
    }

    private long index(final long offset) {
        return startIndex + offset * INT_SIZE_IN_BYTES;
    }

    public void destroy() {
        unsafe.freeMemory(startIndex);
    }
}

public class TestUnsafeAndRefelction {

    private final int value;

    private TestUnsafeAndRefelction() {
        value = 1;
    }

    public void method() {
        System.out.println("Method called " + value);
    }

    @SuppressWarnings("unchecked")
    public static void main(final String args[]) throws InstantiationException, IllegalAccessException,
    IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // creating new instance
        final Constructor<?>[] constructors = TestUnsafeAndRefelction.class.getDeclaredConstructors();
        final TestUnsafeAndRefelction obj = (TestUnsafeAndRefelction) constructors[0].newInstance();
        obj.method();

        // creating instance via UnSafe , without calling constructor
        final Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        final Unsafe unsafe = unsafeConstructor.newInstance();
        final TestUnsafeAndRefelction instance = (TestUnsafeAndRefelction) unsafe.allocateInstance(TestUnsafeAndRefelction.class);
        assertEquals(0, instance.value);

        // Using reflection factory , without calling some other class constructor constructor (borrowing)
        final Constructor<TestUnsafeAndRefelction> silentConstructor =
                (Constructor<TestUnsafeAndRefelction>) ReflectionFactory.getReflectionFactory().newConstructorForSerialization(TestUnsafeAndRefelction.class,
                        Object.class.getConstructor());
        silentConstructor.setAccessible(true);
        assertEquals(0, silentConstructor.newInstance().value);

        // Using reflection factory , without calling some other class constructor constructor (borrowing)
        final Constructor<TestUnsafeAndRefelction> silentConstructor2 =
                (Constructor<TestUnsafeAndRefelction>) ReflectionFactory.getReflectionFactory().newConstructorForSerialization(TestUnsafeAndRefelction.class,
                        Test1.class.getConstructor());
        silentConstructor2.setAccessible(true);
        assertEquals(1, silentConstructor2.newInstance().value);

        // Using reflection factory , calling constructor
        final Constructor<TestUnsafeAndRefelction> nonSilentConstructor =
                (Constructor<TestUnsafeAndRefelction>) ReflectionFactory.getReflectionFactory().newConstructorForSerialization(TestUnsafeAndRefelction.class,
                        TestUnsafeAndRefelction.class.getDeclaredConstructor());
        nonSilentConstructor.setAccessible(true);
        assertEquals(1, nonSilentConstructor.newInstance().value);

        // TEsting direct in array

        // final long maximum = Integer.MAX_VALUE + 1L;
        final long maximum = 100;
        final DirectIntArray directIntArray = new DirectIntArray(unsafe, maximum);
        directIntArray.setValue(0L, 10);
        directIntArray.setValue(maximum, 20);
        assertEquals(10, directIntArray.getValue(0L));
        assertEquals(20, directIntArray.getValue(maximum));
        directIntArray.destroy();
    }

    public long sizeOf(Class<?> clazz, final Unsafe unsafe) {
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
}

class Test1 {
    private final int value;

    public Test1() {
        value = 1;
    }
}

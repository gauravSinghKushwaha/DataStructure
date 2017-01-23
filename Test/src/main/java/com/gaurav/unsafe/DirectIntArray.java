package com.gaurav.unsafe;

import static java.lang.Integer.MAX_VALUE;
import static junit.framework.Assert.assertEquals;

import java.lang.reflect.Constructor;

import sun.misc.Unsafe;

// creating array outside heap in native memory
class DirectIntArray {

	private final static long INT_SIZE_IN_BYTES = 4;

	public static void main(final String args[]) throws Exception {
		// final long maximum = MAX_VALUE + 1L;
		final long maximum = MAX_VALUE - MAX_VALUE / 2 - MAX_VALUE / 4;
		final DirectIntArray directIntArray = new DirectIntArray(maximum);
		directIntArray.setValue(0L, 10);
		directIntArray.setValue(maximum, 20);
		assertEquals(10, directIntArray.getValue(0L));
		assertEquals(20, directIntArray.getValue(maximum));
		directIntArray.destroy();
	}

	private final long startIndex;

	private final Unsafe unsafe;

	public DirectIntArray(final long size) throws Exception {
		// final Field field = Unsafe.class.getField("theUnsafe");
		// field.setAccessible(true);
		// unsafe = (Unsafe) field.get(null);

		final Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
		unsafeConstructor.setAccessible(true);
		unsafe = unsafeConstructor.newInstance();

		startIndex = unsafe.allocateMemory(size * INT_SIZE_IN_BYTES);
		unsafe.setMemory(startIndex, size * INT_SIZE_IN_BYTES, (byte) 0);
	}

	public void destroy() {
		unsafe.freeMemory(startIndex);
	}

	public int getValue(final long index) {
		return unsafe.getInt(index(index));
	}

	private long index(final long offset) {
		return startIndex + offset * INT_SIZE_IN_BYTES;
	}

	public void setValue(final long index, final int value) {
		unsafe.putInt(index(index), value);
	}

}

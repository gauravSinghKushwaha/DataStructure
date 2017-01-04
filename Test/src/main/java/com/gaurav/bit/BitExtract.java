package com.gaurav.bit;


public class BitExtract {

    // copied from
    // http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/7-b147/java/lang/Long.java#Long.bitCount%28long%29
    public static int mybitCount(long i) {
        i = i - (i >>> 1 & 0x5555555555555555L);
        i = (i & 0x3333333333333333L) + (i >>> 2 & 0x3333333333333333L);
        i = i + (i >>> 4) & 0x0f0f0f0f0f0f0f0fL;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        i = i + (i >>> 32);
        return (int) i & 0x7f;
    }

    // copied from
    // http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/7-b147/java/lang/Long.java#Long.bitCount%28long%29
    public static int mynumberOfTrailingZeros(final long i) {
        int x, y;
        if (i == 0) {
            return 64;
        }
        int n = 63;
        y = (int) i;
        if (y != 0) {
            n = n - 32;
            x = y;
        } else {
            x = (int) (i >>> 32);
        }
        y = x << 16;
        if (y != 0) {
            n = n - 16;
            x = y;
        }
        y = x << 8;
        if (y != 0) {
            n = n - 8;
            x = y;
        }
        y = x << 4;
        if (y != 0) {
            n = n - 4;
            x = y;
        }
        y = x << 2;
        if (y != 0) {
            n = n - 2;
            x = y;
        }
        return n - (x << 1 >>> 31);
    }

    public static int bitscan0(final long[] bitmaps, final int[] output) {
        int counter = 0;
        for (int k = 0; k < bitmaps.length; ++k) {
            for (int c = 0; c < 64; ++c) {
                if ((1l << c & bitmaps[k]) != 0) {
                    output[counter++] = k * 64 + c;
                }
            }
        }
        return counter;
    }

    // inspired by http://www.steike.com/code/bits/debruijn/
    public static int bitscan1(final long[] bitmaps, final int[] output) {
        int pos = 0;
        for (int k = 0; k < bitmaps.length; ++k) {
            long bitset = bitmaps[k];
            while (bitset != 0) {
                final long t = bitset & -bitset;
                output[pos++] = k * 64 + Long.bitCount(t - 1);
                bitset ^= t;
            }
        }
        return pos;
    }

    public static int bitscan1Kaser(final long[] bitmaps, final int[] output) {
        int pos = 0;
        for (int k = 0; k < bitmaps.length; ++k) {
            long bitset = bitmaps[k];
            while (bitset != 0) {
                final long t = bitset & -bitset;
                output[pos++] = k * 64 + Long.bitCount(t - 1);
                bitset &= bitset - 1;
            }
        }
        return pos;
    }

    public static int bitscan1Kaser2(final long[] bitmaps, final int[] output) {
        int pos = 0;
        for (int k = 0; k < bitmaps.length; ++k) {
            long bitset = bitmaps[k];
            while (bitset != 0) {
                final long t = bitset & -bitset;
                final long newbitset = bitset & bitset - 1;
                output[pos++] = k * 64 + Long.bitCount(t - 1);
                bitset = newbitset;
            }
        }
        return pos;
    }

    // inspired by http://www.steike.com/code/bits/debruijn/
    public static int bitscan1f(final long[] bitmaps, final int[] output) {
        int pos = 0;
        for (int k = 0; k < bitmaps.length; ++k) {
            long bitset = bitmaps[k];
            while (bitset != 0) {
                final long t = bitset & -bitset;
                output[pos++] = k * 64 + mybitCount(t - 1);
                bitset ^= t;
            }
        }
        return pos;
    }

    public static int bitscan2(final long[] bitmaps, final int[] output) {
        int pos = 0;
        for (int k = 0; k < bitmaps.length; ++k) {
            long data = bitmaps[k];
            while (data != 0) {
                final int ntz = Long.numberOfTrailingZeros(data);
                output[pos++] = k * 64 + ntz;
                data ^= 1l << ntz;
            }
        }
        return pos;
    }

    public static int bitscan2f(final long[] bitmaps, final int[] output) {
        int pos = 0;
        for (int k = 0; k < bitmaps.length; ++k) {
            long data = bitmaps[k];
            while (data != 0) {
                final int ntz = mynumberOfTrailingZeros(data);
                output[pos++] = k * 64 + ntz;
                data ^= 1l << ntz;
            }
        }
        return pos;
    }

    public static int bitscan2n(final long[] bitmaps, final int[] output) {
        int pos = 0;
        for (int k = 0; k < bitmaps.length; ++k) {
            long data = bitmaps[k];
            while (data != 0) {
                final long t = data & -data;
                final int ntz = mynumberOfTrailingZeros(data);
                output[pos++] = k * 64 + ntz;
                data ^= t;
            }
        }
        return pos;
    }

    public static int bitscan2nn(final long[] bitmaps, final int[] output) {
        int pos = 0;
        for (int k = 0; k < bitmaps.length; ++k) {
            long data = bitmaps[k];
            while (data != 0) {
                final int ntz = mynumberOfTrailingZeros(data);
                output[pos++] = k * 64 + ntz;
                data &= data - 1;
            }
        }
        return pos;
    }

    public static int bitscan2nnn(final long[] bitmaps, final int[] output) {
        int pos = 0;
        for (int k = 0; k < bitmaps.length; ++k) {
            long data = bitmaps[k];
            while (data != 0) {
                final long copy = data;
                data &= data - 1;
                final int ntz = mynumberOfTrailingZeros(copy);
                output[pos++] = k * 64 + ntz;
            }
        }
        return pos;
    }

    // inspired by http://www.steike.com/code/bits/debruijn/
    public static int bitscan3(final long[] bitmaps, final int[] output) {
        final int[] table =
                { 0, 1, 2, 7, 3, 13, 8, 19, 4, 25, 14, 28, 9, 34, 20, 40, 5, 17, 26, 38, 15, 46, 29, 48, 10, 31, 35,
                        54, 21, 50, 41, 57, 63, 6, 12, 18, 24, 27, 33, 39, 16, 37, 45, 47, 30, 53, 49, 56, 62, 11, 23,
                        32, 36, 44, 52, 55, 61, 22, 43, 51, 60, 42, 59, 58 };
        int pos = 0;
        for (int k = 0; k < bitmaps.length; ++k) {
            long bitset = bitmaps[k];
            while (bitset != 0) {
                final long t = bitset & -bitset;
                output[pos++] = table[(int) (t * 0x0218a392cd3d5dbfL) >>> 58];
                bitset ^= t;
            }
        }
        return pos;
    }

    public static void main(final String[] args) {
        {
            final long[] bitmap = new long[10];
            bitmap[1] = 10;
            bitmap[2] = 10;
            final int[] output = new int[64];
            int sum = 0;
            for (int t = 0; t < 10; ++t) {
                for (int k = 0; k < 1000000; ++k) {
                    sum +=
                            bitscan0(bitmap, output) + bitscan1(bitmap, output) + bitscan1Kaser(bitmap, output)
                                    + bitscan1Kaser2(bitmap, output) + bitscan1f(bitmap, output)
                                    + bitscan2(bitmap, output) + bitscan2f(bitmap, output) + bitscan3(bitmap, output);
                }
                System.out.print(".");
                System.out.flush();
            }
            System.out.println("ignore = " + sum);

        }
        final int N = 100000;
        for (int sb = 1; sb <= 32; sb *= 2) {
            final int setbitsmax = sb * N;
            final long[] bitmap = new long[N];
            final java.util.Random r = new java.util.Random();
            for (int k = 0; k < setbitsmax; ++k) {
                final int bit = Math.abs(r.nextInt()) % (N * 64);
                if (bit >= N * 64) {
                    throw new RuntimeException("what?");
                }
                bitmap[bit / 64] |= 1L << bit % 64;
            }
            int bitcount = 0;
            for (int k = 0; k < bitmap.length; ++k) {
                bitcount += Long.bitCount(bitmap[k]);
            }

            final int[] output = new int[bitcount];
            for (int t = 0; t < 5; ++t) {
                final long bef0 = System.nanoTime();
                int c0 = 0;
                for (int t1 = 0; t1 < 100; ++t1) {
                    c0 = bitscan0(bitmap, output);
                }
                final long aft0 = System.nanoTime();
                final long bef1Kaser = System.nanoTime();
                int c1Kaser = 0;
                for (int t1 = 0; t1 < 100; ++t1) {
                    c1Kaser = bitscan1Kaser(bitmap, output);
                }
                final long aft1Kaser = System.nanoTime();
                if (c1Kaser != c0) {
                    throw new RuntimeException("bug1Kaser");
                }
                final long bef1 = System.nanoTime();
                int c1 = 0;
                for (int t1 = 0; t1 < 100; ++t1) {
                    c1 = bitscan1(bitmap, output);
                }
                final long aft1 = System.nanoTime();
                if (c1 != c0) {
                    throw new RuntimeException("bug1");
                }
                final long bef1Kaser2 = System.nanoTime();
                int c1Kaser2 = 0;
                for (int t1 = 0; t1 < 100; ++t1) {
                    c1Kaser2 = bitscan1Kaser2(bitmap, output);
                }
                final long aft1Kaser2 = System.nanoTime();
                if (c1Kaser2 != c0) {
                    throw new RuntimeException("bug1Kaser");
                }
                final long bef1f = System.nanoTime();
                int c1f = 0;
                for (int t1 = 0; t1 < 100; ++t1) {
                    c1f = bitscan1f(bitmap, output);
                }
                final long aft1f = System.nanoTime();
                if (c1f != c0) {
                    throw new RuntimeException("bug1f");
                }
                final long bef2 = System.nanoTime();
                int c2 = 0;
                for (int t1 = 0; t1 < 100; ++t1) {
                    c2 = bitscan2(bitmap, output);
                }
                final long aft2 = System.nanoTime();
                if (c1 != c2) {
                    throw new RuntimeException("bug2");
                }
                final long bef2f = System.nanoTime();
                int c2f = 0;
                for (int t1 = 0; t1 < 100; ++t1) {
                    c2f = bitscan2f(bitmap, output);
                }
                final long aft2f = System.nanoTime();
                if (c1 != c2f) {
                    throw new RuntimeException("bug2f");
                }
                final long bef2n = System.nanoTime();
                int c2n = 0;
                for (int t1 = 0; t1 < 100; ++t1) {
                    c2n = bitscan2n(bitmap, output);
                }
                final long aft2n = System.nanoTime();
                if (c1 != c2n) {
                    throw new RuntimeException("bug2n");
                }
                final long bef2nn = System.nanoTime();
                int c2nn = 0;
                for (int t1 = 0; t1 < 100; ++t1) {
                    c2nn = bitscan2nn(bitmap, output);
                }
                final long aft2nn = System.nanoTime();
                if (c1 != c2nn) {
                    throw new RuntimeException("bug2n");
                }
                final long bef2nnn = System.nanoTime();
                int c2nnn = 0;
                for (int t1 = 0; t1 < 100; ++t1) {
                    c2nnn = bitscan2nnn(bitmap, output);
                }
                final long aft2nnn = System.nanoTime();
                if (c1 != c2nnn) {
                    throw new RuntimeException("bug2n");
                }

                final long bef3 = System.nanoTime();
                int c3 = 0;
                for (int t1 = 0; t1 < 100; ++t1) {
                    c3 = bitscan3(bitmap, output);
                }
                final long aft3 = System.nanoTime();
                if (c1 != c3) {
                    throw new RuntimeException("bug3");
                }
                if (t > 2) {
                    System.out.println("# density 0 1 1f 2 2f 2n 2nn 2nnn 3 1k 1k2");
                    System.out.println(sb + " " + bitcount * 100.0 * 1000 / (aft0 - bef0) + " "
                            + bitcount * 100.0 * 1000 / (aft1 - bef1) + " " + bitcount * 100.0 * 1000 / (aft1f - bef1f)
                            + " " + bitcount * 100.0 * 1000 / (aft2 - bef2) + " "
                            + bitcount * 100.0 * 1000 / (aft2f - bef2f) + " "
                            + bitcount * 100.0 * 1000 / (aft2n - bef2n) + " "
                            + bitcount * 100.0 * 1000 / (aft2nn - bef2nn) + " "
                            + bitcount * 100.0 * 1000 / (aft2nnn - bef2nnn) + " "
                            + bitcount * 100.0 * 1000 / (aft3 - bef3) + " "
                            + bitcount * 100.0 * 1000 / (aft1Kaser - bef1Kaser) + " " + bitcount * 100.0 * 1000
                            / (aft1Kaser2 - bef1Kaser2));
                }
            }
        }

    }

}
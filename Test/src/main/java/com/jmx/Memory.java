package com.jmx;

public enum Memory {
    BYTES {
        @Override
        public double toBytes(final double d) {
            return d;
        }

        @Override
        public double toGigaBytes(final double d) {
            return toMegaBytes(d) / 1024;
        }

        @Override
        public double toKiloBytes(final double d) {
            return toBytes(d) / 1024;
        }

        @Override
        public double toMegaBytes(final double d) {
            return toKiloBytes(d) / 1024;
        }

        @Override
        public double toTeraBytes(final double d) {
            return toGigaBytes(d) / 1024;
        }
    },
    GIGABYTES {
        @Override
        public double toBytes(final double d) {
            return toKiloBytes(d) * 1024;
        }

        @Override
        public double toGigaBytes(final double d) {
            return d;
        }

        @Override
        public double toKiloBytes(final double d) {
            return toMegaBytes(d) * 1024;
        }

        @Override
        public double toMegaBytes(final double d) {
            return toGigaBytes(d) * 1024;
        }

        @Override
        public double toTeraBytes(final double d) {
            return toGigaBytes(d) / 1024;
        }
    },
    KILOBYTES {
        @Override
        public double toBytes(final double d) {
            return toKiloBytes(d) * 1024;
        }

        @Override
        public double toGigaBytes(final double d) {
            return toMegaBytes(d) / 1024;
        }

        @Override
        public double toKiloBytes(final double d) {
            return d;
        }

        @Override
        public double toMegaBytes(final double d) {
            return toKiloBytes(d) / 1024;
        }

        @Override
        public double toTeraBytes(final double d) {
            return toGigaBytes(d) / 1024;
        }
    },
    MEGABYTES {
        @Override
        public double toBytes(final double d) {
            return toKiloBytes(d) * 1024;
        }

        @Override
        public double toGigaBytes(final double d) {
            return toMegaBytes(d) / 1024;
        }

        @Override
        public double toKiloBytes(final double d) {
            return toMegaBytes(d) * 1024;
        }

        @Override
        public double toMegaBytes(final double d) {
            return d;
        }

        @Override
        public double toTeraBytes(final double d) {
            return toGigaBytes(d) / 1024;
        }
    },
    TERABYTES {
        @Override
        public double toBytes(final double d) {
            return toKiloBytes(d) * 1024;
        }

        @Override
        public double toGigaBytes(final double d) {
            return toTeraBytes(d) * 1024;
        }

        @Override
        public double toKiloBytes(final double d) {
            return toMegaBytes(d) * 1024;
        }

        @Override
        public double toMegaBytes(final double d) {
            return toGigaBytes(d) * 1024;
        }

        @Override
        public double toTeraBytes(final double d) {
            return d;
        }
    };
    public static String format(final double d, final Memory unit, final int decimals) {
        String unitStr;
        double val;
        final double bytes = unit.toBytes(d);
        if (bytes < 1024) {
            val = bytes;
            unitStr = "B";
        } else if (bytes < 1024 * 1024) {
            val = BYTES.toKiloBytes(bytes);
            unitStr = "KB";
        } else if (bytes < 1024 * 1024 * 1024) {
            val = BYTES.toMegaBytes(bytes);
            unitStr = "MB";
        } else if (bytes < 1024 * 1024 * 1024 * 1024L) {
            val = BYTES.toGigaBytes(bytes);
            unitStr = "GB";
        } else {
            val = BYTES.toTeraBytes(bytes);
            unitStr = "TB";
        }
        return String.format("%." + decimals + "f%s", val, unitStr);
    }

    public abstract double toBytes(final double d);

    public abstract double toGigaBytes(final double d);

    public abstract double toKiloBytes(final double d);

    public abstract double toMegaBytes(final double d);

    public abstract double toTeraBytes(final double d);
}
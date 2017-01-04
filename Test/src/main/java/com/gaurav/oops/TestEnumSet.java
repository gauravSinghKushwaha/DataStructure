package com.gaurav.oops;

import java.util.EnumSet;

public class TestEnumSet {

    private enum DAYS {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

        DAY_TYPE getTypeOfDay() {
            switch (this) {
                case SUNDAY:
                case SATURDAY:
                    return DAY_TYPE.OFFDAY_SALARY;
                case MONDAY:
                case TUESDAY:
                case WEDNESDAY:
                case THURSDAY:
                case FRIDAY:
                    return DAY_TYPE.WEEKDAY_SALARY;
            }
            return null;
        }

        private enum DAY_TYPE {
            WEEKDAY_SALARY, OFFDAY_SALARY;

            int getSalary() {
                switch (this) {
                    case WEEKDAY_SALARY:
                        return 100;
                    case OFFDAY_SALARY:
                        return 200;
                }
                return 0;
            }
        }
    }

    public static void main(final String args[]) {
        System.out.println(DAYS.MONDAY.getTypeOfDay().getSalary());
        final EnumSet<DAYS> set = EnumSet.allOf(DAYS.class);
        for (final DAYS days : set) {
            System.out.println(days);
        }

        final EnumSet<DAYS> set2 = EnumSet.noneOf(DAYS.class);
        for (final DAYS days : set2) {
            System.out.println(days);
        }

        final EnumSet<DAYS> set3 = EnumSet.of(DAYS.SATURDAY, DAYS.SUNDAY);
        for (final DAYS days : set3) {
            System.out.println(days);
        }
    }

}

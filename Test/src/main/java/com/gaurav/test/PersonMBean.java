package com.gaurav.test;

public interface PersonMBean {
    String getName();

    int getAge();

    void setAge(final int age);

    boolean isAlive();

}

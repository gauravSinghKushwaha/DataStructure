/**
 * 
 */
package com.nimbuzz.test.Test;

import java.io.Serializable;
import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author gkushwaha
 *
 */
public class Person implements Serializable, PersonMBean {
    private static final long serialVersionUID = 1633343915728223785L;
    private String name;
    private int age;
    private final boolean isAlive;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Person(final String name, final int age, final boolean isAlive) {
        super();
        this.name = name;
        this.age = age;
        this.isAlive = isAlive;
    }

    public static void main(final String args[]) throws Exception {
        final Person p = new Person("name", 50, true);
        // Get the MBean server
        final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        // register the MBean
        final ObjectName name = new ObjectName("com.nimbuzz.test.Test:type=p");
        mbs.registerMBean(p, name);
        while (p.getAge() <= 100) {
            System.out.println(p);
            Thread.sleep(1000);
        }
        System.out.println(p);
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", isAlive=" + isAlive + "]";
    }

}

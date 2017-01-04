package com.gaurav.design.pattern;

import java.lang.reflect.Method;

class ReflectiveVisitorDemo {

    // The "operation" hierarchy

    abstract class ReflectiveVisitor {
        abstract public void visit(final Object o);

        public void visitTheOther(final TheOther e) {
            System.out.println("ReflectiveVisitor: do Base on " + e.theOther());
        }

        // 1. Look for visitElementClassName() in the current class
        // 2. Look for visitElementClassName() in superclasses
        // 3. Look for visitElementClassName() in interfaces
        // 4. Look for visitObject() in current class
        protected Method getMethod(final Class c) {
            Class newc = c;
            Method m = null;
            while (m == null && newc != Object.class) {
                String method = newc.getName();
                method = "visit" + method.substring(method.lastIndexOf('.') + 1);
                try {
                    m = getClass().getMethod(method, new Class[] { newc });
                } catch (final NoSuchMethodException ex) {
                    newc = newc.getSuperclass();
                }
            }
            if (newc == Object.class) {
                // System.out.println( "Searching for interfaces" );
                final Class[] interfaces = c.getInterfaces();
                for (int i = 0; i < interfaces.length; i++) {
                    String method = interfaces[i].getName();
                    method = "visit" + method.substring(method.lastIndexOf('.') + 1);
                    try {
                        m = getClass().getMethod(method, new Class[] { interfaces[i] });
                    } catch (final NoSuchMethodException ex) {
                    }
                }
            }
            if (m == null) {
                try {
                    m = getClass().getMethod("visitObject", new Class[] { Object.class });
                } catch (final Exception ex) {
                }
            }
            return m;
        }
    }

    class UpVisitor extends ReflectiveVisitor {
        @Override
        public void visit(final Object o) {
            try {
                getMethod(o.getClass()).invoke(this, new Object[] { o });
            } catch (final Exception ex) {
                System.out.println("UpVisitor - no appropriate visit() method");
            }
        }

        public void visitThis(final This e) {
            System.out.println("UpVisitor: do Up on " + e.thiss());
        }

        public void visitObject(final Object e) {
            System.out.println("UpVisitor: generic visitObject() method");
        }
    }

    class DownVisitor extends ReflectiveVisitor {
        @Override
        public void visit(final Object o) {
            try {
                getMethod(o.getClass()).invoke(this, new Object[] { o });
            } catch (final Exception ex) {
                System.out.println("DownVisitor - no appropriate visit() method");
            }
        }

        public void visitThat(final That e) {
            System.out.println("DownVisitor: do Down on " + e.that());
        }
    }

    interface Element {
        public void accept(final ReflectiveVisitor v);
    }

    class This implements Element {
        @Override
        public void accept(final ReflectiveVisitor v) {
            v.visit(this);
        }

        public String thiss() {
            return "This";
        }
    }

    class That implements Element {
        @Override
        public void accept(final ReflectiveVisitor v) {
            v.visit(this);
        }

        public String that() {
            return "That";
        }
    }

    class TheOther implements Element {
        @Override
        public void accept(final ReflectiveVisitor v) {
            v.visit(this);
        }

        public String theOther() {
            return "TheOther";
        }
    }

    public static void main(final String[] args) {
        final ReflectiveVisitorDemo demo = new ReflectiveVisitorDemo();
        final Element[] list = { demo.new This(), demo.new That(), demo.new TheOther() };
        final UpVisitor up = demo.new UpVisitor();
        final DownVisitor down = demo.new DownVisitor();
        for (int i = 0; i < list.length; i++) {
            list[i].accept(up);
        }
        for (int i = 0; i < list.length; i++) {
            list[i].accept(down);
        }
    }
}
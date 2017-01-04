package com.gaurav.design.pattern;

interface Element {
    // 1. accept(Visitor) interface
    public void accept(final Visitor v); // first dispatch
}

class This implements Element {
    // 1. accept(Visitor) implementation
    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

    public String thiss() {
        return "This";
    }
}

class That implements Element {
    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

    public String that() {
        return "That";
    }
}

class TheOther implements Element {
    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

    public String theOther() {
        return "TheOther";
    }
}

// 2. Create a "visitor" base class with a visit() method for every "element" type
interface Visitor {
    public void visit(final This e); // second dispatch

    public void visit(final That e);

    public void visit(final TheOther e);
}

// 3. Create a "visitor" derived class for each "operation" to perform on "elements"
class UpVisitor implements Visitor {
    @Override
    public void visit(final This e) {
        System.out.println("do Up on " + e.thiss());
    }

    @Override
    public void visit(final That e) {
        System.out.println("do Up on " + e.that());
    }

    @Override
    public void visit(final TheOther e) {
        System.out.println("do Up on " + e.theOther());
    }
}

class DownVisitor implements Visitor {
    @Override
    public void visit(final This e) {
        System.out.println("do Down on " + e.thiss());
    }

    @Override
    public void visit(final That e) {
        System.out.println("do Down on " + e.that());
    }

    @Override
    public void visit(final TheOther e) {
        System.out.println("do Down on " + e.theOther());
    }
}

class VisitorDemo {
    public static Element[] list = { new This(), new That(), new TheOther() };

    // 4. Client creates "visitor" objects and passes each to accept() calls
    public static void main(final String[] args) {
        final UpVisitor up = new UpVisitor();
        final DownVisitor down = new DownVisitor();
        for (int i = 0; i < list.length; i++) {
            list[i].accept(up);
        }
        for (int i = 0; i < list.length; i++) {
            list[i].accept(down);
        }
    }
}
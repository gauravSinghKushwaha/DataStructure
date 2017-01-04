package com.nimbuzz.oops;

public class MainClass {

    public static void main(final String aegs[]) {
        new StaticChildClass.ChildClassChild().method2();
        new StaticChildClass().new ChildClassNonStatic().method2();
        StaticChildClass.ChildClassPublic.method3();
        StaticChildClass.ChildClassChild.method3();
    }

}

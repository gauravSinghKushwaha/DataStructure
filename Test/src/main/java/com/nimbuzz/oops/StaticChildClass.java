package com.nimbuzz.oops;

final class StaticChildClass {

    static class ChildClassPublic {
        ChildClassPublic() {
            System.out.println("ChildClassPublic " + this.getClass().getName());
        }

        void method2() {
            System.out.println("ChildClassPublic method2");
        }

        public static void method3() {
            System.out.println("ChildClassPublic method3");

        }
    }

    static class ChildClassChild extends ChildClassPublic {
        ChildClassChild() {
            System.out.println("ChildClassChild " + this.getClass().getName());
        }

        @Override
        void method2() {
            System.out.println("ChildClassChild method2");
        }

        public static void method3() {
            System.out.println("ChildClassChild method3");
        }
    }

    class ChildClassNonStatic extends ChildClassPublic {

        ChildClassNonStatic() {
            System.out.println("ChildClassNonStatic " + this.getClass().getName());
        }

        @Override
        void method2() {
            System.out.println("ChildClassNonStatic method2");
        }

    }

}

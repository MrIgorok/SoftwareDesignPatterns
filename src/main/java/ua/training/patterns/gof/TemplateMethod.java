package ua.training.patterns.gof;

import ua.training.Testable;

public class TemplateMethod implements Testable {
    @Override
    public void test() {
        C a = new A();
        a.templateMethod();

        C b = new B();
        b.templateMethod();
    }

    private static abstract class C {
        void templateMethod() {
            System.out.println("1");
            differ();
            System.out.println("3");
            differ2();
        }

        abstract void differ();
        abstract void differ2();
    }

    private static class A extends C {
        void differ() {
            System.out.println("2");
        }

        void differ2() {
            System.out.println("5");
        }
    }

    private static class B extends C {
        void differ() {
            System.out.println("4");
        }

        void differ2() {
            System.out.println("8");
        }
    }
}

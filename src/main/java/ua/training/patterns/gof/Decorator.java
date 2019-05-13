package ua.training.patterns.gof;

import ua.training.Testable;

public class Decorator implements Testable {
    @Override
    public void test() {
        Printer printer = new QuotesDecorator(new RightBrakeDecorator(new LeftBracketDecorator(new PrinterImpl("Hello"))));
        printer.print();
        System.out.println();
    }

    private interface Printer {
        void print();
    }

    private static class PrinterImpl implements Printer {
        private String value;

        public PrinterImpl(String value) {
            this.value = value;
        }

        @Override
        public void print() {
            System.out.print(value);
        }
    }

    private static abstract class DecoratorImpl implements  Printer {
        private Printer component;

        DecoratorImpl(Printer component) {
            this.component = component;
        }

        @Override
        public void print() {
            component.print();
        }
    }

    private static class QuotesDecorator extends DecoratorImpl {


        QuotesDecorator(Printer component) {
            super(component);
        }

        @Override
        public void print() {
            System.out.print("\"");
            super.print();
            System.out.print("\"");
        }
    }

    private static class LeftBracketDecorator extends DecoratorImpl {

        public LeftBracketDecorator(Printer component) {
            super(component);
        }

        @Override
        public void print() {
            System.out.print("[");
            super.print();
        }
    }

    private static class RightBrakeDecorator extends DecoratorImpl {

        public RightBrakeDecorator(Printer component) {
            super(component);
        }

        @Override
        public void print() {
            super.print();
            System.out.print("]");
        }
    }
}

package ua.training.patterns.gof;

import ua.training.Testable;

import java.util.Arrays;

public class Visitor implements Testable {
    @Override
    public void test() {
        Element car = new CarElement();

        car.accept(new HooliganVisitor());
        car.accept(new MechanicVisitor());
    }

    private interface IVisitor {
        void visit(EngineElement engine);
        void visit(BodyElement body);
        void visit(WheelElement wheel);
        void visit(CarElement car);
    }

    private interface Element {
        void accept(IVisitor visitor);
    }

    private static class BodyElement implements Element {

        @Override
        public void accept(IVisitor visitor) {
            visitor.visit(this);
        }
    }

    private static class EngineElement implements Element {

        @Override
        public void accept(IVisitor visitor) {
            visitor.visit(this);
        }
    }

    private static class WheelElement implements Element {
        private String name;

        public WheelElement(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void accept(IVisitor visitor) {
            visitor.visit(this);
        }
    }

    private static class CarElement implements Element {
        private Element [] elements;

        CarElement() {
            this.elements = new Element[] {
                    new WheelElement("front left wheel"),
                    new WheelElement("front right wheel"),
                    new WheelElement("back left wheel"),
                    new WheelElement("back right wheel"),
                    new BodyElement(),
                    new EngineElement()
            };
        }

        @Override
        public void accept(IVisitor visitor) {
            Arrays.stream(elements).forEach(elm -> elm.accept(visitor));
            visitor.visit(this);
        }
    }

    private static class HooliganVisitor implements IVisitor {

        @Override
        public void visit(EngineElement engine) {
            System.out.println("Engine on.");
        }

        @Override
        public void visit(BodyElement body) {
            System.out.println("Hit body.");
        }

        @Override
        public void visit(WheelElement wheel) {
            System.out.println("Hit wheel " + wheel.getName());
        }

        @Override
        public void visit(CarElement car) {
            System.out.println("Hit car");
        }
    }

    private static class MechanicVisitor implements IVisitor {

        @Override
        public void visit(EngineElement engine) {
            System.out.println("Repair engine.");
        }

        @Override
        public void visit(BodyElement body) {
            System.out.println("Repair body.");
        }

        @Override
        public void visit(WheelElement wheel) {
            System.out.println("Repair wheel " + wheel.getName());
        }

        @Override
        public void visit(CarElement car) {
            System.out.println("Repair car");
        }
    }

}

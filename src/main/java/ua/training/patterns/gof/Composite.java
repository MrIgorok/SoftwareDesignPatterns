package ua.training.patterns.gof;

import ua.training.Testable;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Testable {
    @Override
    public void test() {
        Shape square1 = new Square();
        Shape square2 = new Square();
        Shape triangle1 = new Triangle();

        Shape square3 = new Square();
        Shape circle1 = new Circle();
        Shape circle2 = new Circle();
        Shape triangle2 = new Triangle();

        CompositeImpl composite = new CompositeImpl();
        CompositeImpl composite1 = new CompositeImpl();
        CompositeImpl composite2 = new CompositeImpl();

        composite1.addComponent(square1);
        composite1.addComponent(square2);
        composite1.addComponent(triangle1);

        composite2.addComponent(square3);
        composite2.addComponent(circle1);
        composite2.addComponent(circle2);
        composite2.addComponent(triangle2);

        composite.addComponent(composite1);
        composite.addComponent(composite2);

        composite.draw();
    }

    private interface Shape {
        void draw();
    }

    private static class Square implements Shape {

        @Override
        public void draw() {
            System.out.println("Hello, I am Square");
        }
    }

    private static class Triangle implements Shape {

        @Override
        public void draw() {
            System.out.println("Hello, I am Triangle");
        }
    }

    private static class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("Hello, I am Circle");
        }
    }

    private static class CompositeImpl implements Shape {
        private List<Shape> components = new ArrayList<>();

        void addComponent(Shape component) {
            components.add(component);
        }

        void removeComponent(Shape component) {
            components.remove(component);
        }

        @Override
        public void draw() {
            components.forEach(Shape::draw);
        }
    }
}

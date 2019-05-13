package ua.training.patterns.gof;

import ua.training.Testable;

import java.util.*;

public class Flyweight implements Testable {
    @Override
    public void test() {
        ShapeFactory shapeFactory = new ShapeFactory();

        List<Shape> shapeList = new ArrayList<>();

        shapeList.add(shapeFactory.getShape("Square"));
        shapeList.add(shapeFactory.getShape("Circle"));
        shapeList.add(shapeFactory.getShape("Circle"));
        shapeList.add(shapeFactory.getShape("Point"));
        shapeList.add(shapeFactory.getShape("Point"));
        shapeList.add(shapeFactory.getShape("Square"));
        shapeList.add(shapeFactory.getShape("Circle"));

        Random rand = new Random();

        for (Shape shape : shapeList) {
            int x = rand.nextInt(100);
            int y = rand.nextInt(100);
            shape.draw(x, y);
        }
    }

    private interface Shape {
        void draw(int x, int y);
    }

    private static class Point implements Shape {

        @Override
        public void draw(int x, int y) {
            System.out.println(x + ", " + y + ": point");
        }
    }

    private static class Square implements Shape {

        @Override
        public void draw(int x, int y) {
            System.out.println(x + ", " + y + ": square");
        }
    }

    private static class Circle implements Shape {

        @Override
        public void draw(int x, int y) {
            System.out.println(x + ", " + y + ": circle");
        }
    }

    private static class ShapeFactory {
        private static final Map<String, Shape> shapes = new HashMap<>();

        Shape getShape(String shapeName) {
            Shape shape = shapes.get(shapeName);

            if(shape == null) {
                switch (shapeName) {
                    case "Point":
                        shape = new Point();
                        break;
                    case "Circle":
                        shape = new Circle();
                        break;
                    case "Square":
                        shape = new Square();
                        break;
                    default:
                        throw new RuntimeException();
                }

                shapes.put(shapeName, shape);
            }

            return shape;
        }

    }
}

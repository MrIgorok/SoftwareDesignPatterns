package ua.training.patterns.gof;

import ua.training.Testable;

public class Delegate implements Testable {

    public void test() {
        Painter painter = new Painter();
        painter.setGraphics(new Square());
        painter.draw();

        painter.setGraphics(new Triangle());
        painter.draw();
    }

    private interface Graphics {
        void draw();
    }

    private static class Triangle implements Graphics {

        public void draw() {
            System.out.println("Draw triangle.");
        }
    }

    private static class Square implements  Graphics {

        public void draw() {
            System.out.println("Draw square.");
        }
    }

    private static class Circle implements Graphics {

        public void draw() {
            System.out.println("Draw circle.");
        }
    }

    private static class Painter {
        private Graphics graphics;

        void setGraphics(Graphics graphics) {
            this.graphics = graphics;
        }

        void draw() {
            graphics.draw();
        }
    }
}

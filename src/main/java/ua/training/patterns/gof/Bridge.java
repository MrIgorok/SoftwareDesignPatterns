package ua.training.patterns.gof;

import ua.training.Testable;

public class Bridge implements Testable {
    @Override
    public void test() {
        Car c = new Hatchback(new Skoda());
        c.showDetail();
    }

    private static abstract class Car {
        protected Mark mark;
        Car(Mark mark) {
            this.mark = mark;
        }
        abstract void showType();

        void showDetail() {
            showType();
            mark.setMark();
        }
    }

    private static class Sedan extends Car {
        public Sedan(Mark mark) {
            super(mark);
        }

        @Override
        void showType() {
            System.out.println("Sedan");
        }
    }

    private static class Hatchback extends Car {

        public Hatchback(Mark mark) {
            super(mark);
        }

        @Override
        void showType() {
            System.out.println("Hatchback");
        }
    }

    private interface Mark {
        void setMark();
    }

    private static class Kia implements Mark {

        @Override
        public void setMark() {
            System.out.println("Kia");
        }
    }

    private static class Skoda implements Mark {

        @Override
        public void setMark() {
            System.out.println("Skoda");
        }
    }
}

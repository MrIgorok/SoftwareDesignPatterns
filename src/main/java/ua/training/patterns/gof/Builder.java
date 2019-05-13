package ua.training.patterns.gof;

import ua.training.Testable;

public class Builder implements Testable {

    @Override
    public void test() {
        Director director = new Director();
        director.setBuilder(new FordMondeoBuilder());
        Car car = director.buildCar();
        System.out.println(car);
    }

    private enum Transmission {
        MANUAL, AUTO
    }

    private static class Car {
        private String make;
        private Transmission transmission;
        private int maxSpeed;

        void setMaxSpeed(int maxSpeed) {
            this.maxSpeed = maxSpeed;
        }

        void setMake(String make) {
            this.make = make;
        }

        void setTransmission(Transmission transmission) {
            this.transmission = transmission;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "make='" + make + '\'' +
                    ", transmission=" + transmission +
                    ", maxSpeed=" + maxSpeed +
                    '}';
        }
    }

    private static abstract class CarBuilder {
        Car car;

        CarBuilder() {
            car = new Car();
        }

        Car getCar() {
            return car;
        }

        abstract void buildMake();
        abstract void buildTransmission();
        abstract void buildMaxSpeed();
    }

    private static class FordMondeoBuilder extends CarBuilder {

        @Override
        void buildMake() {
            car.setMake("Ford Mondeo");
        }

        @Override
        void buildTransmission() {
            car.setTransmission(Transmission.AUTO);
        }

        @Override
        void buildMaxSpeed() {
            car.setMaxSpeed(200);
        }
    }

    private static class SubaruBuilder extends CarBuilder {

        @Override
        void buildMake() {
            car.setMake("Subaru");
        }

        @Override
        void buildTransmission() {
            car.setTransmission(Transmission.MANUAL);
        }

        @Override
        void buildMaxSpeed() {
            car.setMaxSpeed(320);
        }
    }

    private static class Director {
        CarBuilder builder;

        void setBuilder(CarBuilder builder) {
            this.builder = builder;
        }

        Car buildCar() {
            builder.buildMake();
            builder.buildMaxSpeed();
            builder.buildTransmission();
            return builder.getCar();
        }
    }
}

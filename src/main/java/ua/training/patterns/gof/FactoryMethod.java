package ua.training.patterns.gof;

import ua.training.Testable;

import java.util.Date;

public class FactoryMethod implements Testable {

    @Override
    public void test() {
        WatchMaker maker = getMakerByName("Digital");

        Watch watch = maker.createWatch();
        watch.showTime();
    }

    private static WatchMaker getMakerByName(String maker) {
        if (maker.equals("Digital")) {
            return new DigitalWatchMaker();
        } else if (maker.equals("Rome")) {
            return new RomeWatchMaker();
        }

        throw new RuntimeException("This watch is not supported: " + maker);
    }

    private interface Watch {
        void showTime();
    }

    private static class DigitalWatch implements Watch {
        @Override
        public void showTime() {
            System.out.println(new Date());
        }
    }

    private static class RomeWatch implements  Watch {
        @Override
        public void showTime() {
            System.out.println("VII-XX");
        }
    }

    private interface WatchMaker {
        Watch createWatch();
    }

    private static class DigitalWatchMaker implements WatchMaker {
        @Override
        public Watch createWatch() {
            return new DigitalWatch();
        }
    }

    private static class RomeWatchMaker implements WatchMaker {
        @Override
        public Watch createWatch() {
            return new RomeWatch();
        }
    }
}


package ua.training.patterns.gof;

import ua.training.Testable;

public class Facade implements Testable {

    public void test() {
        Computer computer = new Computer();

        computer.copy();
    }

    private static class Computer {
        private Power power = new Power();
        private DVDRom dvd = new DVDRom();
        private HDD hdd = new HDD();

        void copy() {
            power.on();
            dvd.load();
            hdd.copyFromDVD(dvd);
            power.off();
        }
    }

    private static class Power {

        void on() {
            System.out.println("Power on");
        }

        void off() {
            System.out.println("Power off");
        }
    }

    private static class DVDRom {
        private boolean data = false;

        boolean hasData() {
            return data;
        }

        void load() {
            data = true;
        }

        void unload() {
            data = false;
        }
    }

    private static class HDD {

        void copyFromDVD(DVDRom dvd) {
            if (dvd.hasData()) {
                System.out.println("Data is copied.");
            } else {
                System.out.print("Insert disk with data.");
            }
        }
    }
}

package ua.training.patterns.gof;

import ua.training.Testable;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Observer implements Testable {
    @Override
    public void test() {
        MeteoStation station = new MeteoStation();

        station.addObserver(new ConsoleObserver());
        station.addObserver(new FileObserver());

        station.setMeasurements(25, 760);
        station.setMeasurements(-5, 745);
    }

    interface Observed {
        void addObserver(IObserver o);
        void removeObserver(IObserver o);
        void notifyObservers();
    }

    private static class MeteoStation implements Observed {
        private int temperature;
        private int pressure;

        List<IObserver> observers = new ArrayList<>();

        @Override
        public void addObserver(IObserver o) {
            observers.add(o);
        }

        @Override
        public void removeObserver(IObserver o) {
            observers.add(o);
        }

        @Override
        public void notifyObservers() {
            observers.forEach(o -> o.handleEvent(temperature, pressure));
        }

        void setMeasurements(int temperature, int pressure) {
            this.temperature = temperature;
            this.pressure = pressure;
            notifyObservers();
        }
    }

    private interface IObserver {
        void handleEvent(int temp, int pressure);
    }

    private static class ConsoleObserver implements IObserver {

        @Override
        public void handleEvent(int temp, int pressure) {
            System.out.println("Wather is changed. Temperature = " + temp + ", pressure = " + pressure + ".");
        }
    }

    private static class FileObserver implements IObserver {

        @Override
        public void handleEvent(int temp, int pressure) {
            File f;
            try {
                f = File.createTempFile("TempPressure", "_txt");
                PrintWriter pw = new PrintWriter(f);
                pw.print("Wather is changed. Temperature = " + temp + ", pressure = " + pressure + ".");
                pw.println();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

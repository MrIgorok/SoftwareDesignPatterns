package ua.training.patterns.gof;

import ua.training.Testable;

import java.util.ArrayList;
import java.util.List;

public class State implements Testable {
    @Override
    public void test() {
        Station dfm = new RadioFM();
        Radio radio = new Radio();
        radio.setStation(dfm);

        for (int i = 0; i < 10; i++) {
            radio.play();
            radio.nextStation();
        }

        Human human = new Human();

        human.setState(new Work());

        for (int i = 0; i < 10; i++) {
            human.doSomething();
        }
    }


    private static class Human {
        private Activity state;
        private void setState(Activity s) {
            this.state = s;
        }

        public void doSomething() {
            state.doSomething(this);
        }
    }

    private interface Activity {
        void doSomething(Human context);
    }

    private static class Work implements Activity {

        @Override
        public void doSomething(Human context) {
            System.out.println("Working");
            context.setState(new WeekEnd());
        }
    }

    private static class WeekEnd implements Activity {
        private int count = 0;

        @Override
        public void doSomething(Human context) {
            if (count < 3) {
                System.out.println("Chill");
                count++;
            } else {
                context.setState(new Work());
            }
        }
    }

    private interface Station {
        void play();
    }

    static class Radio7 implements Station {

        @Override
        public void play() {
            System.out.println("Radio 7 ...");
        }
    }

    static class RadioFM implements Station {

        @Override
        public void play() {
            System.out.println("Radio fm ...");
        }
    }

    static class RadioUa implements Station {

        @Override
        public void play() {
            System.out.println("Radio ua ...");
        }
    }

    private static class Radio {
        private static final List<Class> stations;
        private Station station;

        static {
            stations = new ArrayList<>();
            stations.add(Radio7.class);
            stations.add(RadioFM.class);
            stations.add(RadioUa.class);
        }

        public void setStation(Station station) {
            this.station = station;
        }

        void nextStation() {
            int nextIndex = stations.indexOf(station.getClass()) + 1;

            try {
                station = (Station) ((nextIndex == stations.size() ? stations.get(0) : stations.get(nextIndex)).newInstance());
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        void play() {
            station.play();
        }
    }
}

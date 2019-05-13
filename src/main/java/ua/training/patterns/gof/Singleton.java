package ua.training.patterns.gof;

import ua.training.Testable;

import java.util.Arrays;

public class Singleton implements Testable {
    @Override
    public void test() {
        final int SIZE = 1000;

        Thread [] t = new Thread[SIZE];

        for (int i = 0; i < SIZE; i++) {
            t[i] = new Thread(new R());
            t[i].start();
        }
        try {
            for (int i = 0; i < SIZE; i++) {
                t[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count: " + SingletonImpl.getInstance().getCount());
    }

    private static class R implements Runnable {


        @Override
        public void run() {
            SingletonImpl.getInstance();
        }
    }

    private static class SingletonImpl {
        private int count = 0;
        private static volatile SingletonImpl instance;

        private SingletonImpl() {
            count++;
        }

        int getCount() {
            return count;
        }

        static SingletonImpl getInstance() {
            if (instance == null) {
                synchronized (SingletonImpl.class) {
                    if(instance == null)
                        instance = new SingletonImpl();
                }
            }

            return instance;
        }
    }
}

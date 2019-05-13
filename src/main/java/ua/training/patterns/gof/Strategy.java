package ua.training.patterns.gof;

import ua.training.Testable;

import java.util.Arrays;

public class Strategy implements Testable {
    @Override
    public void test() {
        int [] arr = new int [] { 1, 2, 4 };

        StrategyClient strategyClient = new StrategyClient();

        strategyClient.setStrategy(new BubbleSort());
        strategyClient.executeStrategy(arr);

        strategyClient.setStrategy(new InsertionSort());
        strategyClient.executeStrategy(arr);

        strategyClient.setStrategy(new FastSort());
        strategyClient.executeStrategy(arr);
    }

    private static class StrategyClient {
        private Sorting strategy;

        void setStrategy(Sorting strategy) {
            this.strategy = strategy;
        }
        void executeStrategy(int [] arr) {
            strategy.sort(arr);
        }
    }

    private interface Sorting {
        void sort(int [] arr);
    }

    private static class BubbleSort implements Sorting {

        @Override
        public void sort(int[] arr) {
            System.out.println("Bubble sort: " + Arrays.toString(arr));
        }
    }

    private static class InsertionSort implements Sorting {

        @Override
        public void sort(int[] arr) {
            System.out.println("Insertion sort: " + Arrays.toString(arr));
        }
    }

    private static class FastSort implements Sorting {

        @Override
        public void sort(int[] arr) {
            System.out.println("Fast sort: " + Arrays.toString(arr));
        }
    }
}

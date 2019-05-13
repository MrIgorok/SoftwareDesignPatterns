package ua.training.patterns.gof;

import ua.training.Testable;

public class Command implements Testable {
    @Override
    public void test() {
        Computer computer = new Computer();
        User user = new User(new StartCommand(computer), new StopCommand(computer), new ResetCommand(computer));

        user.startComputer();
        user.resetComputer();
        user.stopComputer();

    }

    private interface ICommand {
        void execute();
    }

    private static class Computer {
        void start() {
            System.out.println("Start computer");
        }

        void stop() {
            System.out.println("Stop computer");
        }

        void reset() {
            System.out.println("Reset computer");
        }
    }

    private static abstract class AbstractCommand implements ICommand {
        Computer computer;

        AbstractCommand(Computer computer) {
            this.computer = computer;
        }
    }

    private static class StartCommand extends AbstractCommand {

        StartCommand(Computer computer) {
            super(computer);
        }

        @Override
        public void execute() {
            computer.start();
        }
    }

    private static class StopCommand extends AbstractCommand {

        StopCommand(Computer computer) {
            super(computer);
        }

        @Override
        public void execute() {
            computer.stop();
        }
    }

    private static class ResetCommand extends AbstractCommand {

        ResetCommand(Computer computer) {
            super(computer);
        }

        @Override
        public void execute() {
            computer.reset();
        }
    }

    private static class User {
        ICommand start;
        ICommand stop;
        ICommand reset;

        User(ICommand start, ICommand stop, ICommand reset) {
            this.start = start;
            this.stop = stop;
            this.reset = reset;
        }

        void startComputer() {
            start.execute();
        }

        void stopComputer() {
            stop.execute();
        }

        void resetComputer() {
            reset.execute();
        }
    }
}

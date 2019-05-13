package ua.training.patterns.gof;

import ua.training.Testable;

import java.util.logging.Level;

public class ChainOfResponsibility implements Testable {
    @Override
    public void test() {
        Logger logger0 = new SMSLogger(LogLevel.ERROR);
        Logger logger1 = new EmailLogger(LogLevel.DEBUG);
        Logger logger2 = new FileLogger(LogLevel.INFO);

        logger0.setNext(logger1);
        logger1.setNext(logger2);

        logger0.writeMessage("All right!", LogLevel.INFO);
        logger0.writeMessage("Debug mode", LogLevel.DEBUG);
        logger0.writeMessage("Error", LogLevel.ERROR);

    }

    enum LogLevel {
        ERROR(1), DEBUG(2), INFO(3);
        int n;

        LogLevel(int n) {
            this.n = n;
        }

        int getN() {
            return n;
        }
    }

    private static abstract class Logger {
        int priority;
        Logger next;

        Logger(LogLevel priority) {
            this.priority = priority.getN();
        }

        public void setNext(Logger next) {
            this.next = next;
        }

        void writeMessage(String message, LogLevel logLevel) {
            if (logLevel.getN() <= priority) {
                write(message);
            }

            if (next != null) {
                next.writeMessage(message, logLevel);
            }
        }

        abstract void write(String message);
    }

    private static class SMSLogger extends Logger {
        public SMSLogger(LogLevel priority) {
            super(priority);
        }

        @Override
        public void write(String message) {
            System.out.println("SMS: " + message);
        }
    }

    private static class FileLogger extends Logger {
        public FileLogger(LogLevel priority) {
            super(priority);
        }

        @Override
        public void write(String message) {
            System.out.println("Write in file: " + message);
        }

    }

    private static class EmailLogger extends Logger {
        public EmailLogger(LogLevel priority) {
            super(priority);
        }

        @Override
        public void write(String message) {
            System.out.println("Email: " + message);
        }
    }
}

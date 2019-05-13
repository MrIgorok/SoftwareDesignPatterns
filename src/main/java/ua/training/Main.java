package ua.training;

import ua.training.patterns.gof.*;

import java.util.Arrays;

public class Main {

    public static void main(String [] args) {
        Testable [] gofPatterns = { new Delegate(), new Facade(), new FactoryMethod(),
                                    new AbstractFactory(), new Builder(), new Prototype(),
                                    new Composite(), new Adapter(), new Decorator(),
                                    new Command(), new ChainOfResponsibility(), new Memento(),
                                    new Mediator(), new Observer(), new State(),
                                    new TemplateMethod(), new Strategy(), new Visitor(),
                                    new Iterator(), new Proxy(), new Flyweight(),
                                    new Singleton(), new Bridge(), new Interpreter() };

        Arrays.stream(gofPatterns).forEach(Main::printPatternTest);
    }

    private static void printPatternTest(Testable patternTest) {
        String fullClassName = patternTest.getClass().getName();
        String patternName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
        System.out.println(patternName + ":");
        System.out.println();

        patternTest.test();

        char [] testEndLine = new char[20];
        Arrays.fill(testEndLine, '-');
        System.out.println(new String(testEndLine));
        System.out.println();
    }
}

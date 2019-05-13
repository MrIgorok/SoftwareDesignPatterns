package ua.training.patterns.gof;

import ua.training.Testable;

public class Iterator implements Testable {
    @Override
    public void test() {
        ConcreteAggregate c = new ConcreteAggregate();

        IIterator it = c.getIterator();

        while (it.hasMoreElements()) {
            System.out.println(it.next());
        }
    }

    private interface IIterator {
        Object first();
        Object next();
        boolean hasMoreElements();
        Object getCurrentElement();
    }

    private interface Aggregate {
        IIterator getIterator();
    }

    private static class ConcreteAggregate implements Aggregate {
        private String [] tasks = {"Build home", "Born child", "make a tree"};

        @Override
        public IIterator getIterator() {
            return new TaskIterator();
        }

        private class TaskIterator implements IIterator {
            private int index = 0;

            @Override
            public Object first() {
                return tasks[0];
            }

            @Override
            public Object next() {
                return tasks[index++];
            }

            @Override
            public boolean hasMoreElements() {
                return index < tasks.length;
            }

            @Override
            public Object getCurrentElement() {
                return tasks[index];
            }
        }
    }
}

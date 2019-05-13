package ua.training.patterns.gof;

import ua.training.Testable;

public class Prototype implements Testable {

    @Override
    public void test() {
        Human original = new Human(18, "Vasya");
        System.out.println(original);

        Human copy = (Human)original.copy();
        System.out.println(copy);

        HumanFactory factory = new HumanFactory(copy);
        Human h1 = factory.makeCopy();
        System.out.println(h1);

        factory.setPrototype(new Human(30, "Valery"));
        Human h2 = factory.makeCopy();
        System.out.println(h2);
    }


    private interface Copyable {
        Object copy();
    }

    private static class Human implements Copyable {
        private int age;
        private String name;

        Human(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Human{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public Object copy() {
            return new Human(age, name);
        }

    }

    private static class HumanFactory {
        private Human human;

        HumanFactory(Human human) {
            setPrototype(human);
        }

        void setPrototype(Human human) {
            this.human = human;
        }

        Human makeCopy() {
            return (Human) human.copy();
        }
    }

}

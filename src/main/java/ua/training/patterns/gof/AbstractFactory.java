package ua.training.patterns.gof;

import ua.training.Testable;

public class AbstractFactory implements Testable {
    @Override
    public void test() {
        DeviceFactory factory = getFactoryByCountryCode("ENG");
        Mouse mouse = factory.getMouse();
        Keyboard keyboard = factory.getKeyboard();

        mouse.click();
        mouse.scroll(1);
        keyboard.print();
    }

    private static DeviceFactory getFactoryByCountryCode(String lang) {
        switch (lang) {
            case "UA":
                return new UaDeviceFactory();
            case "ENG":
                return new EngDeviceFactory();
            default:
                throw new RuntimeException("Unsupported country code: " + lang);
        }
    }

    private interface Mouse {
        void click();
        void doubleClick();
        void scroll(int direction);
    }

    private interface Keyboard {
        void print();
        void println();
    }

    private interface DeviceFactory {
        Mouse getMouse();
        Keyboard getKeyboard();
    }

    private static class UaMouse implements Mouse {
        @Override
        public void click() {
            System.out.println("Клік мишки");
        }

        @Override
        public void doubleClick() {
            System.out.println("");
        }

        @Override
        public void scroll(int direction) {
            StringBuilder builder = new StringBuilder();
            builder.append("Проскроленно: ");
            if (direction > 0) {
                builder.append("Скролл вниз");
            } else if (direction < 0) {
                builder.append("Скролл верх");
            } else {
                builder.append("Не сколлим");
            }

            System.out.println(builder.toString());
        }
    }

    private static class EngMouse implements Mouse {

        @Override
        public void click() {
            System.out.println("Mouse clicked");
        }

        @Override
        public void doubleClick() {
            System.out.println("Mouse double clicked");
        }

        @Override
        public void scroll(int direction) {
            StringBuilder builder = new StringBuilder();
            builder.append("Scrolled: ");
            if (direction > 0) {
                builder.append("down");
            } else if (direction < 0) {
                builder.append("up");
            } else {
                builder.append("not");
            }

            System.out.println(builder.toString());
        }
    }

    private static class UaKeyboard implements Keyboard {
        @Override
        public void print() {
            System.out.println("Кнопка була нажата");
        }

        @Override
        public void println() {
            System.out.println("Кнопка була нажата з переносом строки");
        }
    }

    private static class EngKeyboard implements Keyboard {

        @Override
        public void print() {
            System.out.println("Key was pressed");
        }

        @Override
        public void println() {
            System.out.println("Key was pressed with line carry");
        }
    }

    private static class UaDeviceFactory implements DeviceFactory {

        @Override
        public Mouse getMouse() {
            return new UaMouse();
        }

        @Override
        public Keyboard getKeyboard() {
            return new UaKeyboard();
        }
    }

    private static class EngDeviceFactory implements DeviceFactory {

        @Override
        public Mouse getMouse() {
            return new EngMouse();
        }

        @Override
        public Keyboard getKeyboard() {
            return new EngKeyboard();
        }
    }
}

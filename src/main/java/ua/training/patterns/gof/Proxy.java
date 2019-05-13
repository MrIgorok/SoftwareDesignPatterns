package ua.training.patterns.gof;

import ua.training.Testable;

public class Proxy implements Testable {
    @Override
    public void test() {
        Image image = new ProxyImage("filename");
        image.display();
    }

    private interface Image {
        void display();
    }

    private static class RealImage implements Image {
        private String file;

        public RealImage(String file) {
            this.file = file;
            load();
        }

        void load() {
            System.out.println("Load: " + file);
        }

        @Override
        public void display() {
            System.out.println("display: " + file);
        }
    }

    private static class ProxyImage implements Image {
        private String file;
        private Image image;

        public void setImage(Image image) {
            this.image = image;
        }

        public ProxyImage(String file) {
            this.file = file;
        }

        @Override
        public void display() {
            if (image == null) {
                image = new RealImage(file);
            }

            image.display();
        }
    }
}

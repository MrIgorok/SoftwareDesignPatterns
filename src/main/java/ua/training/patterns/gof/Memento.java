package ua.training.patterns.gof;

import ua.training.Testable;

public class Memento implements Testable {
    @Override
    public void test() {
        Game game = new Game();
        game.set("LVL_1", 30000);
        System.out.println(game);

        File file = new File();
        file.setSave(game.save());

        game.set("LVL_2", 44000);
        System.out.println(game);

        System.out.println("LOAD");
        game.load(file.getSave());
        System.out.println(game);
    }

    private static class Game {
        private String level;;
        private int ms;

        void set(String level, int ms) {
            this.level = level;
            this.ms = ms;
        }

        public void load(Save save) {
            level = save.getLevel();
            ms = save.getMs();
        }

        public Save save() {
            return new Save(level, ms);
        }

        @Override
        public String toString() {
            return "Game{" +
                    "level='" + level + '\'' +
                    ", ms=" + ms +
                    '}';
        }
    }


    private static class Save {
        private final String level;
        private final int ms;

        public Save(String level, int ms) {
            this.level = level;
            this.ms = ms;
        }

        public String getLevel() {
            return level;
        }

        public int getMs() {
            return ms;
        }
    }

    private static class File {
        Save save;

        Save getSave() {
            return save;
        }

        void setSave(Save save) {
            this.save = save;
        }
    }
}

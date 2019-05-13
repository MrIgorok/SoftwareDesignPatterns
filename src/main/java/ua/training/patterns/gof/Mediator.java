package ua.training.patterns.gof;

import ua.training.Testable;

import java.util.ArrayList;
import java.util.List;

public class Mediator implements Testable {
    @Override
    public void test() {
        TextChat chat = new TextChat();

        User admin = new Admin(chat, "Ivan");
        User user1 = new SimpleUser(chat, "Vania");
        User user2 = new SimpleUser(chat, "Vowa");
        User user3 = new SimpleUser(chat, "Sasha");
        user2.setEnabled(false);


        chat.setAdmin(admin);
        chat.addUser(user1);
        chat.addUser(user2);
        chat.addUser(user3);

        user1.sendMessage("Hello");
        admin.sendMessage("Admin Hello");
    }

    private static abstract class User {
        private Chat chat;
        String name;
        boolean isEnabled = true;

        public User(Chat chat, String name) {
            this.chat = chat;
            this.name = name;
        }

        boolean isEnabled() {
            return isEnabled;
        }

        void setEnabled(boolean enabled) {
            isEnabled = enabled;
        }

        String getName() {
            return name;
        }

        void sendMessage(String message) {
            chat.sendMessage(message, this);
        }

        abstract void getMessage(String message);

        @Override
        public String toString() {
            return "User{" +
                    "chat=" + chat +
                    ", name='" + name + '\'' +
                    ", isEnabled=" + isEnabled +
                    '}';
        }
    }

    private static class Admin extends User {

        Admin(Chat chat, String name) {
            super(chat, name);
        }

        @Override
        public void getMessage(String message) {
            System.out.println("Admin receive message: " + message);
        }

    }
    private static class SimpleUser extends User {
        public SimpleUser(Chat chat, String name) {
            super(chat, name);
        }

        @Override
        public void getMessage(String message) {
            System.out.println("User resive message: " + message);
        }

    }

    private interface Chat {
        void sendMessage(String message, User user);
    }

    private static class TextChat implements Chat {

        private User admin;
        List<User> users = new ArrayList<>();

        public void setAdmin(User admin) {
            if (admin != null && admin instanceof Admin) {
                this.admin = admin;
            } else {
                throw new RuntimeException("Permission denied.");
            }
        }

        public void addUser(User user) {
            if (admin == null) {
                throw new RuntimeException("There is no admin in this chat.");
            }

            if(user instanceof  SimpleUser) {
                users.add(user);
            } else {
                throw new RuntimeException("Admin can't enter the chat.");
            }
        }

        @Override
        public void sendMessage(String message, User user) {
            if (user instanceof Admin)
                users.forEach((u) -> { u.getMessage(user.getName() + ": " + message); });

            if (user instanceof SimpleUser) {
                users.stream().filter((u) -> u != user && u.isEnabled())
                              .forEach(u -> u.getMessage(u.getName() + ": " + message));

                if (admin.isEnabled())
                    admin.getMessage(user.getName() + ": " + message);
            }
        }
    }
}

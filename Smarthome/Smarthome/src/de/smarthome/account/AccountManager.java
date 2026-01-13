package de.smarthome.account;

import java.util.ArrayList;

public class AccountManager {

    private User currentUser;
    private ArrayList<User> users;

    public AccountManager() {
        users = new ArrayList<>();
        currentUser = null;
    }

    public void createUser(String username, String password, String firstName, String lastName, boolean isParent) {
        if (findUserByUsername(username) != null) {
            System.out.println("Sorry, this User is already existing!");
            return;
        }

        User user;

        if (isParent) {
            user = new Parent(username, password, firstName, lastName);
        } else {
            user = new Child(username, password, firstName, lastName);
        }

        users.add(user);
    }

    public void removeUser(String username) {
        if (currentUser == null || currentUser.getRole() != Role.PARENT) {
            System.out.println("Only parents are able to remove Users!");
            return;
        }

        User userToRemove = findUserByUsername(username);

        if (userToRemove == null) {
            System.out.println("User not found");
            return;
        }

        users.remove(userToRemove);

        if (userToRemove.equals(currentUser)) {
            currentUser = null;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public User login(String username, String password) {
        User user = findUserByUsername(username);

        if (user != null && user.checkPassword(password)) {
            user.login();
            currentUser = user;
            return user;
        }

        System.out.println("Login failed!");
        return null;
    }

    public void logout() {
        if (currentUser != null) {
            currentUser.logout();
            currentUser = null;
        }
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public void upgradeChildToParent(String username) {

        if (currentUser == null || currentUser.getRole() != Role.PARENT) {
            System.out.println("Only parents can upgrade users!");
            return;
        }

        User user = findUserByUsername(username);

        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        if (user.getRole() != Role.CHILD) {
            System.out.println("User is already a parent!");
            return;
        }

        users.remove(user);
        users.add(new Parent(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName()
        ));
    }

    public void resetChildPassword(String childUsername, String newPassword) {

        if (currentUser == null || currentUser.getRole() != Role.PARENT) {
            System.out.println("Only parents can reset passwords!");
            return;
        }

        User user = findUserByUsername(childUsername);

        if (user == null) {
            System.out.println("User not found!");
            return;
        }
        user.changePassword(newPassword);
    }

    public static void main(String[] args) {

        AccountManager am = new AccountManager();

        am.createUser("parent", "123", "Anna", "Test", true);
        am.createUser("child", "123", "Tom", "Test", false);

        am.login("parent", "123");
        System.out.println(am.getCurrentUser());

        am.logout();
        System.out.println(am.getCurrentUser());

    }
}
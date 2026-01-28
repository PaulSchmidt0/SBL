//Adam Benamar, Matrikelnummer: 2679028
package de.smarthome.account;

import java.util.ArrayList;

/**
 * Die Klasse AccountManager verwaltet alle Benutzerkonten
 * im Smart-Home-System. Sie ermöglicht das Anlegen, Entfernen
 * und Verwalten von Benutzern sowie Login- und Logout-Funktionen.
 */
public class AccountManager {

    private User currentUser;
    private ArrayList<User> users;

    /**
     * Konstruktor: Initialisiert die Benutzerliste
     * und setzt den aktuell angemeldeten Benutzer auf null.
     */
    public AccountManager() {
        users = new ArrayList<>();
        currentUser = null;
    }

    /**
     * Erstellt einen neuen Benutzer, sofern der Benutzername
     * noch nicht existiert.
     * Je nach Parameter wird ein Parent- oder Child-Benutzer angelegt.
     */
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

    /**
     * Entfernt einen Benutzer anhand des Benutzernamens.
     * Diese Funktion darf nur von einem Parent ausgeführt werden.
     */
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

    /**
     * Gibt den aktuell angemeldeten Benutzer zurück.
     */
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

    /**
     * Meldet den aktuell angemeldeten Benutzer ab.
     */
    public void logout() {
        if (currentUser != null) {
            currentUser.logout();
            currentUser = null;
        }
    }

    /**
     * Sucht einen Benutzer anhand seines Benutzernamens.
     * Gibt den Benutzer zurück oder null, falls er nicht existiert.
     */
    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Gibt die Liste aller registrierten Benutzer zurück.
     */
    public ArrayList<User> getAllUsers() {
        return users;
    }

    /**
     * Wandelt einen Benutzer mit der Rolle CHILD in einen
     * Benutzer mit der Rolle PARENT um.
     */
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

    /**
     * Setzt das Passwort eines Child-Benutzers neu.
     * Diese Funktion darf ausschließlich von einem Parent ausgeführt werden.
     */
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


}
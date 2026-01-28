//Adam Benamar, Matrikelnummer: 2679028
package de.smarthome.account;

public class Parent extends User {

    /**
     * Konstruktor zur Erstellung eines Parent-Benutzers.
     * Übergibt alle Parameter an den Konstruktor der Oberklasse User.
     */
    public Parent(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    /**
     * Gibt die Rolle des Benutzers zurück.
     * Hier immer Role.PARENT.
     */
    @Override
    public Role getRole() {
        return Role.PARENT;
    }

    /**
     * Gibt eine textuelle Darstellung des Benutzers zurück und dassen status als Parent.
     */
    @Override
    public String toString() {
        return super.toString() + "\nStatus: Parent";
    }

}

//Adam Benamar, Matrikelnummer: 2679028
package de.smarthome.account;

public class Child extends User{

    /**
     * Konstruktor zur Erstellung eines Child-Benutzers.
     * Übergibt alle Parameter an den Konstruktor der Oberklasse User.
     */
    public Child(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    // Gibt eine textuelle Darstellung des Child-Benutzers zurück.
    @Override
    public String toString() {
        return super.toString() + "\nStatus: Child";
    }
}

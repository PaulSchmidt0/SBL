//Adam Benamar, Matrikelnummer: 2679028
package de.smarthome.account;

public class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isLoggedIn;

    /**
     * Konstruktor zur Erstellung eines Benutzers mit allen Angaben.
     */
    public User (String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isLoggedIn = false;
    }

    /**
     * Überladener Konstruktor zur Erstellung eines Benutzers
     * ohne Vor- und Nachnamen.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.firstName = "";
        this.lastName = "";
        this.isLoggedIn = false;
    }

    public void login(){
        if(!isLoggedIn){
            isLoggedIn = true;
        }
    }

    public void logout(){
        if (isLoggedIn) {
            isLoggedIn = false;
        }
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public boolean getIsLoggedIn(){
        return isLoggedIn;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    /**
     * Gibt die Rolle des Benutzers zurück.
     * Standardmäßig ist hier der User ein Child.
     * Diese Methode wird in Unterklassen überschrieben.
     */
    public Role getRole(){
        return Role.CHILD;
    }

    public void changeUsername(String newUsername) {
        this.username = newUsername;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Prüft, ob das übergebene Passwort korrekt ist.
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Vergleicht zwei Benutzer anhand ihres Benutzernamens.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return this.username.equals(other.username);
    }

    /**
     * Gibt eine textuelle Darstellung des Benutzers zurück.
     */
    @Override
    public String toString() {
        return "User details:\n" + "Name: " + getName() + "\n" + "Username: " + username;
    }

}

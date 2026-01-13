package de.smarthome.account;

public class Child extends User{

    public Child(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    @Override
    public String toString() {
        return super.toString() + "\nStatus: Child";
    }
}

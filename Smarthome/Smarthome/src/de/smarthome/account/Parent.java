package de.smarthome.account;

public class Parent extends User {

    public Parent(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    @Override
    public Role getRole() {
        return Role.PARENT;
    }

    @Override
    public String toString() {
        return super.toString() + "\nStatus: Parent";
    }

}

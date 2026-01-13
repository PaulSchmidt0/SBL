package de.smarthome.account;

public class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isLoggedIn;

    public User (String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isLoggedIn = false;
    }

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

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return this.username.equals(other.username);
    }

    @Override
    public String toString() {
        return "User details:\n" + "Name: " + getName() + "\n" + "Username: " + username;
    }

}

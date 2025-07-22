package model;

public class User {
    public enum Role {
        ADMIN,
        USER
    }

    private int id;
    private String username;
    private String password;
    private Role role;

    public User(int id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    
    public boolean isAdmin() {
        return role == Role.ADMIN;
    }
}
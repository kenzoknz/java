package model;

public class Computer {
    private int id;
    private String name;
    private boolean status;
    private int labId;

    public Computer(int id, String name, boolean status, int labId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.labId = labId;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isStatus() { return status; }
    public int getLabId() { return labId; }
    
    // Setters
    public void setStatus(boolean status) { this.status = status; }
}
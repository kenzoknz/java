package model;

public class ComputerLab {
    private int id;
    private String name;
    private String location;
    private int capacity;

    public ComputerLab(int id, String name, String location, int capacity) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }
    public String toString() {
        return this.name; // Chỉ hiển thị tên phòng
    }
}
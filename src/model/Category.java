package model;

public class Category {
    private int id;
    private String name;
    private String status;

    public Category(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Category( String name, String status) {
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive(){

        return status.equals("Available");
    }

    @Override
    public String toString() {
        return  getName();
    }
}

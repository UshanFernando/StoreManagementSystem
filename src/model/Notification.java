package model;

public class Notification {

    private int id;
    private String message;
    private String status;

    public Notification(String message) {
        this.message = message;
        this.status = "UNREAD";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return message;
    }
}

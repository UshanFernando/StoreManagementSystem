package model;

public class Contact {

    private int id;
    private  String phone;


    public Contact(int id,String phone) {
        this.id = id;
        this.phone= phone;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

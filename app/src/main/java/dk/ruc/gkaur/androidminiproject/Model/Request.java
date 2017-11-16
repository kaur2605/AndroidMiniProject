package dk.ruc.gkaur.androidminiproject.Model;

import java.util.List;

/**
 * Created by Ricky on 11/15/2017.
 */

public class Request {

private String Phone;
    private String address;
    private String Name;
    private  String total;
    private List<Order> foods;

    public Request() {
    }

    public Request(String phone, String address, String name, String total, List<Order> foods) {
        this.Phone = phone;
        this.address = address;
        this.Name = name;
        this.total = total;
        this.foods = foods;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}




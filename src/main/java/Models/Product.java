package Models;

import Models.User;

public class Product {
    private String name;
    private String owner;
    private String information;
    private int id;
    // Category;


    public Product(String name, String owner, String information, int id) {
        this.name = name;
        this.owner = owner;
        this.information = information;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", information='" + information + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}

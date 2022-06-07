package com.example.projectcomic.model;

public class Comic {
    public int id;
    public String nameC;
    public String author;
    public int quantity;
    public double price;

    public Comic(int id, String nameC, String author, int quantity, double price) {
        this.id = id;
        this.nameC = nameC;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }
    public Comic(String nameC, String author, int quantity, double price) {
        this.nameC = nameC;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public String getNameC() {
        return nameC;
    }

    public String getAuthor() {
        return author;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }


}

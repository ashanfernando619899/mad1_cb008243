package com.asus.lookgood.model;

public class Messages
{
    String pname;
    String description;
    String price;
    String imageUrl;

    public Messages()
    {

    }

    public Messages(String pname, String description, String price, String imageUrl) {
        this.pname = pname;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

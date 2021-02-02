package com.asus.lookgood.model;


/*checking the data with the database=============================*/
public class users
{
    private String name,phone,password,image;

    public users()
    {

    }

    public users(String name, String phone, String password, String image) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

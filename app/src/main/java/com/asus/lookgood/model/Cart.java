package com.asus.lookgood.model;

public class Cart {

    private String p_id;
    private String p_price;
    private String p_qty;

    public Cart(String p_id, String p_price, String p_qty) {
        this.p_id = p_id;
        this.p_price = p_price;
        this.p_qty = p_qty;
    }

    public Cart() {

    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public String getP_qty() {
        return p_qty;
    }

    public void setP_qty(String p_qty) {
        this.p_qty = p_qty;
    }
}

package com.azhyun.massxj.bean.aizhongtian;

/**
 * Created by dell on 2020/7/6.
 */

public class SubmitOrderItemsBean {
    private int id;
    private int qty;
    private int region;


    public int getId() {
        return id;
    }

    public int getQty() {
        return qty;
    }

    public int getRegion() {
        return region;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

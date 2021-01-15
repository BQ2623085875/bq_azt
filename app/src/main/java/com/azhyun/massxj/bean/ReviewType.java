package com.azhyun.massxj.bean;

/**
 * Created by tl on 2018/9/6.
 */

public class ReviewType {
    private String name;
    private boolean selected;

    public ReviewType(String name, boolean selected) {
        this.name = name;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

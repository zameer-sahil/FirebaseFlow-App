package com.example.myapplication.classes;

public class ModelClass {
    private int pic;
    private String title;
    private String desc;

    public ModelClass(int pic, String title, String desc) {
        this.pic = pic;
        this.title = title;
        this.desc = desc;
    }

    public int getPic() {
        return pic;
    }

    public void setpic(int pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

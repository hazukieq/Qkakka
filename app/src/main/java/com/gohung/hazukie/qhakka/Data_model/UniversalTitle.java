package com.gohung.hazukie.qhakka.Data_model;

public class UniversalTitle {
    String title;
    int visible=0;
    public UniversalTitle(String title,int visible){
        this.title=title;
        this.visible=visible;
    }

    public String getTitle() {
        return title;
    }

    public int getVisible() {
        return visible;
    }
}

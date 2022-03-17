package com.gohung.hazukie.qhakka.Data_model;

public class WordDetails {
    String word,category,hakkapin,hanz,explanation;
    public WordDetails(String word,String category,String hakkapin,String hanz,String explanation){
        this.word=word;
        this.category=category;
        this.hakkapin=hakkapin;
        this.hanz=hanz;
        this.explanation=explanation;
    }

    public String getWord() {
        return word;
    }

    public String getCategory() {
        return category;
    }

    public String getHakkapin() {
        return hakkapin;
    }

    public String getHanz() {
        return hanz;
    }

    public String getExplanation() {
        return explanation;
    }
}

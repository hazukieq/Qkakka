package com.gohung.hazukie.qhakka;

import android.view.View;

import com.gohung.hazukie.qhakka.database.Word;

public interface onItemClickListener {
    void onItemClick(View v,String s);
    void onItemUrlClick(View v,String s,String  url);
    void onItemLongClick(View v,String s);
    void onCardLongClick(View v, Word word);
}

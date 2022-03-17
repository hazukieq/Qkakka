package com.gohung.hazukie.qhakka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.drakeet.multitype.MultiTypeAdapter;
import com.gohung.hazukie.qhakka.Data_model.WordDetails;
import com.gohung.hazukie.qhakka.binderr.WordDetailsBinder;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.ArrayList;
import java.util.List;

public class WordCategoryAct extends AppCompatActivity {
    private MultiTypeAdapter multiTypeAd;
    private QMUITopBarLayout topbar;
    private RecyclerView recy;
    private ArrayList<Object> items=new ArrayList<>();
    private List<WordDetails> words=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_word_category);
        initsViews();
    }

    private void initsViews(){
        multiTypeAd=new MultiTypeAdapter();
        topbar=(QMUITopBarLayout)findViewById(R.id.frag_word_category_topbar);
        recy=(RecyclerView) findViewById(R.id.frag_word_category_recy);
        topbar.setTitle("词语・落地生根・解释");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        multiTypeAd.register(WordDetails.class,new WordDetailsBinder());
        recy.setAdapter(multiTypeAd);
      WordDetails   One_Word=new WordDetails("落地生根","分类：名词・俗语","客家话读音：lōk tì thàng gīn","客家行文用字：无","普通话解释：这是一个测试") ;
      items.add(One_Word);
      multiTypeAd.setItems(items);

    }
}
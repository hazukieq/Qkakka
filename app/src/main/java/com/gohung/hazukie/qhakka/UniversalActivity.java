package com.gohung.hazukie.qhakka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.drakeet.multitype.MultiTypeAdapter;
import com.gohung.hazukie.qhakka.CustomView.ClearEditText;
import com.gohung.hazukie.qhakka.Data_model.BnannerModel;
import com.gohung.hazukie.qhakka.Data_model.UniversalMuluk;
import com.gohung.hazukie.qhakka.Data_model.UniversalTitle;
import com.gohung.hazukie.qhakka.binderr.UniversalContent;
import com.gohung.hazukie.qhakka.binderr.UniversalMulukBinder;
import com.gohung.hazukie.qhakka.database.Lipoem;
import com.gohung.hazukie.qhakka.database.Word;
import com.gohung.hazukie.qhakka.database.Word_database;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UniversalActivity extends AppCompatActivity {
    private QMUITopBarLayout uniTop;
    private ArrayList<Object> items=new ArrayList<>();
    private List<UniversalMuluk> muluks=new ArrayList<>();
    private List<Lipoem> poemContents=new ArrayList<>();
    private MultiTypeAdapter multiAd;
    private RecyclerView universalRecy;
    private Word_database wdtas;
    private String search="";


    private void initsTop(){
        uniTop=(QMUITopBarLayout) findViewById(R.id.universalAct_topbar);
        uniTop.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal);
        QMUIStatusBarHelper.translucent(this);
        ImageView imageView=(ImageView)findViewById(R.id.universalAct_imageview);
        Glide.with(this).load(R.drawable.icon_app_search).into(imageView);
        ClearEditText clear=(ClearEditText)findViewById(R.id.universalAct_clearsearch);

        LinearLayout universal_linear=(LinearLayout)findViewById(R.id.universalAct_linear);
        Intent ing=getIntent();
        Bundle bu=ing.getExtras();
        search=bu.getString("search");
        if(search==null||search.isEmpty()||search.equals("0")){
            universal_linear.setVisibility(View.VISIBLE);


        }else if(search.equals("1")|search.equals("2")|search.equals("3")|search.equals("4")){
            universal_linear.setVisibility(View.VISIBLE);
        }else{
            universal_linear.setVisibility(View.GONE);
        }

        initsTop();
        wdtas=Word_database.getInstance(this);
        multiAd=new MultiTypeAdapter();
        UniversalMulukBinder universalMulukBinder=new UniversalMulukBinder();
        universalMulukBinder.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, String s) {
                if(s.equals("内篇")|s.equals("外篇")|s.equals("杂篇")|s.equals("道经")|s.equals("德经")){}else {
                    Intent ink = new Intent();
                    ink.setClass(UniversalActivity.this, UniversalActivity.class);
                    Bundle buh = new Bundle();
                    buh.putString("search", s);
                    ink.putExtras(buh);
                    startActivity(ink);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

            }

            @Override
            public void onItemUrlClick(View v, String s, String url) {

            }

            @Override
            public void onItemLongClick(View v, String s) {

            }

            @Override
            public void onCardLongClick(View v, Word word) {

            }
        });
        multiAd.register(UniversalMuluk.class,universalMulukBinder);
        multiAd.register(Lipoem.class,new UniversalContent());
        universalRecy=(RecyclerView) findViewById(R.id.universalAct_recy);
        initsView();

        clear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<UniversalMuluk> persisternList=new ArrayList<>();
                if(charSequence.toString().isEmpty()){
                    items.clear();
                    items.addAll(muluks);
                    multiAd.notifyDataSetChanged();
                }else {
                    for (UniversalMuluk muluk : muluks) {
                        String mulus = muluk.getTitle();
                        if (mulus.contains(charSequence.toString())) {
                            persisternList.add(muluk);
                        }
                    }
                    items.clear();
                    items.addAll(persisternList);
                    multiAd.notifyDataSetChanged();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }




    private void initsView(){
        universalRecy.setAdapter(multiAd);
        multiAd.setItems(items);
        if(search==null||search.isEmpty()||search.equals("0")){
            new QueryAllMuluksTask(0).execute();
        }else if(search.equals("1")){
            new QueryAllMuluksTask(1).execute();
        }else if(search.equals("2")){
            new QueryAllMuluksTask(2).execute();
        }else if(search.equals("3")){
            new QueryAllMuluksTask(3).execute();
        }else  if(search.equals("4")){
            new  QueryAllMuluksTask(4).execute();
        }
        else{
            new QueryWordForContent(search).execute();
        }

    }

    private class QueryWordForContent extends AsyncTask<Void,Void,Void> {
        String searchWord;
        public QueryWordForContent(String searchWord){
                  this.searchWord=searchWord;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            items.clear();
            Lipoem lis=wdtas.lipoem_dao().getLipoemByMuluk(searchWord);
            items.add(lis);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            multiAd.notifyDataSetChanged();

        }
    }

    private class QueryAllMuluksTask extends AsyncTask<Void,Void,Void> {
        int type=0;
        public QueryAllMuluksTask(int type){
                this.type=type;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            items.clear();
            switch (type){
                case 0:
                    poemContents=wdtas.lipoem_dao().getAllLipoems();
                    break;
                case 1:
                    poemContents=wdtas.lipoem_dao().getAllLunnzis();
                    break;
                case 2:
                    poemContents=wdtas.lipoem_dao().getAllDufupoems();
                    break;
                case 3:
                    poemContents=wdtas.lipoem_dao().getAllZongzus();
                    break;
                case 4:
                    poemContents=wdtas.lipoem_dao().getAllLauzus();
                    break;
            }

            List<UniversalMuluk> sk=new ArrayList<>();
            if(type==3){
                for(int i=0;i<poemContents.size();i++) {
                    Lipoem zini=poemContents.get(i);
                    if(i==0) sk.add(new UniversalMuluk("内篇",""));
                    if(i==8) sk.add(new UniversalMuluk("外篇",""));
                    if(i==23) sk.add(new UniversalMuluk("杂篇",""));
                    sk.add(new UniversalMuluk(zini.getMuluk(), ""));
                }
                muluks.addAll(sk);
                items.addAll(sk);
            }
            else  if(type==4){
                for(int j=0;j<poemContents.size();j++){
                    Lipoem loni=poemContents.get(j);
                    if(j==0) sk.add(new UniversalMuluk("道经",""));
                    if(j==38) sk.add(new UniversalMuluk("德经",""));
                    sk.add(new UniversalMuluk(loni.getMuluk(),""));
                }
                muluks.addAll(sk);
                items.addAll(sk);
            }
            else {
                for (Lipoem onis : poemContents) sk.add(new UniversalMuluk(onis.getMuluk(), ""));
                muluks.addAll(sk);
                items.addAll(sk);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            multiAd.notifyDataSetChanged();

        }
    }
}
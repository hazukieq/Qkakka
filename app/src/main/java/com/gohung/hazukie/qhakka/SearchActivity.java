package com.gohung.hazukie.qhakka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.drakeet.multitype.MultiTypeAdapter;
import com.gohung.hazukie.qhakka.CustomView.ClearEditText;
import com.gohung.hazukie.qhakka.CustomView.MyRecyclerview;
import com.gohung.hazukie.qhakka.Utils.FilterData;
import com.gohung.hazukie.qhakka.binderr.SingleHanzBinder;
import com.gohung.hazukie.qhakka.database.Word;
import com.gohung.hazukie.qhakka.database.Word_database;
import com.gohung.hazukie.qhakka.viewmodels.WordViewModel;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private QMUITopBarLayout topba;
    private TextView txt;
    private ScrollView scroll;
    private ClearEditText clear;
    private ImageView imags;
    private QMUIRoundButton roundButton;
    private MyRecyclerview recy;
    private LinearLayout emptyView;
    private ArrayList<Object> items=new ArrayList<>();
    private List<Word> copyList=new ArrayList<>();
    private Word_database wdatabase;
    private MultiTypeAdapter hanzAd;
    private Spinner spinner;
    private String[] spinner_strs;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    private void initViews(){
        scroll=(ScrollView)findViewById(R.id.searchAct_scroll);
        txt=(TextView)findViewById(R.id.searchAct_text);
        txt.setText(getText(R.string.searchAct_text_content));
        recy=(MyRecyclerview) findViewById(R.id.searchAct_recy);
        emptyView=(LinearLayout) findViewById(R.id.searchAct_empty);
        TextView empty_txt=(TextView)findViewById(R.id.searchAct_emptyView_text);
        wdatabase=Word_database.getInstance(this);
        hanzAd=new MultiTypeAdapter();
        SingleHanzBinder hanzBinder=new SingleHanzBinder();
        hanzBinder.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, String s) {
                Intent inl=new Intent();
                inl.setClass(SearchActivity.this,SecondActivity.class);
                Bundle bu=new Bundle();
                bu.putString("hz",s);
                inl.putExtras(bu);
                startActivity(inl);
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
        hanzAd.register(Word.class,hanzBinder);
        hanzAd.setItems(items);
        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setAdapter(hanzAd);
        String str="<h3 style=\"text-align:center\"><font color=\"red\">查询失败</font></h3><br/><br/>" +
                "        似乎出了点意外...<strong>请您核对以下条件：</strong>\n" +
                "        <br/><div><h4><font color=\"#7285aa\">输入内容与查询条件是否匹配</font></h4></div>" +
                "<div><p style=\"text-indent:2em\">当查询条件为汉字时，您需要输入汉字；</p><p style=\"text-indent:2em\">当查询条件为客家话或普通话时，您需要输入客家话拼音或普通话拼音；请您仔细检查拼音格式是否有误；</p>" +
                "       <p style=\"text-indent:2em\"> <font color=\"blue\">请您仔细阅读「查询使用说明」；其他未尽事宜，请邮件或开源地址上反馈。<br/><br/>感谢您的使用，祝您生活愉快！</p></font>";
        empty_txt.setText(Html.fromHtml(str));
        recy.setEmptyView(emptyView);
        clear=(ClearEditText)findViewById(R.id.searchAct_clearEdit);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                clear.requestFocus();
                InputMethodManager imm=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput(clear,InputMethodManager.SHOW_IMPLICIT);
            }
        },200);
        roundButton=(QMUIRoundButton)findViewById(R.id.searchAct_btn);
        roundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yhz = clear.getText().toString();
                int spinner_selected=sp.getInt("spinner_selected",0);
                String input_str=clear.getText().toString();
                if(input_str.isEmpty()){
                    items.clear();
                    scroll.setVisibility(View.VISIBLE);
                    hanzAd.notifyDataSetChanged();
                }else{
                    items.clear();
                    FilterData filterData=new FilterData(input_str,copyList);
                    switch (spinner_selected) {
                        case 0:
                                List<Word> retru = new ArrayList<>();
                                retru.clear();
                                retru = filterData.returnHanzlist();
                                items.addAll(retru);
                                scroll.setVisibility(View.GONE);
                                hanzAd.notifyDataSetChanged();
                                break;

                        case 1:
                            List<Word> het = new ArrayList<>();
                            het.clear();
                            het = filterData.returnHakkaList();
                            items.addAll(het);
                            scroll.setVisibility(View.GONE);
                            hanzAd.notifyDataSetChanged();
                            recy.scrollToPosition(0);
                            break;
                        case 2:
                            List<Word> ret = new ArrayList<>();
                            ret.clear();
                            ret = filterData.returnCmnList();
                            items.addAll(ret);
                            scroll.setVisibility(View.GONE);
                            hanzAd.notifyDataSetChanged();
                            recy.scrollToPosition(0);
                            break;
                    }

                    }

            }
        });

        topba=(QMUITopBarLayout)findViewById(R.id.searchAct_top);
        imags=(ImageView)findViewById(R.id.searchAct_imageview);
        Glide.with(this).load(R.drawable.icon_app_search).into(imags);
        topba.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(SearchActivity.this);
        setContentView(R.layout.activity_search);


        sp= PreferenceManager.getDefaultSharedPreferences(SearchActivity.this);
        editor=sp.edit();
        spinner=(Spinner)findViewById(R.id.actionDown);
        WordViewModel wordViewModel=new ViewModelProvider(this).get(WordViewModel.class);
        wordViewModel.getLiveDataWord().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                copyList.addAll(words);
            }
        });
        initViews();
        initSpinner();

    }

    private void initSpinner(){

        spinner_strs= new String[]{getString(R.string.frag_main_hanz),getString(R.string.frag_main_hakka),getString(R.string.frag_main_common)};
        ArrayAdapter<String> spinnerAdp=new ArrayAdapter<String>(this,R.layout.spinner_item_selected,spinner_strs);
        spinnerAdp.setDropDownViewResource(R.layout.sinner_dropdown_item);
        spinner.setAdapter(spinnerAdp);
        sp= this.getPreferences(Context.MODE_PRIVATE);
        editor=sp.edit();
        int selected=sp.getInt("spinner_selected",0);
        Log.i("spinner_selected is--->", String.valueOf(selected));
        spinner.setSelection(selected);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                editor.putInt("spinner_selected",i);
                editor.commit();
                Log.i( "onItemSelected commit is-->", String.valueOf(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



}
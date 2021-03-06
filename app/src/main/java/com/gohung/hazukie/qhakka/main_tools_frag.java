package com.gohung.hazukie.qhakka;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drakeet.multitype.MultiTypeAdapter;
import com.gohung.hazukie.qhakka.Data_model.UniversalMuluk;
import com.gohung.hazukie.qhakka.Data_model.UniversalTitle;
import com.gohung.hazukie.qhakka.binderr.UniversalMulukBinder;
import com.gohung.hazukie.qhakka.binderr.UniversalTitleBinder;
import com.gohung.hazukie.qhakka.database.Word;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link main_tools_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main_tools_frag extends Fragment {
    private RecyclerView recy;
    private ArrayList<Object> items=new ArrayList<>();
    private MultiTypeAdapter multiTypeAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public main_tools_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static main_tools_frag newInstance(String param1, String param2) {
        main_tools_frag fragment = new main_tools_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_main_tools_frag, container, false);
        recy=(RecyclerView) root.findViewById(R.id.frag_main_tools_recy);
        recy.setHasFixedSize(true);
        multiTypeAdapter=new MultiTypeAdapter();
        multiTypeAdapter.register(UniversalTitle.class,new UniversalTitleBinder());
        UniversalMulukBinder mulukBinder=new UniversalMulukBinder();
        mulukBinder.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, String s) {

            }

            @Override
            public void onItemUrlClick(View v, String s, String url) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),WebViewActivity.class);
                Bundle hj=new Bundle();
                hj.putString("link",url);
                intent.putExtras(hj);
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(View v, String s) {

            }

            @Override
            public void onCardLongClick(View v, Word word) {

            }
        });
        multiTypeAdapter.register(UniversalMuluk.class,mulukBinder);
        items.add(new UniversalTitle("????????????",1));
        items.add(new UniversalMuluk("??????????????????(?????????:benc)","https://pan.baidu.com/s/1o1wSXIGBH9VbcLcc9Nr5iw?pwd=benc"));
        items.add(new UniversalMuluk("?????????????????????","https://m.runoob.com/"));
        items.add(new UniversalMuluk("W3School?????????","https://www.w3school.com.cn/"));
        items.add(new UniversalMuluk("???????????????????????????","https://www.iconfont.cn/"));
        items.add(new UniversalMuluk("?????????","https://www.wanandroid.com/"));
        items.add(new UniversalMuluk("???????????????","https://c.runoob.com/front-end/6214/"));
        items.add(new UniversalMuluk("??????API??????","https://www.mxnzp.com/doc/list"));
        items.add(new UniversalMuluk("????????????API????????????","https://www.cnblogs.com/trackingmore/p/7156877.html"));
        items.add(new UniversalMuluk("???????????????","http://tools.jb51.net/static/colorpicker/"));

        items.add(new UniversalTitle("??????????????????",1));
        items.add(new UniversalMuluk("???????????????(????????????jimj)","https://pan.baidu.com/s/1HZPNOHt1qW4TUwSohVh2lw?pwd=jimj" ));
        items.add(new UniversalMuluk("?????????","https://www.zdic.net/"));
        items.add(new UniversalMuluk("?????????","https://ytenx.org/"));
        items.add(new UniversalMuluk("??????????????????","http://www.ccamc.org/index.php"));
        items.add(new UniversalMuluk("?????????????????????????????????","http://corpus.china-language.edu.cn/"));
        items.add(new UniversalMuluk("???????????????","http://www.yywzw.comm/"));
        items.add(new UniversalMuluk("?????????????????????","https://api.shufashibie.com/page/index.html"));
        items.add(new UniversalMuluk("?????????????????????","http://www.chinese-linguipedia.org/"));
        items.add(new UniversalMuluk("?????????","http://zisea.com/"));
        items.add(new UniversalMuluk("?????????","https://xiaoxue.iis.sinica.edu.tw/"));
        items.add(new UniversalMuluk("???????????????????????????","http://hanchi.ihp.sinica.edu.tw/ihp/hanji.htm"));
        items.add(new UniversalMuluk("?????????????????????","https://kangxizidian.com/index.php"));
        items.add(new UniversalMuluk("??????????????????????????????","http://www.homeinmists.com/index.htm"));
        items.add(new UniversalMuluk("????????????","http://www.kaom.net/"));

        items.add(new UniversalTitle("????????????",1));
        items.add(new UniversalMuluk("???????????????(????????????hkbo)","https://pan.baidu.com/s/1FXB2N9RuNJkeolre2avwLg?pwd=hkbo"));
        items.add(new UniversalMuluk("??????","https://www.syndict.com/"));
        items.add(new UniversalMuluk("??????","https://www/moedict.tw/"));

        items.add(new UniversalTitle("?????????????????????",1));
        items.add(new UniversalMuluk("RIME?????????","https://rime.im/"));
        items.add(new UniversalMuluk("?????????RIME???????????????","https://zhuanlan.zhihu.com/p/356862875"));

        multiTypeAdapter.setItems(items);
        recy.setAdapter(multiTypeAdapter);

        return root;
    }
}
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
 * Use the {@link main_interesting_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main_interesting_frag extends Fragment {
    private MultiTypeAdapter multi;
    private ArrayList<Object> items=new ArrayList<>();
    private RecyclerView recy;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public main_interesting_frag() {
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
    public static main_interesting_frag  newInstance(String param1, String param2) {
        main_interesting_frag fragment = new main_interesting_frag();
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

         View root=inflater.inflate(R.layout.fragment_main_interesting_frag, container, false);
         multi=new MultiTypeAdapter();
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
         multi.register(UniversalTitle.class,new UniversalTitleBinder());
         multi.register(UniversalMuluk.class,mulukBinder);
         recy=(RecyclerView) root.findViewById(R.id.frag_main_interesting_recy);
         recy.setAdapter(multi);
         String urlhead="https://www.bilibili.com/read/mobile?id=";
         items.add(new UniversalTitle("???????????????",1));
         items.add(new UniversalMuluk("A-Soul??????????????????","https://www.bilibili.com/read/mobile?id=11750289"));
         items.add(new UniversalMuluk("A-Soul?????????????????????","https://www.bilibili.com/read/mobile?id=10189015"));
         items.add(new UniversalMuluk("???????????????Up??????????????????????????????","https://www.bilibili.com/read/mobile?id=9751098"));
         items.add(new UniversalMuluk("A-Soul!?????????????????????????????????","https://www.bilibili.com/read/mobile?id=11255821"));
         items.add(new UniversalMuluk("???????????????????????????A-SOUL????????????","https://www.bilibili.com/read/mobile?id=10612124"));
         items.add(new UniversalMuluk("A-Soul???????????????????????????","https://www.bilibili.com/read/mobile?id=11060830"));
         items.add(new UniversalMuluk("A-Soul??????????????????????????????","https://www.bilibili.com/read/mobile?id=10246036"));
         items.add(new UniversalMuluk("A-Soul|????????????MV","https://b23.tv/6WuG1jk"));
         items.add(new UniversalMuluk("????????????????????????"," https://b23.tv/uDyj6xp"));
         items.add(new UniversalMuluk("??????(?????????)","https://b23.tv/QBoCyDo"));


         items.add(new UniversalTitle("????????????",1));
         items.add(new UniversalTitle("???????????????????????????",1));
         items.add(new UniversalMuluk("?????????????????????(??????)","https://b23.tv/xfSLCzU"));
         items.add(new UniversalMuluk("?????????????????????(??????)","https://b23.tv/f2J1Xow"));
         items.add(new UniversalMuluk("??????(??????)","https://b23.tv/zi3x951"));
         items.add(new UniversalMuluk("???????????????????????????(??????)","https://b23.tv/TQlF9Cm"));
         items.add(new UniversalMuluk("?????????-?????????(??????)","https://b23.tv/v00nUkl"));
         items.add(new UniversalMuluk("???????????????","https://www.zhihu.com/answer/14274221"));
         items.add(new UniversalMuluk("?????????????????????","https://www.zhihu.com/answer/131540407"));
         items.add(new UniversalMuluk("???????????????","http://www.yywzw.com/stw/stw1-005.htm"));
         items.add(new UniversalMuluk("????????????","http://www.yywzw.com/jt/feng/"));
         items.add(new UniversalMuluk("??????????????????","https://mp.weixin.qq.com/s?__biz=MjM5Nzk5Mzg0Mw==&mid=2654203116&idx=1&sn=b2ca1c93ae9c7ef20e2db23f88fc95e3&chksm=bd16c0f08a6149e63201775f39e750de131e6bb37e10d84de7baed2e5b4317599ec8421ff8b1&scene=21#wechat_redirect"));
        items.add(new UniversalMuluk("??????????????????", "https://www.zhihu.com/question/31138854/answer/54252415"));

         multi.setItems(items);
         multi.notifyDataSetChanged();
         return root;
    }
}
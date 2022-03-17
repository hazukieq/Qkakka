package com.gohung.hazukie.qhakka;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gohung.hazukie.qhakka.CustomView.ClearEditText;
import com.gohung.hazukie.qhakka.Data_model.Wordlabel;
import com.gohung.hazukie.qhakka.adapers.QMUIFragmentPagerAdapter;
import com.gohung.hazukie.qhakka.adapers.SinglehanzAdapter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.qmuiteam.qmui.widget.tab.QMUIBasicTabSegment;
import com.qmuiteam.qmui.widget.tab.QMUITab;
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder;
import com.qmuiteam.qmui.widget.tab.QMUITabSegment;
import com.qmuiteam.qmui.widget.tab.QMUITabSegment2;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link word_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class word_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private FrameLayout frameLayout;
    private SinglehanzAdapter adapter;
    private List<String> alls=new ArrayList<>();
    private List<Wordlabel> nouns=new ArrayList<>();
    private List<Wordlabel> verbs=new ArrayList<>();



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public word_Fragment() {
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
    public static word_Fragment newInstance(String param1, String param2) {
        word_Fragment fragment = new word_Fragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_word, container, false);

        nouns.add(new Wordlabel("天象地理"));
        nouns.add(new Wordlabel("矿物及无生命自然物"));
        nouns.add(new Wordlabel("方位时间"));
        nouns.add(new Wordlabel("动物"));
        nouns.add(new Wordlabel("植物"));
        nouns.add(new Wordlabel("身体心理"));
        nouns.add(new Wordlabel("亲属"));
        nouns.add(new Wordlabel("人的称谓"));
        nouns.add(new Wordlabel("居住建筑交通"));
        nouns.add(new Wordlabel("服饰"));
        nouns.add(new Wordlabel("生活用具、武器"));
        nouns.add(new Wordlabel("生产工具"));
        nouns.add(new Wordlabel("经济文化娱乐"));
        nouns.add(new Wordlabel("行政区域民族"));
        nouns.add(new Wordlabel("宗教风俗"));
        nouns.add(new Wordlabel("疾病医药"));
        nouns.add(new Wordlabel("食品饲料"));
        nouns.add(new Wordlabel("抽象及其他"));
        nouns.add(new Wordlabel("新名词"));
        nouns.add(new Wordlabel("增加名词"));

        verbs.add(new Wordlabel("自然现象及一般事物的活动变化"));
        verbs.add(new Wordlabel("动植物活动及生长变化"));
        verbs.add(new Wordlabel("判断、存在、趋向、能愿动词"));
        verbs.add(new Wordlabel("心理活动"));
        verbs.add(new Wordlabel("疾病医疗"));
        verbs.add(new Wordlabel("风俗迷信"));
        verbs.add(new Wordlabel("五官动作"));
        verbs.add(new Wordlabel("日常生活一般行为动作"));
        verbs.add(new Wordlabel("手的动作(不用工具)"));
        verbs.add(new Wordlabel("手的动作(用工具)"));
        verbs.add(new Wordlabel("脚的动作"));
        verbs.add(new Wordlabel("手工业劳动"));
        verbs.add(new Wordlabel("农牧业劳动"));
        verbs.add(new Wordlabel("炊事劳动"));
        verbs.add(new Wordlabel("经济活动"));
        verbs.add(new Wordlabel("抽象行为及其他"));
        verbs.add(new Wordlabel("新借动词"));




        ImageView imags=(ImageView)root.findViewById(R.id.imageview);
        frameLayout=(FrameLayout)root.findViewById(R.id.frag_word_frame);
        recyclerView=(RecyclerView)root.findViewById(R.id.frag_word_recy);
        alls.add("名词");
        alls.add("动词");
        alls.add("代词");
        alls.add("连词");
        alls.add("助词");
        alls.add("形容词");
        alls.add("数词");
        alls.add("量词");
        adapter=new SinglehanzAdapter(alls);
        recyclerView.setAdapter(adapter);

        if(adapter.currentposiotion==0){
            replaceFragment(new Word_Subfrag("名词",nouns));
            //Toast.makeText(getActivity(), "0", Toast.LENGTH_SHORT).show();
        }
        adapter.setOnItemClickListener(new SinglehanzAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position,String s) {
                adapter.currentposiotion=position;
                List<Wordlabel> prepare=new ArrayList<>();
                adapter.notifyDataSetChanged();
                switch (s){
                    case "名词":
                        prepare.clear();
                        prepare.addAll(nouns);
                        break;
                    case "动词":
                        prepare.clear();
                        prepare.addAll(verbs);
                        break;
                    case "形容词":
                        break;
                    case "数词":
                        break;
                    case "量词":
                        break;
                    case "代词":
                        break;
                    case "副词":
                        break;
                    case "连词":
                        break;



                }
                replaceFragment(new Word_Subfrag(s,prepare));
            }
        });
        Glide.with(root.getContext()).load(R.drawable.icon_app_search).into(imags);
        ClearEditText editText=(ClearEditText)root.findViewById(R.id.clearsearch);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i==KeyEvent.KEYCODE_ENTER){
                    //Toast.makeText(root.getContext(), "clicked enter key",Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });

        return root;
    }


    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frag_word_frame,fragment);
        //transaction.addToBackStack(null);
        transaction.commit();

    }

}
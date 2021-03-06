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

        nouns.add(new Wordlabel("????????????"));
        nouns.add(new Wordlabel("???????????????????????????"));
        nouns.add(new Wordlabel("????????????"));
        nouns.add(new Wordlabel("??????"));
        nouns.add(new Wordlabel("??????"));
        nouns.add(new Wordlabel("????????????"));
        nouns.add(new Wordlabel("??????"));
        nouns.add(new Wordlabel("????????????"));
        nouns.add(new Wordlabel("??????????????????"));
        nouns.add(new Wordlabel("??????"));
        nouns.add(new Wordlabel("?????????????????????"));
        nouns.add(new Wordlabel("????????????"));
        nouns.add(new Wordlabel("??????????????????"));
        nouns.add(new Wordlabel("??????????????????"));
        nouns.add(new Wordlabel("????????????"));
        nouns.add(new Wordlabel("????????????"));
        nouns.add(new Wordlabel("????????????"));
        nouns.add(new Wordlabel("???????????????"));
        nouns.add(new Wordlabel("?????????"));
        nouns.add(new Wordlabel("????????????"));

        verbs.add(new Wordlabel("??????????????????????????????????????????"));
        verbs.add(new Wordlabel("??????????????????????????????"));
        verbs.add(new Wordlabel("???????????????????????????????????????"));
        verbs.add(new Wordlabel("????????????"));
        verbs.add(new Wordlabel("????????????"));
        verbs.add(new Wordlabel("????????????"));
        verbs.add(new Wordlabel("????????????"));
        verbs.add(new Wordlabel("??????????????????????????????"));
        verbs.add(new Wordlabel("????????????(????????????)"));
        verbs.add(new Wordlabel("????????????(?????????)"));
        verbs.add(new Wordlabel("????????????"));
        verbs.add(new Wordlabel("???????????????"));
        verbs.add(new Wordlabel("???????????????"));
        verbs.add(new Wordlabel("????????????"));
        verbs.add(new Wordlabel("????????????"));
        verbs.add(new Wordlabel("?????????????????????"));
        verbs.add(new Wordlabel("????????????"));




        ImageView imags=(ImageView)root.findViewById(R.id.imageview);
        frameLayout=(FrameLayout)root.findViewById(R.id.frag_word_frame);
        recyclerView=(RecyclerView)root.findViewById(R.id.frag_word_recy);
        alls.add("??????");
        alls.add("??????");
        alls.add("??????");
        alls.add("??????");
        alls.add("??????");
        alls.add("?????????");
        alls.add("??????");
        alls.add("??????");
        adapter=new SinglehanzAdapter(alls);
        recyclerView.setAdapter(adapter);

        if(adapter.currentposiotion==0){
            replaceFragment(new Word_Subfrag("??????",nouns));
            //Toast.makeText(getActivity(), "0", Toast.LENGTH_SHORT).show();
        }
        adapter.setOnItemClickListener(new SinglehanzAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position,String s) {
                adapter.currentposiotion=position;
                List<Wordlabel> prepare=new ArrayList<>();
                adapter.notifyDataSetChanged();
                switch (s){
                    case "??????":
                        prepare.clear();
                        prepare.addAll(nouns);
                        break;
                    case "??????":
                        prepare.clear();
                        prepare.addAll(verbs);
                        break;
                    case "?????????":
                        break;
                    case "??????":
                        break;
                    case "??????":
                        break;
                    case "??????":
                        break;
                    case "??????":
                        break;
                    case "??????":
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
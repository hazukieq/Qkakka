package com.gohung.hazukie.qhakka;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.drakeet.multitype.MultiTypeAdapter;
import com.gohung.hazukie.qhakka.Data_model.UniversalMuluk;
import com.gohung.hazukie.qhakka.Data_model.UniversalTitle;
import com.gohung.hazukie.qhakka.Data_model.Wordlabel;
import com.gohung.hazukie.qhakka.binderr.UniversalMulukBinder;
import com.gohung.hazukie.qhakka.binderr.WordlabelBinder;
import com.gohung.hazukie.qhakka.database.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link word_Fragment_categories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class word_Fragment_categories extends Fragment {
    private Button back;
    private RecyclerView recy;
    private MultiTypeAdapter multiTypeAdap;
    private ArrayList<Object> items=new ArrayList<>();
    private List<Wordlabel> alls=new ArrayList<>();
    private String str="";
    private TextView title;


    public word_Fragment_categories(String str){
        this.str=str;
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public word_Fragment_categories() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment word_Fragment_categories.
     */
    // TODO: Rename and change types and number of parameters
    public static word_Fragment_categories newInstance(String param1, String param2) {
        word_Fragment_categories fragment = new word_Fragment_categories();
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
        View root=inflater.inflate(R.layout.frag_word_categories, container, false);
        back=(Button) root.findViewById(R.id.frag_word_categories_button);
        recy=(RecyclerView) root.findViewById(R.id.frag_word_categories_recy);
        title=(TextView)root.findViewById(R.id.frag_word_categories_title);
        multiTypeAdap=new MultiTypeAdapter();
        multiTypeAdap.register(UniversalMuluk.class,new UniversalMulukBinder());
        WordlabelBinder wordlabelBinder=new WordlabelBinder();
        wordlabelBinder.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, String s) {
                Intent ih=new Intent();
                ih.setClass(getActivity(),WordCategoryAct.class);
                startActivity(ih);
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
        multiTypeAdap.register(Wordlabel.class,wordlabelBinder);
        initsViews();
        return root;
    }

    private void initsViews(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        title.setText(str);
        for(int i=0;i<100;i++){
            alls.add(new Wordlabel("这是测试"+i));
        }
        recy.setAdapter(multiTypeAdap);
        items.addAll(alls);
        multiTypeAdap.setItems(items);
    }



}
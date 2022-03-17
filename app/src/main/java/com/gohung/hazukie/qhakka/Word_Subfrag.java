package com.gohung.hazukie.qhakka;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drakeet.multitype.MultiTypeAdapter;
import com.gohung.hazukie.qhakka.Data_model.UniversalMuluk;
import com.gohung.hazukie.qhakka.Data_model.Wordlabel;
import com.gohung.hazukie.qhakka.binderr.UniversalMulukBinder;
import com.gohung.hazukie.qhakka.binderr.WordlabelBinder;
import com.gohung.hazukie.qhakka.database.Word;
import com.gohung.hazukie.qhakka.database.Word_database;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Word_Subfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Word_Subfrag extends Fragment {
    String wantStr;
    private Word_database wdata;
    private RecyclerView recy;
    private MultiTypeAdapter multiTypeAdapter;
    private ArrayList<Object> items=new ArrayList<>();
    private List<Wordlabel> wordlabels=new ArrayList<>();

    public Word_Subfrag(String wantSearch, List<Wordlabel> categorys){
        this.wantStr=wantSearch;
        this.wordlabels=categorys;
    }



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public Word_Subfrag() {
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
    public static Word_Subfrag newInstance(String param1, String param2) {
        Word_Subfrag fragment = new Word_Subfrag();
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
         View root=inflater.inflate(R.layout.fragment_word_subfrag, container, false);
         recy=(RecyclerView) root.findViewById(R.id.frag_word_subfrag_recy);
      LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
         recy.setLayoutManager(linearLayoutManager);

        multiTypeAdapter=new MultiTypeAdapter();
        WordlabelBinder wordlabelBinder=new WordlabelBinder();
        wordlabelBinder.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, String s) {
                returnFragment(new word_Fragment_categories(s));
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
        multiTypeAdapter.register(Wordlabel.class, wordlabelBinder);
        UniversalMulukBinder mulukBinder=new UniversalMulukBinder();
        multiTypeAdapter.register(UniversalMuluk.class,mulukBinder);
        items.clear();
        items.add(new UniversalMuluk(wantStr,""));
        if(wordlabels!=null) items.addAll(wordlabels);
        multiTypeAdapter.setItems(items);
        recy.setAdapter(multiTypeAdapter);
        multiTypeAdapter.notifyDataSetChanged();
         return root;
    }

    public void returnFragment(Fragment fragment){
        FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right);
        transaction.replace(R.id.frag_word_frame,fragment,"fragment1");
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
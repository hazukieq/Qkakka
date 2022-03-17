package com.gohung.hazukie.qhakka;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SubFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubFragment newInstance(String param1, String param2) {
        SubFragment fragment = new SubFragment();
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

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String tile;
    private QMUITopBarLayout top;
    private ListView lsit;
    private String[] strings;
    private int select=0;
    private ArrayAdapter<String> arrayAdapter;
    private String settings_one,settings_two;

    public SubFragment(String title,String[] strings,int selectedType){
        this.tile=title;
        this.strings=strings;
        this.select=selectedType;
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_setting_lang_sub,container,false);
        lsit=(ListView)root.findViewById(R.id.sub_frag_list);
        top=(QMUITopBarLayout)root.findViewById(R.id.sub_frag_top);
        sp= PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor=sp.edit();
        top.setTitle(tile);
        top.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        initsList();
        return root;
    }

    private void initsList(){
        lsit.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        arrayAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice,strings);
        lsit.setAdapter(arrayAdapter);
        int listRecord=0;
        switch (select){
            case 0:
                int cmnWay=sp.getInt("cmn_visible_way",0);
                listRecord=cmnWay;
                break;
            case 1:
                int hkWay=sp.getInt("hakka_visible_way",0);
                listRecord=hkWay;
                break;
            case 2:
                int ipaWay=sp.getInt("ipa_visible_way",1);
                listRecord=ipaWay;
                break;
            case 3:
                int fontWay=sp.getInt("font_visible_way",0);
                listRecord=fontWay;
                break;
            default:
                break;
        }
        lsit.setItemChecked(listRecord,true);
        lsit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (select){
                    case 0:
                        editor.putInt("cmn_visible_way",i);
                        editor.commit();
                        break;
                    case 1:
                        editor.putInt("hakka_visible_way",i);
                        editor.commit();
                        break;
                    case 2:
                        editor.putInt("ipa_visible_way",i);
                        editor.commit();
                        break;
                    case 3:
                        editor.putInt("font_visible_way",i);
                        editor.commit();
                        break;

                }
            }
        });

    }

}
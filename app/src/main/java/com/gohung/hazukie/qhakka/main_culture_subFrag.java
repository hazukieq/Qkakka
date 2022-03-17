package com.gohung.hazukie.qhakka;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.drakeet.multitype.MultiTypeAdapter;
import com.gohung.hazukie.qhakka.Data_model.BnannerModel;
import com.gohung.hazukie.qhakka.Data_model.SecondHk;
import com.gohung.hazukie.qhakka.Data_model.UniversalMuluk;
import com.gohung.hazukie.qhakka.Data_model.UniversalTitle;
import com.gohung.hazukie.qhakka.Utils.ShowDialogUtil;
import com.gohung.hazukie.qhakka.adapers.ImageAdapter;
import com.gohung.hazukie.qhakka.binderr.BannerBinder;
import com.gohung.hazukie.qhakka.binderr.UniversalMulukBinder;
import com.gohung.hazukie.qhakka.binderr.UniversalTitleBinder;
import com.gohung.hazukie.qhakka.database.Lipoem;
import com.gohung.hazukie.qhakka.database.Word;
import com.gohung.hazukie.qhakka.database.Word_database;
import com.gohung.hazukie.qhakka.viewmodels.LipoemViewModel;
import com.qmuiteam.qmui.layout.QMUIFrameLayout;
import com.qmuiteam.qmui.skin.QMUISkinHelper;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.skin.QMUISkinValueBuilder;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.popup.QMUIFullScreenPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.AlphaPageTransformer;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link main_culture_subFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main_culture_subFrag extends Fragment {
    private Banner banner;
    private ArrayList<Object> items = new ArrayList<>();
    private List<Lipoem> Lipoems = new ArrayList<>();
    private List<Lipoem> lunnzis = new ArrayList<>();
    private List<Lipoem> dufus = new ArrayList<>();
    private List<Lipoem> zongzus = new ArrayList<>();
    private List<Lipoem> lauzus = new ArrayList<>();
    private Word_database wdat;
    private MultiTypeAdapter multiAD;
    private ShowDialogUtil dialogUtil;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public main_culture_subFrag() {
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
    public static main_culture_subFrag newInstance(String param1, String param2) {
        main_culture_subFrag fragment = new main_culture_subFrag();
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
        View root = inflater.inflate(R.layout.fragment_main_culture_sub, container, false);
        // banner = (Banner) root.findViewById(R.id.banner_card_banner);
        dialogUtil=new ShowDialogUtil(root.getContext());
        wdat = Word_database.getInstance(this.getActivity());
        RecyclerView recy = (RecyclerView) root.findViewById(R.id.frag_main_culture_recy);
        multiAD = new MultiTypeAdapter();
        UniversalTitleBinder universalTitleBinder = new UniversalTitleBinder();
        universalTitleBinder.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, String s) {
                Intent iy = new Intent();
                iy.setClass(root.getContext(), UniversalActivity.class);
                Bundle bun = new Bundle();
                switch (s) {
                    case "李太白诗集专栏":
                        bun.putString("search", "0");
                        iy.putExtras(bun);
                        startActivity(iy);
                        break;

                    case "论语专栏":
                        bun.putString("search", "1");
                        iy.putExtras(bun);
                        startActivity(iy);
                        // Toast.makeText(getActivity(), ""+s, Toast.LENGTH_SHORT).show();
                        break;

                    case "杜甫诗集":
                        bun.putString("search", "2");
                        iy.putExtras(bun);
                        startActivity(iy);
                        break;

                    case "道德经专栏":
                        bun.putString("search", "4");
                        iy.putExtras(bun);
                        startActivity(iy);
                        break;
                    case "庄子专栏":
                        bun.putString("search", "3");
                        iy.putExtras(bun);
                        startActivity(iy);
                        break;
                    default:
                        break;


                }


            }

            @Override
            public void onItemUrlClick(View v, String s, String url) {
                switch (s) {
                    case "李太白诗集专栏":
                        dialogUtil.ShowDialog(v, R.string.lipak_introduction);
                        break;
                    case "杜甫诗集":
                        dialogUtil.ShowDialog(v, R.string.dufu_introduction);
                        break;
                    case "论语专栏":
                        dialogUtil.ShowDialog(v, R.string.lunnzi_introduction);
                        break;
                    case "庄子专栏":
                        dialogUtil.ShowDialog(v, R.string.zongzu_introduction);
                        break;
                    case "道德经专栏":
                       dialogUtil.ShowDialog(v, R.string.lauzu_introduction);
                        break;

                }

            }

            @Override
            public void onItemLongClick(View v, String s) {

            }

            @Override
            public void onCardLongClick(View v, Word word) {

            }
        });
        multiAD.register(UniversalTitle.class, universalTitleBinder);
        UniversalMulukBinder mulukBinder = new UniversalMulukBinder();
        mulukBinder.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(View v, String s) {
                if ((s.equals("内篇")) | (s.equals("外篇")) | (s.equals("杂篇"))) {
                } else {
                    Intent forUni = new Intent();
                    forUni.setClass(getActivity(), UniversalActivity.class);
                    Bundle bu = new Bundle();
                    bu.putString("search", s);
                    forUni.putExtras(bu);
                    startActivity(forUni);
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
        multiAD.register(UniversalMuluk.class, mulukBinder);
        multiAD.register(BnannerModel.class, new BannerBinder(getActivity()));
        multiAD.setItems(items);
        recy.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recy.setAdapter(multiAD);
        new QueryAllWordsTask().execute();
        initDatas();

        return root;
    }


    private void initDatas() {

    }


    private class QueryAllWordsTask extends AsyncTask<Void, Void, Void> {
        public QueryAllWordsTask() {

        }
        @Override
        protected Void doInBackground(Void... voids) {
            Lipoems.clear();

            Lipoems.addAll(wdat.lipoem_dao().getFiftypoems());
            lunnzis.addAll(wdat.lipoem_dao().getAllLunnzis());
            dufus.addAll(wdat.lipoem_dao().getTwentyDufupoems());
            zongzus.addAll(wdat.lipoem_dao().getTwentyZongzus());
            lauzus.addAll(wdat.lipoem_dao().getTwentyLauzus());


            items.clear();
            //items.add(new BnannerModel(0,null));
            items.add(new UniversalTitle("李太白诗集专栏",0));
            for (Lipoem ini : Lipoems) items.add(new UniversalMuluk(ini.getMuluk(), ""));

            items.add(new UniversalTitle("杜甫诗集",0));
            for (Lipoem tini : dufus) items.add(new UniversalMuluk(tini.getMuluk(), ""));

            items.add(new UniversalTitle("论语专栏",0));
            for (Lipoem oni : lunnzis) items.add(new UniversalMuluk(oni.getMuluk(), ""));

            items.add(new UniversalTitle("道德经专栏",0));
            for (Lipoem loni : lauzus) items.add(new UniversalMuluk(loni.getMuluk(), ""));


            items.add(new UniversalTitle("庄子专栏",0));
            for (int i = 0; i < zongzus.size(); i++) {
                Lipoem zini = zongzus.get(i);
                if (i == 0) items.add(new UniversalMuluk("内篇", ""));
                if (i == 8) items.add(new UniversalMuluk("外篇", ""));
                if (i == 23) items.add(new UniversalMuluk("杂篇", ""));
                items.add(new UniversalMuluk(zini.getMuluk(), ""));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            multiAD.notifyDataSetChanged();

        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }


}
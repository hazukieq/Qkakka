package com.gohung.hazukie.qhakka;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.gohung.hazukie.qhakka.CustomView.ClearEditText;
import com.gohung.hazukie.qhakka.adapers.QMUIFragmentPagerAdapter;
import com.gohung.hazukie.qhakka.database.Lipoem;
import com.gohung.hazukie.qhakka.database.Word_database;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;
import com.qmuiteam.qmui.widget.tab.QMUIBasicTabSegment;
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder;
import com.qmuiteam.qmui.widget.tab.QMUITabSegment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link main_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main_frag extends Fragment {
    private QMUITabSegment tabs;
    private QMUIViewPager pagers;
    private QMUITopBarLayout tops;
    private QMUIPopup mNormalPopup;
    private ArrayList<Object> items=new ArrayList<>();
    private List<Lipoem> lis=new ArrayList<>();
    private Word_database wdata;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public main_frag() {
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
    public static main_frag newInstance(String param1, String param2) {
        main_frag fragment = new main_frag();
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
         View root=inflater.inflate(R.layout.frag_main, container, false);
         tabs=(QMUITabSegment) root.findViewById(R.id.frag_main_tabs);
         tops=(QMUITopBarLayout)root.findViewById(R.id.frag_main_topb);
         initTops();
         ClearEditText clearText=(ClearEditText)root.findViewById(R.id.frag_main_clears);
         clearText.setFocusable(false);
         clearText.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                Intent uin=new Intent();
                uin.setClass(root.getContext(), SearchActivity.class);
                startActivity(uin);
             }
         });
         pagers=(QMUIViewPager) root.findViewById(R.id.frag_main_contentViewPager);
        ImageView ima=(ImageView)root.findViewById(R.id.frag_main_imag);
        Glide.with(root.getContext()).load(R.drawable.icon_app_search).into(ima);
        wdata=Word_database.getInstance(root.getContext());
         initsPagers();
         return root;
    }
    private void initTops(){
        tops.addRightTextButton(getString(R.string.mainAllmenu),R.id.top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] listItems = new String[]{
                        getString(R.string.mainAll_settings),
                        getString(R.string.mainAll_about),
                };
                List<String> data = new ArrayList<>();

                Collections.addAll(data, listItems);

                ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.simple_list_item, data);
                AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(root.getContext(), "Item " + (i + 1), Toast.LENGTH_SHORT).show();
                        if (mNormalPopup != null) {
                            mNormalPopup.dismiss();
                        }
                        switch (i){
                            case 0:
                                Intent inli=new Intent();
                                inli.setClass(getActivity(), SettingActivity.class);
                                startActivity(inli);
                                break;
                            case 1:
                                Intent inl=new Intent();
                                inl.setClass(getActivity(), AboutActivity.class);
                                startActivity(inl);
                                break;
                            default:
                                break;
                        }

                    }
                };
                mNormalPopup = QMUIPopups.listPopup(getActivity(),
                        QMUIDisplayHelper.dp2px(getActivity(), 160),
                        QMUIDisplayHelper.dp2px(getActivity(), 120),
                        adapter,
                        onItemClickListener)
                        .animStyle(QMUIPopup.ANIM_GROW_FROM_CENTER)
                        .preferredDirection(QMUIPopup.DIRECTION_TOP)
                        .shadow(true)
                        .edgeProtection(QMUIDisplayHelper.dp2px(getActivity(), 5))
                        .offsetYIfTop(QMUIDisplayHelper.dp2px(getActivity(), 4))
                        .skinManager(QMUISkinManager.defaultInstance(getActivity()))
                        .onDismiss(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                //Toast.makeText(MainAllActivity.this, "onDismiss", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(v);
            }

        });
    }

    private void initsPagers(){
        FragmentManager fm=getActivity().getSupportFragmentManager();
        QMUIFragmentPagerAdapter fragAdp=new QMUIFragmentPagerAdapter(fm) {
            @Override
            public Fragment createFragment(int position) {
                switch (position){
                    case 0:
                        return new main_culture_subFrag();
                    case 1:
                        return new main_interesting_frag();
                    case 2:
                        return new main_tools_frag();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };



        QMUITabBuilder builder=tabs.tabBuilder();
        builder.setDynamicChangeIconColor(true);
        int normal=QMUIDisplayHelper.dp2px(getActivity(),16);
        int selected=QMUIDisplayHelper.dp2px(getActivity(),20);
        builder.setTextSize(normal,selected);
        //builder.setColor(getActivity().getColor(R.color.qmui_config_color_white),getActivity().getColor(R.color.qmui_config_color_red));
        tabs.addTab(builder.setText("文学").build(this.getActivity()));
        tabs.addTab(builder.setText("趣味").build(this.getActivity()));
        tabs.addTab(builder.setText("工具").build(this.getActivity()));
        // tabs.notifyDataChanged();
        tabs.setMode(QMUITabSegment.MODE_FIXED);
        tabs.addOnTabSelectedListener(new QMUIBasicTabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {

            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {

            }

            @Override
            public void onDoubleTap(int index) {
                tabs.clearSignCountView(index);
            }
        });
        pagers.setAdapter(fragAdp);
        pagers.setOffscreenPageLimit(3);
        tabs.setupWithViewPager(pagers,false);

    }

    private class findLipoemsTask extends AsyncTask<Void,Void,Void>{

        public findLipoemsTask(){

        }

        @Override
        protected Void doInBackground(Void... voids) {

            lis.addAll(wdata.lipoem_dao().getAllLipoems());
            for(Lipoem li:lis){
                items.add(li);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }

    }

}
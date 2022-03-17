package com.gohung.hazukie.qhakka.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerview  extends RecyclerView {
    private View emptyView;
    private static String TAG="EmptyRecyclerview";



    final private AdapterDataObserver adapterDataObserver=new AdapterDataObserver() {
        @Override
        public void onChanged() {
            CheckIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            CheckIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            CheckIfEmpty();
        }
    };

    private void CheckIfEmpty(){
        if(emptyView!=null&&getAdapter()!=null){
            final  boolean  emptyViewVisible=getAdapter().getItemCount()==0;
            emptyView.setVisibility(emptyViewVisible?VISIBLE:GONE);
            setVisibility(emptyViewVisible?GONE:VISIBLE);
        }
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        final Adapter oldAdapter=getAdapter();
        if(oldAdapter!=null){
            adapter.unregisterAdapterDataObserver(adapterDataObserver);
        }
        super.setAdapter(adapter);
        if(adapter!=null){
            adapter.registerAdapterDataObserver(adapterDataObserver);
        }
        CheckIfEmpty();
    }

    public void setEmptyView(View emptyView) {
        this.emptyView=emptyView;
        CheckIfEmpty();
    }



    public MyRecyclerview(@NonNull Context context) {
        super(context);
    }

    public  MyRecyclerview(@NonNull Context context, @NonNull AttributeSet attrs){
        super(context,attrs);
    }

    public MyRecyclerview(@NonNull Context context,@NonNull AttributeSet attrs,int defStyle){
        super(context, attrs,defStyle);
    }

}

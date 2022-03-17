package com.gohung.hazukie.qhakka.binderr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drakeet.multitype.ItemViewBinder;
import com.gohung.hazukie.qhakka.Data_model.UniversalTitle;
import com.gohung.hazukie.qhakka.R;

public class UniversalTitleBinder extends ItemViewBinder<UniversalTitle,UniversalTitleBinder.ViewHolder> {
    private com.gohung.hazukie.qhakka.onItemClickListener onItemClickListener=null;
    int visible=0;

    public void setOnItemClickListener(com.gohung.hazukie.qhakka.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        return new ViewHolder(layoutInflater.inflate(R.layout.universal_lanmuk,viewGroup,false));
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, UniversalTitle lipoem) {
                 viewHolder.title.setText(lipoem.getTitle());
                  visible= lipoem.getVisible();
                 viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         if(onItemClickListener!=null){
                             onItemClickListener.onItemUrlClick(viewHolder.itemView, lipoem.getTitle(), "");
                         }
                     }
                 });
                 switch (visible){
                     case 0:
                         viewHolder.more.setVisibility(View.VISIBLE);
                         viewHolder.more.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                 if(onItemClickListener!=null){
                                     onItemClickListener.onItemClick(view, lipoem.getTitle());
                                 }

                             }
                         });
                         break;
                     case 1:
                         viewHolder.more.setVisibility(View.INVISIBLE);
                         break;
                     default:
                         break;
                 }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private Button more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            more=(Button)itemView.findViewById(R.id.lammuk_more);
            title = (TextView) itemView.findViewById(R.id.universal_title);
        }
    }
}

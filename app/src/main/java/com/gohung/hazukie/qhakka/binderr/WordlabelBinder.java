package com.gohung.hazukie.qhakka.binderr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drakeet.multitype.ItemViewBinder;
import com.gohung.hazukie.qhakka.Data_model.UniversalMuluk;
import com.gohung.hazukie.qhakka.Data_model.Wordlabel;
import com.gohung.hazukie.qhakka.R;
import com.gohung.hazukie.qhakka.onItemClickListener;

public class WordlabelBinder extends ItemViewBinder<Wordlabel,WordlabelBinder.ViewHolder> {
    private com.gohung.hazukie.qhakka.onItemClickListener onItemClickListener=null;

    public void setOnItemClickListener(com.gohung.hazukie.qhakka.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        return new ViewHolder(layoutInflater.inflate(R.layout.frag_word_item_label,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, Wordlabel wordlabel) {
        viewHolder.txt.setText(wordlabel.getLabel());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(viewHolder.itemView,wordlabel.getLabel());
                }
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt=(TextView) itemView.findViewById(R.id.frag_word_item_label_txt);
        }
    }

}

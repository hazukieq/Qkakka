package com.gohung.hazukie.qhakka.binderr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drakeet.multitype.ItemViewBinder;
import com.gohung.hazukie.qhakka.Data_model.WordDetails;
import com.gohung.hazukie.qhakka.R;

public class WordDetailsBinder extends ItemViewBinder<WordDetails,WordDetailsBinder.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View root=layoutInflater.inflate(R.layout.frag_word_categories_details_card,viewGroup,false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, WordDetails wordDetails) {
        viewHolder.title.setText(wordDetails.getWord());
        viewHolder.category.setText(wordDetails.getCategory());
        viewHolder.hakkapin.setText(wordDetails.getHakkapin());
        viewHolder.hanz.setText(wordDetails.getHanz());
        viewHolder.explanation.setText(wordDetails.getExplanation());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,category,hakkapin,hanz,explanation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.frag_word_category_details_title);
            category=(TextView) itemView.findViewById(R.id.frag_word_category_details_category);
            hakkapin=(TextView) itemView.findViewById(R.id.frag_word_category_details_hakkapin);
            explanation=(TextView) itemView.findViewById(R.id.frag_word_categpry_details_explaination);
            hanz=(TextView) itemView.findViewById(R.id.frag_word_category_details_hanz);
        }
    }

}

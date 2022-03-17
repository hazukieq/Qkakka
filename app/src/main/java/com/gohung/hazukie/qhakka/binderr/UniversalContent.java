package com.gohung.hazukie.qhakka.binderr;

import static androidx.constraintlayout.widget.StateSet.TAG;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drakeet.multitype.ItemViewBinder;
import com.gohung.hazukie.qhakka.R;
import com.gohung.hazukie.qhakka.database.Lipoem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniversalContent extends ItemViewBinder<Lipoem,UniversalContent.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        return new ViewHolder(layoutInflater.inflate(R.layout.universal_content,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, Lipoem lipoem) {
        viewHolder.universal_poem_title.setText(lipoem.getMuluk());


        String content= lipoem.getContent();
        Pattern p=Pattern.compile("<font color=\"#7285aa\"><p>");
        Matcher matcher=p.matcher(content);

        while (matcher.find()){
            String con=content.replace("<font color=\"#7285aa\"><p>","<font color=\"#7285aa\">");
            String co=con.replace("</p></font>","</font>");
            content=co;
        }


        viewHolder.universal_poem_content.setText(Html.fromHtml(content));
        //  Log.i(TAG, "onBindViewHolder: "+content);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView universal_poem_title,universal_poem_content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            universal_poem_content=(TextView) itemView.findViewById(R.id.universal_poem_content);
            universal_poem_title=(TextView) itemView.findViewById(R.id.universal_poem_title);
        }
    }
}

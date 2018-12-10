package com.example.volley.zeon.RecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.volley.zeon.Model.MainNews;
import com.example.volley.zeon.R;

import java.util.List;

public class AdapterMainNews extends RecyclerView.Adapter<AdapterMainNews.Holder> {
    Context mContext;
    List<MainNews> mainNewsList;

    public AdapterMainNews(Context mContext, List<MainNews> mainNewsList) {
        this.mContext = mContext;
        this.mainNewsList = mainNewsList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        MainNews mainNews = mainNewsList.get(position);

        holder.projectName.setText(mainNews.getProjectName());
        holder.brief.setText(mainNews.getProjectBrief());
    }

    @Override
    public int getItemCount() {
        return mainNewsList.size();
    }


    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView projectName;
        TextView brief;
        ImageView arrow;

        Holder(View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.primary_text);
            brief = itemView.findViewById(R.id.supporting_text);
            arrow = itemView.findViewById(R.id.expand_button);
            arrow.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if (brief.getMaxLines() == 1) {
                arrow.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.arrow_up));
                brief.setMaxLines(3);
            } else {

                arrow.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.arrow_down));
                brief.setMaxLines(1);
            }
        }
    }
}

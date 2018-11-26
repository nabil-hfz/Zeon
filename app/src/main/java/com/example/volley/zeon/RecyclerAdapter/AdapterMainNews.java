package com.example.volley.zeon.RecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.volley.zeon.Model.MainNews;
import com.example.volley.zeon.R;

import java.util.List;

public class AdapterMainNews extends RecyclerView.Adapter<AdapterMainNews.Holder>{
    Context mContext;
    List<MainNews>mainNewsList;

    public AdapterMainNews(Context mContext, List<MainNews> mainNewsList) {
        this.mContext = mContext;
        this.mainNewsList = mainNewsList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        MainNews mainNews=mainNewsList.get(position);

        holder.projectName.setText(mainNews.getProjectName());
        holder.brief.setText(mainNews.getProjectBrief());
    }

    @Override
    public int getItemCount() {
        return mainNewsList.size();
    }



    class Holder extends RecyclerView.ViewHolder {

        TextView projectName;
        TextView brief;

        Holder(View itemView) {
            super(itemView);
            projectName=itemView.findViewById(R.id.project_name);
            brief=itemView.findViewById(R.id.brief);
        }
    }
}

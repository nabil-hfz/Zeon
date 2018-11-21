package com.example.volley.zeon.RecyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.volley.zeon.Model.FutureVision;
import com.example.volley.zeon.R;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Author of this class is Nabil  in 2018/01/11
 */

/**
 * Loads a list of FutureVision Activity by using an AdapterFutureVision with Recycler .
 * you can make network request in future .
 */

public class AdapterFutureVision extends RecyclerView.Adapter<AdapterFutureVision.Holder> {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = AdapterFutureVision.class.getSimpleName();

    Context context;

    List<FutureVision> futureVisionList;

    /**
     * Create a new {@link AdapterFutureVision} object.
     *
     * @param context          is the current context (i.e. Activity) that the adapter is being created in.
     * @param futureVisionList is the list of {@link Future Vision  }s to be displayed.
     */

    public AdapterFutureVision(Context context, List<FutureVision> futureVisionList) {
        this.context = context;
        this.futureVisionList = futureVisionList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_future_vision, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        FutureVision currentFutureVision = futureVisionList.get(position);

        // Get the  Short Article about Future Vision for team  from the currentFutureVision object and set this text on
        // the mShortArticleFutureVision TextView.
        holder.mTitle.setText(currentFutureVision.getmTitle());
        holder.mShortArticleFutureVision.setText(currentFutureVision.getShortArticleFutureVision());

    }

    @Override
    public int getItemCount() {
        return futureVisionList.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitle;

        private TextView mShortArticleFutureVision;

        private ImageView arrow_up, arrow_down;

        Holder(View view) {

            super(view);

            mTitle = view.findViewById(R.id.tv_title);
            mShortArticleFutureVision = view.findViewById(R.id.description_text);
            arrow_down = view.findViewById(R.id.show);
            arrow_up = view.findViewById(R.id.hide);
            arrow_up.setOnClickListener(this);
            arrow_down.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (mShortArticleFutureVision.getMaxLines() == 0) {
                arrow_up.setVisibility(View.VISIBLE);
                arrow_down.setVisibility(View.INVISIBLE);
                mShortArticleFutureVision.setMaxLines(Integer.MAX_VALUE);
            } else {
                arrow_up.setVisibility(View.INVISIBLE);
                arrow_down.setVisibility(View.VISIBLE);
                mShortArticleFutureVision.setMaxLines(0);
            }
        }
    }
}

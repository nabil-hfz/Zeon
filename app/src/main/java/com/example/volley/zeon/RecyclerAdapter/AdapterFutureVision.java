package com.example.volley.zeon.RecyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.volley.zeon.Model.FutureVision;
import com.example.volley.zeon.R;
import java.util.List;
import java.util.concurrent.Future;
import de.hdodenhof.circleimageview.CircleImageView;

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
        holder.mShortArticleFutureVision.setText(currentFutureVision.getShortArticleFutureVisiom());

        // Get Future Vision image from the currentFutureVision object and set this image on
        holder.mImageArticleFutureVision.setImageResource(currentFutureVision.getImageArticleFutureVision());

        // Make sure the view is visible
        holder.mImageArticleFutureVision.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return futureVisionList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView mShortArticleFutureVision;

        private CircleImageView mImageArticleFutureVision;

        public Holder(View view) {

            super(view);

            mShortArticleFutureVision = view.findViewById(R.id.summary_project_future_vision);

            mImageArticleFutureVision = view.findViewById(R.id.future_vision_image);
        }
    }
}

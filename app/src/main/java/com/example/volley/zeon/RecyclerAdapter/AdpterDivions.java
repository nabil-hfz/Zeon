package com.example.volley.zeon.RecyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.volley.zeon.Model.Division;
import com.example.volley.zeon.R;

import java.util.List;

public class AdpterDivions extends RecyclerView.Adapter<AdpterDivions.Holder> {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = AdpterDivions.class.getSimpleName();

    Context context;

    List<Division> divisionList;

    /**
     * Create a new {@link AdpterDivions} object.
     *
     * @param context      is the current context (i.e. Activity) that the adapter is being created in.
     * @param divisionList is the list of {@link Division }s to be displayed.
     */

    public AdpterDivions(Context context, List<Division> divisionList) {
        this.context = context;
        this.divisionList = divisionList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_divsion, parent, false);
        return new Holder(view);

    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Division currentDivision = divisionList.get(position);


        // Get the name member from the currentDivision object and set this text on
        // the name member TextView.

        holder.mNameMember.setText(currentDivision.getNameMember());

        // Get the Majority Member from the currentDivision object and set this text on
        // the MajorityMember TextView.

        holder.mMajorityMember.setText(currentDivision.getMajorityMember());

        // If an image is available, display the provided
        holder.mImageMember.setImageResource(currentDivision.getImageMember());

        // Make sure the view is visible
        holder.mImageMember.setVisibility(View.VISIBLE);

        // Get the summary info  Member from the currentDivision object and set this text on
        // the summaryinfo TextView.

        holder.mInfoSummaryMember.setText(currentDivision.getSummaryInfoMember());
    }

    @Override
    public int getItemCount() {
        return divisionList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView mNameMember;

        private TextView mMajorityMember;

        private ImageView mImageMember;

        private TextView mInfoSummaryMember;

        private Button mSeeMoreProject;


        public Holder(View view) {
            super(view);

            mNameMember = view.findViewById(R.id.name_members);

            mMajorityMember = view.findViewById(R.id.majority_member);

            mImageMember = view.findViewById(R.id.member_photo);

            mInfoSummaryMember = view.findViewById(R.id.summary_member);

            mSeeMoreProject = view.findViewById(R.id.button_see_more);
        }
    }
}

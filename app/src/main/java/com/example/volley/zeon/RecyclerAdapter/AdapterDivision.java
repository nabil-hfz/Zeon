package com.example.volley.zeon.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.volley.zeon.DivisionDetails;
import com.example.volley.zeon.Model.Division;
import com.example.volley.zeon.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.volley.zeon.Util.UtilTools.makeIntentToOpenPhotoInGalleryBrowser;

/**
 * Author of this class is Nabil  in 2018/01/11
 */


public class AdapterDivision extends RecyclerView.Adapter<AdapterDivision.Holder> {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = AdapterDivision.class.getSimpleName();

    Context mContext;

    List<Division> mDivisionList;

    TextView mEmptyStateTextView;

    /**
     * Create a new {@link AdapterDivision} object.
     *
     * @param mContext     is the current mContext (i.e. Activity) that the adapter is being created in.
     * @param divisionList is the list of {@link Division }s to be displayed.
     */

    public AdapterDivision(Context mContext, List<Division> divisionList) {
        this.mContext = mContext;
        this.mDivisionList = divisionList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_divsion, parent, false);
        return new Holder(view);

    }


    @Override
    public void onBindViewHolder(final Holder holder, int position) {

        final Division currentDivision = mDivisionList.get(position);


        // Get the name member from the currentDivision object and set this text on
        // the name member TextView.

        holder.mNameMember.setText(currentDivision.getNameMember());

        // Get the Majority Member from the currentDivision object and set this text on
        // the MajorityMember TextView.

        holder.mMajorityMember.setText(currentDivision.getMajorityMember());

        // If an image is available, display the provided   Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
        //"https://cdn.pixabay.com/user/2013/11/05/02-10-23-764_250x250.jpg"
        Picasso.get().load(currentDivision.getImageMemberUrl()).fit().centerInside().into(holder.mImageMember);
        // holder.mImageMember.setImageResource(currentDivision.getImageMemberUrl());
        // Make sure the ImageView is visible
        holder.mImageMember.setVisibility(View.VISIBLE);

        holder.mImageMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mContext.startActivity(
                        makeIntentToOpenPhotoInGalleryBrowser(
                                currentDivision.getImageMemberUrl()));

            }
        });
        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent visionIntent = new Intent(mContext, DivisionDetails.class);
                visionIntent.putExtra("ID", currentDivision.getId());
                visionIntent.putExtra("IMAGE", currentDivision.getImageMemberUrl());
                visionIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                mContext.startActivity(visionIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // mEmptyStateTextView.setVisibility(mDivisionList.size() > 0 ? View.GONE : View.VISIBLE);
        return mDivisionList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView mNameMember;

        private TextView mMajorityMember;

        private ImageView mImageMember;

        private ConstraintLayout mConstraintLayout;


        Holder(View view) {
            super(view);

            mNameMember = view.findViewById(R.id.name_members);

            mMajorityMember = view.findViewById(R.id.majority_member);

            mImageMember = view.findViewById(R.id.member_photo);

            mConstraintLayout = view.findViewById(R.id.WholeConstraintLayout);

        }

       /* @Override
        public void onClick(View view) {

            int position = view.getId();
            switch (position) {
                case R.id.member_photo:
                    // Toast.makeText(mContext,mNameMember.getText().toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.division_card:
                    Toast.makeText(mContext, mMajorityMember.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;

            }
        }*/
    }
}

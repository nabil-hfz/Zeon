package com.example.volley.zeon.MenuActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volley.zeon.MainActivity;
import com.example.volley.zeon.R;
import com.example.volley.zeon.Util.Constants;
import com.example.volley.zeon.Util.UtilTools;
import com.squareup.picasso.Picasso;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;

/**
 * Author of this class is Louay .
 * <p>
 * Update this class Nabil in in 2018/03/11
 */

public class aboutUsActivity extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = aboutUsActivity.class.getSimpleName();
    /**
     * Progress Bar that for two sec
     */
    ProgressBar mSimpleProgressBar;

    public static final String TEAM_NAME = "ZEON TEAM";

    private TextView EmailContactUsPressed;

    private ImageView mZeonTeamImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        final Toolbar toolbar = findViewById(R.id.toolbar_aboutUs);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // First, hide loading indicator so error message will be visible
        mSimpleProgressBar = findViewById(R.id.simpleProgressBar);

        mSimpleProgressBar.setVisibility(View.GONE);

        mZeonTeamImage = findViewById(R.id.Zeon_team_image);

        // Picasso.get().load(Constants.ALL_TEAM_PHOTO_URL).fit().centerCrop().into(mZeonTeamImage);

        Picasso.get().load(Uri.parse(Constants.ALL_TEAM_PHOTO_URL)).fit().centerInside().into(mZeonTeamImage);
        //  Glide.with(this).load(Uri.parse(Constants.ALL_TEAM_PHOTO_URL)).apply(RequestOptions.centerCropTransform()).into(mZeonTeamImage);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setTitle(TEAM_NAME);

        openZeonImage();

        textViewContactUsPressedEmail();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Press back to exit", Toast.LENGTH_SHORT).show();
    }

    //TODO : Advanced ...
    // This method for : MainActivity needs to know which album we want to display.
    // On TrackActivity, we need to override onPrepareSupportNavigateUpTaskStack to edit the intent
    // that will start the parent activity when pressing Up: specially to use when get Notification
    // and get back to mainActivity instead of go back to phone .

    @Override
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onPrepareNavigateUpTaskStack(builder);
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        NavUtils.navigateUpTo(this, intent);
    }

    private void openZeonImage() {

        mZeonTeamImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(Constants.ALL_TEAM_PHOTO_URL), "image/*");
                startActivity(Intent.createChooser(intent, "Open [App] images"));
            }
        });

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }*/

    private void textViewContactUsPressedEmail() {

        EmailContactUsPressed = findViewById(R.id.contact_us_press);

        EmailContactUsPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = UtilTools.makeIntentOfEmail("https://www.facebook.com/MahmoudTrro");
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }
            }
        });
    }
}
package com.example.volley.zeon.MenuActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volley.zeon.MainActivity;
import com.example.volley.zeon.R;
import com.example.volley.zeon.Util.UtilTools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
    private ImageView zeonTeamImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_us);

        // First, hide loading indicator so error message will be visible
        mSimpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);

        mSimpleProgressBar.setVisibility(View.GONE);

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

        zeonTeamImage = (ImageView) findViewById(R.id.Zeon_team_image);

        zeonTeamImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.all_team);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

                File f = new File(Environment.getExternalStorageDirectory() + File.separator
                        + "test.jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Uri path = Uri.fromFile(f);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(path, "image/*");
                startActivity(intent);
            }
        });

    }

    private void textViewContactUsPressedEmail() {

        EmailContactUsPressed = (TextView) findViewById(R.id.contact_us_press);

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
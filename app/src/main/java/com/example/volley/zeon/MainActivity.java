package com.example.volley.zeon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.volley.zeon.MenuActivity.ContactActivity;
import com.example.volley.zeon.MenuActivity.DivisionActivity;
import com.example.volley.zeon.MenuActivity.FutureVisionActivity;
import com.example.volley.zeon.MenuActivity.ProjectActivity;
import com.example.volley.zeon.MenuActivity.aboutUsActivity;
import com.example.volley.zeon.Util.UtilTools;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * TEAM EMAIL for send the  messages to it .
     */
    public static final String T_EMAIL = "https://www.facebook.com/profile.php?id=100003729378979";

    private NavigationView mNavigationView;

    private DrawerLayout mDrawer;

    private ImageView mImageZeonHeaderMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawer.addDrawerListener(toggle);

        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);

        mNavigationView.setNavigationItemSelectedListener(this);

        imageZeonListener();
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        // Instead of all setChecked :: mNavigationView.setCheckedItem(item.getItemId());
        switch (item.getItemId()) {
            case (R.id.nav_main):
                item.setChecked(true);
                mDrawer.closeDrawer(GravityCompat.START);
                break;
            case (R.id.nav_division):
                item.setChecked(true);
                Intent divisionIntent = new Intent(MainActivity.this, DivisionActivity.class);
                startActivity(divisionIntent);
                break;
            case (R.id.nav_project):
                item.setChecked(true);
                Intent projectsIntent = new Intent(MainActivity.this, ProjectActivity.class);
                startActivity(projectsIntent);
                break;
            case (R.id.nav_vision):
                item.setChecked(true);
                Intent visionIntent = new Intent(MainActivity.this, FutureVisionActivity.class);
                startActivity(visionIntent);
                break;

            case (R.id.nav_contact_us):
                item.setChecked(true);
                Intent contactUsIntent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(contactUsIntent);
                break;

            case (R.id.nav_about_us):
                item.setChecked(true);
                Intent aboutUsIntent = new Intent(MainActivity.this, aboutUsActivity.class);
                startActivity(aboutUsIntent);
                break;

            default:
                item.setChecked(true);
                mDrawer.closeDrawer(GravityCompat.START);
                break;
        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void imageZeonListener() {

        View header = mNavigationView.getHeaderView(0);

        mImageZeonHeaderMain = (ImageView) header.findViewById(R.id.zeon_header_main);

        mImageZeonHeaderMain.setOnClickListener(new ZeonImageClickListener());

        mDrawer.closeDrawer(GravityCompat.START);
    }

    public class ZeonImageClickListener implements View.OnClickListener {

        public ZeonImageClickListener() {
        }

        @Override
        public void onClick(View view) {
            Intent intent = UtilTools.makeIntentOfEmail(MainActivity.T_EMAIL);
            if (intent.resolveActivity(getPackageManager()) != null) {
                Toast.makeText(MainActivity.this, R.string.Greeting_client, Toast.LENGTH_SHORT).show();
                startActivity(intent);
                mDrawer.closeDrawer(GravityCompat.START);
            }

        }
    }

}
    /*@Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
       // Instead of all setChecked :: mNavigationView.setCheckedItem(item.getItemId());
        switch (item.getItemId()) {
            case (R.id.nav_main):
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case (R.id.nav_division):
                item.setChecked(true);
                Intent divisionIntent = new Intent(MainActivity.this, DivisionActivity.class);
                startActivity(divisionIntent);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case (R.id.nav_project):
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START, false);
                Intent projectsIntent = new Intent(MainActivity.this, ProjectActivity.class);
                startActivity(projectsIntent);
                drawer.closeDrawer(Gravity.LEFT, false);
                return true;
            case (R.id.nav_vision):
                item.setChecked(true);
                Intent visionIntent = new Intent(MainActivity.this, FutureVisionActivity.class);
                startActivity(visionIntent);
                drawer.closeDrawers();
                return true;
            case (R.id.nav_contact_us):
                item.setChecked(true);
                Intent contactUsIntent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(contactUsIntent);
                drawer.closeDrawers();
                return true;
            case (R.id.nav_about_us):
                item.setChecked(true);
                Intent aboutUsIntent = new Intent(MainActivity.this, aboutUsActivity.class);
                startActivity(aboutUsIntent);
                drawer.closeDrawers();
                return true;
            default:
                item.setChecked(true);
                drawer.closeDrawers();
                return false;
        }
    }*/

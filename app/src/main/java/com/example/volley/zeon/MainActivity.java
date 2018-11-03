package com.example.volley.zeon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.volley.zeon.MenuActivity.ContactAvtivity;
import com.example.volley.zeon.MenuActivity.DivisionActivity;
import com.example.volley.zeon.MenuActivity.ProjectActivity;
import com.example.volley.zeon.MenuActivity.VisionActivity;
import com.example.volley.zeon.MenuActivity.aboutUsActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        switch (item.getItemId()) {
            case (R.id.nav_main):
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case (R.id.nav_division):
                Intent divisionIntent = new Intent(MainActivity.this, DivisionActivity.class);
                startActivity(divisionIntent);
                return true;
            case (R.id.nav_project):
                Intent projectsIntent = new Intent(MainActivity.this, ProjectActivity.class);
                startActivity(projectsIntent);
                return true;
            case (R.id.nav_vision):
                Intent visionIntent = new Intent(MainActivity.this, VisionActivity.class);
                startActivity(visionIntent);
                return true;
            case (R.id.nav_contact_us):
                Intent contactUsIntent = new Intent(MainActivity.this, ContactAvtivity.class);
                startActivity(contactUsIntent);
                return true;
            case (R.id.nav_about_us):
                Intent aboutUsIntent = new Intent(MainActivity.this, aboutUsActivity.class);
                startActivity(aboutUsIntent);
                return true;
            default:
                return false;
        }
    }
}

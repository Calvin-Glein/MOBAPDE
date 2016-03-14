package com.example.glenn.lathalanavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Publish extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final static String KEY_CURRENT_PHOTO = "current_photo";
    
    final static int REQUEST_PHOTO = 0;

    EditText etContent;
    Button btnSelectPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSelectPhoto = (Button) findViewById(R.id.bt_AddPhoto);
        etContent = (EditText) findViewById(R.id.et_Content);

        btnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String photoPath = "none";

                Intent explicit = new Intent();
                explicit.setClass(getBaseContext(), PhotoGallery.class);
                //add genderValue as an extra to explicit

                explicit.putExtra(KEY_CURRENT_PHOTO, photoPath);

                startActivityForResult(explicit, REQUEST_PHOTO);
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_PHOTO && resultCode == RESULT_OK)
        {
            String photoPath = data.getExtras().getString(KEY_CURRENT_PHOTO);
            etContent.setText(photoPath);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.report_bug) {
            Toast.makeText(this, "Bug Reported", Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.review){
            Toast.makeText(this, "You gave us 5 stars", Toast.LENGTH_LONG).show();

        }
        else if(id == R.id.recommend){
            Toast.makeText(this, "You just made us famous", Toast.LENGTH_LONG).show();

        }
        else if(id == R.id.sponsor){
            Toast.makeText(this, "Thanks for donating", Toast.LENGTH_LONG).show();

        }


            return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_facebook) {
            Intent intent = new Intent();

            intent.setClass(getBaseContext(), Facebook.class);

            startActivity(intent);
        } else if (id == R.id.nav_twitter) {
            Intent intent = new Intent();

            intent.setClass(getBaseContext(), Twitter.class);

            startActivity(intent);
        } else if (id == R.id.nav_instagram) {
            Intent intent = new Intent();

            intent.setClass(getBaseContext(), Instagram.class);

            startActivity(intent);

        } else if (id == R.id.nav_publish) {
//            Intent intent = new Intent();
//
//            intent.setClass(getBaseContext(), Home.class);
//            //add genderValue as an extra to explicit
//
//            startActivity(intent);

        } else if (id == R.id.nav_Logout) {
            Login.ACCOUNT_NAME=null;
            Login.ACCOUNT_NUMBER=-1;
            finish();
        } else if (id == R.id.nav_About) {
            Intent intent = new Intent();

            intent.setClass(getBaseContext(), About.class);

            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

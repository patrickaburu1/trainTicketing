package com.example.user.trainticketingsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.trainticketingsystem.Fragment.BookingRoute;
import com.example.user.trainticketingsystem.Fragment.ContactUs;
import com.example.user.trainticketingsystem.Fragment.FareDetails;
import com.example.user.trainticketingsystem.Fragment.Home;
import com.example.user.trainticketingsystem.Fragment.Routes;
import com.example.user.trainticketingsystem.Fragment.Schedule;
import com.example.user.trainticketingsystem.Fragment.TicketList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //to be done by joshua

//        TextView name= (TextView) findViewById(R.id.authName);
//        TextView email= (TextView) findViewById(R.id.authEmail);
//
//        SharedPreferences editorget=getSharedPreferences(LoginActivity.MYSHAREDPREFS, LoginActivity.MODE_PRIVATE);
//        String authfirstname= editorget.getString("firstname",null);
//        String authemail= editorget.getString("email",null);
//
//        name.setText(authfirstname);
//        email.setText(authemail);

        FragmentTransaction f=getSupportFragmentManager().beginTransaction();
        f.replace(R.id.mainPage, new Home(), getString(R.string.app_name));
        f.commit();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        if (id == R.id.logout) {

            SharedPreferences.Editor logout=getSharedPreferences(LoginActivity.MYSHAREDPREFS,MODE_PRIVATE).edit();
            logout.clear();
            logout.commit();

            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            f.replace(R.id.mainPage, new Home(), getString(R.string.app_name));
            f.commit();
        } else if (id == R.id.bookTrain) {
            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            f.replace(R.id.mainPage, new BookingRoute(), getString(R.string.app_name));
            f.commit();

        } else if (id == R.id.viewTickets) {
            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            f.replace(R.id.mainPage, new TicketList(), getString(R.string.app_name));
            f.commit();

        } else if (id == R.id.fareDetails) {
            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            f.replace(R.id.mainPage, new FareDetails(), getString(R.string.app_name));
            f.commit();

        } else if (id == R.id.routes) {
            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            f.replace(R.id.mainPage, new Routes(), getString(R.string.app_name));
            f.commit();

        }
        else if (id == R.id.trainSchedule) {
            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            f.replace(R.id.mainPage, new Schedule(), getString(R.string.app_name));
            f.commit();
        }

//          else if (id == R.id.cancelTicket) {
//
//        }
        else if (id == R.id.contactUs) {
            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            f.replace(R.id.mainPage, new ContactUs(), getString(R.string.app_name));
            f.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

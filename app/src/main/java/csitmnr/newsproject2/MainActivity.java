package csitmnr.newsproject2;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import csitmnr.newsproject2.Fragments.CalenderFragment;
import csitmnr.newsproject2.Fragments.DashboardFragment;
import csitmnr.newsproject2.Fragments.InternationalNewsFragment;
import csitmnr.newsproject2.Fragments.LocalNewsFragment;
import csitmnr.newsproject2.Fragments.ToolsFragment;

public class MainActivity extends AppCompatActivity /* Implementing DrawerActivity */ implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawer;
    FrameLayout container;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Putting toolbar here
        toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);

//Putting DrawerLayout here
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

//Inserting toggle button at DrawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

// StartView

        Fragment dashBoard = new DashboardFragment();
        manager = getSupportFragmentManager();
        manager
                .beginTransaction()
                .replace(R.id.container,dashBoard)
                .commit();
    }

    // For toolbar menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    // What happen while clicking on toolbar menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.setting) {

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    // What happen while clicking on Navigation menu items
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawer.closeDrawer(GravityCompat.START);

        Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.dashboard) {

//            fragment = new NewsAndEvents();
            fragment = new DashboardFragment();

        } else if (id == R.id.nav_local_news) {

            fragment = new LocalNewsFragment();

        } else if (id == R.id.nav_international_news) {

            fragment = new InternationalNewsFragment();

        } else if (id == R.id.nav_calender) {
            fragment = new CalenderFragment();

        } else if (id == R.id.nav_tools) {

            fragment = new ToolsFragment();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if (fragment != null) {

            manager.beginTransaction().replace(R.id.container, fragment).commit();
        }

        return true;

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

}

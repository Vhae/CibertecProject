package com.example.cibertecproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ListCoursesFragment.CourseListener {
    ActionBarDrawerToggle toggle;
    NavigationView navView;
    FragmentManager fragmentManager=  getSupportFragmentManager();
    final static String BUNDLE_KEY_ACTIVE_FRAGMENT="Active Fragment";

    int activeFragment=0;// para hacer hide al add de menu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        navView = findViewById(R.id.menu_view_nav);
        navView.setNavigationItemSelectedListener(this);

        if (savedInstanceState!=null){
            activeFragment=savedInstanceState.getInt(BUNDLE_KEY_ACTIVE_FRAGMENT,0);

        }else{
            ListEventsFragment listEventsFragment=new ListEventsFragment().newInstance();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.content_frame,listEventsFragment).commit();
            activeFragment=1;

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                switch (activeFragment){
                    case 1:
                        Intent createintent=new Intent(this,EventCreateEditActivity.class);
                        startActivity(createintent);
                        return true;
                    case 2:
                        Toast.makeText(this, "Click en expositor", Toast.LENGTH_SHORT).show();
                        return true;
                    case 3:
                        Toast.makeText(this, "Click en Curso", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar,menu);
        if(activeFragment==4){
            menu.getItem(0).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    protected void onStart() {
        super.onStart();
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId()){
            case R.id.list_events_nav:

                ListEventsFragment listEventsFragment=new ListEventsFragment().newInstance();
                FragmentTransaction fragmenteventsTransaction=fragmentManager.beginTransaction();
                fragmenteventsTransaction.replace(R.id.content_frame,listEventsFragment).addToBackStack(null).commit();
                activeFragment=1;
                this.invalidateOptionsMenu();
                break;
            case R.id.expositor_nav:

                ListExpositorsFragment listExpositorsFragment=new ListExpositorsFragment().newInstance();
                FragmentTransaction fragmentexpoTransaction=fragmentManager.beginTransaction();
                fragmentexpoTransaction.replace(R.id.content_frame,listExpositorsFragment).addToBackStack(null).commit();
                activeFragment=2;
                this.invalidateOptionsMenu();
                break;
            case R.id.course_nav:

                ListCoursesFragment listCoursesFragment=new ListCoursesFragment().newInstance();
                FragmentTransaction fragmentcoursesTransaction=fragmentManager.beginTransaction();
                fragmentcoursesTransaction.replace(R.id.content_frame,listCoursesFragment).addToBackStack(null).commit();
                activeFragment=3;
                this.invalidateOptionsMenu();
                break;
            case R.id.attendance_nav:
                ListAttendanceFragment listAttendanceFragment =new ListAttendanceFragment().newInstance();
                FragmentTransaction fragmentattenTransaction=fragmentManager.beginTransaction();
                fragmentattenTransaction.replace(R.id.content_frame,listAttendanceFragment).addToBackStack(null).commit();
                activeFragment=4;
                this.invalidateOptionsMenu();
                break;
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(BUNDLE_KEY_ACTIVE_FRAGMENT,activeFragment);
    }

    public void setCourseList(){
        ListCoursesFragment listCoursesFragment=new ListCoursesFragment().newInstance();
        FragmentTransaction fragmentcoursesTransaction=fragmentManager.beginTransaction();
        fragmentcoursesTransaction.replace(R.id.content_frame,listCoursesFragment).addToBackStack(null).commit();
    }


    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof ListCoursesFragment) {
            ListCoursesFragment courseListener = (ListCoursesFragment) fragment;
            courseListener.setCourseListener(this);
        }
    }

    @Override
    public void onCourseListener() {
        ListCoursesFragment listCoursesFragment=new ListCoursesFragment().newInstance();
        FragmentTransaction fragmentcoursesTransaction=fragmentManager.beginTransaction();
        fragmentcoursesTransaction.replace(R.id.content_frame,listCoursesFragment).addToBackStack(null).commit();
    }
}

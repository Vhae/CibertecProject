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

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.cibertecproject.Modelo.Event;
import com.google.android.material.navigation.NavigationView;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ActionBarDrawerToggle toggle;
    NavigationView navView;
    FragmentManager fragmentManager=  getSupportFragmentManager();
    final static String BUNDLE_KEY_ACTIVE_FRAGMENT="Active Fragment";
    public static final String CHANNEL_ID = "Canal de Notificaciones";

    int activeFragment=0;// parasaber que frament estamos y poder hacer hide al add de menu
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
            if(getIntent().hasExtra("origen") ){

                ListCoursesFragment listCoursesFragment=new ListCoursesFragment().newInstance();
                FragmentTransaction fragmentcoursesTransaction=fragmentManager.beginTransaction();
                fragmentcoursesTransaction.replace(R.id.content_frame,listCoursesFragment).addToBackStack(null).commit();
                activeFragment=3;//para saber que fragment estamos
                this.invalidateOptionsMenu();
            }else {
                ListEventsFragment listEventsFragment = new ListEventsFragment().newInstance();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.content_frame, listEventsFragment).commit();
                activeFragment = 1;
            }
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(CHANNEL_ID, "Canal de Notificaciones");
        }
        //creamos data y llenamos para probar la notificacion
        ArrayList<String> fechas=new ArrayList<String>();
        ArrayList<String> name= new ArrayList<String>();
        ArrayList<Event> idEvento=new ArrayList<Event>();
        Boolean probando=true;

        Event Item1=new Event("prueba","07-03-2020 00:59:00");
        idEvento.add(Item1);
        Event Item2=new Event("prueba2","06-03-2020 23:06:00");
        idEvento.add(Item2);

        int size=idEvento.size();//vemos el tamaño de la lista eventos

        for(int i=0;i<size;i++){//le damos los valores de la lista eventos a las otras dos listas para pasarlo por el intent al IntentService
            fechas.add(idEvento.get(i).getSchedule());
            name.add(idEvento.get(i).getName());
        }
        //hacemos un intent al IntentService y le mandamos la data
        Intent serviceIntent= new Intent(this,MyIntentService.class);
        serviceIntent.putStringArrayListExtra("fechas",fechas);
        serviceIntent.putStringArrayListExtra("name",name);
        startService(serviceIntent);// inicializamos el service
        //MyIntentService.running=false;//este comando es para detener el IntentService y despues inicializarlo de nuevo con la nueva data
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                switch (activeFragment){
                    case 1:
                        //opcion de menu para Fragment ListEvents
                        Intent createintent=new Intent(this,EventCreateEditActivity.class);
                        startActivity(createintent);
                        return true;
                    case 2:
                        //opcion de menu para Fragment ListExpositors
                        Toast.makeText(this, "Click en expositor", Toast.LENGTH_SHORT).show();
                        return true;
                    case 3:
                        //opcion de menu para Fragment ListCourses
                        Intent intent=new Intent(this,AddCourseEventActivity.class);
                        startActivity(intent);
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
                activeFragment=1;//para saber que fragment estamos
                this.invalidateOptionsMenu();
                break;
            case R.id.expositor_nav:

                ListExpositorsFragment listExpositorsFragment=new ListExpositorsFragment().newInstance();
                FragmentTransaction fragmentexpoTransaction=fragmentManager.beginTransaction();
                fragmentexpoTransaction.replace(R.id.content_frame,listExpositorsFragment).addToBackStack(null).commit();
                activeFragment=2;//para saber que fragment estamos
                this.invalidateOptionsMenu();
                break;
            case R.id.course_nav:

                ListCoursesFragment listCoursesFragment=new ListCoursesFragment().newInstance();
                FragmentTransaction fragmentcoursesTransaction=fragmentManager.beginTransaction();
                fragmentcoursesTransaction.replace(R.id.content_frame,listCoursesFragment).addToBackStack(null).commit();
                activeFragment=3;//para saber que fragment estamos
                this.invalidateOptionsMenu();
                break;
            case R.id.attendance_nav:
                ListAttendanceFragment listAttendanceFragment =new ListAttendanceFragment().newInstance();
                FragmentTransaction fragmentattenTransaction=fragmentManager.beginTransaction();
                fragmentattenTransaction.replace(R.id.content_frame,listAttendanceFragment).addToBackStack(null).commit();
                activeFragment=4;//para saber que fragment estamos
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
    private void createNotificationChannel(String id, String name){
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null){
            notificationManager.createNotificationChannel(
                    new NotificationChannel(
                            id,
                            name,
                            NotificationManager.IMPORTANCE_HIGH
                    )
            );
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

    }


}

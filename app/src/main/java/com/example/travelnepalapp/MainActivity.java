package com.example.travelnepalapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.travelnepalapp.API.PostAPI;
import com.example.travelnepalapp.Adapters.DashboardAdapter;
import com.example.travelnepalapp.Models.DashboardModel;
import com.example.travelnepalapp.Models.PostModel;
import com.example.travelnepalapp.Retrofit.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MenuItem menuItem;
    private String data = "";

    private RecyclerView recyclerAdapter;
    private List<DashboardModel> dashboardModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);


        toolbar = findViewById(R.id.toolbar);
        menuItem = findViewById(R.id.updateprofile);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerAdapter = findViewById(R.id.rv_recyclerview);
//        recyclerAdapter.setLayoutManager(new GridLayoutManager(this, 2));



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.updateprofile:
                Intent intent = new Intent(MainActivity.this, UpdateProfile.class);
                startActivity(intent);
                break;

            case R.id.addpost:
                Intent intent1 = new Intent(MainActivity.this, AddPost.class);
                startActivity(intent1);
                break;

            case R.id.dashboard:
                Intent intent2= new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent2);
                break;

        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);

        return true;
    }
}

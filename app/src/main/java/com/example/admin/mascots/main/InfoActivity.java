package com.example.admin.mascots.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.admin.mascots.R;

public class InfoActivity extends AppCompatActivity {

    public static final String MASCOT= "com.example.admin.mascots.main.KEY.MASCOT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


}

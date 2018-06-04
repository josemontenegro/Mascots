package com.example.admin.mascots.main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.mascots.R;
import com.example.admin.mascots.data.CurrentUser;
import com.example.admin.mascots.data.EmailSanitized;
import com.example.admin.mascots.data.Nodes;
import com.example.admin.mascots.login.LoginActivity;
import com.example.admin.mascots.models.Mascot;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    Mascot mascot = new Mascot();
    CurrentUser user = new CurrentUser();
    String emailSanitized = new EmailSanitized().emailSanitized(user.email());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_mascot);

                Button buttonSave = dialog.findViewById(R.id.saveBtn);
                buttonSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText editText = dialog.findViewById(R.id.nameMascotEt);
                        String name = editText.getText().toString();

                       String key = new Nodes().pending(emailSanitized).push().getKey();

                        mascot = new Mascot(name,"",key,emailSanitized);

                        if (name.trim().length() > 0) {

                            new Nodes().pending(emailSanitized).child(key).setValue(mascot);

                        }
                        dialog.dismiss();

                    }
                });

                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                dialog.show();
            }
        });

        TextView textView = findViewById(R.id.logoutTv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(MainActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                startActivity(intent);
                                MainActivity.this.finish();
                            }
                        });
            }
        });

        TextView userEmail = findViewById(R.id.activityTV);
        userEmail.setText(new CurrentUser().email());

    }
}

package com.example.admin.mascots.login;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.mascots.R;
import com.example.admin.mascots.data.CurrentUser;
import com.example.admin.mascots.main.MainActivity;
import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity  {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setContentView(R.layout.activity_login);
        if (new CurrentUser().getCurrentUser() != null) {
            logged();
        } else {
            signUp();
        }


        // new Presenter(LoginActivity.this).currentUser();
    }


    public void signUp() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build()//,
                                // new AuthUI.IdpConfig.PhoneBuilder().build(),
                                //new AuthUI.IdpConfig.GoogleBuilder().build()//,
                                //new AuthUI.IdpConfig.FacebookBuilder().build(),
                                // new AuthUI.IdpConfig.TwitterBuilder().build()
                        ))

                        //.setTheme(R.style.LoginTheme)
                       // .setLogo(R.mipmap.res)

                        .build(),
                RC_SIGN_IN);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RC_SIGN_IN == requestCode && RESULT_OK == resultCode){
            logged();
        }
    }


    public void logged() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}








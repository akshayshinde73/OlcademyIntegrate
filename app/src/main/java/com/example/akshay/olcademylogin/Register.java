package com.example.akshay.olcademylogin;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {
Button toLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toLogin=findViewById(R.id.btnLinkToLoginScreen);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();


    toLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(Register.this,MainActivity.class);
            startActivity(i);
        }
    });
    }
}

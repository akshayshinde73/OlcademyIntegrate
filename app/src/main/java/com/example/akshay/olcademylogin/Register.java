package com.example.akshay.olcademylogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Register extends AppCompatActivity {

    EditText user,passw,nameu;
    Button submit,display;
    private static final String Reg_URL="https://6tendulkar5.000webhostapp.com/Store.php";
Button toLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toLogin=findViewById(R.id.btnLinkToLoginScreen);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        nameu=findViewById(R.id.name);
        user=(EditText) findViewById(R.id.ed1);
        passw=(EditText) findViewById(R.id.ed2);
        submit=(Button) findViewById(R.id.bt1);
        //display=(Button) findViewById(R.id.bt2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                registerUser();
            }


        });
    toLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(Register.this,MainActivity.class);
            startActivity(i);
        }
    });
    }


    private void registerUser() {

        final String namecheck = nameu.getText().toString().trim();
        String name=user.getText().toString().trim();
        String password=passw.getText().toString().trim();

        if (TextUtils.isEmpty(namecheck)) {
            nameu.setError("Please enter username");
            nameu.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(name)) {
            user.setError("Please enter your email");
            user.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
            user.setError("Enter a valid email");
            user.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passw.setError("Enter a password");
            passw.requestFocus();
            return;
        }


        register(name,password);
    }
    private void register(String name,String pass)
    {

        String urlsuffix="?name="+name+"&pass="+pass;
        class RegisterUser extends AsyncTask<String,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                loading=ProgressDialog.show(Register.this,"Please wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPreExecute();
                Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
                loading.hide();
            }



            @Override
            protected String doInBackground(String... params) {

                String s=params[0];

                BufferedReader bufferReader=null;
                try {

                    URL url=new URL(Reg_URL+s);
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    bufferReader =new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result=bufferReader.readLine();
                    return result;
                }
                catch (Exception e)
                {
                    return null;
                }

            }
        }
        RegisterUser ur=new RegisterUser();
        ur.execute(urlsuffix);
    }

}

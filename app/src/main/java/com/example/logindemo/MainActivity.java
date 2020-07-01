package com.example.logindemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

     EditText Edit_Username;
      EditText Edit_Password;
      TextView Edit_btn;
      TextView Btn_sign;

    private static final String REGISTER_URL = "http://karlson/teda.uz/chek.php";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Edit_Username = (EditText)findViewById(R.id.username_input);
            Edit_Password = (EditText)findViewById(R.id.pass);
            Edit_btn = (TextView)findViewById(R.id.txtbtn);
            Btn_sign = (TextView)findViewById(R.id.Btn_sign);




            //login yani Edit_btn bosilganda ishga tushadigan kod qatori
            Edit_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerUser();
                }
            });


//            //login bosilgan payt ishga tushdi
//            Edit_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(MainActivity.this , SecondActivity.class);
//                startActivity(intent);
//            }  }  ) ;

    }

     private void registerUser(){
            String Username = Edit_Username.getText().toString().trim().toLowerCase();
            String Password = Edit_Password.getText().toString().trim().toLowerCase();
            register(Username ,  Password) ;
     }

     private void register(String Username , String Password) {

            String urlsuffix = "?username" + Username + "?password" + Password;
            class RegisterUser  extends AsyncTask<String , Void , String>{
                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(MainActivity.this  ,"Please wait" , null , true  ,true);
                }

                @Override
                protected void onPostExecute(String s){
                    super.onPostExecute(s);
                    Toast.makeText(getApplicationContext(), "Internetga ulanmagansiz..." , Toast.LENGTH_SHORT).show();
                }


                @Override
                protected String doInBackground(String... params) {
                   String s = params[0];
                    BufferedReader bufferedReader = null ;
                    try {
                        URL url = new URL(REGISTER_URL + s);
                        HttpURLConnection con = (HttpURLConnection)url.openConnection();
                        bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String result ;
                        result = bufferedReader.readLine();
                        return result ;
                    } catch (Exception e){
                        return null;
                    }

                }
            }
            RegisterUser ur  = new RegisterUser();
            ur.execute(urlsuffix);

     }
  }
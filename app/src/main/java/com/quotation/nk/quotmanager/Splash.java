package com.quotation.nk.quotmanager;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;



public class Splash extends AppCompatActivity {

   private static int SPLAH_TIME_OUT = 3000;
   private boolean InternetCheck = true;
   private ProgressBar spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        spinner = (ProgressBar)findViewById(R.id.progressbar1);
        spinner.setVisibility(View.VISIBLE);
        PostDelayMethod();


    }

    public void PostDelayMethod(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean InternetResult = checkConnection();
                if(InternetResult){
                    Intent intent = new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    spinner.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.GONE);
                    DialogAppear();
                }
            }
        },SPLAH_TIME_OUT);
    }
    public void DialogAppear()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Splash.this);
        builder.setTitle("Network Error");
        builder.setMessage("No Internet Connectivity");
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PostDelayMethod();
            }
        });
        builder.show();
    }

    protected  boolean isOnline(){
        ConnectivityManager  cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean checkConnection(){
        if(isOnline()){
            return InternetCheck;
        }
        else{
            InternetCheck= false;
            return InternetCheck;
        }
    }
}

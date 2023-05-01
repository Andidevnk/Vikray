package com.quotation.nk.quotmanager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Welcome_Activity extends AppCompatActivity {



    SharedPreferences sharedPreferences;
    CardView quotation_btn,reports_btn,exit_btn,enquirybtn,btn_stock,btn_vheiclelocate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_);


        quotation_btn = (CardView)findViewById(R.id.quotation);
        reports_btn = (CardView)findViewById(R.id.reports);
        exit_btn = (CardView)findViewById(R.id.exit);
        enquirybtn = (CardView)findViewById(R.id.enquiryquot);
        btn_stock = (CardView)findViewById(R.id.stockmange);
        btn_vheiclelocate = (CardView)findViewById(R.id.vehiclelocation);



        quotation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Activity.this,Quotation_Form.class);
                startActivity(intent);
            }
        });

        reports_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Welcome_Activity.this,Reports_View.class);
                startActivity(intent);
            }
        });


        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent i = new Intent(Welcome_Activity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        enquirybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Activity.this, Enquiry_Quot.class);
                startActivity(intent);
            }
        });

        btn_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Activity.this,StockActivity.class);
                startActivity(intent);
            }
        });

        btn_vheiclelocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Activity.this,Gatepass_Genrate_Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        final Dialog shippingDialog = new Dialog(Welcome_Activity.this);
        shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        shippingDialog.setContentView(R.layout.logoutscrren);
        shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        shippingDialog.setCancelable(true);
        shippingDialog.setCanceledOnTouchOutside(true);
        Button cancel = (Button)shippingDialog.findViewById(R.id.logoutcancel);
        Button done=(Button)shippingDialog.findViewById(R.id.donelogout);
        final TextView messagebox=(TextView)shippingDialog.findViewById(R.id.mesgebox);
        messagebox.setText(" Are you Sure you Want Logout this Screen!");



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shippingDialog.dismiss();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent i = new Intent(Welcome_Activity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        shippingDialog.show();


    }
}


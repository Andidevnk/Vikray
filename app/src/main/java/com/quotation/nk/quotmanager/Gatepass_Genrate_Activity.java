package com.quotation.nk.quotmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Gatepass_Genrate_Activity extends AppCompatActivity {

    CardView btn_gatepass,btn_sending,btn_received,btn_lacatevehical;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatepass_genrate);


        btn_gatepass = (CardView)findViewById(R.id.gatepass);
        btn_sending = (CardView)findViewById(R.id.sendinggoods);
        btn_received = (CardView)findViewById(R.id.receivedgoods);
        btn_lacatevehical = (CardView)findViewById(R.id.vehicleltd);




        btn_gatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gatepass_Genrate_Activity.this,GatePass_Activity.class);
                startActivity(intent);
            }
        });

        btn_sending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gatepass_Genrate_Activity.this,Sending_Gatepass_Activity.class);
                startActivity(intent);
            }
        });

        btn_received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gatepass_Genrate_Activity.this,Received_Gatepass_Activity.class);
                startActivity(intent);
            }
        });

        btn_lacatevehical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gatepass_Genrate_Activity.this,Vehicle_Locate_Activity.class);
                startActivity(intent);
            }
        });


    }

}

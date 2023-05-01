package com.quotation.nk.quotmanager;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.quotation.nk.quotmanager.Adapter.Compelete_Adapter;
import com.quotation.nk.quotmanager.Adapter.Update_Adapter;
import com.quotation.nk.quotmanager.Model.Compelete_Java;
import com.quotation.nk.quotmanager.Model.Update_java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Compelete_Status extends AppCompatActivity {


    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private ArrayList<Compelete_Java> compeleteJavas=new ArrayList<Compelete_Java>();
    Compelete_Adapter adapter;

    Button received_model;

    String  special_id,location_from,location_to,from_to,to_from,dates,mdata,fdata,edata;
    public Compelete_Status() {

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compelete__status);


        received_model = (Button)findViewById(R.id.recieveddata);


        compeleteJavas = new ArrayList<Compelete_Java>();
        recyclerView = (RecyclerView)findViewById(R.id.recycelcomplete);
        recyclerView.setLayoutManager(new LinearLayoutManager(Compelete_Status.this));


        adapter = new Compelete_Adapter(Compelete_Status.this,compeleteJavas);
        linearLayoutManager= new LinearLayoutManager(Compelete_Status.this);


        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final String test1 = userDetails.getString("Name", "");
        String test2 = userDetails.getString("Pwd", "");



        final Dialog shippingDialog = new Dialog(Compelete_Status.this);
        shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        shippingDialog.setContentView(R.layout.waitscreen);
        shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        shippingDialog.setCancelable(true);
        shippingDialog.setCanceledOnTouchOutside(false);

        shippingDialog.show();


        String qury = "http://loadcrm.com/quotationdiwali/api/CompletedStatusDetails/1?SalesmanID="+test1+"";

// String url="https://loadcrm.com/QMS/api/ExeQuery/1?qry=select%20Reason%20from%20DealayReason";
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, qury, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                shippingDialog.dismiss();

                Log.e("delaerdata", response.toString());
                try {
                    JSONArray jsonarray = new JSONArray(response);
                    if (jsonarray.length() > 0) {
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject obj = (JSONObject) jsonarray.get(i);


                            special_id= obj.getString("Id");
                            location_from = obj.getString("FromLocation");
                            location_to = obj.getString("ToLocation");
                            from_to = obj.getString("DeliveredBy");
                            to_from = obj.getString("ReceivedBy");
                            dates = obj.getString("EntryDate");
                            mdata = obj.getString("Model");
                            fdata = obj.getString("FrameNo");
                            edata = obj.getString("EngineNo");


                            Compelete_Java compeleteJava=new Compelete_Java();
                            compeleteJava.setId(special_id);
                            compeleteJava.setL_from(location_from);
                            compeleteJava.setL_to(location_to);
                            compeleteJava.setFrom(from_to);
                            compeleteJava.setTo(to_from);
                            compeleteJava.setDate(dates);
                            compeleteJava.setModell(mdata);
                            compeleteJava.setModelframe(fdata);
                            compeleteJava.setModelengine(edata);
                            compeleteJavas.add(compeleteJava);
                            adapter.notifyDataSetChanged();





                            //Toast.makeText(StockActivity.this, modelsnams, Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", error.toString());
            }
        });

        MainController.getInstance().getRequestQueue().getCache().clear();
        MainController.getInstance().addToRequestQueue(stringRequest1);


    }
}

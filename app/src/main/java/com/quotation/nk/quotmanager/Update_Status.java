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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.quotation.nk.quotmanager.Adapter.Adapter_Model;
import com.quotation.nk.quotmanager.Adapter.Update_Adapter;
import com.quotation.nk.quotmanager.Model.Model_java;
import com.quotation.nk.quotmanager.Model.Update_java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Update_Status extends AppCompatActivity {

    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private ArrayList<Update_java> updateJavas=new ArrayList<Update_java>();
    Update_Adapter adapter;

    Button received_model;


    String  special_id,location_from,location_to,from_to,to_from,dates,mdata,fdata,edata;
    public Update_Status() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__status);



        received_model = (Button)findViewById(R.id.recieveddata);


        updateJavas = new ArrayList<Update_java>();
        recyclerView = (RecyclerView)findViewById(R.id.recycleupdate);
        recyclerView.setLayoutManager(new LinearLayoutManager(Update_Status.this));



        adapter = new Update_Adapter(Update_Status.this,updateJavas);
        linearLayoutManager= new LinearLayoutManager(Update_Status.this);


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
        //Toast.makeText(this, test1+test2, Toast.LENGTH_SHORT).show();


        final Dialog shippingDialog = new Dialog(Update_Status.this);
        shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        shippingDialog.setContentView(R.layout.waitscreen);
        shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        shippingDialog.setCancelable(true);
        shippingDialog.setCanceledOnTouchOutside(false);

        shippingDialog.show();



        String qury = "http://loadcrm.com/quotationdiwali/api/PendingStatusDetailsCumUpdate/1?SalesmanID="+test1+"";

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


                            Update_java updateJava=new Update_java();
                            updateJava.setId(special_id);
                            updateJava.setL_from(location_from);
                            updateJava.setL_to(location_to);
                            updateJava.setFrom(from_to);
                            updateJava.setTo(to_from);
                            updateJava.setDate(dates);
                            updateJava.setModell(mdata);
                            updateJava.setModelframe(fdata);
                            updateJava.setModelengine(edata);
                            updateJavas.add(updateJava);
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

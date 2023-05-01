package com.quotation.nk.quotmanager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.quotation.nk.quotmanager.Adapter.Adapter_Model2;
import com.quotation.nk.quotmanager.Model.Full_Java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FullDetails_Models extends AppCompatActivity {

    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private ArrayList<Full_Java> fullJavas=new ArrayList<Full_Java>();
    Adapter_Model2 adapter;

    String m_model,e_engine,f_frame,d_date,c_color;
    public FullDetails_Models() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_full_details__models);

        recyclerView = (RecyclerView)findViewById(R.id.recyclefulldetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(FullDetails_Models.this));


        fullJavas = new ArrayList<Full_Java>();
        adapter = new Adapter_Model2(FullDetails_Models.this,fullJavas);
        linearLayoutManager= new LinearLayoutManager(FullDetails_Models.this);


        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        //   movieList = new ArrayList<com.smartworkshop.kuldeep.vsshonda.Movie>();
        // adapter = new MovieAdapter(TVScrren.this,movieList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);


        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final String test1 = userDetails.getString("Name", "");
        final String godown = userDetails.getString("godown", "");
        String test2 = userDetails.getString("Pwd", "");

        //Toast.makeText(this, test1+test2, Toast.LENGTH_SHORT).show();


       Intent intent = getIntent();

        String fName = intent.getStringExtra("Modelsn");
        fName=fName.replaceAll("\\s","%20");

        final Dialog shippingDialog = new Dialog(FullDetails_Models.this);
        shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        shippingDialog.setContentView(R.layout.waitscreen);
        shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        shippingDialog.setCancelable(true);
        shippingDialog.setCanceledOnTouchOutside(false);

        shippingDialog.show();


        String qury = "http://loadcrm.com/quotationdiwali/api/ModelDetails/1?SalesmanID="+test1+"&CurrentLocation="+godown+"&Model="+fName+"";

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


                            m_model= obj.getString("Model");
                            f_frame = obj.getString("FrameNo");
                            e_engine = obj.getString("EngineNo");
                            d_date = obj.getString("ManufacturingDate");
                            c_color = obj.getString("Color");
                            Full_Java fullJava=new Full_Java();
                            fullJava.setModelnm(m_model);
                            fullJava.setFrameno(f_frame);
                            fullJava.setEngineno(e_engine);
                            fullJava.setDatesupdate(d_date);
                            fullJava.setColornm(c_color);
                            fullJavas.add(fullJava);
                            adapter.notifyDataSetChanged();



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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

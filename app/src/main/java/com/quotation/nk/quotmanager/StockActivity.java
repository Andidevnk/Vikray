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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.quotation.nk.quotmanager.Adapter.Adapter_Model;
import com.quotation.nk.quotmanager.Model.Model_java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class StockActivity extends AppCompatActivity {


    TextView totalcounting_txt,txtonce;

    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private ArrayList<Model_java> modelJavas=new ArrayList<Model_java>();
    Adapter_Model adapter;

    String modelsnams,modelscounting;
    public StockActivity() {
        // Required empty public constructor
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);


        txtonce = (TextView)findViewById(R.id.once);
        totalcounting_txt  = (TextView)findViewById(R.id.totalcounting);



        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(StockActivity.this));


        modelJavas = new ArrayList<Model_java>();
        adapter = new Adapter_Model(StockActivity.this,modelJavas);
        linearLayoutManager= new LinearLayoutManager(StockActivity.this);


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
        String test2 = userDetails.getString("Pwd", "");
        //Toast.makeText(this, test1+test2, Toast.LENGTH_SHORT).show();


        final Dialog shippingDialog = new Dialog(StockActivity.this);
        shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        shippingDialog.setContentView(R.layout.waitscreen);
        shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        shippingDialog.setCancelable(true);
        shippingDialog.setCanceledOnTouchOutside(false);

        shippingDialog.show();




        String qury = "http://loadcrm.com/quotationdiwali/api/GodownVehicleCount/1?SalesmanID="+test1+"";

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


                            modelscounting= obj.getString("Column1");
                            modelsnams = obj.getString("CurrentLocation");
                            Model_java modelJava=new Model_java();
                            modelJava.setModel_qunatity(modelscounting);
                            modelJava.setModelname(modelsnams);
                            modelJavas.add(modelJava);
                            adapter.notifyDataSetChanged();


                            final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref1", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("lOCATION", modelsnams);

                            editor.commit();
                            editor.apply();
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



        String qury1 = "http://loadcrm.com/quotationdiwali/api/VehicleCount/1?SalesmanID="+test1+"";

// String url="https://loadcrm.com/QMS/api/ExeQuery/1?qry=select%20Reason%20from%20DealayReason";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, qury1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                shippingDialog.dismiss();
                Log.e("delaerdata", response.toString());
                try {
                    JSONArray jsonarray = new JSONArray(response);
                    if (jsonarray.length() > 0) {
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject obj = (JSONObject) jsonarray.get(i);


                            totalcounting_txt.setText(obj.getString("Column1"));
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
        MainController.getInstance().addToRequestQueue(stringRequest);




    }


}

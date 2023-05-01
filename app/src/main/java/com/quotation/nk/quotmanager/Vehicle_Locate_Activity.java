package com.quotation.nk.quotmanager;

import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.quotation.nk.quotmanager.Adapter.Location_Adapter;
import com.quotation.nk.quotmanager.Model.Location_java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Vehicle_Locate_Activity extends AppCompatActivity {

    Spinner spin_model,spin_frame,spin_engine;
    Button btn_search;

    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    public Vehicle_Locate_Activity() {
        // Required empty public constructor
    }
    private ArrayList<String> getssx = new ArrayList<String>();
    private ArrayList<String> getssx1 = new ArrayList<String>();
    private ArrayList<String> getssx2 = new ArrayList<String>();

    private ArrayList<Location_java> locationJavas=new ArrayList<Location_java>();

    String godownnsmr,hidtory;


    Location_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle__locate_);


        recyclerView = (RecyclerView)findViewById(R.id.locationrecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(Vehicle_Locate_Activity.this));


        locationJavas = new ArrayList<Location_java>();
        adapter = new Location_Adapter(Vehicle_Locate_Activity.this,locationJavas);
        linearLayoutManager= new LinearLayoutManager(Vehicle_Locate_Activity.this);


        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        //   movieList = new ArrayList<com.smartworkshop.kuldeep.vsshonda.Movie>();
        // adapter = new MovieAdapter(TVScrren.this,movieList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);



        spin_model = (Spinner)findViewById(R.id.serachmodel_spiner);
        spin_frame = (Spinner)findViewById(R.id.searchmodel_frameno);
        spin_engine = (Spinner)findViewById(R.id.searchmodel_enginemno);


        btn_search = (Button)findViewById(R.id.search_btn);


        SharedPreferences md = getApplicationContext().getSharedPreferences("MyPref1", MODE_PRIVATE);
         final String td = md.getString("modelselect","");

        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final String test1 = userDetails.getString("Name", "");
        String test2 = userDetails.getString("Pwd", "");




        final Dialog shippingDialog = new Dialog(Vehicle_Locate_Activity.this);
        shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        shippingDialog.setContentView(R.layout.waitscreen);
        shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        shippingDialog.setCancelable(true);
        shippingDialog.setCanceledOnTouchOutside(false);

        shippingDialog.show();


        String qury1 = "http://loadcrm.com/quotationdiwali/api/ModelCheck/1?SalesmanID="+test1+"";

// String url="https://loadcrm.com/QMS/api/ExeQuery/1?qry=select%20Reason%20from%20DealayReason";
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, qury1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                shippingDialog.dismiss();
                Log.e("delaerdata", response.toString());
                try {
                    JSONArray jsonarray = new JSONArray(response);
                    if (jsonarray.length() > 0) {
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject obj = (JSONObject) jsonarray.get(i);

                            getssx.add(obj.getString("model").toString());
                            String ss = (obj.getString("model"));


                            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getssx);

// Drop down layout style - list view with radio button
                            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// attaching data adapter to spinner
                            spin_model.setAdapter(dataAdapter1);

                            spin_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                    final Dialog shippingDialog = new Dialog(Vehicle_Locate_Activity.this);
                                    shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    shippingDialog.setContentView(R.layout.waitscreen);
                                    shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                                    shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    shippingDialog.setCancelable(true);
                                    shippingDialog.setCanceledOnTouchOutside(false);

                                    shippingDialog.show();
                                    String models = spin_model.getSelectedItem().toString().trim();
                                    models = models.replaceAll("\\s", "%20");


                                    getssx1.clear();
                                    getssx1.removeAll(getssx1);
                                    String qury1 = "http://loadcrm.com/quotationdiwali/api/SelectedFrameNo/1?SalesmanID="+test1+"&Model="+models+"";

// String url="https://loadcrm.com/QMS/api/ExeQuery/1?qry=select%20Reason%20from%20DealayReason";
                                    StringRequest stringRequest1 = new StringRequest(Request.Method.GET, qury1, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {


                                            shippingDialog.dismiss();
                                            Log.e("delaerdata", response.toString());
                                            try {
                                                JSONArray jsonarray = new JSONArray(response);
                                                if (jsonarray.length() > 0) {
                                                    for (int i = 0; i < jsonarray.length(); i++) {
                                                        JSONObject obj = (JSONObject) jsonarray.get(i);

                                                        getssx1.add(obj.getString("FrameNo").toString());
                                                        String ss = (obj.getString("FrameNo"));


                                                        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getssx1);

// Drop down layout style - list view with radio button
                                                        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// attaching data adapter to spinner
                                                        spin_frame.setAdapter(dataAdapter1);

                                                        spin_frame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                                final Dialog shippingDialog = new Dialog(Vehicle_Locate_Activity.this);
                                                                shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                                shippingDialog.setContentView(R.layout.waitscreen);
                                                                shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                                                                shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                                shippingDialog.setCancelable(true);
                                                                shippingDialog.setCanceledOnTouchOutside(false);

                                                                shippingDialog.show();
                                                                String frame = spin_frame.getSelectedItem().toString().trim();
                                                                frame = frame.replaceAll("\\s", "%20");



                                                                getssx2.clear();
                                                                getssx2.removeAll(getssx2);
                                                                String qury1 = "http://loadcrm.com/quotationdiwali/api/EngineNo/1?FrameNo="+frame+"";

// String url="https://loadcrm.com/QMS/api/ExeQuery/1?qry=select%20Reason%20from%20DealayReason";
                                                                StringRequest stringRequest1 = new StringRequest(Request.Method.GET, qury1, new Response.Listener<String>() {
                                                                    @Override
                                                                    public void onResponse(String response) {

                                                                        shippingDialog.dismiss();
                                                                        Log.e("delaerdata", response.toString());
                                                                        try {
                                                                            JSONArray jsonarray = new JSONArray(response);
                                                                            if (jsonarray.length() > 0) {
                                                                                for (int i = 0; i < jsonarray.length(); i++) {
                                                                                    JSONObject obj = (JSONObject) jsonarray.get(i);

                                                                                    getssx2.add(obj.getString("EngineNo").toString());
                                                                                    String ss = (obj.getString("EngineNo"));


                                                                                    ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getssx2);

// Drop down layout style - list view with radio button
                                                                                    dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// attaching data adapter to spinner
                                                                                    spin_engine.setAdapter(dataAdapter1);

                                                                                    spin_engine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                        @Override
                                                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                                            if (!spin_engine.getSelectedItem().toString().equalsIgnoreCase("No Time")) {

                                                                                                String ss = spin_engine.getItemAtPosition(position).toString();


                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onNothingSelected(AdapterView<?> parent) {

                                                                                        }
                                                                                    });
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
                                                            public void onNothingSelected(AdapterView<?> parent) {

                                                            }
                                                        });
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
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
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


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog shippingDialog = new Dialog(Vehicle_Locate_Activity.this);
                shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                shippingDialog.setContentView(R.layout.waitscreen);
                shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                shippingDialog.setCancelable(true);
                shippingDialog.setCanceledOnTouchOutside(false);

                shippingDialog.show();

                String framnumber = spin_frame.getSelectedItem().toString().trim();

                String qury = "http://loadcrm.com/quotationdiwali/api/LocationStatus/1?SalesmanID="+test1+"&FrameNo="+framnumber+"";

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


                                    godownnsmr= obj.getString("History");
                                    hidtory = obj.getString("Date");


                                    //Toast.makeText(Vehicle_Locate_Activity.this, godownnsmr, Toast.LENGTH_SHORT).show();

                                    //Toast.makeText(Vehicle_Locate_Activity.this, hidtory, Toast.LENGTH_SHORT).show();

                                    Location_java locationJava=new Location_java();
                                    locationJava.setName_godown(godownnsmr);
                                    locationJava.setHistorydate(hidtory);
                                    locationJavas.add(locationJava);
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
        });

    }
}

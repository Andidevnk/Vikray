package com.quotation.nk.quotmanager;

import android.app.Dialog;
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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.quotation.nk.quotmanager.Adapter.Adapter;
import com.quotation.nk.quotmanager.Model.Reports;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GatePass_Activity extends AppCompatActivity {


    RecyclerView list_recycle_select;
    Spinner spin_from_location,spin_to_location,spin_delivered_by,spin_delivered_to,spin_model_list,spin_frame_list,spin_engine_list;
    Button btn_submit_list;
    ImageButton btn_add_list;


    EditText edit_transported_name;
    Adapter adapters;
    private ArrayList<String> getssx = new ArrayList<String>();
    private ArrayList<String> getssx1 = new ArrayList<String>();
    private ArrayList<String> getssx2 = new ArrayList<String>();
    private ArrayList<String> getssx3 = new ArrayList<String>();
    private ArrayList<String> getssx4 = new ArrayList<String>();
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Reports> as=new ArrayList<Reports>();
    private DividerItemDecoration dividerItemDecoration;
    private ArrayList<String> getssx5 = new ArrayList<String>();
    private ArrayList<String> getssx6 = new ArrayList<String>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_gate_pass_);


        spin_from_location = (Spinner)findViewById(R.id.locationfrom_spiner);
        spin_to_location = (Spinner)findViewById(R.id.locationto_spiner);
        spin_delivered_by = (Spinner)findViewById(R.id.deliveredby_spiner);
        spin_delivered_to = (Spinner)findViewById(R.id.deliveredto_spiner);
        spin_model_list = (Spinner)findViewById(R.id.modelslist_spiner);
        spin_frame_list = (Spinner)findViewById(R.id.frame_list_spiner);
        spin_engine_list = (Spinner)findViewById(R.id.engine_list_spiner);

        edit_transported_name = (EditText)findViewById(R.id.transportedname);


        btn_add_list = (ImageButton)findViewById(R.id.adding_lits_btn);
        btn_submit_list = (Button)findViewById(R.id.submit_list);

        list_recycle_select = (RecyclerView)findViewById(R.id.select_list_recycle);


        Intent intent = getIntent();

        final String mname = intent.getStringExtra("movie_id_key");
        final String fname = intent.getStringExtra("movie_rating_key");
        final String ename = intent.getStringExtra("movie_release_date_key");





        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final String test1 = userDetails.getString("Name", "");
        String test2 = userDetails.getString("Pwd", "");



        final Dialog shippingDialog = new Dialog(GatePass_Activity.this);
        shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        shippingDialog.setContentView(R.layout.waitscreen);
        shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        shippingDialog.setCancelable(true);
        shippingDialog.setCanceledOnTouchOutside(false);

        shippingDialog.show();


        getssx.clear();
        getssx.removeAll(getssx);
        String qury = "http://loadcrm.com/quotationdiwali/api/DealersGodown/1?SalesmanID="+test1+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, qury, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                shippingDialog.dismiss();
                Log.e("delaerdata", response.toString());
                try {
                    JSONArray jsonarray = new JSONArray(response);
                    if (jsonarray.length() > 0) {
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject obj = (JSONObject) jsonarray.get(i);

                            getssx.add(obj.getString("GodownName").toString());
                            String ss = (obj.getString("GodownName"));

                            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getssx);

// Drop down layout style - list view with radio button
                            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// attaching data adapter to spinner
                            spin_from_location.setAdapter(dataAdapter1);

                            spin_from_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                    final Dialog shippingDialog = new Dialog(GatePass_Activity.this);
                                    shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    shippingDialog.setContentView(R.layout.waitscreen);
                                    shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                                    shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    shippingDialog.setCancelable(true);
                                    shippingDialog.setCanceledOnTouchOutside(false);

                                    shippingDialog.show();
                                    String Selectlocation = spin_from_location.getSelectedItem().toString().trim();
                                    Selectlocation = Selectlocation.replaceAll("\\s", "%20");
                                   // Toast.makeText(getApplicationContext(), Selectlocation, Toast.LENGTH_SHORT).show();

                                    getssx4.clear();
                                    getssx4.removeAll(getssx4);
                                    String qury1 = "http://loadcrm.com/quotationdiwali/api/GodownModels/1?SalesmanID="+test1+"&CurrentLocation="+Selectlocation+"";

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

                                                        getssx4.add(obj.getString("Model").toString());
                                                        String ss = (obj.getString("Model"));




                                                        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getssx4);

// Drop down layout style - list view with radio button
                                                        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// attaching data adapter to spinner
                                                        spin_model_list.setAdapter(dataAdapter1);

                                                        spin_model_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                                final Dialog shippingDialog = new Dialog(GatePass_Activity.this);
                                                                shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                                shippingDialog.setContentView(R.layout.waitscreen);
                                                                shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                                                                shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                                shippingDialog.setCancelable(true);
                                                                shippingDialog.setCanceledOnTouchOutside(false);

                                                                shippingDialog.show();

                                                                String Selectlocation = spin_from_location.getSelectedItem().toString().trim();
                                                                Selectlocation = Selectlocation.replaceAll("\\s", "%20");


                                                               // Toast.makeText(GatePass_Activity.this, Selectlocation, Toast.LENGTH_SHORT).show();


                                                                String Selectmodellist = spin_model_list.getSelectedItem().toString().trim();
                                                                Selectmodellist = Selectmodellist.replaceAll("\\s", "%20");

                                                               // Toast.makeText(GatePass_Activity.this, Selectmodellist, Toast.LENGTH_SHORT).show();
                                                              //  Toast.makeText(getApplicationContext(), Selectmodellist, Toast.LENGTH_SHORT).show();


                                                                getssx5.clear();
                                                                getssx5.removeAll(getssx5);
                                                                String qury1 = "http://loadcrm.com/quotationdiwali/api/FrameNo/1?SalesmanID="+test1+"&CurrentLocation="+ Selectlocation +"&Model="+Selectmodellist+"";

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

                                                                                    getssx5.add(obj.getString("FrameNo").toString());
                                                                                    String ss = (obj.getString("FrameNo"));


                                                                                    ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getssx5);

// Drop down layout style - list view with radio button
                                                                                    dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// attaching data adapter to spinner
                                                                                    spin_frame_list.setAdapter(dataAdapter1);

                                                                                    spin_frame_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                        @Override
                                                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                                                            final Dialog shippingDialog = new Dialog(GatePass_Activity.this);
                                                                                            shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                                                            shippingDialog.setContentView(R.layout.waitscreen);
                                                                                            shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                                                                                            shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                                                            shippingDialog.setCancelable(true);
                                                                                            shippingDialog.setCanceledOnTouchOutside(false);

                                                                                            shippingDialog.show();
                                                                                            String Selectframelist = spin_frame_list.getSelectedItem().toString().trim();
                                                                                            Selectframelist = Selectframelist.replaceAll("\\s", "%20");

                                                                                           // Toast.makeText(GatePass_Activity.this, Selectframelist, Toast.LENGTH_SHORT).show();
                                                                                           // Toast.makeText(getApplicationContext(), Selectframelist, Toast.LENGTH_SHORT).show();


                                                                                            getssx6.clear();
                                                                                            getssx6.removeAll(getssx6);
                                                                                            String qury1 = "http://loadcrm.com/quotationdiwali/api/EngineNo/1?FrameNo="+Selectframelist+"";

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

                                                                                                                getssx6.add(obj.getString("EngineNo").toString());
                                                                                                                String ss = (obj.getString("EngineNo"));


                                                                                                                ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getssx6);

// Drop down layout style - list view with radio button
                                                                                                                dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// attaching data adapter to spinner
                                                                                                                spin_engine_list.setAdapter(dataAdapter1);

                                                                                                                spin_engine_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                    @Override
                                                                                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                                                                        if (!spin_engine_list.getSelectedItem().toString().equalsIgnoreCase("No Time")) {

                                                                                                                            String ss = spin_engine_list.getItemAtPosition(position).toString();


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
        MainController.getInstance().addToRequestQueue(stringRequest);

        String qury1 = "http://loadcrm.com/quotationdiwali/api/Salesman/1?SalesmanID="+test1+"";

// String url="https://loadcrm.com/QMS/api/ExeQuery/1?qry=select%20Reason%20from%20DealayReason";
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, qury1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("delaerdata", response.toString());
                try {
                    JSONArray jsonarray = new JSONArray(response);
                    if (jsonarray.length() > 0) {
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject obj = (JSONObject) jsonarray.get(i);

                            getssx1.add(obj.getString("Salesman").toString());
                            String ss = (obj.getString("Salesman"));


                            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getssx1);

// Drop down layout style - list view with radio button
                            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// attaching data adapter to spinner
                            spin_delivered_by.setAdapter(dataAdapter1);

                            spin_delivered_by.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (!spin_delivered_by.getSelectedItem().toString().equalsIgnoreCase("No Time")) {

                                        String ss = spin_delivered_by.getItemAtPosition(position).toString();


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


        String qury2 = "http://loadcrm.com/quotationdiwali/api/DealersGodown/1?SalesmanID="+test1+"";

// String url="https://loadcrm.com/QMS/api/ExeQuery/1?qry=select%20Reason%20from%20DealayReason";
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, qury2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("delaerdata", response.toString());
                try {
                    JSONArray jsonarray = new JSONArray(response);
                    if (jsonarray.length() > 0) {
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject obj = (JSONObject) jsonarray.get(i);

                            getssx2.add(obj.getString("GodownName").toString());
                            String ss = (obj.getString("GodownName"));


                            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getssx2);

// Drop down layout style - list view with radio button
                            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// attaching data adapter to spinner
                            spin_to_location.setAdapter(dataAdapter1);

                            spin_to_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (!spin_to_location.getSelectedItem().toString().equalsIgnoreCase("No Time")) {

                                        String ss = spin_to_location.getItemAtPosition(position).toString();


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
        MainController.getInstance().addToRequestQueue(stringRequest2);


        String qury3 = "http://loadcrm.com/quotationdiwali/api/Salesman/1?SalesmanID="+test1+"";

// String url="https://loadcrm.com/QMS/api/ExeQuery/1?qry=select%20Reason%20from%20DealayReason";
        StringRequest stringRequest3 = new StringRequest(Request.Method.GET, qury3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("delaerdata", response.toString());
                try {
                    JSONArray jsonarray = new JSONArray(response);
                    if (jsonarray.length() > 0) {
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject obj = (JSONObject) jsonarray.get(i);

                            getssx3.add(obj.getString("Salesman").toString());
                            String ss = (obj.getString("Salesman"));


                            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getssx3);

// Drop down layout style - list view with radio button
                            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// attaching data adapter to spinner
                            spin_delivered_to.setAdapter(dataAdapter1);

                            spin_delivered_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (!spin_delivered_to.getSelectedItem().toString().equalsIgnoreCase("No Time")) {

                                        String ss = spin_delivered_to.getItemAtPosition(position).toString();


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
        MainController.getInstance().addToRequestQueue(stringRequest3);


        btn_add_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                adapters = new Adapter(getApplicationContext(),as);
                linearLayoutManager= new LinearLayoutManager(getApplicationContext());


                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                dividerItemDecoration = new DividerItemDecoration(list_recycle_select.getContext(), linearLayoutManager.getOrientation());

                list_recycle_select.setHasFixedSize(true);
                list_recycle_select.setLayoutManager(linearLayoutManager);
                list_recycle_select.addItemDecoration(dividerItemDecoration);
                list_recycle_select.setAdapter(adapters);
                Reports reports=new Reports();

                reports.setId(spin_model_list.getSelectedItem().toString());
                reports.setCustomer_name(spin_frame_list.getSelectedItem().toString());
                reports.setQua_date(spin_engine_list.getSelectedItem().toString());
                as.add(reports);


                String from = spin_from_location.getSelectedItem().toString().trim();
                from = from.replaceAll("\\s", "%20");
                String to = spin_to_location.getSelectedItem().toString().trim();
                to = to.replaceAll("\\s", "%20");
                String delivered_from = spin_delivered_by.getSelectedItem().toString().trim();
                delivered_from = delivered_from.replaceAll("\\s", "%20");
                String delivered_to = spin_delivered_to.getSelectedItem().toString().trim();
                delivered_to = delivered_to.replaceAll("\\s", "%20");
                String transportedby = edit_transported_name.getText().toString();
                transportedby = transportedby.replaceAll("\\s", "%20");

                String models = spin_model_list.getSelectedItem().toString().trim();
                models = models.replaceAll("\\s", "%20");
                String framename  = spin_frame_list.getSelectedItem().toString().trim();
                framename = framename.replaceAll("\\s", "%20");
                String enginename = spin_engine_list.getSelectedItem().toString().trim();
                enginename = enginename.replaceAll("\\s", "%20");


                String url = "http://loadcrm.com/quotationdiwali/api/Gatepass/1?SalesmanID="+test1+"&FromLocation="+from+"&ToLocation="+to+"" +
                        "&DeleverdBy="+delivered_from+"&DeleveredTo="+delivered_to+"&Transporter="+transportedby+"&Model="+mname+"&FrameNo="+fname+"&EngineNo="+ename+"" +
                        "&Qty="+10+"&SpecialFrameNo="+framename+"";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response.toString());
                        Log.e("Mysuccess", "" + response);
                       // Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonobject = new JSONObject(response);
                            if (jsonobject != null) {
                                String Message = jsonobject.getString("Message");
                                String Error = jsonobject.getString("Error");
                                if (Error.equalsIgnoreCase(null) || Error.equalsIgnoreCase("null")) {
                                    Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), Error, Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                MainController.getInstance().addToRequestQueue(stringRequest);
                MainController.getInstance().getRequestQueue().getCache().clear();





                Toast.makeText(GatePass_Activity.this, "saved", Toast.LENGTH_SHORT).show();
                adapters.notifyDataSetChanged();
            }
        });

        btn_submit_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(GatePass_Activity.this, "Gatepass Genrated", Toast.LENGTH_SHORT).show();
            }
        });

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

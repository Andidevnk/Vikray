package com.quotation.nk.quotmanager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class Quotation_Form extends AppCompatActivity {


    Spinner models_sp;
    EditText edit_customer_name, edit_mobile_number, edit_email, edit_shotaddres, edit_discount;
    Button submit_btn, submitback;


    private ArrayList<String> getssx = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation__form);
        models_sp = (Spinner) findViewById(R.id.model_spinner);
        submit_btn = (Button) findViewById(R.id.submit);
        submitback = (Button) findViewById(R.id.backdash);
        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final String test1 = userDetails.getString("Name", "");
        String test2 = userDetails.getString("Pwd", "");
        //Toast.makeText(this, test1+test2, Toast.LENGTH_SHORT).show();



        final Dialog shippingDialog = new Dialog(Quotation_Form.this);
        shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        shippingDialog.setContentView(R.layout.waitscreen);
        shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        shippingDialog.setCancelable(true);
        shippingDialog.setCanceledOnTouchOutside(false);

        shippingDialog.show();

        String qury = "http://loadcrm.com/quotationdiwali/api/VehicleModel/1?SalesManID="+test1+"";

// String url="https://loadcrm.com/QMS/api/ExeQuery/1?qry=select%20Reason%20from%20DealayReason";
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

                            getssx.add(obj.getString("MODEL").toString());
                            String ss = (obj.getString("MODEL"));


                            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getssx);

// Drop down layout style - list view with radio button
                            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// attaching data adapter to spinner
                            models_sp.setAdapter(dataAdapter1);

                            models_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (!models_sp.getSelectedItem().toString().equalsIgnoreCase("No Time")) {

                                        String ss = models_sp.getItemAtPosition(position).toString();


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
        MainController.getInstance().addToRequestQueue(stringRequest);


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                edit_customer_name = (EditText) findViewById(R.id.customername);
                edit_mobile_number = (EditText) findViewById(R.id.mobilenumber);
                edit_email = (EditText) findViewById(R.id.email);
                edit_shotaddres = (EditText) findViewById(R.id.shortaddress);
                edit_discount = (EditText) findViewById(R.id.discount);
                String customername = edit_customer_name.getText().toString();
                String phonenumber = edit_mobile_number.getText().toString();
                String emailaddress = edit_email.getText().toString();
                String addresss = edit_shotaddres.getText().toString();
                String discountedrate = edit_discount.getText().toString();
                String modelllss = models_sp.getSelectedItem().toString().trim();
                modelllss = modelllss.replaceAll("\\s", "%20");




                String url = "http://loadcrm.com/quotationdiwali/api/QuotationDetails/1?SalesManID=" + test1 + "&CustomerName=" + customername + "&MobileNo=" + phonenumber + "&EmailID=" + emailaddress + "&ShortAddress=" + addresss + "&VehicleModel=" + modelllss + "&Offer=" + discountedrate + "";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {



                        Log.d("Response", response.toString());

                        Log.e("Mysuccess", "" + response);

                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

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

                edit_customer_name.setText("");
                edit_mobile_number.setText("");
                edit_email.setText("");
                edit_shotaddres.setText("");
                edit_discount.setText("");


                Toast.makeText(Quotation_Form.this, "Quotation Generated", Toast.LENGTH_SHORT).show();




//               Toast.makeText(getApplicationContext(), "Quotation Sending....", Toast.LENGTH_SHORT).show();


            }
        });
        submitback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog shippingDialog = new Dialog(Quotation_Form.this);
                shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                shippingDialog.setContentView(R.layout.waitscreen);
                shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                shippingDialog.setCancelable(true);
                shippingDialog.setCanceledOnTouchOutside(false);
                shippingDialog.show();

                Intent i = new Intent(Quotation_Form.this,Welcome_Activity.class);
                startActivity(i);

                shippingDialog.dismiss();

            }
        });
    }
}
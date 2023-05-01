package com.quotation.nk.quotmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.quotation.nk.quotmanager.Model.CallDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

public class Booking_Status extends AppCompatActivity implements View.OnClickListener{
    TextView c_name,m_name,p_number,e_adrresss;
    TextView txt_remarks_spineer,txt_remarks;
    Spinner statusspinner,reason_spineer;
    LinearLayout layout1,layout2;
    EditText edit_date,edit_time;
    Button status_btn,date,time;
    EditText edit_remarks;
    int war2;
    String getdata,CustomerName,VehicleModel,MobileNo,EmailID;
    private List<CallDetails> callDetails;
    int hoours,minutes,ye,mon,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_booking__status);


        statusspinner = (Spinner)findViewById(R.id.statusspinner);
        reason_spineer = (Spinner)findViewById(R.id.reasoonsspinner);
        c_name = (TextView)findViewById(R.id.name);
        m_name = (TextView)findViewById(R.id.model);
        p_number = (TextView)findViewById(R.id.phone_number);
        e_adrresss = (TextView)findViewById(R.id.e_address);
        edit_date = (EditText)findViewById(R.id.deliverydate);
        edit_time  = (EditText)findViewById(R.id.deliverytime);
        date = (Button)findViewById(R.id.date);
        time = (Button)findViewById(R.id.time);
        edit_remarks = (EditText)findViewById(R.id.remarkss);

        status_btn =(Button)findViewById(R.id.submitstatus);

        txt_remarks_spineer = (TextView)findViewById(R.id.spiner);
        txt_remarks = (TextView)findViewById(R.id.txtremarksnotintrested);

        layout1 = (LinearLayout)findViewById(R.id.layout11);
        layout2 = (LinearLayout)findViewById(R.id.layout22);


        time.setOnClickListener(this);
        date.setOnClickListener(this);


        getdata = getIntent().getStringExtra("idd");

        String qury="http://loadcrm.com/quotationdiwali/api/CustomerDetails/1?CustomerName="+getdata;

// String url="https://loadcrm.com/QMS/api/ExeQuery/1?qry=select%20Reason%20from%20DealayReason";
        StringRequest stringRequest  = new StringRequest(Request.Method.GET, qury, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {

                Log.e("delaerdata", response.toString());
                try
                {
                    JSONArray jsonarray = new JSONArray(response);
                    if (jsonarray.length()>0)
                    {
                        for (int i=0;i<jsonarray.length();i++)
                        {
                            JSONObject obj = (JSONObject) jsonarray.get(i);
                            CustomerName=obj.getString("CustomerName").toString();
                            VehicleModel=obj.getString("VehicleModel").toString();
                            MobileNo=obj.getString("MobileNo").toString();
                            EmailID=obj.getString("EmailID").toString();

                            c_name.setText(CustomerName);
                            m_name.setText(VehicleModel);
                            p_number.setText(MobileNo);
                            e_adrresss.setText(EmailID);


                        }
                    }
                }catch (Exception e)
                {

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





        statusspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                war2 = position;

                if (war2 == 1) {
                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.VISIBLE);
                    txt_remarks.setVisibility(View.VISIBLE);
                    edit_remarks.setVisibility(View.VISIBLE);
                    reason_spineer.setVisibility(View.GONE);
                    txt_remarks_spineer.setVisibility(View.GONE);
                }

                else{

                    edit_remarks.setVisibility(View.GONE);
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    txt_remarks.setVisibility(View.GONE);
                    reason_spineer.setVisibility(View.GONE);
                    reason_spineer.setVisibility(View.GONE);
                    txt_remarks_spineer.setVisibility(View.GONE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        status_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String option = statusspinner.getSelectedItem().toString().trim();

                if(option.equals("NotIntrested")){

                    String x = reason_spineer.getSelectedItem().toString().trim();
                    String dates = edit_date.getText().toString();
                    String times = edit_time.getText().toString();
                    String concats=dates+"%20"+times;

                    x=x.replaceAll("\\s","%20");
                    String url = "http://loadcrm.com/quotationdiwali/api/CustomerDetailsStatusUpdate/1?id="+getdata+"&Value="+option+"&date="+concats+"&remark="+x+"";

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("Response", response.toString());
                            Log.e("Mysuccess", "" + response);
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
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
                }
                else {
                    String remarks = edit_remarks.getText().toString();
                    String dates = edit_date.getText().toString();
                    String times = edit_time.getText().toString();

                    String concats = dates + "%20" + times;

                    String url = "http://loadcrm.com/quotationdiwali/api/CustomerDetailsStatusUpdate/1?id=" + getdata + "&Value=" + option + "&date=" + concats + "&remark=" + remarks + "";

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("Response", response.toString());
                            Log.e("Mysuccess", "" + response);
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
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
                }


                //edit_customer_name.setText("");
                //edit_mobile_number.setText("");
                //edit_email.setText("");
                //edit_shotaddres.setText("");
                //edit_discount.setText("");

                /*  Toast.makeText(getApplicationContext(),"Quotation_Status Updated.....",Toast.LENGTH_SHORT).show();*/

            }
        });
    }



    @Override
    public void onClick(View v) {

        if (v == date) {

            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(Booking_Status.this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            ye=year;
                            day=dayOfMonth;
                            int ss =month+1;
                            mon=ss;

                            edit_date.setText(ye + "-" + ss + "-" + day);
                        }



                    }, mYear, mMonth, mDay);
            dpd.show();
        }
        if (v == time) {

            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog

            TimePickerDialog tpd = new TimePickerDialog(Booking_Status.this,android.R.style.Theme_Holo_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                int hourOfDay;

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {



                    hoours=hourOfDay;

                    minutes =minute;
                    edit_time.setText(hourOfDay + ":" + minute);
                }



            }, mHour, mMinute, false);
            tpd.show();
        }
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

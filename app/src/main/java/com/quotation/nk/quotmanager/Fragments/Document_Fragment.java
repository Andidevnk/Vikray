package com.quotation.nk.quotmanager.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.quotation.nk.quotmanager.SwipeDisable;
import com.quotation.nk.quotmanager.MainController;
import com.quotation.nk.quotmanager.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

import static android.content.Context.MODE_PRIVATE;


public class Document_Fragment extends Fragment implements View.OnClickListener{


    private SwipeDisable viewPager;
    RadioButton radioyes,radiono;
    RadioGroup exhangeradiogroup;
    EditText edit_model,edit_year,edit_expectedvalu,edit_quotvalue,edit_slaesmanname,edit_salesmanmobile,edit_date;
    CheckBox ck_adhar,ck_passport,ck_voterid,ck_photos,ck_drivinglicence,ck_pancard;

    String adhar = "", passport= "",photos="",voterid = "",drivinglicence= "",pancard = "";
    String requestyes = "",requestno = "";

    Button date,submit_btn,back_btn;
    int ye, mon, day;


    public Document_Fragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_document_, container, false);
        submit_btn = (Button) v.findViewById(R.id.submit);
        back_btn = (Button) v.findViewById(R.id.backdash);
        ck_adhar = (CheckBox)v.findViewById(R.id.checkadharcard);
        ck_passport = (CheckBox)v.findViewById(R.id.checkpassport);
        ck_photos =(CheckBox)v.findViewById(R.id.checkphotos);
        ck_voterid = (CheckBox)v.findViewById(R.id.checkvoterid);
        ck_drivinglicence = (CheckBox)v.findViewById(R.id.checkdrivinglicence);
        ck_pancard = (CheckBox)v.findViewById(R.id.checkpancard);
        exhangeradiogroup = (RadioGroup) v.findViewById(R.id.exhangegroup);
        radioyes = (RadioButton) v.findViewById(R.id.yesexchange);
        radiono = (RadioButton) v.findViewById(R.id.noexhange);
        edit_model = (EditText)v.findViewById(R.id.modelnames);
        edit_year  = (EditText)v.findViewById(R.id.yearmodel);
        edit_expectedvalu  = (EditText)v.findViewById(R.id.expectedvalue);
        edit_quotvalue = (EditText)v.findViewById(R.id.quotvalues);
        edit_slaesmanname = (EditText)v.findViewById(R.id.salesmanname);
        edit_salesmanmobile = (EditText)v.findViewById(R.id.salesmannumber);
        edit_date = (EditText)v.findViewById(R.id.datess);
        date = (Button) v.findViewById(R.id.datebtn);

        date.setOnClickListener(this);

        SharedPreferences userDetails = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final String test1 = userDetails.getString("Name", "");
        String test2 = userDetails.getString("Pwd", "");


        ck_adhar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    adhar  = "AdharCard";
                }
                else{
                    adhar = "";

                }
            }
        });

        ck_passport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    passport = "Passport";
                }
                else {

                    passport = "";
                }

            }
        });

        ck_voterid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    voterid = "VoterId";
                    //Toast.makeText(getActivity(), voterid, Toast.LENGTH_SHORT).show();
                }
                else {
                    voterid = "";
                }
            }
        });


        ck_photos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    photos = "Photos";
                } else {
                    photos = "";
                }
            }
        });

        ck_drivinglicence.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    drivinglicence = "DrivingLicence";
                }
                else{

                    drivinglicence = "";

                }
            }
        });

        ck_pancard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pancard = "Pancard";
                }
                else{

                    pancard = "";
                }
            }
        });



        exhangeradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(radioyes.isChecked()){
                    requestyes = "Yes";
                    edit_model.setVisibility(View.VISIBLE);
                    edit_year.setVisibility(View.VISIBLE);
                    edit_expectedvalu.setVisibility(View.VISIBLE);
                    edit_quotvalue.setVisibility(View.VISIBLE);
                }
                else{
                    requestyes = "No";
                    edit_model.setVisibility(View.GONE);
                    edit_year.setVisibility(View.GONE);
                    edit_expectedvalu.setVisibility(View.GONE);
                    edit_quotvalue.setVisibility(View.GONE);
                }
            }
        });




        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager = (SwipeDisable) getActivity().findViewById(
                        R.id.swipdisables);
                viewPager.setCurrentItem(0);

            }
        });
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String str_oldmodel = edit_model.getText().toString();
                final String str_year = edit_year.getText().toString();
                final String str_expectedvalue = edit_expectedvalu.getText().toString();
                final String str_quatevalue = edit_quotvalue.getText().toString();


                final  String str_salesmanname = edit_slaesmanname.getText().toString();
                final String str_salesmannumber = edit_salesmanmobile.getText().toString();
                final String str_salesmandate = edit_date.getText().toString();

               final String str_adahar = adhar;
               // str_adahar = str_adahar.replaceAll("\\s", "%20");
                String str_passpoert = passport;
                //str_passpoert = str_passpoert.replaceAll("\\s", "%20");
                String str_voterid = voterid;
               // str_voterid = str_voterid.replaceAll("\\s", "%20");
                String str_photos = photos;
               // str_photos = str_photos.replaceAll("\\s", "%20");
                String str_drivinglicence = drivinglicence;
               // str_drivinglicence = str_drivinglicence.replaceAll("\\s", "%20");
                String str_pancard = pancard;
              //  str_pancard = str_pancard.replaceAll("\\s", "%20");
                final String concats=str_adahar+","+str_passpoert+","+str_voterid+","+str_photos+","+str_drivinglicence+","+str_pancard;
               // Toast.makeText(getActivity(), concats, Toast.LENGTH_SHORT).show();
                final String str_exchange =requestyes;
               // Toast.makeText(getActivity(), str_exchange, Toast.LENGTH_SHORT).show();


                final SharedPreferences pref = getActivity().getSharedPreferences("list2", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("documents",concats);
                editor.putString("exchange",str_exchange);
                editor.putString("oldmodel",str_oldmodel);
                editor.putString("year",str_year);
                editor.putString("expectvalue",str_expectedvalue);
                editor.putString("quotationvalue",str_quatevalue);
                editor.putString("salesmanname",str_salesmanname);
                editor.putString("salesmannumber",str_salesmannumber);
                editor.putString("salesmandate",str_salesmandate);
                editor.commit();
                if (str_exchange.equals("Yes"))
                {
                   // Toast.makeText(getActivity(), str_adahar+str_passpoert+str_voterid+str_photos+str_drivinglicence+str_pancard, Toast.LENGTH_SHORT).show();

                    String url = "http://loadcrm.com/quotationdiwali/api/RegistrationRequirementExchange/1?SalesManID="+test1+"&Documents="+concats+"" +
                            "&ExchangeReq="+str_exchange+"&Model="+str_oldmodel+"&Year="+str_year+"&ExpectedValue="+str_expectedvalue+"" +
                            "&QuotValue="+str_quatevalue+"&SalesmanName="+str_salesmanname+"&PhoneNo="+str_salesmannumber+"&Date="+str_salesmandate+"";

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("Response", response.toString());
                            Log.e("Mysuccess", "" + response);
                           // Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject jsonobject = new JSONObject(response);
                                if (jsonobject != null) {
                                    String Message = jsonobject.getString("Message");
                                    String Error = jsonobject.getString("Error");
                                    if (Error.equalsIgnoreCase(null) || Error.equalsIgnoreCase("null")) {
                                        Toast.makeText(getActivity(), Message, Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(), Error, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
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
                else if (str_exchange.equals("No"))
                {
                    String url = "http://loadcrm.com/quotationdiwali/api/RegistrationRequirement/1?SalesManID="+test1+"&Documents="+concats+"&ExchangeReq="+str_exchange+"&SalesmanName="+str_salesmanname+"&PhoneNo="+str_salesmannumber+"&Date="+str_salesmandate+"";

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("Response", response.toString());
                            Log.e("Mysuccess", "" + response);
                            //Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject jsonobject = new JSONObject(response);
                                if (jsonobject != null) {
                                    String Message = jsonobject.getString("Message");
                                    String Error = jsonobject.getString("Error");
                                    if (Error.equalsIgnoreCase(null) || Error.equalsIgnoreCase("null")) {
                                        Toast.makeText(getActivity(), Message, Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(), Error, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
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
                else
                {
                    Toast.makeText(getActivity(), "Not Check", Toast.LENGTH_SHORT).show();
                }


                viewPager = (SwipeDisable) getActivity().findViewById(
                        R.id.swipdisables);
                viewPager.setCurrentItem(2);

            }
        });
        return v;

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
            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            ye = year;
                            day = dayOfMonth;
                            int ss = month + 1;
                            mon = ss;

                            edit_date.setText(ye + "-" + ss + "-" + day);
                            // updateDisplay();
                        }


                    }, mYear, mMonth, mDay);
            dpd.show();
        }
    }

}

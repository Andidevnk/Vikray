package com.quotation.nk.quotmanager.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.quotation.nk.quotmanager.SwipeDisable;
import com.quotation.nk.quotmanager.MainController;
import com.quotation.nk.quotmanager.R;
import com.quotation.nk.quotmanager.Welcome_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

import static android.content.Context.MODE_PRIVATE;


public class Customer_Fragment extends Fragment implements View.OnClickListener {

    private SwipeDisable viewPager;
    Spinner spin_models, spin_varient, spin_colors, spin_paymenttype,spin_Pronounced;
    private ArrayList<String> getss = new ArrayList<String>();
    private ArrayList<String> getss1 = new ArrayList<String>();
    private ArrayList<String> getss2 = new ArrayList<String>();
    String exshowroomprice, com_insurance, road_tax, on_road, ew_rsa, totalcost;
    EditText edit_ex_showroom, edit_com_insurance, edit_road_tax, edit_on_road, edit_ewrsa, edit_total_price;
    EditText edit_date, edit_custname, edit_custmobile, edit_custemail, edit_custoaddress, edit_custcity, edit_custopincode, edit_custoage, edit_customphone;
    Button date;
    int ye, mon, day;


    public Customer_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_, container, false);
        final CircularProgressButton back_btn, submit_btn;
        submit_btn = (CircularProgressButton) v.findViewById(R.id.submit);
        back_btn = (CircularProgressButton) v.findViewById(R.id.backdash);
        spin_Pronounced = (Spinner)v.findViewById(R.id.pronouncedname);
        date = (Button) v.findViewById(R.id.datebtn);
        edit_date = (EditText) v.findViewById(R.id.dateofbirth);
        edit_custname = (EditText) v.findViewById(R.id.customernames);
        edit_custmobile = (EditText) v.findViewById(R.id.customermobilenumber);
        edit_custemail = (EditText) v.findViewById(R.id.customeremail);
        edit_custoaddress = (EditText) v.findViewById(R.id.customeraddress);
        edit_custcity = (EditText) v.findViewById(R.id.customercity);
        edit_custopincode = (EditText) v.findViewById(R.id.customerpincode);
        edit_custoage = (EditText) v.findViewById(R.id.customerage);
        edit_customphone = (EditText) v.findViewById(R.id.customerphonenumber);
        spin_models = (Spinner) v.findViewById(R.id.modelprefixspinner);
        spin_varient = (Spinner) v.findViewById(R.id.modelvarientspinner);
        spin_colors = (Spinner) v.findViewById(R.id.modelcolorsspinner);
        spin_paymenttype = (Spinner) v.findViewById(R.id.paymenttypesopinner);
        edit_ex_showroom = (EditText) v.findViewById(R.id.exshowroomrate);
        edit_com_insurance = (EditText) v.findViewById(R.id.com_insurance_rate);
        edit_road_tax = (EditText) v.findViewById(R.id.road_tax_price);
        edit_on_road = (EditText) v.findViewById(R.id.on_raod_cost);
        edit_ewrsa = (EditText) v.findViewById(R.id.ew_rsa_price);
        edit_total_price = (EditText) v.findViewById(R.id.totalprice);
        SharedPreferences userDetails = getActivity().getSharedPreferences("MyPref", MODE_PRIVATE);
        final String test1 = userDetails.getString("Name", "");
        String test2 = userDetails.getString("Pwd", "");

        date.setOnClickListener(this);


        spin_models.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                final Dialog shippingDialog = new Dialog(getActivity());
                shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                shippingDialog.setContentView(R.layout.waitscreen);
                shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                shippingDialog.setCancelable(true);
                shippingDialog.setCanceledOnTouchOutside(false);

                shippingDialog.show();
                String modelsslecetd = spin_models.getSelectedItem().toString().trim();
                modelsslecetd = modelsslecetd.replaceAll("\\s", "%20");


                getss.clear();
                getss.removeAll(getss);
                String url2 = "http://loadcrm.com/quotationdiwali/api/Models/1?Model=" + modelsslecetd + "&SalesmanID=" + test1 + "";
                StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2/*AppCommonUrl.commonURL1 + "ExeQuery/1?qry="+query*/, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        shippingDialog.dismiss();
                        Log.e("delaerdata", response.toString());
                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            if (jsonarray.length() > 0) {
                                for (int i = 0; i < jsonarray.length(); i++) {
                                    JSONObject obj = (JSONObject) jsonarray.get(i);


                                    //      getss.add("Select Varient");
                                    getss.add(obj.getString("MODEL").toString());

                                    ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getss);

                                    // Drop down layout style - list view with radio button
                                    dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    // attaching data adapter to spinner
                                    spin_varient.setAdapter(dataAdapter1);

                                    spin_varient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                            final Dialog shippingDialog = new Dialog(getActivity());
                                            shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            shippingDialog.setContentView(R.layout.waitscreen);
                                            shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                                            shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                            shippingDialog.setCancelable(true);
                                            shippingDialog.setCanceledOnTouchOutside(false);

                                            shippingDialog.show();


                                            String Selectvariant = spin_varient.getSelectedItem().toString().trim();
                                            Selectvariant = Selectvariant.replaceAll("\\s", "%20");
                                            //Toast.makeText(getActivity(), Selectvariant, Toast.LENGTH_SHORT).show();

                                            //--------------------------------------------------------
                                            getss1.clear();
                                            getss1.removeAll(getss1);
                                            String url2 = "http://loadcrm.com/quotationdiwali/api/VehicleColor/1?Varient=" + Selectvariant;
                                            final String finalSelectvariant = Selectvariant;
                                            StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2/*AppCommonUrl.commonURL1 + "ExeQuery/1?qry="+query*/, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {


                                                    shippingDialog.dismiss();
                                                    Log.e("delaerdata", response.toString());
                                                    try {
                                                        JSONArray jsonarray = new JSONArray(response);
                                                        if (jsonarray.length() > 0) {
                                                            for (int i = 0; i < jsonarray.length(); i++) {

                                                                JSONObject obj = (JSONObject) jsonarray.get(i);

                                                                getss1.add(obj.getString("Color").toString());
                                                                ArrayAdapter<String> dataAdapter12 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getss1);

                                                                // Drop down layout style - list view with radio button
                                                                dataAdapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                                // attaching data adapter to spinner
                                                                spin_colors.setAdapter(dataAdapter12);

                                                                spin_colors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                    @Override
                                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                                        String qury = "http://loadcrm.com/quotationdiwali/api/TotalPrice/1?Varient=" + finalSelectvariant + "&SalesmanID=" + test1 + "";

// String url="https://loadcrm.com/QMS/api/ExeQuery/1?qry=select%20Reason%20from%20DealayReason";
                                                                        StringRequest stringRequest = new StringRequest(Request.Method.GET, qury, new Response.Listener<String>() {
                                                                            @Override
                                                                            public void onResponse(String response) {



                                                                                Log.e("delaerdata", response.toString());
                                                                                try {
                                                                                    JSONArray jsonarray = new JSONArray(response);
                                                                                    if (jsonarray.length() > 0) {
                                                                                        for (int i = 0; i < jsonarray.length(); i++) {
                                                                                            JSONObject obj = (JSONObject) jsonarray.get(i);
                                                                                            exshowroomprice = obj.getString("EX_SROOM");
                                                                                            com_insurance = obj.getString("INS_1YR");
                                                                                            road_tax = obj.getString("ROAD TAX");
                                                                                            on_road = obj.getString("OnRoadCost");
                                                                                            ew_rsa = obj.getString("EXTENDED WARRANTY");
                                                                                            totalcost = obj.getString("Total");
                                                                                            Log.e("Data", "Data" + exshowroomprice + com_insurance);
                                                                                            //Toast.makeText(getActivity(), exshowroomprice, Toast.LENGTH_SHORT).show();


                                                                                            edit_ex_showroom.setText(exshowroomprice);
                                                                                            edit_com_insurance.setText(com_insurance);
                                                                                            edit_road_tax.setText(road_tax);
                                                                                            edit_on_road.setText(on_road);
                                                                                            edit_ewrsa.setText(ew_rsa);
                                                                                            edit_total_price.setText(totalcost);


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



                                            //-------------------------------------------------------
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


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Welcome_Activity.class);
                startActivity(intent);
            }
        });


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String str_pronounced = spin_Pronounced.getSelectedItem().toString().trim();
                String str_customername = edit_custname.getText().toString();
                String str_csutomermoile = edit_custmobile.getText().toString();
                String str_customeremail = edit_custemail.getText().toString();
                String str_cusyomeraddress = edit_custoaddress.getText().toString();
                str_cusyomeraddress = str_cusyomeraddress.replaceAll("\\s", "%20");
                String str_customercity = edit_custcity.getText().toString();
                String str_customerpincode = edit_custopincode.getText().toString();
                String str_customerdob = edit_date.getText().toString();
                String str_customerage = edit_custoage.getText().toString();
                String str_customerphone = edit_customphone.getText().toString();
                String str_model = spin_models.getSelectedItem().toString().trim();
                str_model = str_model.replaceAll("\\s", "%20");
                String str_modelvarient = spin_varient.getSelectedItem().toString().trim();
                str_modelvarient = str_modelvarient.replaceAll("\\s", "%20");
                String str_modelcolor = spin_colors.getSelectedItem().toString().trim();
                str_modelcolor = str_modelcolor.replaceAll("\\s", "%20");
                String str_paymentmode = spin_paymenttype.getSelectedItem().toString().trim();
                String str_exshowroom = edit_ex_showroom.getText().toString();
                String str_comprehenshive_insu = edit_com_insurance.getText().toString();
                String str_roadtax = edit_road_tax.getText().toString();
                String str_onroad= edit_on_road.getText().toString();
                String str_ewrsa = edit_ewrsa.getText().toString();
                String str_total = edit_total_price.getText().toString();

                final SharedPreferences pref = getActivity().getSharedPreferences("list1", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("Pronounced", str_pronounced);
                editor.putString("Customername", str_customername);
                editor.putString("Customermobilr",str_csutomermoile);
                editor.putString("Customeremail",str_customeremail);
                editor.putString("Customeraddress",str_cusyomeraddress);
                editor.putString("Customercity",str_customercity);
                editor.putString("Customerpincode",str_customerpincode);
                editor.putString("Customerdateofbirth",str_customerdob);
                editor.putString("Customerage",str_customerage);
                editor.putString("Customerofficeresidence",str_customerphone);
                editor.putString("Model",str_model);
                editor.putString("Varient",str_modelvarient);
                editor.putString("color",str_modelcolor);
                editor.putString("paymenttype",str_paymentmode);
                editor.putString("exshowroom",str_exshowroom);
                editor.putString("comprehensiveinsurance",str_comprehenshive_insu);
                editor.putString("roadtax",str_roadtax);
                editor.putString("onroad",str_onroad);
                editor.putString("ewandrsa",str_ewrsa);
                editor.putString("total",str_total);
                editor.commit();

                String url = "http://loadcrm.com/quotationdiwali/api/EnquiryForm/1?SalesManID="+test1+"&CustomerName="+str_customername+"&PhoneNo="+str_csutomermoile+"&Email="+str_customeremail+"k&Address="+str_cusyomeraddress+"" +
                        "&City="+str_customercity+"&PinCode="+str_customerpincode+"&DOB="+str_customerdob+"&Age="+str_customerage+"&OfficeNo="+str_customerphone+"&Model="+str_model+"&Varient="+str_modelvarient+"&color="+str_modelcolor+"&PaymentMode="+str_paymentmode+"&ExShowroom="+str_exshowroom+"" +
                        "&Insurance="+str_comprehenshive_insu+"&RoadTax="+str_roadtax+"&OnRoad="+str_onroad+"&EWRSA="+str_ewrsa+"&TotalAmount="+str_total+"";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response.toString());
                        Log.e("Mysuccess", "" + response);
                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
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


                edit_custname.setText("");
                edit_custmobile.setText("");
                edit_custemail.setText("");
                edit_custoaddress.setText("");
                edit_custcity.setText("");
                edit_custopincode.setText("");
                edit_date.setText("");
                edit_custoage.setText("");
                edit_customphone.setText("");
                edit_ex_showroom.setText("");
                edit_com_insurance.setText("");
                edit_road_tax.setText("");
                edit_on_road.setText("");
                edit_ewrsa.setText("");
                edit_total_price.setText("");




//               Toast.makeText(getApplicationContext(), "Quotation Sending....", Toast.LENGTH_SHORT).show();

                viewPager = (SwipeDisable) getActivity().findViewById(
                        R.id.swipdisables);
                viewPager.setCurrentItem(1);


            }

        });
        return v;


    }




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

                            Calendar c = Calendar.getInstance();
                            c.set(Calendar.YEAR, year);
                            c.set(Calendar.MONTH, month);
                            c.set(Calendar.DAY_OF_MONTH, day);
                            String format = new SimpleDateFormat("dd MM yyyy").format(c.getTime());
                            edit_custoage.setText(format);
                            edit_custoage.setText(Integer.toString(calculateAge(c.getTimeInMillis())));
                        }


                    }, mYear, mMonth, mDay);
            dpd.show();
        }
    }

    int calculateAge(long date){
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }
        return age;
    }

}
package com.quotation.nk.quotmanager.Fragments;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.quotation.nk.quotmanager.GatePass_Activity;
import com.quotation.nk.quotmanager.SwipeDisable;
import com.quotation.nk.quotmanager.MainController;
import com.quotation.nk.quotmanager.R;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

import static android.widget.Toast.LENGTH_SHORT;


public class Advertisement_Fragment extends Fragment {

    private SwipeDisable viewPager;
    CheckBox ck_newspaper,ck_visibiltyonroad,ck_tvadvertisment,ck_internet,ck_showroomwalikin,ck_throughfriend,ck_servicecenter,ck_activity;
    CheckBox ck_telephone,ck_walkin,ck_roadshow,ck_quotation;
    RadioGroup rg_testdrive;
    RadioButton rb_driveyes,rb_driveno;
    String feedback = "",quary= "",str_testdrive= "";

    String news = "",visibilty = "",tv = "",internet = "",showroom = "",through ="",service = "",activity = "",telephone = "",walkin = "",roadshow = "",quoation = "",testdrive = "";

    Button back_btn,submit;
    XSSFSheet sheet;
    String yesdrive = "";
    public Advertisement_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_advertisement_, container, false);
        back_btn = (Button) v.findViewById(R.id.backdash);
        submit  = (Button)v.findViewById(R.id.submit);
        ck_newspaper = (CheckBox)v.findViewById(R.id.checknewspaperad);
        ck_visibiltyonroad = (CheckBox)v.findViewById(R.id.checkvisbiltyroadad);
        ck_tvadvertisment = (CheckBox)v.findViewById(R.id.checktvadvertisment);
        ck_internet = (CheckBox)v.findViewById(R.id.checkinternet);
        ck_showroomwalikin = (CheckBox)v.findViewById(R.id.checkshowroomwalikin);
        ck_roadshow = (CheckBox)v.findViewById(R.id.checkroadshow);
        ck_throughfriend  = (CheckBox)v.findViewById(R.id.checkthroughfriendfamily);
        ck_servicecenter = (CheckBox)v.findViewById(R.id.checkservicecenter);
        ck_activity = (CheckBox)v.findViewById(R.id.checkactivity);

        ck_telephone = (CheckBox)v.findViewById(R.id.checktelephopne);
        ck_walkin = (CheckBox)v.findViewById(R.id.checkwalkin);
        ck_quotation = (CheckBox)v.findViewById(R.id.checkquotation);

        rg_testdrive = (RadioGroup)v.findViewById(R.id.testdrivegroup);
        rb_driveyes = (RadioButton)v.findViewById(R.id.driveyes);
        rb_driveno = (RadioButton)v.findViewById(R.id.driveno);

        SharedPreferences userDetails = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final String test1 = userDetails.getString("Name", "");
        String test2 = userDetails.getString("Pwd", "");


        ck_newspaper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    news  = "News Paper Ad";
                }
                else{
                    news = "";

                }
            }
        });
        ck_visibiltyonroad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    visibilty  = "Visibilty On Road";
                }
                else{
                    visibilty = "";

                }
            }
        });
        ck_tvadvertisment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tv  = "Tv Advertisment";
                }
                else{
                    tv = "";

                }
            }
        });
        ck_internet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    internet  = "Internet";
                }
                else{
                    internet = "";

                }
            }
        });
        ck_showroomwalikin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    showroom  = "Showroom Waklk-in";
                }
                else{
                    showroom = "";

                }
            }
        });
        ck_throughfriend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    through  = "Through Friend Family";
                }
                else{
                    through = "";

                }
            }
        });
        ck_servicecenter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    service  = "Service Center";
                }
                else{
                    service = "";

                }
            }
        });
        ck_activity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    activity  = "Activity";
                }
                else{
                    activity = "";

                }
            }
        });
        ck_telephone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    telephone  = "Telephone";
                }
                else{
                    telephone = "";

                }
            }
        });
        ck_walkin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    walkin  = "Walkin";
                }
                else{
                    walkin = "";

                }
            }
        });
        ck_roadshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    roadshow  = "Roadshow";
                }
                else{
                    roadshow = "";

                }
            }
        });
        ck_quotation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    quoation  = "Quotation";
                }
                else{
                    quoation = "";

                }
            }
        });

        rg_testdrive.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rb_driveyes.isChecked()){
                    yesdrive = "Yes";

                }
                else{
                    yesdrive = "NO";
                }
            }
        });

        rg_testdrive.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rb_driveno.isChecked()){
                    yesdrive = "Yes";

                }
                else{
                    yesdrive = "No";
                }
            }
        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String str_news = news;
                str_news = str_news.replaceAll("\\s", "");
                String str_visibilty = visibilty;
                str_visibilty = str_visibilty.replaceAll("\\s", "");
                String str_tv = tv;
                str_tv = str_tv.replaceAll("\\s", "");
                String str_internet = internet;
                str_internet = str_internet.replaceAll("\\s", "");
                String str_showrrom = showroom;
                str_showrrom = str_showrrom.replaceAll("\\s", "");
                String str_through = through;
                str_through = str_through.replaceAll("\\s", "");
                String str_service = service;
                str_service = str_service.replaceAll("\\s", "");
                String str_activity = activity;
                str_activity = str_activity.replaceAll("\\s", "");
                String str_telephone = telephone;
                str_telephone = str_telephone.replaceAll("\\s", "");
                String str_walkin = walkin;
                str_walkin = str_walkin.replaceAll("\\s", "");
                String str_roadshow = roadshow;
                str_roadshow = str_roadshow.replaceAll("\\s", "");
                String str_quoation = quoation;
                str_quoation = str_quoation.replaceAll("\\s", "");

                str_testdrive = yesdrive;

                feedback = str_news + "," + str_visibilty + "," + str_tv + "," + str_internet + "," + str_showrrom + "," + str_through + "," + str_service + "," + str_activity;
                quary = str_telephone + "," + str_walkin + "," + str_roadshow + "," + str_quoation;





                String url = "http://loadcrm.com/quotationdiwali/api/Feedback/1?SalesManID=" + test1 + "&Feedback=" + feedback + "&QueryType=" + quary + "&TestDrive=" + str_testdrive + "";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.d("Response", response.toString());
                        Log.e("Mysuccess", "" + response);
                        Toast.makeText(getActivity(), response.toString(), LENGTH_SHORT).show();
                        try {
                            JSONObject jsonobject = new JSONObject(response);
                            if (jsonobject != null) {
                                String Message = jsonobject.getString("Message");
                                String Error = jsonobject.getString("Error");
                                if (Error.equalsIgnoreCase(null) || Error.equalsIgnoreCase("null")) {
                                    Toast.makeText(getActivity(), Message, LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), Error, LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Something went wrong!", LENGTH_SHORT).show();
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
                try {
                    getprint();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void getprint() throws IOException {



                SharedPreferences chk1  = getActivity().getSharedPreferences("list1",Context.MODE_PRIVATE);
                String pronouncedcall = chk1.getString("Pronounced","");
                String customer_name =chk1.getString("Customername","");
                String customer_mobile = chk1.getString("Customermobilr","");
                String customer_email = chk1.getString("Customeremail","");
                String customer_address = chk1.getString("Customeraddress","");
                String customer_city = chk1.getString("Customercity","");
                String customer_dob = chk1.getString("Customerdateofbirth","");
                String customer_age = chk1.getString("Customerage","");
                String customer_phone = chk1.getString("Customerofficeresidence","");
                String customer_pincode = chk1.getString("Customerpincode","");
                String model_name = chk1.getString("Model","");
                String model_varientname = chk1.getString("Varient","");
                String model_color_name = chk1.getString("color","");
                String paymentmode = chk1.getString("paymenttype","");
                String exshowroom = chk1.getString("exshowroom","");
                String comprenshivinsurance = chk1.getString("comprehensiveinsurance","");
                String roadtax = chk1.getString("roadtax","");
                String onroadprice = chk1.getString("onroad","");
                String ewrsa = chk1.getString("ewandrsa","");
                String totals = chk1.getString("total","");


                SharedPreferences chk2  = getActivity().getSharedPreferences("list2",Context.MODE_PRIVATE);
                String documents = chk2.getString("documents","");
                String exchange_reques = chk2.getString("exchange","");
                String old_model = chk2.getString("oldmodel","");
                String model_year = chk2.getString("year","");
                String expect_value = chk2.getString("expectvalue","");
                String quoatevalue = chk2.getString("quotationvalue","");
                String salesman_name = chk2.getString("salesmanname","");
                String salesman_number = chk2.getString("salesmannumber","");
                String salesman_date = chk2.getString("salesmandate","");





                InputStream stream = getResources().openRawResource(R.raw.book1);
                XSSFWorkbook workbook = new XSSFWorkbook(stream);
                sheet = workbook.getSheetAt(0);


                getvalue(4,2,pronouncedcall);//Customer Details :Name
                getvalue(5,2,customer_name);
                getvalue(5,8,customer_mobile);
                getvalue(7,8,customer_email);
                getvalue(6,2,customer_address);
                getvalue(9,6,customer_pincode);
                getvalue(9,2,customer_city);
                getvalue(9,11,customer_dob);
                getvalue(9,8,customer_age);
                getvalue(4,8,customer_phone);
                getvalue(12,2,model_name);
                getvalue(13,2,model_varientname);
                getvalue(14,2,model_color_name);
                getvalue(15,2,paymentmode);
                getvalue(12,7,exshowroom);
                getvalue(13,7,comprenshivinsurance);
                getvalue(14,7,roadtax);
                getvalue(15,7,onroadprice);
                getvalue(16,7,ewrsa);
                getvalue(17,7,totals);
                getvalue(19,1,documents);
                getvalue(24,5,exchange_reques);
                getvalue(25,9,old_model);
                getvalue(26,9,model_year);
                getvalue(27,9,expect_value);
                getvalue(28,9,quoatevalue);
                getvalue(19,8,salesman_name);
                getvalue(20,8,salesman_number);
                getvalue(21,8,salesman_date);
                getvalue(25,1,feedback);
                getvalue(12,9,quary);
                getvalue(17,9,str_testdrive);


                String outFileName = "filetoshare.xlsx";



                //printlnToUser("" + outFileName);
                File cacheDir = getActivity().getCacheDir();
                File outFile = new File(cacheDir, outFileName);
                String path1 = Environment.getExternalStorageDirectory()+"/"+outFileName;
                outFile = new File(path1);
                OutputStream outputStream = new FileOutputStream(outFile.getAbsolutePath());
                workbook.write(outputStream);
                outputStream.flush();
                outputStream.close();
                // printlnToUser("sharing file...");
                share(outFileName, getActivity());
            }

                public void getvalue(int row, int cell, String value) {



                    Cell celln = sheet.getRow(row).getCell(cell);
                    celln.setCellValue(value);// dealership address

                }

            public void share(String fileName, Context context) {
                Uri fileUri = Uri.parse("content://"+getActivity().getPackageName()+"/"+fileName);
//        File file =new File(String.valueOf(fileUri));

                // printlnToUser("sending "+fileUri.toString()+" ...");

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_VIEW);
                shareIntent.setData(fileUri);
                shareIntent.setDataAndType(fileUri,"application/vnd.ms-excel");

                shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                try {
                    startActivity(shareIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getActivity(),"No Application available to viewExcel", LENGTH_SHORT).show();
                }


            }
        });


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager = (SwipeDisable) getActivity().findViewById(
                        R.id.swipdisables);
                viewPager.setCurrentItem(1);



            }
        });
        return v;


        }
    }



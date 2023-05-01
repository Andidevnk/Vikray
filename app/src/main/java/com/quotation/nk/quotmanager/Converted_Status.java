package com.quotation.nk.quotmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.quotation.nk.quotmanager.Model.CallDetails;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Converted_Status extends AppCompatActivity {
        TextView c_name,m_name,p_number,e_adrresss;
        String getdata,CustomerName,VehicleModel,MobileNo,EmailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_converted__status);


        c_name = (TextView)findViewById(R.id.name);
        m_name = (TextView)findViewById(R.id.model);
        p_number = (TextView)findViewById(R.id.phone_number);
        e_adrresss = (TextView)findViewById(R.id.e_address);







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

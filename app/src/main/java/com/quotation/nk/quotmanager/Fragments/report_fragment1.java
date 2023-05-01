package com.quotation.nk.quotmanager.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.quotation.nk.quotmanager.MainController;
import com.quotation.nk.quotmanager.R;
import com.quotation.nk.quotmanager.Adapter.RecycleViewAdapter_Reports;
import com.quotation.nk.quotmanager.Model.Reports;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class report_fragment1 extends Fragment {

    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Reports> reportsList;
    RecycleViewAdapter_Reports adapter;
  SwipeRefreshLayout    mSwipeRefreshLayout;
    private JSONArray reports = null;

    public report_fragment1() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report_fragment1, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerepoert1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        reportsList = new ArrayList<Reports>();
        adapter = new RecycleViewAdapter_Reports(getActivity(),reportsList);
        linearLayoutManager= new LinearLayoutManager(getActivity());
        //  TextView cus_id = (TextView)findViewById(R.id.iddddd);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        //   movieList = new ArrayList<com.smartworkshop.kuldeep.vsshonda.Movie>();
        // adapter = new MovieAdapter(TVScrren.this,movieList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);


        shuffle();
        // Toast.makeText(this, test1+test2, Toast.LENGTH_SHORT).show();


        mSwipeRefreshLayout=(SwipeRefreshLayout)v.findViewById(R.id.itemlistswipabledonebutton);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuffle();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });



        return v;
    }

    private void shuffle() {
        SharedPreferences userDetails = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final String test1 = userDetails.getString("Name", "");
        String test2 = userDetails.getString("Pwd", "");

        final Dialog shippingDialog = new Dialog(getActivity());
        shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        shippingDialog.setContentView(R.layout.waitscreen);
        shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        shippingDialog.setCancelable(true);
        shippingDialog.setCanceledOnTouchOutside(false);

        shippingDialog.show();


        reportsList.clear();
        adapter.notifyDataSetChanged();
        String qury="http://loadcrm.com/quotationdiwali/api/QuotationReport/1?SalesmanID="+test1+"";

// String url="https://loadcrm.com/QMS/api/ExeQuery/1?qry=select%20Reason%20from%20DealayReason";
        StringRequest stringRequest  = new StringRequest(Request.Method.GET, qury, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {


                shippingDialog.dismiss();
                Log.e("delaerdata", response.toString());
                try
                {
                    JSONArray jsonarray = new JSONArray(response);
                    if (jsonarray.length()>0)
                    {
                        for (int i=0;i<jsonarray.length();i++)
                        {
                            JSONObject obj = (JSONObject) jsonarray.get(i);
                            Reports reports=new Reports();
                            reports.setId(obj.getString("ID"));
                            reports.setCustomer_name(obj.getString("CustomerName"));
                            reports.setQua_date(obj.getString("QuotationDate"));
                            reports.setModel(obj.getString("VehicleModel"));
                            reportsList.add(reports);
                            adapter.notifyDataSetChanged();
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
}

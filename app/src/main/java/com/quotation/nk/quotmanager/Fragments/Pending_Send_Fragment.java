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
import com.quotation.nk.quotmanager.Gatepass_Status;
import com.quotation.nk.quotmanager.MainController;
import com.quotation.nk.quotmanager.R;
import com.quotation.nk.quotmanager.Adapter.Adapter_Gate_Send_Pending;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Pending_Send_Fragment extends Fragment {


    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Gatepass_Status> gatepass_statuses;
    Adapter_Gate_Send_Pending adapter;
    private JSONArray reports = null;

    SwipeRefreshLayout mSwipeRefreshLayout;


    public Pending_Send_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_pending__send_, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerepoert1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        gatepass_statuses = new ArrayList<Gatepass_Status>();
        adapter = new Adapter_Gate_Send_Pending(getActivity(),gatepass_statuses);
        linearLayoutManager= new LinearLayoutManager(getActivity());


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
        // Toast.makeText(this, test1+test2, Toast.LENGTH_SHORT).show();

        final Dialog shippingDialog = new Dialog(getActivity());
        shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        shippingDialog.setContentView(R.layout.waitscreen);
        shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        shippingDialog.setCancelable(true);
        shippingDialog.setCanceledOnTouchOutside(false);

        shippingDialog.show();

        String qury="http://loadcrm.com/quotationdiwali/api/PendingStatus/1?SalesmanID="+test1+"";

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
                            Gatepass_Status gatepassStatus=new Gatepass_Status();
                            gatepassStatus.setId(obj.getString("Id"));
                            gatepassStatus.setLocation_from(obj.getString("FromLocation"));
                            gatepassStatus.setLocation_to(obj.getString("ToLocation"));
                            gatepassStatus.setDelivered_date(obj.getString("EntryDate"));
                            gatepass_statuses.add(gatepassStatus);
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

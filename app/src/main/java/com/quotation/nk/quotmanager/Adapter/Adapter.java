package com.quotation.nk.quotmanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quotation.nk.quotmanager.GatePass_Activity;
import com.quotation.nk.quotmanager.R;
import com.quotation.nk.quotmanager.Model.Reports;

import java.util.List;

/**
 * Created by Nk on 10/30/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<Reports> list;
    int i=1;



    Adapter adapter;


    public Adapter(Context context, List<Reports> list) {
        this.context = context;
        this.list = list;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_cardview, parent, false);
        return new ViewHolder(v);
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reports reports = list.get(position);

        final String idd = String.valueOf(reports.getId());
        String customer_nname = String.valueOf(reports.getCustomer_name());
        String quatation_date = String.valueOf(reports.getQua_date());


        holder.id.setText(idd);
        holder.cus_name.setText(customer_nname);
        holder.quaau_date.setText(quatation_date);

        Intent intent = new Intent(context, GatePass_Activity.class);
        intent.putExtra("movie_id_key", list.get(position).getId()); //you can name the keys whatever you like
        intent.putExtra("movie_rating_key", list.get(position).getCustomer_name()); //note that all these values have to be primitive (i.e boolean, int, double, String, etc.)
        intent.putExtra("movie_release_date_key", list.get(position).getQua_date());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id,cus_name,quaau_date,models;


        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.modelname);
            cus_name=itemView.findViewById(R.id.framename);
            quaau_date=itemView.findViewById(R.id.enginename);


        }
    }

}

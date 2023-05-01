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

import com.quotation.nk.quotmanager.Converted_Status;
import com.quotation.nk.quotmanager.Quotation_Status;
import com.quotation.nk.quotmanager.R;
import com.quotation.nk.quotmanager.Model.Reports;

import java.util.List;

/**
 * Created by Nk on 10/30/2018.
 */

public class RecycleViewAdapter_Reports4 extends RecyclerView.Adapter<RecycleViewAdapter_Reports4.ViewHolder> {

    private Context context;
    private List<Reports> list;
    int i=1;



    RecycleViewAdapter_Reports4 adapter;


    public RecycleViewAdapter_Reports4(Context context, List<Reports> list) {
        this.context = context;
        this.list = list;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.quotation_lits, parent, false);
        return new ViewHolder(v);
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reports reports = list.get(position);

        final String idd = String.valueOf(reports.getId());
        String customer_nname = String.valueOf(reports.getCustomer_name());
        String quatation_date = String.valueOf(reports.getQua_date());
        String model_name = String .valueOf(reports.getModel());
        String nextfollowupdate = String.valueOf(reports.getNxt_folllow_date());
        String bookindate = String.valueOf(reports.getConvertdate());
        holder.id.setText(idd);
        holder.cus_name.setText(customer_nname);
        holder.models.setText(model_name);
        holder.quaau_date.setText(bookindate);
        //holder.nexxxxtfodate.setText(nextfollowupdate);
//       holder.boodate.setText(bookindate);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Converted_Status.class);
                intent.putExtra("idd",idd);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id,cus_name,quaau_date,models,nexxxxtfodate,boodate;


        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.iddddd);
            cus_name=itemView.findViewById(R.id.cus_name);
            quaau_date=itemView.findViewById(R.id.qqqqdate);
            models = itemView.findViewById(R.id.modelstatus);
           // nexxxxtfodate=itemView.findViewById(R.id.qqqqdate);
            //boodate=itemView.findViewById(R.id.qqqqdate);

        }
    }

}

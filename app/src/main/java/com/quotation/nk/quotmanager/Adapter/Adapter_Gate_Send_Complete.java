package com.quotation.nk.quotmanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quotation.nk.quotmanager.Compelete_Status;
import com.quotation.nk.quotmanager.Gatepass_Status;
import com.quotation.nk.quotmanager.R;

import java.util.List;

public class Adapter_Gate_Send_Complete extends RecyclerView.Adapter<Adapter_Gate_Send_Complete.ViewHolder> {


    private Context context;
    private List<Gatepass_Status> list;

    Adapter_Gate_Send_Complete adapter;


    public Adapter_Gate_Send_Complete(Context context, List<Gatepass_Status> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_sending_list, parent, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Gatepass_Status gatepass_status = list.get(position);

        final String idd = String.valueOf(gatepass_status.getId());
        String from_data= String .valueOf(gatepass_status.getLocation_from());
        String to_data = String.valueOf(gatepass_status.getLocation_to());
        String dates = String .valueOf(gatepass_status.getDelivered_date());

        holder.id.setText(idd);
        holder.locationfrom.setText(from_data);
        holder.tolocation.setText(to_data);
        holder.dat.setText(dates);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Compelete_Status.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id,locationfrom,tolocation,dat;
        public ViewHolder(View itemView) {

            super(itemView);

            id = itemView.findViewById(R.id.id);
            locationfrom = itemView.findViewById(R.id.fromdeliver);
            tolocation   = itemView.findViewById(R.id.quantity_delivered);
            dat = itemView.findViewById(R.id.date_send);

        }
    }
}

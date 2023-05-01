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

import com.quotation.nk.quotmanager.FullDetails_Models;
import com.quotation.nk.quotmanager.Model.Full_Java;
import com.quotation.nk.quotmanager.R;

import java.util.List;

public class Adapter_Model2 extends RecyclerView.Adapter<Adapter_Model2.ViewHolder> {



    private Context context;
    private List<Full_Java> list;

    Adapter_Model2 adapter;

    public Adapter_Model2(Context context, List<Full_Java> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.full_detals_cardview, parent, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull Adapter_Model2.ViewHolder holder, int position) {
        Full_Java fullJava = list.get(position);

        final String idd = String.valueOf(fullJava.getId());

//        holder.id.setText(fullJava.getId());
        holder.modal_nm.setText(fullJava.getModelnm());
        holder.frmane_nm.setText(fullJava.getFrameno());
        holder.engine_nm.setText(fullJava.getEngineno());
        holder.date_details.setText(fullJava.getDatesupdate());
        holder.color_nm.setText(fullJava.getColornm());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FullDetails_Models.class);
                intent.putExtra("idd",idd);


                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() { return list.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id,modal_nm,frmane_nm,engine_nm,date_details,color_nm;


        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            modal_nm = itemView.findViewById(R.id.detailmodelname);
            frmane_nm   = itemView.findViewById(R.id.detailframeno);
            engine_nm   = itemView.findViewById(R.id.detailengineno);
            date_details   = itemView.findViewById(R.id.detailmanufacturingdate);
            color_nm   = itemView.findViewById(R.id.detailcolor);


        }
    }
}

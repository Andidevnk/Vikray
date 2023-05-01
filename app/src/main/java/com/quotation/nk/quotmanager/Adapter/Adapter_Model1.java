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
import com.quotation.nk.quotmanager.Model.Model_java;
import com.quotation.nk.quotmanager.R;

import java.util.List;

public class Adapter_Model1 extends RecyclerView.Adapter<Adapter_Model1.ViewHolder> {



    private Context context;
    private List<Model_java> list;

    Adapter_Model1 adapter;

    public Adapter_Model1(Context context, List<Model_java> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.model_update_list, parent, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull Adapter_Model1.ViewHolder holder, int position) {
        final Model_java modelJava = list.get(position);

        final String idd = String.valueOf(modelJava.getId());
        final String modelssss = String.valueOf(modelJava.getModelname());

        final String model_quantrity = String.valueOf(modelJava.getModel_qunatity());
        holder.modelcounting.setText(modelJava.getModel_qunatity());
        holder.modelnamess.setText(modelJava.getModelname());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FullDetails_Models.class);
                intent.putExtra("idd",idd);
                intent.putExtra("Modelsn",modelJava.getModelname());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() { return list.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id,modelnamess,modelcounting;


        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            modelnamess = itemView.findViewById(R.id.countingmodelsnames);
            modelcounting   = itemView.findViewById(R.id.modelcounting);


        }
    }
}

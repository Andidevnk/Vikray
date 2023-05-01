package com.quotation.nk.quotmanager.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.quotation.nk.quotmanager.Model.Update_java;
import com.quotation.nk.quotmanager.R;


import java.util.List;

public class Update_Adapter extends RecyclerView.Adapter<Update_Adapter.ViewHolder>  {

    private Context context;
    private List<Update_java> list;
    int i=1;



    Update_Adapter adapter;


    public Update_Adapter(Context context, List<Update_java> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.update_list_card, parent, false);
        return new ViewHolder(v);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Update_java updateJava = list.get(position);

        final String idd = String.valueOf(updateJava.getId());
        String location_from = String.valueOf(updateJava.getL_from());
        String location_to = String.valueOf(updateJava.getL_to());
        String fromperson = String.valueOf(updateJava.getFrom());
        String toperson = String.valueOf(updateJava.getTo());
        String dates = String.valueOf(updateJava.getDate());
        String model = String.valueOf(updateJava.getModell());
        String frame = String.valueOf(updateJava.getModelframe());
        String engine = String.valueOf(updateJava.getModelengine());


        holder.id.setText(idd);
        holder.l_form_location.setText(location_from);
        holder.l_to_location.setText(location_to);
        holder.from_to.setText(fromperson);
        holder.to_from.setText(toperson);
        holder.dates.setText(dates);
        holder.md_name.setText(model);
        holder.fr_name.setText(frame);
        holder.en_name.setText(engine);




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id,md_name,fr_name,en_name,l_form_location,l_to_location,from_to,to_from,dates;


        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idss);
            l_form_location = itemView.findViewById(R.id.updatelocationfrom);
            l_to_location = itemView.findViewById(R.id.updatelocationto);
            from_to = itemView.findViewById(R.id.updatefrom);
            to_from = itemView.findViewById(R.id.updateto);
            dates = itemView.findViewById(R.id.updatedate);
            md_name=itemView.findViewById(R.id.updatemodel);
            fr_name=itemView.findViewById(R.id.updateframe);
            en_name=itemView.findViewById(R.id.updateengine);




        }
    }
}

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

import com.quotation.nk.quotmanager.Model.Compelete_Java;
import com.quotation.nk.quotmanager.Model.Update_java;
import com.quotation.nk.quotmanager.R;

import java.util.List;

public class Compelete_Adapter extends RecyclerView.Adapter<Compelete_Adapter.ViewHolder> {

    private Context context;
    private List<Compelete_Java> list;
    int i = 1;


    Compelete_Adapter adapter;


    public Compelete_Adapter(Context context, List<Compelete_Java> list) {
        this.context = context;
        this.list = list;
    }

    {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.complete_card_list, parent, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Compelete_Java compeleteJava = list.get(position);

        final String idd = String.valueOf(compeleteJava.getId());
        String location_from = String.valueOf(compeleteJava.getL_from());
        String location_to = String.valueOf(compeleteJava.getL_to());
        String fromperson = String.valueOf(compeleteJava.getFrom());
        String toperson = String.valueOf(compeleteJava.getTo());
        String dates = String.valueOf(compeleteJava.getDate());
        String model = String.valueOf(compeleteJava.getModell());
        String frame = String.valueOf(compeleteJava.getModelframe());
        String engine = String.valueOf(compeleteJava.getModelengine());


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
        public TextView id, md_name, fr_name, en_name, l_form_location, l_to_location, from_to, to_from, dates;


        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idss);
            l_form_location = itemView.findViewById(R.id.updatelocationfrom);
            l_to_location = itemView.findViewById(R.id.updatelocationto);
            from_to = itemView.findViewById(R.id.updatefrom);
            to_from = itemView.findViewById(R.id.updateto);
            dates = itemView.findViewById(R.id.updatedate);
            md_name = itemView.findViewById(R.id.updatemodel);
            fr_name = itemView.findViewById(R.id.updateframe);
            en_name = itemView.findViewById(R.id.updateengine);


        }
    }

}

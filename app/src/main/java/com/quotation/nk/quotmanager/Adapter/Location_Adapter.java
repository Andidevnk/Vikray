package com.quotation.nk.quotmanager.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quotation.nk.quotmanager.Model.Location_java;
import com.quotation.nk.quotmanager.R;

import java.util.List;

public class Location_Adapter  extends RecyclerView.Adapter<Location_Adapter.ViewHolder> {


    private Context context;
    private List<Location_java> list;
    int i=1;



    Location_Adapter adapter;


    public Location_Adapter(Context context, List<Location_java> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.location_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Location_java locationJava = list.get(position);

        final String idd = String.valueOf(locationJava.getId());
        String location = String.valueOf(locationJava.getName_godown());
        String date = String.valueOf(locationJava.getHistorydate());

//        holder.id.setText(idd);
        holder.locationame.setText(location);
        holder.quaau_date.setText(date);

    }

    @Override
    public int getItemCount() { return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id, locationame, quaau_date;


        public ViewHolder(View itemView) {
            super(itemView);
            locationame = itemView.findViewById(R.id.locationname);
            quaau_date = itemView.findViewById(R.id.datessss);


        }
    }
}

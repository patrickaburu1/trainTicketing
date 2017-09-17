package com.example.user.trainticketingsystem.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.trainticketingsystem.Model.GetRoutes;
import com.example.user.trainticketingsystem.R;

import java.util.List;

/**
 * Created by user on 5/13/2017.
 */

public class RecyclerRouteAdapter extends RecyclerView.Adapter<RecyclerRouteAdapter.ViewHolder> {
    Context context;
    List<GetRoutes> getRoutes;

    public RecyclerRouteAdapter(List<GetRoutes> getRoutes,Context context){
        super();
        this.getRoutes=getRoutes;
        this.context=context;
    }


    @Override
    public RecyclerRouteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_routes, parent, false);

        RecyclerRouteAdapter.ViewHolder viewHolder = new RecyclerRouteAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerRouteAdapter.ViewHolder holder, int position) {

        GetRoutes getTicketAdapter1 = getRoutes.get(position);

        holder.id.setText(getTicketAdapter1.getId());
        holder.from.setText(getTicketAdapter1.getFrom());
        holder.to.setText(getTicketAdapter1.getTo());


    }

    //get counts
    @Override
    public int getItemCount() {

        return getRoutes.size();
    }


    //data holders

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id;
        public TextView from;
        public TextView to;



        public ViewHolder(View itemView) {

            super(itemView);

            id = (TextView) itemView.findViewById(R.id.routeId);
            from = (TextView) itemView.findViewById(R.id.routeFrom);
            to = (TextView) itemView.findViewById(R.id.routeDestination);


        }
    }


}
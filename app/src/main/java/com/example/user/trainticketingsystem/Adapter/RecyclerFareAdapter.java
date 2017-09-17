package com.example.user.trainticketingsystem.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.trainticketingsystem.Model.GetFareDetails;
import com.example.user.trainticketingsystem.R;

import java.util.List;

/**
 * Created by user on 5/13/2017.
 */

public class RecyclerFareAdapter extends RecyclerView.Adapter<RecyclerFareAdapter.ViewHolder> {
    Context context;
    List<GetFareDetails> getFare;

    public RecyclerFareAdapter(List<GetFareDetails> getFare,Context context){
        super();
        this.getFare=getFare;
        this.context=context;
    }


    @Override
    public RecyclerFareAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fare, parent, false);

        RecyclerFareAdapter.ViewHolder viewHolder = new RecyclerFareAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerFareAdapter.ViewHolder holder, int position) {

        GetFareDetails getFareAdapter1 = getFare.get(position);

        holder.train.setText(getFareAdapter1.getTrain());
        holder.from.setText(getFareAdapter1.getFrom());
        holder.to.setText(getFareAdapter1.getTo());
        holder.amount.setText(getFareAdapter1.getAmount());

    }

    //get counts
    @Override
    public int getItemCount() {

        return getFare.size();
    }


    //data holders

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView train;
        public TextView from;
        public TextView to;
        public TextView amount;


        public ViewHolder(View itemView) {

            super(itemView);

            train = (TextView) itemView.findViewById(R.id.fareTrainName);
            from = (TextView) itemView.findViewById(R.id.fareFrom);
            to = (TextView) itemView.findViewById(R.id.fareDestination);
            amount = (TextView) itemView.findViewById(R.id.fareAmount);


        }
    }



}

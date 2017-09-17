package com.example.user.trainticketingsystem.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.trainticketingsystem.Model.GetSchedule;
import com.example.user.trainticketingsystem.R;

import java.util.List;

/**
 * Created by user on 5/21/2017.
 */

public class RecyclerScheduleAdapter extends RecyclerView.Adapter<RecyclerScheduleAdapter.ViewHolder> {
    Context context;
    List<GetSchedule> getSchedule;

    public RecyclerScheduleAdapter(List<GetSchedule> getSchedule,Context context){
        super();
        this.getSchedule=getSchedule;
        this.context=context;
    }


    @Override
    public RecyclerScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_schedule, parent, false);

        RecyclerScheduleAdapter.ViewHolder viewHolder = new RecyclerScheduleAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerScheduleAdapter.ViewHolder holder, int position) {

        GetSchedule getTicketAdapter1 = getSchedule.get(position);

        holder.train.setText(getTicketAdapter1.getTrainName());
        holder.departure.setText(getTicketAdapter1.getDeparture());
        holder.arrival.setText(getTicketAdapter1.getArrival());
        holder.to.setText(getTicketAdapter1.getTo());
        holder.from.setText(getTicketAdapter1.getFrom());
    }

    //get counts
    @Override
    public int getItemCount() {

        return getSchedule.size();
    }


    //data holders

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView train,to,from,arrival,departure;


        public ViewHolder(View itemView) {

            super(itemView);

            train = (TextView) itemView.findViewById(R.id.scheduleTrainName);
            departure = (TextView) itemView.findViewById(R.id.scheduleDeparture);
            arrival = (TextView) itemView.findViewById(R.id.scheduleArrival);
            to = (TextView) itemView.findViewById(R.id.scheduleTo);
            from = (TextView) itemView.findViewById(R.id.scheduleFrom);

        }
    }



}

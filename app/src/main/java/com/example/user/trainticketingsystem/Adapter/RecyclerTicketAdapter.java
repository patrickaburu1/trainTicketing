package com.example.user.trainticketingsystem.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.trainticketingsystem.Model.GetTickets;
import com.example.user.trainticketingsystem.R;

import java.util.List;

/**
 * Created by user on 5/13/2017.
 */

public class RecyclerTicketAdapter  extends RecyclerView.Adapter<RecyclerTicketAdapter.ViewHolder> {
    Context context;
    List<GetTickets> getTickets;

    public RecyclerTicketAdapter(List<GetTickets> getTickets,Context context){
        super();
        this.getTickets=getTickets;
        this.context=context;
    }


    @Override
    public RecyclerTicketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_tickets, parent, false);

        RecyclerTicketAdapter.ViewHolder viewHolder = new RecyclerTicketAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerTicketAdapter.ViewHolder holder, int position) {

        GetTickets getTicketAdapter1 = getTickets.get(position);

        holder.ticketId.setText(getTicketAdapter1.getId());
        holder.from.setText(getTicketAdapter1.getFrom());
        holder.destination.setText(getTicketAdapter1.getDestination());
        holder.amount.setText(getTicketAdapter1.getAmount());

    }

    //get counts
    @Override
    public int getItemCount() {

        return getTickets.size();
    }


    //data holders

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ticketId;
        public TextView from;
        public TextView destination;
        public TextView amount;


        public ViewHolder(View itemView) {

            super(itemView);

            ticketId = (TextView) itemView.findViewById(R.id.ticketId);
            from = (TextView) itemView.findViewById(R.id.ticketFrom);
            destination = (TextView) itemView.findViewById(R.id.ticketDestination);
            amount = (TextView) itemView.findViewById(R.id.ticketAmount);


        }
    }


}
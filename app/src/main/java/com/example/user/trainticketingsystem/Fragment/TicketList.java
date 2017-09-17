package com.example.user.trainticketingsystem.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.trainticketingsystem.Adapter.RecyclerTicketAdapter;
import com.example.user.trainticketingsystem.LoginActivity;
import com.example.user.trainticketingsystem.Model.GetTickets;
import com.example.user.trainticketingsystem.Network.URLs;
import com.example.user.trainticketingsystem.R;
import com.example.user.trainticketingsystem.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TicketList extends Fragment {

    List<GetTickets> GetTicket1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    ProgressBar progressBar;

    //testing response
    String JSON_ID = "id";
    String JSON_FROM = "from";
    String JSON_DESTINATION = "to";
    String JSON_AMOUNT = "amount";

    RequestQueue requestQueue ;



    public static TicketList newInstance(String param1, String param2) {
        TicketList fragment = new TicketList();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_ticket_list, container, false);


        GetTicket1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewTickets);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarTicket);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        tickets();

        // CardView listOfTickets= (CardView) view.findViewById(R.id.listOfTickets);
//        recyclerView.addOnItemTouchListener(new RecyclerTicketAdapter.R);



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    public void tickets(){

        SharedPreferences editorget=getActivity().getSharedPreferences(LoginActivity.MYSHAREDPREFS, LoginActivity.MODE_PRIVATE);
        String user_id= editorget.getString("user_id",null);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLs.TICKETS+"/"+user_id,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        progressBar.setVisibility(View.GONE);

                        json_parse_data(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
//                                .setTitleText("Oops...!")
//                                .setContentText("Something went wrong!PLEASE CHECK YOUR NETWORK CONNECTION")
//                                .show();
                        Toast.makeText(getActivity(), "Something went wrong!PLEASE CHECK YOUR NETWORK CONNECTION", Toast.LENGTH_SHORT).show();


                    }
                });

        requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(jsonArrayRequest);
    }

    public void  json_parse_data(JSONArray array){
        for(int i = 0; i<array.length(); i++) {

            GetTickets GetDataAdapter2 = new GetTickets();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);


                GetDataAdapter2.setId(json.getString(JSON_ID));
                GetDataAdapter2.setFrom(json.getString(JSON_FROM));
                GetDataAdapter2.setDestination(json.getString(JSON_DESTINATION));
                GetDataAdapter2.setAmount(json.getString(JSON_AMOUNT));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetTicket1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerTicketAdapter(GetTicket1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);
    }
    }

package com.example.user.trainticketingsystem.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.trainticketingsystem.Adapter.RecyclerFareAdapter;
import com.example.user.trainticketingsystem.Adapter.RecyclerTicketAdapter;
import com.example.user.trainticketingsystem.Model.GetFareDetails;
import com.example.user.trainticketingsystem.Model.GetTickets;
import com.example.user.trainticketingsystem.Network.URLs;
import com.example.user.trainticketingsystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FareDetails extends Fragment {

    List<GetFareDetails> GetGetFareDetails1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    ProgressBar progressBar;

    //testing
    String JSON_NAME = "train";
    String JSON_FROM = "from";
    String JSON_DESTINATION = "to";
    String JSON_AMOUNT = "amount";

    RequestQueue requestQueue ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FareDetails() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FareDetails newInstance(String param1, String param2) {
        FareDetails fragment = new FareDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fare_details, container, false);

        GetGetFareDetails1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewFare);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarFare);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        fareDetails();

        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void fareDetails(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLs.FAREDETAILS,

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

            GetFareDetails GetDataAdapter2 = new GetFareDetails();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);


                GetDataAdapter2.setTrain(json.getString(JSON_NAME));
                GetDataAdapter2.setFrom(json.getString(JSON_FROM));
                GetDataAdapter2.setTo(json.getString(JSON_DESTINATION));
                GetDataAdapter2.setAmount(json.getString(JSON_AMOUNT));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetGetFareDetails1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerFareAdapter(GetGetFareDetails1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);
    }
}

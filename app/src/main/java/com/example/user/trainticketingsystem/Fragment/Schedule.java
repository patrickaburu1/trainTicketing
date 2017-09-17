package com.example.user.trainticketingsystem.Fragment;

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
import com.example.user.trainticketingsystem.Adapter.RecyclerScheduleAdapter;
import com.example.user.trainticketingsystem.Model.GetSchedule;
import com.example.user.trainticketingsystem.Network.URLs;
import com.example.user.trainticketingsystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Schedule extends Fragment {
    List<GetSchedule> GetSchedule1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    ProgressBar progressBar;

    //testing
    String JSON_TRAIN = "train_name";
    String JSON_ARRIVAL = "arrival";
    String JSON_DEPARTURE = "departure";
    String JSON_TO = "to";
    String JSON_FROM = "from";


    RequestQueue requestQueue ;
   

    public Schedule() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_schedule, container, false);

        GetSchedule1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSchedule);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarSchedule);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        schedules();
        return view;
    }
    public void  schedules(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLs.SCHEDULE,

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

            GetSchedule GetDataAdapter2 = new GetSchedule();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);


                GetDataAdapter2.setTrainName(json.getString(JSON_TRAIN));
                GetDataAdapter2.setArrival(json.getString(JSON_ARRIVAL));
                GetDataAdapter2.setDeparture(json.getString(JSON_DEPARTURE));
                GetDataAdapter2.setFrom(json.getString(JSON_FROM));
                GetDataAdapter2.setTo(json.getString(JSON_TO));


            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetSchedule1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerScheduleAdapter(GetSchedule1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);
    }
   
}

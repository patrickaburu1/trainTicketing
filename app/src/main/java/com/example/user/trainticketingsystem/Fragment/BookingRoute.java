package com.example.user.trainticketingsystem.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.trainticketingsystem.Network.AppController;
import com.example.user.trainticketingsystem.Network.CustomRequest;
import com.example.user.trainticketingsystem.Network.URLs;
import com.example.user.trainticketingsystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRoute extends Fragment {
    Spinner from;
    public  static String fromm,destinationn,trainn,amountt;
    ProgressDialog pDialog;
    public static  EditText amount;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button continueBooking;

    private OnFragmentInteractionListener mListener;

    public BookingRoute() {
        // Required empty public constructor
    }


    public static BookingRoute newInstance(String param1, String param2) {
        BookingRoute fragment = new BookingRoute();
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
        View view= inflater.inflate(R.layout.fragment_booking_route, container, false);

        continueBooking= (Button) view.findViewById(R.id.continueToBooking);

        // Spinner element
        from = (Spinner) view.findViewById(R.id.from);
        final Spinner destination= (Spinner) view.findViewById(R.id.destination);
        final Spinner trainName= (Spinner) view.findViewById(R.id.trainName);
        amount= (EditText) view.findViewById(R.id.bookingAmount);

        // Spinner click listener

        // Spinner From
        List<String> fromList = new ArrayList<String>();
        fromList.add("Islamabad");
        fromList.add("Gujranwala");
        fromList.add("Lahore");
        fromList.add("Faisla");
        fromList.add("Multan");
        fromList.add("Bahawal");
        fromList.add("Karachi");

        // Creating adapter for from spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, fromList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner from
        from.setAdapter(dataAdapter);
        
        
        // Spinner Destination
        List<String> destinationList = new ArrayList<String>();
        destinationList.add("Karachi");
        destinationList.add("Bahawal");
        destinationList.add("Multan");
        destinationList.add("Faisla");
        destinationList.add("Lahore");
        destinationList.add("Gujranwala");
        destinationList.add("Islamabad");

        // Creating adapter for from spinner
        ArrayAdapter<String> destinationAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, destinationList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner from
        destination.setAdapter(destinationAdapter);

        // Spinner Train Name
        List<String> trainNameList = new ArrayList<String>();
        trainNameList.add("Allama Iqbal");
        trainNameList.add("Lassani Express");
        trainNameList.add("Taiz ghaam");
        trainNameList.add("Night Coach");
        trainNameList.add("Pakistan Express");



        // Creating adapter for train name spinner
        ArrayAdapter<String> trainAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, trainNameList);

        // Drop down layout style - list view with radio button
        trainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner from
        trainName.setAdapter(trainAdapter);

        continueBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fromm=String.valueOf(from.getSelectedItem());
                destinationn=String.valueOf(destination.getSelectedItem());
                trainn=String.valueOf(trainName.getSelectedItem());
               // Toast.makeText(getActivity(), "Selected: " + fromm, Toast.LENGTH_LONG).show();
               // nextToBooking();
                getAmount();
            }
        });

        return  view;
    }



    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }
    public  void nextToBooking(){
        FragmentTransaction f=getActivity().getSupportFragmentManager().beginTransaction();
        f.replace(R.id.mainPage, new Book(), getString(R.string.app_name));
        f.commit();
    }

    public void getAmount(){
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading......................Hold On!");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

       // date=other_date.getText().toString();
        Map<String, String> params = new HashMap<String, String>();
        params.put("from", fromm);
        params.put("to", destinationn);
//        params.put("password", password);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, URLs.BOOKROUTE, params,
                new Response.Listener<JSONObject>() {
                    String success;

                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            success = response.getString("amount");
                            if (success !="null") {
                                Log.d("Amount Successful!", response.toString());
                                amountt = response.getString("amount");
                               // amount.setText(amountt);

                               // Toast.makeText(getActivity(), amountt, Toast.LENGTH_SHORT).show();
                                FragmentTransaction f=getActivity().getSupportFragmentManager().beginTransaction();
                                f.replace(R.id.mainPage, new Book(), getString(R.string.app_name));
                                f.commit();
                            } else {
                                pDialog.dismiss();
                                Log.d("Amount Failure!", response.toString());
//                                new SweetAlertDialog(getActivity(), SweetAlertDialog.NORMAL_TYPE)
//                                        .setTitleText("Hi..")
//                                        .setContentText("no records found  date::  "+date)
//                                        .show();
                                 Toast.makeText(getActivity(), "no such route found ", Toast.LENGTH_SHORT).show();
                                //totalsales.setText("KSH:::   "+00.0);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                pDialog.dismiss();
                Log.d("Response: ", response.toString());
//                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
//                        .setTitleText("Oops!!")
//                        .setContentText("Somthing went wrong please try again ")
//                        .show();
                Toast.makeText(getActivity(), "Somthing went wrong please try again ", Toast.LENGTH_SHORT).show();
                //totalsales.setText("00.00");

            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
}

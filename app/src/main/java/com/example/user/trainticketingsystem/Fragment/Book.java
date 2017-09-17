package com.example.user.trainticketingsystem.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.trainticketingsystem.LoginActivity;
import com.example.user.trainticketingsystem.Network.AppController;
import com.example.user.trainticketingsystem.Network.CustomRequest;
import com.example.user.trainticketingsystem.Network.URLs;
import com.example.user.trainticketingsystem.R;
import com.example.user.trainticketingsystem.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.user.trainticketingsystem.Fragment.BookingRoute.amountt;
import static com.example.user.trainticketingsystem.Fragment.BookingRoute.destinationn;
import static com.example.user.trainticketingsystem.Fragment.BookingRoute.fromm;
import static com.example.user.trainticketingsystem.Fragment.BookingRoute.trainn;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Book.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Book#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Book extends Fragment {
    EditText from,destination,train,amount;
    String fromString,destinationString,trainString,amountString;
    ProgressDialog pDialog;
    Button book;
    SharedPreferences pref;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Book() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Book.
     */
    // TODO: Rename and change types and number of parameters
    public static Book newInstance(String param1, String param2) {
        Book fragment = new Book();
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
        View view= inflater.inflate(R.layout.fragment_book, container, false);
         from= (EditText) view.findViewById(R.id.bookingFrom);
         destination= (EditText) view.findViewById(R.id.bookingDestination);
         train= (EditText) view.findViewById(R.id.bookingTrainName);
         amount= (EditText) view.findViewById(R.id.bookingAmount);

        from.setText(fromm);
        destination.setText(destinationn);
        train.setText(trainn);
        amount.setText(amountt);

        book= (Button) view.findViewById(R.id.book);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book();
            }
        });



        return view;

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
    public void book(){
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading......................Hold On!");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        fromString=from.getText().toString();
        destinationString=destination.getText().toString();
        trainString=train.getText().toString();
        amountString=amount.getText().toString();

       SharedPreferences editorget=getActivity().getSharedPreferences(LoginActivity.MYSHAREDPREFS, LoginActivity.MODE_PRIVATE);
        String user_id= editorget.getString("user_id",null);

        // date=other_date.getText().toString();
        Map<String, String> params = new HashMap<String, String>();
        params.put("from", fromString);
        params.put("to", destinationString);
        params.put("train", trainString);
        params.put("amount", amountString);
       params.put("user_id", user_id);

        //Toast.makeText(getActivity(), user_id, Toast.LENGTH_SHORT).show();

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, URLs.BOOK, params,
                new Response.Listener<JSONObject>() {
                    int success;

                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            success = response.getInt("id");
                            if (success >=1) {


                                Toast.makeText(getActivity(), "Successfully Booked", Toast.LENGTH_SHORT).show();

                                FragmentTransaction f=getActivity().getSupportFragmentManager().beginTransaction();
                                f.replace(R.id.mainPage, new Home(), getString(R.string.app_name));
                                f.commit();

                            } else {
                                pDialog.dismiss();
                                Log.d("Amount Failure!", response.toString());
//                                new SweetAlertDialog(getActivity(), SweetAlertDialog.NORMAL_TYPE)
//                                        .setTitleText("Hi..")
//                                        .setContentText("no records found  date::  "+date)
//                                        .show();
                                Toast.makeText(getActivity(), "Error ", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "Something went wrong please try again ", Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
}

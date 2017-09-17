package com.example.user.trainticketingsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.trainticketingsystem.Network.AppController;
import com.example.user.trainticketingsystem.Network.CustomRequest;
import com.example.user.trainticketingsystem.Network.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
EditText username,fname,lname,email,password;
    Button register;
    String username1,fname1,lname1,email1,password1;

    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username= (EditText) findViewById(R.id.registerUsername);
        fname= (EditText) findViewById(R.id.registerFirstName);
        lname= (EditText) findViewById(R.id.registerLastName);
        email= (EditText) findViewById(R.id.registerEmail);
        password= (EditText) findViewById(R.id.registerPassword);

        register= (Button) findViewById(R.id.createAccount);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }
    public void register(){
         username1=username.getText().toString();
         fname1=fname.getText().toString();
        lname1=lname.getText().toString();
        email1=email.getText().toString();
        password1=password.getText().toString();



        pDialog = new ProgressDialog(RegisterActivity.this);
        pDialog.setMessage("Creating Account...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username1);
        params.put("password", password1);
        params.put("firstname", fname1);
        params.put("lastname", lname1);
        params.put("email", email1);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, URLs.SIGNUP, params, new Response.Listener<JSONObject>() {
            int success;
            String suc;

            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                //google.setVisibility(View.INVISIBLE);
                try {
                    success = response.getInt("id");
                    if (success >= 1) {
                        Log.d("Login Successful!", response.toString());
                        final String id = response.getString("id");
                        final String firstname = response.getString("firstname");
                        final String email = response.getString("email");

//                            Toast.makeText(LoginActivity.this, "WELCOME " + firstname, Toast.LENGTH_SHORT).show();
//                                final String lastname = response.getString("last_name");

                        // Creating user login session
                        // For testing i am stroing name, email as follow
                        // Use user real data
//                            session.createLoginSession(firstname, email, id);
//
//                            String firstn=getSharedPreferences(email,0);

//                        SharedPreferences.Editor editor=getSharedPreferences(MYSHAREDPREFS, MODE_PRIVATE).edit();
//                        editor.putString("user_id",id);
//                        editor.putString("firstname",firstname);
//                        editor.putString("email",email);
//                        editor.commit();

                        Toast.makeText(RegisterActivity.this, "Successfully Registered " + firstname, Toast.LENGTH_SHORT).show();

                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                pDialog.dismiss();
                //  google.setVisibility(View.INVISIBLE);
                Log.d("Response: ", response.toString());
//                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
//                        .setTitleText("OOPS")
//                        .setContentText("INCORRECT USERNAME OR PASSWORD")
//                        .show();
                Toast.makeText(RegisterActivity.this, "NETWORK ERROR", Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
}

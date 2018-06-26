package com.example.norhan.firebasetry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUP2 extends AppCompatActivity implements View.OnClickListener {


    private EditText editTextUsername, editTextEmail, editTextPassword,editTextPhone,editTextAddress;
    private Button buttonRegister;
    private ProgressDialog progressDialog;

    private TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, CarServiceActivity.class));//////go to another screen
            return;
        }
        editTextUsername = (EditText) findViewById(R.id.editTextUsernameid);
        editTextEmail = (EditText) findViewById(R.id.editeTextEmailid);

        editTextPassword = (EditText) findViewById(R.id.editTextPasswordid);
        editTextPhone = (EditText) findViewById(R.id.editTextPhoneid);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);


        buttonRegister = (Button) findViewById(R.id.register);

        progressDialog = new ProgressDialog(this);

        buttonRegister.setOnClickListener(this);

    }

    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();
        final String address = editTextAddress.getText().toString().trim();
        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .setUserInfo(
                                                obj.getInt("personID")
                                        );
                                startActivity(new Intent(getApplicationContext(),Home.class));
                                finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                            Log.d("Main Activity", "onResponse: "+response);

                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Main Activity", "onResponse: error "+response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), Constants.URL_REGISTER+" "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("personName", username);
                params.put("email", email);
                params.put("password", password);
                params.put("telephone", phone);
                params.put("address", address);
                params.put("token",String.valueOf(SharedPrefManager.getInstance(getApplicationContext()).getToken()));

                // params.put("token",String.valueOf(SharedPrefManager.getInstance(getApplicationContext()).getToken()));
                Log.d("signup", "getParams: token is "+String.valueOf(SharedPrefManager.getInstance(getApplicationContext()).getToken()));


                return params;
            }
        };


        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister)
            registerUser();
        if(view == textViewLogin)
            startActivity(new Intent(this, SignIn.class));
    }
}

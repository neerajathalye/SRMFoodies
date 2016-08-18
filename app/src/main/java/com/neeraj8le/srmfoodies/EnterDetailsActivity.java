package com.neeraj8le.srmfoodies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EnterDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout nameEditText, phoneNumberEditText, emailEditText, addressEditText, dobEditText;
    Button submitButton;
    boolean dataEntered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        nameEditText = (TextInputLayout) findViewById(R.id.name_edit_text);
        phoneNumberEditText = (TextInputLayout) findViewById(R.id.phone_number_edit_text);
        emailEditText = (TextInputLayout) findViewById(R.id.email_edit_text);
        addressEditText = (TextInputLayout) findViewById(R.id.address_edit_text);
        dobEditText = (TextInputLayout) findViewById(R.id.dob_edit_text);
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        dataEntered = sharedPref.getBoolean("dataEntered", false);

        if(dataEntered)
        {
            Intent intent = new Intent(EnterDetailsActivity.this, NavDrawerActivity.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit_button) {
            if (nameEditText.getEditText().getText().toString().trim().equals(""))
                nameEditText.setError("Name cannot be empty");
            else if (phoneNumberEditText.getEditText().getText().toString().trim().equals(""))
                phoneNumberEditText.setError("Phone number cannot be empty");
            else if (phoneNumberEditText.getEditText().getText().toString().trim().length() < 10)
                phoneNumberEditText.setError(getString(R.string.length_error_message));
            else if (emailEditText.getEditText().getText().toString().trim().equals(""))
                emailEditText.setError("Email cannot be empty");
            else if (!(emailEditText.getEditText().getText().toString().trim().contains("@")))
                emailEditText.setError(getString(R.string.invalid_email_error_message));
            else if (addressEditText.getEditText().getText().toString().trim().equals(""))
                addressEditText.setError("Address cannot be empty");
            else if (dobEditText.getEditText().getText().toString().trim().equals(""))
                dobEditText.setError("Date of birth cannot be empty");
            else if (dobEditText.getEditText().getText().toString().trim().length() < 10)
                dobEditText.setError(getString(R.string.invalid_date_error_message));
            else {

                final String name = nameEditText.getEditText().getText().toString().trim();
                final String phoneNumber = phoneNumberEditText.getEditText().getText().toString().trim();
                final String email = emailEditText.getEditText().getText().toString().trim();
                final String address = addressEditText.getEditText().getText().toString().trim();
                final String dob = dobEditText.getEditText().getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, CONSTANTS.JSON_USER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Intent intent = new Intent(EnterDetailsActivity.this, NavDrawerActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(EnterDetailsActivity.this, "Unable to connect to the server...", Toast.LENGTH_LONG).show();
                                Toast.makeText(EnterDetailsActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put(CONSTANTS.KEY_NAME, name);
                        params.put(CONSTANTS.KEY_PHONE_NUMBER, phoneNumber);
                        params.put(CONSTANTS.KEY_EMAIL, email);
                        params.put(CONSTANTS.KEY_ADDRESS, address);
                        params.put(CONSTANTS.KEY_DOB, dob);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

                dataEntered = true;
                SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("dataEntered", dataEntered);
                editor.apply();
            }

        }

    }
}

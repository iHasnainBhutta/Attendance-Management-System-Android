package com.example.ali_hasnain_2k18_bscs_116;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class admin extends AppCompatActivity {
    AppCompatButton algbtn;
    static AppCompatEditText aalemail,aalpassword;
    static String apassemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        aalemail=findViewById(R.id.alemail);
        aalpassword=findViewById(R.id.alpassword);
        algbtn=findViewById(R.id.albtn);

        algbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ae = aalemail.getText().toString();
                String apas = aalpassword.getText().toString();

                if(ae.equals("") && apas.equals("") )
                {


                    aalpassword.setError("Please enter password");
                    aalpassword.requestFocus();
                    aalemail.setError("Please enter rollno");
                    aalemail.requestFocus();
                    return;

                }
                else if(ae.equals(""))
                {
                    aalemail.setError("Please enter rollno");
                    aalemail.requestFocus();
                    return;
                }
                else if(apas.equals(""))
                {
                    aalpassword.setError("Please enter password");
                    aalpassword.requestFocus();
                    return;
                }


                else {

                    final JSONObject emptyJsonObject = new JSONObject();
                    JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET,linkapi.url+"admin.php?aemail="+ae+"&apassword="+apas+"",emptyJsonObject,new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                               boolean bol1 = response.getBoolean("success");
                                if(bol1) {
                                    apassemail = ae;
                                    Intent intent2 = new Intent(admin.this, admindashboard.class);
                                    startActivity(intent2);
                                    Toast.makeText(admin.this, "Login Successfull", Toast.LENGTH_LONG).show();


                                } else {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(admin.this);
                                    alert.setMessage("Email or password is incorrect").setNegativeButton("Retry", null).create().show();
                                }
                            } catch (Exception ex) {
                                Log.d("", "" + ex);
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest2);
                }
            }
        });

    }
}
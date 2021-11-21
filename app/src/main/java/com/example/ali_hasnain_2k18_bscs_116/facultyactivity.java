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

public class facultyactivity extends AppCompatActivity {
    AppCompatButton flgbtn,fregbtn;
    static AppCompatEditText flemail,flpassword;
    static String passemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultyactivity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        flemail=findViewById(R.id.flemail);
        flpassword=findViewById(R.id.flpassword);
        flgbtn=findViewById(R.id.flbtn);
        fregbtn=findViewById(R.id.flregister);

        fregbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"iHassnainBhutta@gmail.com"});  //developer 's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        ""); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "" + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Contact Us"));
            }
        });

        flgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fe = flemail.getText().toString();
                String fpas = flpassword.getText().toString();

                if(fe.equals("") && fpas.equals("") )
                {


                    flpassword.setError("Please enter password");
                    flpassword.requestFocus();
                    flemail.setError("Please enter rollno");
                    flemail.requestFocus();
                    return;

                }
                else if(fe.equals(""))
                {
                    flemail.setError("Please enter rollno");
                    flemail.requestFocus();
                    return;
                }
                else if(fpas.equals(""))
                {
                    flpassword.setError("Please enter password");
                    flpassword.requestFocus();
                    return;
                }


                else {


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, linkapi.url+"facultylogin.php?facultyemail="+fe+"&facultypassword="+fpas+"", null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean bol = response.getBoolean("success");
                                if (bol) {
                                    passemail = fe;
                                    Intent intent2 = new Intent(facultyactivity.this, facultyhome.class);
                                    startActivity(intent2);
                                    Toast.makeText(facultyactivity.this, "Login Successfull", Toast.LENGTH_LONG).show();


                                } else {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(facultyactivity.this);
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
                    Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);
                }
            }
        });

    }
}
package com.example.ali_hasnain_2k18_bscs_116;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

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

import java.util.Locale;

public class studentactivity extends AppCompatActivity {
    AppCompatButton register,login;
    static AppCompatEditText slrolno,slpassword;
   static String passrollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentactivity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        register= findViewById(R.id.slregister);
        login=findViewById(R.id.slbtn);

        slrolno=findViewById(R.id.slrolno);
        slpassword=findViewById(R.id.slpassword);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(studentactivity.this, studentRegister.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String r = slrolno.getText().toString();
                String pas = slpassword.getText().toString();

                if(r.equals("") && pas.equals("") )
                {


                    slpassword.setError("Please enter password");
                    slpassword.requestFocus();
                    slrolno.setError("Please enter rollno");
                    slrolno.requestFocus();
                    return;

                }
                else if(r.equals(""))
                {
                    slrolno.setError("Please enter rollno");
                    slrolno.requestFocus();
                    return;
                }
                else if(pas.equals(""))
                {
                    slpassword.setError("Please enter password");
                    slpassword.requestFocus();
                    return;
                }


                else {


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, linkapi.url + "studentlogin.php?slrolno="+r+"&slpassword="+pas+"", null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean bol = response.getBoolean("success");
                                if (bol) {
                                    passrollno = r;
                                    Intent intent2 = new Intent(studentactivity.this, studentHome.class);
                                    startActivity(intent2);
                                    Toast.makeText(studentactivity.this, "Login Successfull", Toast.LENGTH_LONG).show();


                                } else {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(studentactivity.this);
                                    alert.setMessage("Rollno or password is incorrect").setNegativeButton("Retry", null).create().show();
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
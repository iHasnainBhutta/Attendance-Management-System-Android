
package com.example.ali_hasnain_2k18_bscs_116;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class admindashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    static LinearLayout logout,aheader;
    AppCompatTextView ausername, aheader_username, aheader_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ausername=(AppCompatTextView)findViewById(R.id.ausernametxt);

        /*for header text*/
        drawerLayout = findViewById(R.id.adrawer_layout);
        navigationView = findViewById(R.id.nav_viewa);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        aheader=(LinearLayout)findViewById(R.id.aheader);

        View view =navigationView.getHeaderView(0);
        aheader_username=view.findViewById(R.id.aheader_username);
        aheader_email=view.findViewById(R.id.aheader_mail);
        /*End*/

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navi_drawer_open, R.string.navi_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        /*CardViews Onclick Listener*/
        logout = (LinearLayout) findViewById(R.id.alogout);
        logout.setOnClickListener(this);
        /*End*/

        /*for clear edittext*/
        admin.aalemail.getText().clear();
        admin.aalpassword.getText().clear();
        /*end*/

        view_profilef();

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {
            case R.id.alogout:
                i = new Intent(admindashboard.this, MainActivity.class);
                startActivity(i);
                finish();
                Toast.makeText(admindashboard.this, "Successfully Logout", Toast.LENGTH_LONG).show();
                break;


        }


    }

    private void view_profilef() {

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, linkapi.url+"current_admin.php?aemail="+admin.apassemail+"",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0; i<jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ausername.setText(""+jsonObject.getString("aname"));
                                aheader_username.setText(""+jsonObject.getString("aname"));
                                aheader_email.setText(""+jsonObject.getString("aemail"));


                            }
                        } catch (Exception ex) {
                            Log.d("", "" + ex);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest2);


 /*   @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true ;
    }
*/
    }
}

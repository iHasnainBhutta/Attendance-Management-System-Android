package com.example.ali_hasnain_2k18_bscs_116;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

public class studentHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    public CardView logout;
    public LinearLayout slogout,sheader;
    GridLayout grid_layout;
    public AppCompatTextView h_username,sheaderusername,sheaderemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        h_username=(AppCompatTextView)findViewById(R.id.husername);

        /*Navigation Header*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        sheader=(LinearLayout)findViewById(R.id.header);
        
        View view =navigationView.getHeaderView(0);
        sheaderusername=view.findViewById(R.id.header_username);
        sheaderemail=view.findViewById(R.id.header_mail);
        /*---*/


        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        grid_layout=(GridLayout)findViewById(R.id.grid_layout);
        grid_layout.setOnClickListener(this);
        
        /*CardView*/
        slogout= (LinearLayout)findViewById(R.id.slogout);
        slogout.setOnClickListener(this);
        /*-end-*/


        /*for clear edit text after login*/
        studentactivity.slrolno.getText().clear();
        studentactivity.slpassword.getText().clear();
        /*---*/


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navi_drawer_open,R.string.navi_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        view_profile();

    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        return true ;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        /*for CardView OnClick Listener*/
        Intent i;
        switch (v.getId())
        {
            case R.id.slogout:
                i = new Intent(studentHome.this,  studentactivity.class);
                startActivity(i);
                finish();
                Toast.makeText(studentHome.this, "Successfully Logout", Toast.LENGTH_LONG).show();
                break;
        }/*end*/


    }

    private void view_profile() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, linkapi.url + "current_user.php?srollno="+studentactivity.passrollno+"",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                h_username.setText(""+jsonObject.getString("studentname"));
                                sheaderusername.setText(""+jsonObject.getString("studentname"));
                                sheaderemail.setText(""+jsonObject.getString("semail"));


                            }
                        }
                        catch (Exception ex)
                        {
                            Log.d("",""+ex);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }


}
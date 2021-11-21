
package com.example.ali_hasnain_2k18_bscs_116;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;

public class facultyhome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    static LinearLayout logout, fmarkattendace, fheader;
    AppCompatTextView fusername, fheader_username, fheader_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultyhome);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fusername = (AppCompatTextView) findViewById(R.id.usernametxt);




        /*for header text*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_viewf);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        fheader = (LinearLayout) findViewById(R.id.fheader);

        View view = navigationView.getHeaderView(0);
        fheader_username = view.findViewById(R.id.fheader_username);
        fheader_email = view.findViewById(R.id.fheader_mail);
        /*End*/

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navi_drawer_open, R.string.navi_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        /*CardViews Onclick Listener*/
        logout = (LinearLayout) findViewById(R.id.flogout);
        logout.setOnClickListener(this);

        fmarkattendace = (LinearLayout) findViewById(R.id.markattendace);
        fmarkattendace.setOnClickListener(this);

        /*End*/
        /*For clear*/
        facultyactivity.flemail.getText().clear();
        facultyactivity.flpassword.getText().clear();
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
            case R.id.flogout:
                i = new Intent(facultyhome.this, facultyactivity.class);
                startActivity(i);
                finish();
                Toast.makeText(facultyhome.this, "Successfully Logout", Toast.LENGTH_LONG).show();
                break;


        }


        switch (v.getId()) {
            case R.id.markattendace:
                    Intent intent = new Intent(facultyhome.this,markattendance.class);
                    startActivity(intent);
                    break;




                /*AlertDialog.Builder alert=new AlertDialog.Builder(facultyhome.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_spinner,null);
                alert.setTitle("Select");
                final Spinner mSpinner = (Spinner)mView.findViewById(R.id.deptspinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(facultyhome.this,R.layout.spinner,getResources().getStringArray(R.array.departmentlist));
                adapter.setDropDownViewResource(R.layout.spinner);
                mSpinner.setAdapter(adapter);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Select Department"))
                        {
                            Toast.makeText(facultyhome.this,mSpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alert.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.setView(mView);
                AlertDialog dialog = alert.create();
                dialog.show();
                break;
*/
        }

    }

    private void view_profilef() {

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, linkapi.url+"current_faculty.php?facultyemail="+facultyactivity.passemail+"",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0; i<jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                fusername.setText(""+jsonObject.getString("facultyname"));
                                fheader_username.setText(""+jsonObject.getString("facultyname"));
                                fheader_email.setText(""+jsonObject.getString("facultyemail"));


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

package com.example.ali_hasnain_2k18_bscs_116;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class markattendance extends AppCompatActivity {

    RecyclerView recyclerView;
    List<view_user_class> view_user_classes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markattendance);

        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        view_user_classes=new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        view_user();
    }

    private void view_user() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, linkapi.url + "view_student.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONArray jsonArray=new JSONArray(response);
                    Log.d("","Here"+jsonArray.get(0));
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        Log.d("Loop","");
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        view_user_classes.add(new view_user_class(
                                jsonObject.getInt("id"),
                                jsonObject.getString("studentname"),
                                jsonObject.getString("srollno"),
                                jsonObject.getString("degree"),
                                jsonObject.getString("section"),
                                jsonObject.getInt("session"),
                                jsonObject.getString("semail")

                        ));
                        Log.d("",""+view_user_classes.get(i));
                    }

                    student_adapter view_all_user_adapter=new student_adapter(getApplicationContext(),view_user_classes);
                    recyclerView.setAdapter(view_all_user_adapter);

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
        Volley.newRequestQueue(this).add(stringRequest);


    }
}
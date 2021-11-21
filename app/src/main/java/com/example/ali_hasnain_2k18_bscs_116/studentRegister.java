package com.example.ali_hasnain_2k18_bscs_116;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.widget.TextViewCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class studentRegister extends AppCompatActivity {
    AppCompatSpinner p_spinner, spinner2,spinner3,c_spinner4;
    ArrayList<String> parentlist;
    ArrayList<String> arrayList_computing,arrayList_electrical,arrayList_mechanical,arrayList_civil,arrayList_architecture,arrayList_BioTechnology,arrayList_BA,arrayList_chemical,arrayList_petroleum,arrayList_energy,arrayList_enviro,arrayList_select;
    ArrayAdapter<String> parent_adapter;
    ArrayAdapter<String> child_adapter;

    List<String>  mylist2,mylist3;
    RadioButton male,female;
    RadioGroup radioGroup;

    AppCompatButton sregisterbtn;
    AppCompatEditText srname, srrolno,srmail,srpass;

  /*  NumberPicker yearPicker;*/

   @RequiresApi(api = Build.VERSION_CODES.M)
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        sregisterbtn=findViewById(R.id.sregister);

        srname=findViewById(R.id.srname);
        srmail=findViewById(R.id.sremail);
        srrolno=findViewById(R.id.srrolno);
        srpass=findViewById(R.id.srpassword);

       /* yearPicker = findViewById(R.id.yearpicker);
        yearPicker.setMinValue(2008);
        yearPicker.setMaxValue(2030);*/

        /*Parent Spinner for degree selection*/
        p_spinner=findViewById(R.id.deptspin);
        /*end*/

        /*Child Spinner for degree selection*/
        c_spinner4=findViewById(R.id.degreelevel);
        /*End*/

       spinner2 = findViewById(R.id.sessionspin);
       spinner3=findViewById(R.id.sectionspin);

       radioGroup=findViewById(R.id.radioGroup);
       male=findViewById(R.id.male);
       female=findViewById(R.id.female);


        /*Parent Process*/
        parentlist = new ArrayList<>();

        parentlist.add("Select Department");
        parentlist.add("Dept. of Computing");
        parentlist.add("Dept. of Civil Engg");
        parentlist.add("Dept. of Electrical Engg");
        parentlist.add("Dept. of Mechanical Engg");
        parentlist.add("Dept. of Chemical Engg");
        parentlist.add("Dept. of Petroleum Engg");
        parentlist.add("Dept. of Environ. Engg");
        parentlist.add("Dept. of BioTechnology");
        parentlist.add("Dept. of Energy Sys Engg");
        parentlist.add("Dept. of Architecture Engg");
        parentlist.add("Dept. of Business Administration");


        parent_adapter = new ArrayAdapter<>(this,R.layout.spinner,parentlist);
        p_spinner.setAdapter(parent_adapter);
        /*End*/

       /*Child Spinner Process Started*/

       arrayList_select = new ArrayList<>();
       arrayList_select.add("Select Program");

       arrayList_computing = new ArrayList<>();
       arrayList_computing.add("BS(CS)");
       arrayList_computing.add("BS(SE)");
       arrayList_computing.add("BS(IT)");
       arrayList_computing.add("MS(CS)");
       arrayList_computing.add("MS(SE)");

       arrayList_electrical = new ArrayList<>();
       arrayList_electrical.add("BE(EE)");
       arrayList_electrical.add("BE(EE)");

       arrayList_mechanical = new ArrayList<>();
       arrayList_mechanical.add("BE(ME)");
       arrayList_mechanical.add("ME(ME)");

       arrayList_civil=new ArrayList<>();
       arrayList_civil.add("BE(CE)");
       arrayList_civil.add("ME(CE)");

       arrayList_architecture = new ArrayList<>();
       arrayList_architecture.add("B.Arch");
       arrayList_architecture.add(" M. Arch");

       arrayList_chemical=new ArrayList<>();
       arrayList_chemical.add("BE(Chem.E)");
       arrayList_chemical.add("ME(Chem.E)");

       arrayList_petroleum=new ArrayList<>();
       arrayList_petroleum.add("BE(PE)");
       arrayList_petroleum.add("ME(PE)");

       arrayList_enviro=new ArrayList<>();
       arrayList_enviro.add("BE(Enviro)");
       arrayList_enviro.add("ME(Enviro)");

       arrayList_BioTechnology = new ArrayList<>();
       arrayList_BioTechnology.add("BE(BioTech)");
       arrayList_BioTechnology.add("ME(BioTech)");

       arrayList_energy = new ArrayList<>();
       arrayList_energy.add("BE(Energy)");
       arrayList_energy.add("ME(Energy)");

       arrayList_BA= new ArrayList<>();
       arrayList_BA.add("BBA");
       arrayList_BA.add("MBA");


       p_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


               if(position==0)
               {
                   child_adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner ,arrayList_select);
               }
               if(position==1)
               {
                   child_adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner ,arrayList_computing);
               }
               if(position==2)
               {
                   child_adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner ,arrayList_civil);
               }
               if(position==3)
               {
                   child_adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner ,arrayList_electrical);
               }
               if(position==4)
               {
                   child_adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner ,arrayList_mechanical);
               }
               if(position==5)
               {
                   child_adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner ,arrayList_chemical);
               }
               if(position==6)
               {
                   child_adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner ,arrayList_petroleum);
               }
               if(position==7)
               {
                   child_adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner ,arrayList_enviro);
               }
               if(position==8)
               {
                   child_adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner ,arrayList_BioTechnology);
               }
               if(position==9)
               {
                   child_adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner ,arrayList_energy);
               }
               if(position==10)
               {
                   child_adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner ,arrayList_architecture);
               }
               if(position==11)
               {
                   child_adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner ,arrayList_BA);
               }
              c_spinner4.setAdapter(child_adapter);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

       /*End*/


/*Session List*/
        mylist2 = new ArrayList<>();
        mylist2.add("Session");
        mylist2.add("2018");
        mylist2.add("2019");
        mylist2.add("2020");
        mylist2.add("2021");
        mylist2.add("2022");
        mylist2.add("2023");
        mylist2.add("2024");
        mylist2.add("2025");
        mylist2.add("2026");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,R.layout.spinner2,mylist2);
        spinner2.setAdapter(adapter2);
        /*End*/

       /*Section List*/
        mylist3 = new ArrayList<>();
        mylist3.add("Section");
        mylist3.add("A");
        mylist3.add("B");
        mylist3.add("C");
        mylist3.add("R");
        mylist3.add("G");
        mylist3.add("B");

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,R.layout.spinner2,mylist3);
        spinner3.setAdapter(adapter3);
        /*End*/



/*
      radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton rd=findViewById(checkedId);
            Toast.makeText(studentRegister.this,""+rd.getText(), Toast.LENGTH_LONG).show();
         }
      });
*/



/*Input TextField Validation*/

       sregisterbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String sname = srname.getText().toString();
               String srolno = srrolno.getText().toString();
               String smail = srmail.getText().toString();
               String spass = srpass.getText().toString();
               String dept = p_spinner.getSelectedItem().toString();
               String degree = c_spinner4.getSelectedItem().toString();
               String session = spinner2.getSelectedItem().toString();
               String section = spinner3.getSelectedItem().toString();
              /* String degree = c_spinner4.getContext().toString();*/


            /*   String error = p_spinner.getSelectedItem().toString();*/


            /*For validation*/
               if(sname.equals("") && srolno.equals("") && smail.equals("") && spass.equals("") /*&& error.equals("Select Department")*/ )
               {

                 /*  ((TextView)p_spinner.getSelectedView()).setError("Error message");
                   p_spinner.requestFocus();*/

                   srpass.setError("Please set password");
                   srpass.requestFocus();
                   srmail.setError("Please enter email");
                   srmail.requestFocus();
                   srrolno.setError("Please enter rollno");
                   srrolno.requestFocus();
                   srname.setError("Please enter name");
                   srname.requestFocus();
                   return;

               }
               else if(sname.equals(""))
               {
                   srname.setError("Please enter name ");
                   srname.requestFocus();
                   return;
               }
               else if(srolno.equals(""))
               {
                   srrolno.setError("Please enter rollno");
                   srrolno.requestFocus();
                   return;
               }
               else if(smail.equals(""))
               {
                   srmail.setError("Please enter email");
                   srmail.requestFocus();
                   return;
               }

               else if(spass.equals(""))
               {
                   srpass.setError("Please set password");
                   srpass.requestFocus();
                   return;
               }

               else
                   {
                       /*JSon Request for Store data in Database MySQL*/

                       /*here */
                       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2:8080/AMS_API/studentregister.php?srname="+sname+"&srrolno="+srolno+"&department="+dept+"&degree="+degree+"&session="+session+"&section="+section+"&srmail="+smail+"&srpass="+spass+"", null, new Response.Listener<JSONObject>() {
                           @Override
                           public void onResponse(JSONObject response) {


                               try{
                                   boolean bol = response.getBoolean("success");
                                   if(bol)
                                   {
                                       Intent intent = new Intent(studentRegister.this,studentactivity.class);
                                       startActivity(intent);
                                   }
                                   else
                                   {
                                       AlertDialog.Builder alter = new AlertDialog.Builder(studentRegister.this);
                                       alter.setMessage("Some Error").setNegativeButton("Retry",null).create().show();
                                   }

                               }
                               catch (Exception e) {
                                   Log.d("", ""+ e);
                               }

                           }
                       }, new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               error.printStackTrace();
                           }
                       });
                       Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);
                       /*Json End*/


                   }

           }


       });

        /*End*/
    }
}
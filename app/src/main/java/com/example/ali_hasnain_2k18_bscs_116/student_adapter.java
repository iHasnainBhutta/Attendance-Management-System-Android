package com.example.ali_hasnain_2k18_bscs_116;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class student_adapter extends RecyclerView.Adapter<student_adapter.ViewHolder> {

    List<view_user_class> view_user_classes;
    Context mctx;


    student_adapter(Context mctx,List<view_user_class> view_user_classes)
    {
        this.mctx=mctx;
        this.view_user_classes=view_user_classes;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(mctx);
        View view=layoutInflater.inflate(R.layout.all2,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final  view_user_class view_user_class1=view_user_classes.get(position);
        Log.d("",""+view_user_class1.getStudentname());
        holder.sname.setText(" "+view_user_class1.getStudentname());
        holder.srollno.setText(" "+view_user_class1.getSrollno());


    }

    @Override
    public int getItemCount() {
        return view_user_classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        AppCompatTextView sname,srollno;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout=itemView.findViewById(R.id.parent2);
            sname=itemView.findViewById(R.id.studentnameview);
           srollno=itemView.findViewById(R.id.srollnoview);


        }
    }
}

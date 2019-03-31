package com.app.manikandanr.sampleclients.Adapters;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.manikandanr.sampleclients.Activities.CreateGroupPage;
import com.app.manikandanr.sampleclients.Data.GroupCreateData;
import com.app.manikandanr.sampleclients.Data.MarketingAlertData;
import com.app.manikandanr.sampleclients.Data.Student;
import com.app.manikandanr.sampleclients.R;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class StudentGroupCreateAdapter  extends RecyclerView.Adapter<StudentGroupCreateAdapter.MyViewHolder> {

    public static ArrayList<Student> obj_arr = new ArrayList<>();
    public static ArrayList<String> checkedStudentNames = new ArrayList<>();
    public static ArrayList<String> checkedStudentIds = new ArrayList<>();

    public StudentGroupCreateAdapter(ArrayList<Student> objs) {
        this.obj_arr = objs;
    }

    @Override
    public StudentGroupCreateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_student_view, parent, false);
        return new StudentGroupCreateAdapter.MyViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(final StudentGroupCreateAdapter.MyViewHolder holder, final int position) {
        try {
            holder.studentName.setText(obj_arr.get(position).getName());
            holder.studentSerialNo.setText(obj_arr.get(position).getSerialNo());
            holder.studentPhone.setText(obj_arr.get(position).getPhone());
            holder.studentEmail.setText(obj_arr.get(position).getEmail());
            holder.checkStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(holder.checkStudent.isChecked())
                    {
                        checkedStudentNames.add(obj_arr.get(position).getName());
                        checkedStudentIds.add(""+obj_arr.get(position).getId());
                        Log.e("ADD", "" + obj_arr.get(position).getName()+"---"+obj_arr.get(position).getId());
                    }
                    else
                    {
                        Log.e("ADD", "" + obj_arr.get(position).getName()+"---"+obj_arr.get(position).getId());
                        checkedStudentNames.remove(obj_arr.get(position).getName());
                        checkedStudentIds.remove(""+obj_arr.get(position).getId());
                       // Log.e("ADD", "" + obj_arr.get(position).getName()+"---"+obj_arr.get(position).getId());
                    }
                }
            });
        } catch (Exception ex) {
            Log.e("ERROR_RECENT", "" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return obj_arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView studentName;
        TextView studentSerialNo;
        TextView studentPhone;
        TextView studentEmail;
        CheckBox checkStudent;

        public MyViewHolder(View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.student_name);
            studentSerialNo = itemView.findViewById(R.id.serial_no);
            studentPhone = itemView.findViewById(R.id.phone);
            studentEmail = itemView.findViewById(R.id.email);
            checkStudent = itemView.findViewById(R.id.check_student);
        }
    }


}


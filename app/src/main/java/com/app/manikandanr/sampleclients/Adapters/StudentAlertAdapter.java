package com.app.manikandanr.sampleclients.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.manikandanr.sampleclients.AttendanceActivity;
import com.app.manikandanr.sampleclients.DataModels.AttendanceData;
import com.app.manikandanr.sampleclients.R;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class StudentAlertAdapter  extends RecyclerView.Adapter<StudentAlertAdapter.MyViewHolder> {

    public static ArrayList<AttendanceData> obj_arr = new ArrayList<>();

    public StudentAlertAdapter(ArrayList<AttendanceData> objs) {
        this.obj_arr = objs;
    }

    @Override
    public StudentAlertAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_attendance_lyt, parent, false);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return new StudentAlertAdapter.MyViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(final StudentAlertAdapter.MyViewHolder holder, final int position) {
        try {
            holder.studentName.setText(obj_arr.get(position).get_studentName());
            holder.studentRegNumber.setText(obj_arr.get(position).get_studentRegNumber());
            holder.studentPaymentStatus.setText(obj_arr.get(position).get_studentPaymentStatus());
            holder.studentAttendanceStatus.setText(obj_arr.get(position).get_studentAttendance());

            holder.attendance_present.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = position;
                    //AttendanceActivity.studentPresentList.add(obj_arr.get(pos).get_studentId()+"");
                    AttendanceActivity.studentBuilder.append(obj_arr.get(pos).get_studentId() + ",");
                    Log.e("CLICKED_POSITION", pos + "////" + obj_arr.get(pos).get_studentId() + "");
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
        TextView studentName, studentRegNumber, studentPaymentStatus, studentAttendanceStatus;
        CheckBox attendance_present;
        LinearLayout lyt_students;

        public MyViewHolder(View itemView) {
            super(itemView);

            studentName = itemView.findViewById(R.id.lyt_txt_name);
            studentRegNumber = itemView.findViewById(R.id.lyt_txt_regnumber);
            studentPaymentStatus = itemView.findViewById(R.id.lyt_txt_payment_status);
            studentAttendanceStatus = itemView.findViewById(R.id.lyt_txt_attendance_sts);
            attendance_present = itemView.findViewById(R.id.checkbox_student);
            lyt_students = itemView.findViewById(R.id.lyt_layout_student);

        }
    }

    public static String getIndianRupee(String value) {
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        return format.format(new BigDecimal(value));
    }
}


package com.app.manikandanr.sampleclients.Adapters;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.manikandanr.sampleclients.AttendanceActivity;
import com.app.manikandanr.sampleclients.Data.AttendanceData;
import com.app.manikandanr.sampleclients.Data.StudentAlertData;
import com.app.manikandanr.sampleclients.R;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class StudentAttendanceAdapter  extends RecyclerView.Adapter<StudentAttendanceAdapter.MyViewHolder> {
    public static ArrayList<StudentAlertData> obj_arr=new ArrayList<>();
    public StudentAttendanceAdapter(ArrayList<StudentAlertData> objs) {
        this.obj_arr =objs;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View contentView= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_attendance_lyt,parent,false);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return new MyViewHolder(contentView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try {
            holder.studentName.setText(obj_arr.get(position).getStudentName());
            holder.studentRegNumber.setText(obj_arr.get(position).getStudentSerialNumber());
            holder.studentPaymentStatus.setText(obj_arr.get(position).getStudentPaymentStatus());
            if(obj_arr.get(position).getStudentAttendanceStatus().equalsIgnoreCase("1")) {
                holder.studentAttendanceStatus.setText("Present");
                holder.attendance_present.setVisibility(View.GONE);
            }
            else
            {
                holder.studentAttendanceStatus.setText("Attendance not given");
            }
            //attendancests = 0 =absent; 1= present;

            holder.attendance_present.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos =position;
                    //AttendanceActivity.studentPresentList.add(obj_arr.get(pos).get_studentId()+"");
                    AttendanceActivity.studentBuilder.append(obj_arr.get(pos).getStudentId()+",");
                    AttendanceActivity.studentRegnumbers.append(obj_arr.get(pos).getStudentSerialNumber()+" , ");
                    Log.e("CLICKED_POSITION",pos+"////"+obj_arr.get(pos).getStudentId()+"");
                }
            });

            holder.lyt_students.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater inflater = (LayoutInflater) holder.lyt_students.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    final View layout11 = inflater.inflate(R.layout.dialog_student_info,
                            (ViewGroup) holder.lyt_students.findViewById(R.id.layout_root));
                    final TextView txtStudentInfo = layout11.findViewById(R.id.student_info);
                    final TextView header = layout11.findViewById(R.id.header);
                    header.setText("Student Details");

                    String styledText ="Name :"+obj_arr.get(position).getStudentName()+",<br>"+
                            "Date of Birth :"+obj_arr.get(position).getStudentDob()+",<br>"+
                            "Phone :"+obj_arr.get(position).getStudentPhone()+",<br>"+
                            "Email :"+obj_arr.get(position).getStudentEmail()+",<br>"+
                            "Serial Number :"+obj_arr.get(position).getStudentSerialNumber()+",<br>"+
                            "Category :"+obj_arr.get(position).getStudentCategory()+",<br>"+
                            "Organization :"+obj_arr.get(position).getStudentOrganization()+",<br>"+
                            "Address :"+obj_arr.get(position).getStudentAddress()+","+
                            "Payment Status :"+obj_arr.get(position).getStudentPaymentStatus()+",<br>"+
                            "Attendance Status :"+obj_arr.get(position).getStudentAttendanceStatus()+",<br>"+
                            "Last Payment Date :"+obj_arr.get(position).getStudentLastPaymentDate()+".<br>"+
                            "Balance Payment :"+obj_arr.get(position).getBalanceAmount()+".<br>";
                    txtStudentInfo.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(holder.lyt_students.getContext());
                    builder.setView(layout11);
                    builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    final AlertDialog dialog = builder.create();
                    dialog.setCancelable(false);
                    dialog.show();

                }
            });
        }catch (Exception ex)
        {
            Log.e("ERROR_RECENT",""+ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return obj_arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
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
package com.app.manikandanr.sampleclients.Adapters;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.manikandanr.sampleclients.AttendanceActivity;
import com.app.manikandanr.sampleclients.Data.StudentAlertData;
import com.app.manikandanr.sampleclients.R;
import com.app.manikandanr.sampleclients.Utils.Constants;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class StudentAlertAdapter extends RecyclerView.Adapter<StudentAlertAdapter.MyViewHolder> {

    public static List<StudentAlertData> obj_arr = new ArrayList<>();

    public StudentAlertAdapter(List<StudentAlertData> objs) {
        this.obj_arr = objs;
    }

    @Override
    public StudentAlertAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_alert_lyt, parent, false);
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
                holder.studentName.setText("Name : "+obj_arr.get(position).getStudentName());
                holder.studentPhone.setText(obj_arr.get(position).getStudentPhone());
                holder.studentEmail.setText(""+obj_arr.get(position).getStudentEmail());
                holder.studentDate.setText("Alert Date : "+obj_arr.get(position).getStudentDate());
                holder.imgCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String uri = "tel:" + obj_arr.get(position).getStudentPhone();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(uri));
                        holder.studentPhone.getContext().startActivity(intent);
                    }
                });

                holder.lyt_students.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            LayoutInflater inflater = (LayoutInflater) holder.lyt_students.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                            final View layout11 = inflater.inflate(R.layout.dialog_student_info,
                                    (ViewGroup) holder.lyt_students.findViewById(R.id.layout_root));
                            final TextView txtStudentInfo = layout11.findViewById(R.id.student_info);
                            txtStudentInfo.setText("Name :"+obj_arr.get(position).getStudentName()+",\n"+
                                    "Date of Birth :"+obj_arr.get(position).getStudentDob()+",\n"+
                                    "Phone :"+obj_arr.get(position).getStudentPhone()+",\n"+
                                    "Email :"+obj_arr.get(position).getStudentEmail()+",\n"+
                                    "Serial Number :"+obj_arr.get(position).getStudentSerialNumber()+",\n"+
                                    "Category :"+obj_arr.get(position).getStudentCategory()+",\n"+
                                    "Organization :"+obj_arr.get(position).getStudentOrganization()+",\n"+
                                    "Address :"+obj_arr.get(position).getStudentAddress()+".");
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
        TextView studentName, studentPhone, studentEmail, studentDate;
        LinearLayout lyt_students;
        ImageButton imgCall ;

        public MyViewHolder(View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.lyt_txt_student_name);
            studentPhone = itemView.findViewById(R.id.lyt_txt_studentt_phone);
            studentEmail = itemView.findViewById(R.id.lyt_txt_student_email);
            studentDate = itemView.findViewById(R.id.lyt_txt_date);
            lyt_students = itemView.findViewById(R.id.lyt_student_alert);
            imgCall = itemView.findViewById(R.id.lyt_img_phone);
        }
    }

    public static String getIndianRupee(String value) {
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        return format.format(new BigDecimal(value));
    }
}


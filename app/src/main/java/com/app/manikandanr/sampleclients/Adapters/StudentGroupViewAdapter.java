package com.app.manikandanr.sampleclients.Adapters;

import android.content.DialogInterface;
import android.graphics.Color;
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

import com.app.manikandanr.sampleclients.Data.AttendancePresentData;
import com.app.manikandanr.sampleclients.Data.Student;
import com.app.manikandanr.sampleclients.R;
import com.app.manikandanr.sampleclients.Utils.Constants;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class StudentGroupViewAdapter extends RecyclerView.Adapter<StudentGroupViewAdapter.MyViewHolder> {
    public static ArrayList<Student> obj_arr=new ArrayList<>();
    String pfrom;
    public static ArrayList<AttendancePresentData> attedanceData = new ArrayList<>();
    public StudentGroupViewAdapter(ArrayList<Student> objs, String pageFrom) {
        this.obj_arr =objs;
        this.pfrom = pageFrom;
    }
    @Override
    public StudentGroupViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View contentView= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_group_view_lyt,parent,false);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return new StudentGroupViewAdapter.MyViewHolder(contentView);
    }
    @Override
    public void onBindViewHolder(final StudentGroupViewAdapter.MyViewHolder holder, final int position) {
        try {

            attedanceData.add(new AttendancePresentData(""+obj_arr.get(position).getId(),
                    obj_arr.get(position).getName(), Constants.ATTENDANCE_ABSENT));
            String PaymentStatus = "";
            if(obj_arr.get(position).getPaymentStatus() == 0)
            {
                PaymentStatus = "Not Paid";
                holder.studentPaymentStatus.setText(PaymentStatus);
                holder.studentPaymentStatus.setTextColor(Color.RED);
            }
            else
            {
                PaymentStatus = "Paid";
                holder.studentPaymentStatus.setText(PaymentStatus);
                holder.studentPaymentStatus.setTextColor(Color.GREEN);
            }
            holder.studentName.setText(obj_arr.get(position).getName());
            holder.studentRegNumber.setText(obj_arr.get(position).getSerialNo());
            holder.studentOtherDetails.setText(Html.fromHtml(obj_arr.get(position).getCategory().getCategory()+",<br>"+
                    obj_arr.get(position).getOrganization().getName()+""), TextView.BufferType.SPANNABLE);

            final String finalPaymentStatus = PaymentStatus;
            holder.lyt_students.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater inflater = (LayoutInflater) holder.lyt_students.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    final View layout11 = inflater.inflate(R.layout.dialog_student_info,
                            (ViewGroup) holder.lyt_students.findViewById(R.id.layout_root));
                    final TextView txtStudentInfo = layout11.findViewById(R.id.student_info);
                    final TextView header = layout11.findViewById(R.id.header);
                    header.setText("Student Details");
                    String styledText ="Name : "+obj_arr.get(position).getName()+",<br>"+
                            "Date of Birth : "+obj_arr.get(position).getDob()+",<br>"+
                            "Phone : "+obj_arr.get(position).getPhone()+",<br>"+
                            "Email : "+obj_arr.get(position).getEmail()+",<br>"+
                            "Serial Number : "+obj_arr.get(position).getSerialNo()+",<br>"+
                            "Category : "+obj_arr.get(position).getCategory().getCategory()+",<br>"+
                            "Organization : "+obj_arr.get(position).getOrganization().getName()+",<br>"+
                            "Address : "+obj_arr.get(position).getAddress()+",<br>"+
                            "Payment Status : "+ finalPaymentStatus +""+".";
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
        TextView studentName, studentRegNumber, studentPaymentStatus, studentOtherDetails;

        LinearLayout lyt_students;

        public MyViewHolder(View itemView) {
            super(itemView);

            studentName = itemView.findViewById(R.id.lyt_txt_name);
            studentRegNumber = itemView.findViewById(R.id.lyt_txt_regnumber);
            studentPaymentStatus = itemView.findViewById(R.id.lyt_txt_payment_status);
            studentOtherDetails = itemView.findViewById(R.id.lyt_stu_view_details);
            lyt_students = itemView.findViewById(R.id.lyt_layout_student);

        }
    }
    public static String getIndianRupee(String value) {
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        return format.format(new BigDecimal(value));
    }

}

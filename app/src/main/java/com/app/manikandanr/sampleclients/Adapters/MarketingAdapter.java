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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.manikandanr.sampleclients.Data.MarketingAlertData;
import com.app.manikandanr.sampleclients.R;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MarketingAdapter extends RecyclerView.Adapter<MarketingAdapter.MyViewHolder> {

    public static ArrayList<MarketingAlertData> obj_arr = new ArrayList<>();

    public MarketingAdapter(ArrayList<MarketingAlertData> objs) {
        this.obj_arr = objs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_alert_lyt, parent, false);
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
            MarketingAlertData data=obj_arr.get(position);
            holder.studentName.setText(data.getInstituation_id());
            holder.studentDate.setText(data.getDate());
            holder.studentEmail.setText(data.getEmail());
            holder.studentPhone.setText(data.getPhone());
            holder.imgCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uri = "tel:" + obj_arr.get(position).getPhone();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(uri));
                    holder.studentPhone.getContext().startActivity(intent);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    final View layout11 = inflater.inflate(R.layout.dialog_student_info,null,false);
                    final TextView txtStudentInfo = layout11.findViewById(R.id.student_info);
                    final TextView header = layout11.findViewById(R.id.header);
                    header.setText("Organization Details");
                    String styledText = "Name :"+obj_arr.get(position).getInstituation_id()+",<br>"+
                            "Date  :"+obj_arr.get(position).getDate()+",<br>"+
                            "Phone :"+obj_arr.get(position).getPhone()+",<br>"+
                            "Email :"+obj_arr.get(position).getEmail()+",<br>"+
                            "Website :"+obj_arr.get(position).getWebsite()+",<br>"+
                            "Serial Number :"+obj_arr.get(position).getSerial_no()+",<br>"+
                            "City Id :"+obj_arr.get(position).getCity_id()+",<br>"+
                            "Distance :"+obj_arr.get(position).getDistance()+",<br>"+
                            "LandLine Number :"+obj_arr.get(position).getLandline()+",<br>"+
                            "Organization Id :"+obj_arr.get(position).getOrganization_id()+",<br>"+
                            "<font color='red'>Remarks :"+obj_arr.get(position).getDescription()+",<br></font>"+
                            "<font color='red'>Date :"+obj_arr.get(position).getDate()+",<br></font>"+
                            "Address :"+obj_arr.get(position).getAddress()+".";
                    txtStudentInfo.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(holder.studentEmail.getContext());
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
        ImageView imgCall ;

        public MyViewHolder(View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.lyt_txt_student_name);
            studentPhone = itemView.findViewById(R.id.lyt_txt_studentt_phone);
            studentEmail = itemView.findViewById(R.id.lyt_txt_student_email);
            studentDate = itemView.findViewById(R.id.lyt_txt_date);
            imgCall = itemView.findViewById(R.id.lyt_img_phone);
        }
    }

    public static String getIndianRupee(String value) {
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        return format.format(new BigDecimal(value));
    }
}


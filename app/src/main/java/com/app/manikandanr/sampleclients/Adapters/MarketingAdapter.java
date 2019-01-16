package com.app.manikandanr.sampleclients.Adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.manikandanr.sampleclients.Data.AlertData;
import com.app.manikandanr.sampleclients.R;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MarketingAdapter extends RecyclerView.Adapter<MarketingAdapter.MyViewHolder> {

    public static ArrayList<AlertData> obj_arr = new ArrayList<>();

    public MarketingAdapter(ArrayList<AlertData> objs) {
        this.obj_arr = objs;
    }

    @Override
    public MarketingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_alert_lyt, parent, false);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return new MarketingAdapter.MyViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(final MarketingAdapter.MyViewHolder holder, final int position) {
        try {
            holder.studentName.setText(obj_arr.get(position).getMarketings().get(position).getInstituationId());
            holder.studentPhone.setText(obj_arr.get(position).getMarketings().get(position).getPhone());
            holder.studentEmail.setText(obj_arr.get(position).getMarketings().get(position).getEmail());
            holder.studentDate.setText(obj_arr.get(position).getMarketings().get(position).getDate());

            holder.imgCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uri = "tel:" + obj_arr.get(position).getMarketings().get(position).getPhone();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(uri));
                    holder.studentPhone.getContext().startActivity(intent);
                }
            });
//            holder.studentName.setText(obj_arr.get(position).);
//            holder.studentPhone.setText(obj_arr.get(position).get_studentRegNumber());
//            holder.studentEmail.setText(obj_arr.get(position).get_studentPaymentStatus());
//            holder.studentDate.setText(obj_arr.get(position).get_studentAttendance());


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
        ImageView imgCall ;

        public MyViewHolder(View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.lyt_txt_student_name);
            studentPhone = itemView.findViewById(R.id.lyt_txt_studentt_phone);
            studentEmail = itemView.findViewById(R.id.lyt_txt_student_email);
            studentDate = itemView.findViewById(R.id.lyt_txt_date);
            // lyt_students = itemView.findViewById(R.id.lyt_layout_student);
            imgCall = itemView.findViewById(R.id.lyt_img_phone);
        }
    }

    public static String getIndianRupee(String value) {
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        return format.format(new BigDecimal(value));
    }
}


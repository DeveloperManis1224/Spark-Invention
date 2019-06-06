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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.manikandanr.sampleclients.Data.AttendancePresentData;
import com.app.manikandanr.sampleclients.Data.Student;
import com.app.manikandanr.sampleclients.DataModels.Group;
import com.app.manikandanr.sampleclients.R;
import com.app.manikandanr.sampleclients.Utils.Constants;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class GroupListAdapter   extends RecyclerView.Adapter<GroupListAdapter.MyViewHolder> {
    public static ArrayList<Group> obj_arr=new ArrayList<>();
    String pfrom;
    public static ArrayList<AttendancePresentData> attedanceData = new ArrayList<>();
    public GroupListAdapter(ArrayList<Group> objs, String pageFrom) {
        this.obj_arr =objs;
        this.pfrom = pageFrom;
    }
    @Override
    public GroupListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View contentView= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_view_group_list,parent,false);
        return new GroupListAdapter.MyViewHolder(contentView);
    }
    @Override
    public void onBindViewHolder(final GroupListAdapter.MyViewHolder holder, final int position) {
        try {
            holder.groupName.setText(obj_arr.get(position).getName());
            holder.groupGrade.setText(obj_arr.get(position).getGrade());
            holder.groupNumber.setText(obj_arr.get(position).getGroupNo());
            holder.lyt_group.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }catch (Exception ex) {
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
        TextView groupName, groupGrade, groupNumber;

        LinearLayout lyt_group;

        public MyViewHolder(View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.group_name);
            groupGrade = itemView.findViewById(R.id.group_grade);
            groupNumber = itemView.findViewById(R.id.group_number);
            lyt_group = itemView.findViewById(R.id.lyt_group);
        }
    }
    public static String getIndianRupee(String value) {
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        return format.format(new BigDecimal(value));
    }

}

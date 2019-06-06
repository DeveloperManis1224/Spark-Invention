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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.manikandanr.sampleclients.AddItems;
import com.app.manikandanr.sampleclients.AttendanceActivity;
import com.app.manikandanr.sampleclients.Data.Alert;
import com.app.manikandanr.sampleclients.Data.StudentAlertData;
import com.app.manikandanr.sampleclients.DataModels.Students;
import com.app.manikandanr.sampleclients.R;
import com.app.manikandanr.sampleclients.Utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class StudentAlertAdapter extends RecyclerView.Adapter<StudentAlertAdapter.MyViewHolder> {

    public static List<Students> obj_arr = new ArrayList<>();
    private String selectedDate = "";

    public StudentAlertAdapter(List<Students> objs) {
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
                holder.studentName.setText(""+obj_arr.get(position).getStudent().getName());
                holder.studentPhone.setText(obj_arr.get(position).getStudent().getPhone());
                holder.studentEmail.setText(obj_arr.get(position).getStudent().getEmail());
                holder.studentDate.setText("Alert Date : "+obj_arr.get(position).getDate());

                if(!obj_arr.get(position).getStatus().equalsIgnoreCase("0"))
                {
                    holder.btnFeedback.setVisibility(View.GONE);
                    holder.btnCancel.setVisibility(View.GONE);
                    holder.btnAdmission.setVisibility(View.GONE);
                    holder.statusStudent.setVisibility(View.VISIBLE);
                    holder.statusStudent.setText(obj_arr.get(position).getStatus());
                }

                holder.imgCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String uri = "tel:" + obj_arr.get(position).getStudent().getPhone();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(uri));
                        holder.studentPhone.getContext().startActivity(intent);

                    }
                });

                holder.btnFeedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        LayoutInflater inflater = (LayoutInflater) holder.lyt_students.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        final View layout11 = inflater.inflate(R.layout.dialog_feed_back,
                                (ViewGroup) holder.lyt_students.findViewById(R.id.layout_root));
                       final EditText edtFeedBack = layout11.findViewById(R.id.edt_feedback);
                        final AlertDialog.Builder builder = new AlertDialog.Builder(holder.lyt_students.getContext());
                        builder.setView(layout11);
                        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(!edtFeedBack.getText().toString().isEmpty()){
                                    addAction(view,obj_arr.get(position).getId(),"",Constants.STATUS_FEEDBACK,edtFeedBack.getText().toString().trim());
                                    dialogInterface.dismiss();
                                }
                                else {
                                    Toast.makeText(view.getContext(), "Feedback is Empty!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        final AlertDialog dialog = builder.create();
                        dialog.setCancelable(true);
                        dialog.show();
                    }
                });
                holder.lyt_students.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LayoutInflater inflater = (LayoutInflater) holder.lyt_students.getContext()
                                .getSystemService(LAYOUT_INFLATER_SERVICE);
                        final View layout11 = inflater.inflate(R.layout.dialog_student_info,
                                (ViewGroup) holder.lyt_students.findViewById(R.id.layout_root));
                        final TextView txtStudentInfo = layout11.findViewById(R.id.student_info);
                        final TextView header = layout11.findViewById(R.id.header);

                        String typeUser = "";
                        if(obj_arr.get(position).getStudent().getJoin_status().equalsIgnoreCase(Constants.ROLE_SCHOOL)) {
                            typeUser = "School";
                        }
                        else if(obj_arr.get(position).getStudent().getJoin_status().equalsIgnoreCase(Constants.ROLE_COLLEGE)) {
                            typeUser = "College";
                        }
                        else {
                            typeUser = "Project / Program";
                        }

                        header.setText("Student Details");
                        String styledText ="Name :"+obj_arr.get(position).getStudent().getName()+",<br>"+
                                "Date of Birth :"+obj_arr.get(position).getStudent().getDob()+",<br>"+
                                "Phone :"+obj_arr.get(position).getStudent().getPhone()+",<br>"+
                                "Email :"+obj_arr.get(position).getStudent().getEmail()+",<br>"+
                                "Serial Number :"+obj_arr.get(position).getStudent().getSerial_no()+",<br>"+
                                "Category :"+obj_arr.get(position).getStudent().getCategory().getCategory()+",<br>"+
                                "Organization :"+obj_arr.get(position).getStudent().getOrganization().getName()+",<br>"+
                                "<font color='red'>Date :"+obj_arr.get(position).getDate()+",<br></font>"+
                                "<font color='red'>Type :"+typeUser+",<br></font>"+
                                "Address :"+obj_arr.get(position).getStudent().getAddress()+".";
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

                holder.btnAdmission.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setPositiveButton("Yes,Add to Admission", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                addAction(view,obj_arr.get(position).getId(),"",Constants.STATUS_ADMISSION,"Nothing");
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                            }
                        });
                        final AlertDialog dialog = builder.create();
                        dialog.setCancelable(false);
                        dialog.setTitle("Confirmation");
                        dialog.setMessage("Are you sure want to add this student to admission?");
                        dialog.show();
                    }
                });
                holder.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setPositiveButton("Yes, Remove", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                addAction(view,obj_arr.get(position).getId(),"",Constants.STATUS_CANCEL,"Nothing");
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        final AlertDialog dialog = builder.create();
                        dialog.setCancelable(false);
                        dialog.setTitle("Confirmation");
                        dialog.setMessage("Are you sure want to remove this student from alert?");
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
        TextView studentName, studentPhone, studentEmail, studentDate, statusStudent;
        LinearLayout lyt_students;
        ImageButton imgCall ;
        Button btnFeedback, btnAdmission, btnCancel;

        public MyViewHolder(View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.lyt_txt_student_name);
            studentPhone = itemView.findViewById(R.id.lyt_txt_studentt_phone);
            studentEmail = itemView.findViewById(R.id.lyt_txt_student_email);
            studentDate = itemView.findViewById(R.id.lyt_txt_date);
            lyt_students = itemView.findViewById(R.id.lyt_student_alert);
            imgCall = itemView.findViewById(R.id.lyt_img_phone);
            btnFeedback = itemView.findViewById(R.id.btn_feedback);
            btnAdmission = itemView.findViewById(R.id.btn_admission);
            btnCancel = itemView.findViewById(R.id.btn_cancel);
            statusStudent = itemView.findViewById(R.id.status_student);
        }
    }

    public static String getIndianRupee(String value) {
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        return format.format(new BigDecimal(value));
    }

    private void addAction(final View v, final String alert_student_id, final String date, final String status,
                            final String description) {
        RequestQueue queue = Volley.newRequestQueue(v.getContext());
        String url = Constants.BASE_URL+"api/alert-status";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            String status = jobj.getString("status");
                            String msg = jobj.getString("message");
                            if(status.equalsIgnoreCase("1")){
                                Toast.makeText(v.getContext(),  msg, Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(v.getContext(), ""+msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(v.getContext(), "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("join_alert_id",alert_student_id);
                params.put("date",date);
                params.put("status",status);
                params.put("description",description);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
}


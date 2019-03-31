
package com.app.manikandanr.sampleclients.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Payment implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("initial_amount")
    @Expose
    private String initialAmount;
    @SerializedName("total_amount")
    @Expose
    private String totalAmount;
    @SerializedName("tenure")
    @Expose
    private String tenure;
    @SerializedName("tenure_amount")
    @Expose
    private String tenureAmount;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("balance_amount")
    @Expose
    private String balanceAmount;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("quotation_id")
    @Expose
    private String quotationId;
    @SerializedName("online_transaction_id")
    @Expose
    private Object onlineTransactionId;
    @SerializedName("student")
    @Expose
    private Student student;

    protected Payment(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        studentId = in.readString();
        paymentMode = in.readString();
        initialAmount = in.readString();
        totalAmount = in.readString();
        tenure = in.readString();
        tenureAmount = in.readString();
        dueDate = in.readString();
        balanceAmount = in.readString();
        paymentStatus = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        quotationId = in.readString();
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel in) {
            return new Payment(in);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(String initialAmount) {
        this.initialAmount = initialAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getTenureAmount() {
        return tenureAmount;
    }

    public void setTenureAmount(String tenureAmount) {
        this.tenureAmount = tenureAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(String quotationId) {
        this.quotationId = quotationId;
    }

    public Object getOnlineTransactionId() {
        return onlineTransactionId;
    }

    public void setOnlineTransactionId(Object onlineTransactionId) {
        this.onlineTransactionId = onlineTransactionId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("studentId", studentId).append("paymentMode", paymentMode).append("initialAmount", initialAmount).append("totalAmount", totalAmount).append("tenure", tenure).append("tenureAmount", tenureAmount).append("dueDate", dueDate).append("balanceAmount", balanceAmount).append("paymentStatus", paymentStatus).append("createdAt", createdAt).append("updatedAt", updatedAt).append("quotationId", quotationId).append("onlineTransactionId", onlineTransactionId).append("student", student).toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(studentId);
        parcel.writeString(paymentMode);
        parcel.writeString(initialAmount);
        parcel.writeString(totalAmount);
        parcel.writeString(tenure);
        parcel.writeString(tenureAmount);
        parcel.writeString(dueDate);
        parcel.writeString(balanceAmount);
        parcel.writeString(paymentStatus);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
        parcel.writeString(quotationId);
    }
}

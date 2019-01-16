
package com.app.manikandanr.sampleclients.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Payment {

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

}

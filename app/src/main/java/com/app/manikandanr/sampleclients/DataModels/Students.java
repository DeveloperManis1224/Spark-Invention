package com.app.manikandanr.sampleclients.DataModels;

public class Students {

    private String date;

    private String updated_at;

    private Student student;

    private String student_id;

    private String created_at;

    private String description;

    private String id;

    private String status;

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public Student getStudent ()
    {
        return student;
    }

    public void setStudent (Student student)
    {
        this.student = student;
    }

    public String getStudent_id ()
    {
        return student_id;
    }

    public void setStudent_id (String student_id)
    {
        this.student_id = student_id;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [date = "+date+", updated_at = "+updated_at+", student = "+student+", student_id = "+student_id+", created_at = "+created_at+", description = "+description+", id = "+id+", status = "+status+"]";
    }
}

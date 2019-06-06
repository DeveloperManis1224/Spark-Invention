package com.app.manikandanr.sampleclients.DataModels;

public class Course {
    private String offer;

    private String amount;

    private String updated_at;

    private String category_id;

    private String course;

    private String created_at;

    private String id;

    private String offer_type;

    private String status;

    public String getOffer ()
    {
        return offer;
    }

    public void setOffer (String offer)
    {
        this.offer = offer;
    }

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getCategory_id ()
    {
        return category_id;
    }

    public void setCategory_id (String category_id)
    {
        this.category_id = category_id;
    }

    public String getCourse ()
    {
        return course;
    }

    public void setCourse (String course)
    {
        this.course = course;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getOffer_type ()
    {
        return offer_type;
    }

    public void setOffer_type (String offer_type)
    {
        this.offer_type = offer_type;
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
        return "ClassPojo [offer = "+offer+", amount = "+amount+", updated_at = "+updated_at+", category_id = "+category_id+", course = "+course+", created_at = "+created_at+", id = "+id+", offer_type = "+offer_type+", status = "+status+"]";
    }
}

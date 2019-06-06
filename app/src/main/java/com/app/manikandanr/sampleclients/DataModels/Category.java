package com.app.manikandanr.sampleclients.DataModels;

public class Category {
    private String updated_at;

    private String created_at;

    private String id;

    private String category;

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
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

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [updated_at = "+updated_at+", created_at = "+created_at+", id = "+id+", category = "+category+"]";
    }
}

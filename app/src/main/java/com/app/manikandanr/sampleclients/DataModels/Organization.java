package com.app.manikandanr.sampleclients.DataModels;

public class Organization {

    private String offer;

    private String role;

    private String updated_at;

    private String branch_id;

    private String user_id;

    private String name;

    private String created_at;

    private String id;

    private String offer_type;

    private String city_id;

    private String status;

    public String getOffer ()
    {
        return offer;
    }

    public void setOffer (String offer)
    {
        this.offer = offer;
    }

    public String getRole ()
    {
        return role;
    }

    public void setRole (String role)
    {
        this.role = role;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getBranch_id ()
    {
        return branch_id;
    }

    public void setBranch_id (String branch_id)
    {
        this.branch_id = branch_id;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
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

    public String getCity_id ()
    {
        return city_id;
    }

    public void setCity_id (String city_id)
    {
        this.city_id = city_id;
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
        return "ClassPojo [offer = "+offer+", role = "+role+", updated_at = "+updated_at+", branch_id = "+branch_id+", user_id = "+user_id+", name = "+name+", created_at = "+created_at+", id = "+id+", offer_type = "+offer_type+", city_id = "+city_id+", status = "+status+"]";
    }
}

package com.app.manikandanr.sampleclients.DataModels;

import java.util.ArrayList;

public class StudentObjectALertData {

    private ArrayList<Students> students;

    private String message;

    private ArrayList<Marketing> marketings;

    private String status;

    public ArrayList<Students> getStudents ()
    {
        return students;
    }

    public void setStudents (ArrayList<Students> students)
    {
        this.students = students;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public ArrayList<Marketing> getMarketings ()
    {
        return marketings;
    }

    public void setMarketings (ArrayList<Marketing> marketings)
    {
        this.marketings = marketings;
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
        return "ClassPojo [students = "+students+", message = "+message+", marketings = "+marketings+", status = "+status+"]";
    }
}

package com.ecse428.project.fitboi.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4787.f023c4bb4 modeling language!*/


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// line 74 "model.ump"
// line 127 "model.ump"
@Entity
public class Footnote
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int id;

  //Footnote Attributes
  private Date date;
  private String note;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Footnote(Date aDate, String aNote)
  {
    date = aDate;
    note = aNote;
  }

  public Footnote(){

  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setNote(String aNote)
  {
    boolean wasSet = false;
    note = aNote;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public String getNote()
  {
    return note;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "note" + ":" + getNote()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}
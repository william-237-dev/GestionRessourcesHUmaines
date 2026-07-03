package com.axitech.dao.entities;

import java.sql.Date;

public class Tache {
   private int id;
   private String description;
   private Date date;
   
   public Tache() {
   }

   public Tache(int id, String description, Date date) {
    this.id = id;
    this.description = description;
    this.date = date;
   }

   public int getId() {
    return id;
   }

   public void setId(int id) {
    this.id = id;
   }

   public String getDescription() {
    return description;
   }

   public void setDescription(String description) {
    this.description = description;
   }

   public Date getDate() {
    return date;
   }

   public void setDate(Date date) {
    this.date = date;
   }

   

    
}

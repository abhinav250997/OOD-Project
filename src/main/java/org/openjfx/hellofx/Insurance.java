package org.openjfx.hellofx;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abhishekvenkata
 */
public class Insurance {
    public int id;
    public String email_id;
    public String insurance_type;
    public String insurance_name;
    public int monthly_premium;
    public int tenure;

    public Insurance() {
    }

    public Insurance(int id, String email_id, String insurance_type, String insurance_name, int monthly_premium, int tenure) {
        this.id = id;
        this.email_id = email_id;
        this.insurance_type = insurance_type;
        this.insurance_name = insurance_name;
        this.monthly_premium = monthly_premium;
        this.tenure = tenure;
    }

    public int getId() {
        return id;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getInsurance_type() {
        return insurance_type;
    }

    public String getInsurance_name() {
        return insurance_name;
    }

    public int getMonthly_premium() {
        return monthly_premium;
    }

    public int getTenure() {
        return tenure;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public void setInsurance_type(String insurance_type) {
        this.insurance_type = insurance_type;
    }

    public void setInsurance_name(String insurance_name) {
        this.insurance_name = insurance_name;
    }

    public void setMonthly_premium(int monthly_premium) {
        this.monthly_premium = monthly_premium;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }
    
    
}

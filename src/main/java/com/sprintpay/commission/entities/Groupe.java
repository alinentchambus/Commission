/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ALINE-PSE
 */
@Entity
public class Groupe implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    
    @OneToMany(mappedBy = "group")
    List<Country> countries;
    
    @OneToMany(mappedBy = "group", cascade = {CascadeType.REMOVE})
    List<Commission> commissions;

    public Groupe() {
    }

    public Groupe(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Commission> getCommissions() {
        return commissions;
    }

    public void setCommissions(List<Commission> commissions) {
        this.commissions = commissions;
    }

    @Override
    public String toString() {
        return "Group{" + "id=" + id + ", name=" + name + '}';
    }

}

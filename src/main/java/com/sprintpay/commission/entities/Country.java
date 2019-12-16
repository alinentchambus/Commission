/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author ALINE-PSE
 */
@Entity
public class Country implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String code;
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Groupe group;

    public Country() {
    }

    public Country(String code, String name) {
        this.name = name;
        this.code = code;
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

//    public Groupe getGroup() {
//        return group;
//    }

    public void setGroup(Groupe group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}

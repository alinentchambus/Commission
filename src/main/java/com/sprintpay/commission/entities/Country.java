/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Boolean isActive;
    
    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @JsonIgnoreProperties("countries")
    private Groupe group;

    public Country() {
    }

    public Country(int id, String code, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.isActive = isActive;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Groupe getGroup() {
        return group;
    }

    public void setGroup(Groupe group) {
        this.group = group;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", code=" + code + ", name=" + name + ", isActive=" + isActive + ", group=" + group + '}';
    }

        
}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author ALINE-PSE
 */
@Entity
public class Transaction implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String name;    
    private String code;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
    
    public Transaction() {
    }

    public Transaction(int id,String code, String name, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
    }
    
    public Transaction(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", name=" + name + ", code=" + code + ", description=" + description + ", service=" + service + '}';
    }
}

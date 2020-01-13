/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.dto;

import com.sprintpay.commission.entities.Groupe;

/**
 *
 * @author ALINE-PSE
 */
public class CountryDTO {
    public int id;
    public  String code;
    public  String name;
    public int groupId;
    public Groupe groupe;

    public CountryDTO(int id, String code, String name, int groupId, Groupe groupe) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.groupId = groupId;
        this.groupe = groupe;
    }
    
    
}

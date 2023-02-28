package com.ouvriers.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class PublicData extends CategoryData {

    @Column(name = "name")
    private String name;  // Egypt

}

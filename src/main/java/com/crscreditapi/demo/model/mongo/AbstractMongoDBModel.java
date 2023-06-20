package com.crscreditapi.demo.model.mongo;

import com.crscreditapi.demo.model.BaseModel;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractMongoDBModel extends BaseModel {

    @Id
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

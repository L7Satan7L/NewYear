package com.example.newyear.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "present")
public class Present {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "wish")
    private String wish;

    @Column(name = "get")
    private Boolean get;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "wish_id")
    private int wishId;

    public String toJson(Present present){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(present);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }


}

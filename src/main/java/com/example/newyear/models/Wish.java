package com.example.newyear.models;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "wish")
public class Wish {

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "wish_id")
    private List<Present> presents;

    public String toJson(Wish wish){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(wish);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}

package com.example.newyear.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_name", unique = true)
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Wish> wishes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Present> presents;

    public String toJson(User user){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String wishToJson(User user){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(user.getWishes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String presentToJson(User user){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(user.getPresents());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}

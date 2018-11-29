package com.example.pc_2018.housing.Models;

public class MessageMod  {

    private int id;
    private String message;
    private String username;
    private String house_number;

    public MessageMod(int id, String message, String username, String house_number) {
        this.id = id;
        this.message = message;
        this.username = username;
        this.house_number = house_number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public int getId() {
        return id;
    }



    public String getMessage() {
        return message;
    }


    public String getUsername() {
        return username;
    }



    public String getHouse_number() {
        return house_number;
    }


}
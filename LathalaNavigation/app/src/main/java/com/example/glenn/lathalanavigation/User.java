package com.example.glenn.lathalanavigation;

/**
 * Created by Glenn on 3/13/2016.
 */
public class User {
    public static final String TABLE_NAME="user";
    public static final String  COLUMN_ID="_id";
    public static final String COLUMN_USERNAME="username";
    public static final String COLUMN_PASSWORD="password";
    public static final String COLUMN_NAME="name";


    private int id;
    private String username;
    private String password;
    private String name;

    public User(){}

    public User(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

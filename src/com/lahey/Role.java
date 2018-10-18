package com.lahey;

import java.util.ArrayList;
import java.util.HashSet;

public class Role {

    //static variables
    private static long roleCounter = 3001;

    //class variables
    private long  roleID;
    private String roleName;
    private HashSet<User> userSet;

    //*************************************************************************
    //* Constructors
    //*************************************************************************
    //default constructor
    public Role (){

        this.roleID = this.roleCounter++;
        userSet = new HashSet<User>();
    }

    public Role(String name){

        this.roleID = this.roleCounter++;
        this.roleName = name;
        userSet = new HashSet<User>();
    }

    //*************************************************************************
    //* Getters and Setters
    //*************************************************************************
    public long getRoleID() {
        return roleID;
    }

    protected void setRoleName(String s) {
        this.roleName = s;
    }

    public String getRoleName() {
        return roleName;
    }

    //*************************************************************************
    //* methods for user set
    //*************************************************************************
    public void addUser(User user){

        userSet.add(user);
    }

    public HashSet getUserSet(){

        return userSet;
    }


}

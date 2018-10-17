package com.lahey;

import java.util.HashSet;

public class User {

    //static variables
    private static long userCounter = 101;

    //class variables
    private long  userID;
    private String userName;
    private String userPassword;
    private HashSet<Role> roleSet;

    //*************************************************************************
    //* Constructors
    //*************************************************************************
    public User(String sName, String sPassword){

        this.userID = this.userCounter++;
        this.userName = sName;
        this.userPassword = sPassword;
        roleSet = new HashSet<Role>();
    }


    //*************************************************************************
    //* getters & setters
    //*************************************************************************
    public long getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public HashSet<Role> getRoleSet() {
        return roleSet;
    }


    //*************************************************************************
    //* HashSet methods
    //*************************************************************************
    public void addRole(Role role){

        roleSet.add(role);
    }

    public HashSet getRoles(){

        return roleSet;
    }

}//end public class User

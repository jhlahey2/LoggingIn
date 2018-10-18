package com.lahey;

import java.util.HashMap;
import java.util.HashSet;

public class User {

    //static variables
    private static long userCounter = 101;
    private static HashMap<String, String> userPasswordMap = new HashMap<String, String> ();

    //class variables
    private long  userID;
    private String userName;
    private String userPassword;
    private HashSet<Role> roleSet;

    //*************************************************************************
    //* static authentication methods
    //*************************************************************************
    public static boolean authenticateUser(String sUserName){


        if( userPasswordMap.containsKey(sUserName)){

            return true;
        }

        return false;
    }

    public static User authenticateUserPassword(String userid, String userpassword) {

        String password = userPasswordMap.get(userid);

        if (password.equalsIgnoreCase(userpassword)) {

            return null;
        }
    }//end public static User authenticateUserPassword(String userid, String userpassword)
    //*************************************************************************
    //* Constructors
    //*************************************************************************
    public User(String sName, String sPassword){

        this.userID = this.userCounter++;
        this.userName = sName;
        this.userPassword = sPassword;
        userPasswordMap.put(this.userName, this.userPassword);
        roleSet = new HashSet<Role>();
    }


    //*************************************************************************
    //* getters & setters
    //*************************************************************************
    public static HashMap<String, String> getUserPasswordMap() {
        return userPasswordMap;
    }
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

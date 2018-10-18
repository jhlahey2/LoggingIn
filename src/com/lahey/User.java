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

    /**
     * This method returns the user if user name matches user password, otherwise returns null
     *
     * @param userName
     * @param userpassword
     * @param allCurrentUsers
     * @return
     */
    public static User authenticateUserPassword(String userName, String userpassword, HashSet<User> allCurrentUsers) {

        if(authenticateUser(userName)){

            String password = userPasswordMap.get(userName);

            if (password.equalsIgnoreCase(userpassword)) {

                for (User temp : allCurrentUsers){

                    if(temp.getUserName().equalsIgnoreCase(userName)){

                        return temp;
                    }
                }

            }//end if (password.equalsIgnoreCase(userpassword))

        }//end if(authenticateUser(userName))

        return null;

    }//end public static User authenticateUserPassword(String userid, String userpassword)


    //*************************************************************************
    //* Constructor
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

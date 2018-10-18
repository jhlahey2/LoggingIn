package com.lahey;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static User currentUser = null;

    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);
//        HashSet loggedOnUsers = new HashSet<User>();
        HashSet allCurrentUsers = new HashSet<User>();
        HashSet allCurrentRoles = new HashSet<Role>();
        int iMenuQuit = 8;
        int iMenuSelection = 0;
        String mainMenu = "Please make a selection\n" +
                "1. Display All Users\n" +
                "2. Display all Roles\n" +
                "3. Add a Role\n" +
                "4. Add a User\n" +
                "5. Link User and Role\n" +
                "6. Log In\n" +
                "7. Log Out\n" +
                "8. Quit";


        //create initial data elements
        initialize(allCurrentUsers, allCurrentRoles );

        do{
            System.out.println(mainMenu);
            while( !inputScanner.hasNextInt() ){
                System.out.print("Please enter a number");
                inputScanner.nextLine();
            }
            iMenuSelection = inputScanner.nextInt();
            inputScanner.nextLine();

            switch(iMenuSelection){

                case 1:
                    System.out.println("Display All Users");
                    displayAllCurrentUsers(allCurrentUsers);
                    break;
                case 2:
                    System.out.println("Display all Roles");
                    displayAllCurrentRoles(allCurrentRoles);
                    break;
                case 3:
                    System.out.println("Add a Role");
                    addNewRole(inputScanner, allCurrentRoles);
                    break;
                case 4:
                    System.out.println("Add a User");
                    break;
                case 5:
                    System.out.println("Link User and Role");
                    break;
                case 6:
                    System.out.println("Log In");

                    break;
                case 7:
                    System.out.println("Log Out");
                    break;
                case 8:
                    System.out.println("Quit");
                    break;
                default :
                    System.out.println("Please select on of the menu options");
                    break;

            }//end switch(iMenuSelection)

        }while( !(iMenuSelection == iMenuQuit) );

    }//end public static void main(String[] args)

    public static void displayAllCurrentUsers(HashSet<User> allUsers ){

        for(User user : allUsers){

            displaySingleUser(user);
        }

    }//end public static void displayAllUsers()

    public static void displaySingleUser(User user){

        String sPrefix = "";
        StringBuffer buffer = new StringBuffer();

        System.out.println("User ID:       " + user.getUserID());
        System.out.println("User Name:     " + user.getUserName());
        System.out.println("User Password: " + user.getUserPassword());
        HashSet<Role> set = user.getRoleSet();
        sPrefix = "";
        for(Role eachRole : set){

            buffer.append(sPrefix);
            buffer.append(eachRole.getRoleName());
            sPrefix = ", ";
        }
        buffer.append("\n");
        System.out.println(buffer);

    }//end public static void displaySingleUser()

    public static void displayAllCurrentRoles(HashSet<Role> allCurrentRoles){

        for(Role role : allCurrentRoles){

            displaySingleRole(role);
        }

    }//end public static void displayAllCurrentRoles()


    public static void displaySingleRole(Role role){

        String sPrefix = "";
        StringBuffer buffer = new StringBuffer();

        System.out.println("Role:    " + role.getRoleName());
        System.out.println("Role ID: " + role.getRoleID() );
        HashSet<User> set = role.getUserSet();
        sPrefix = "";
        buffer.append(role.getRoleName() + "s: ");
        for(User eachUser : set){

            buffer.append(sPrefix);
            buffer.append("User ID: " + eachUser.getUserID() + "  User Name: " + eachUser.getUserName());
            sPrefix = ", ";
        }
        buffer.append("\n");
        System.out.println(buffer);

    }//end public static void displaySingleRole(Role role)

    public static void linkUserAndRole(User user, Role role){


    }

    public static void addNewRole(Scanner inputScan, HashSet allCurrentRoles){

        int iQuit = 5;
        int iSelection = 0;
        String sMenuYes = "Y";
        String sMenuNo = "N";
        String sInput = "";
        boolean bNewRoleAdded = false;


        String sSubMenu = "Please make a selection\n" +
                "1. Add Administrator Role\n" +
                "2. Add Contributor Role\n" +
                "3. Add Editor Role\n" +
                "4. Add Janitor Role\n" +
                "5. Quit";
        String sMenuYesNo = "Do you want to add another Role?  Enter \"Y\" or \"N\"";

        do{
            System.out.println(sSubMenu);

            while( !(inputScan.hasNextInt()) ){

                System.out.println("Please enter a number");
                inputScan.nextLine();
            }
            iSelection = inputScan.nextInt();
            inputScan.nextLine();

            Role role = null;
            bNewRoleAdded = false;

            switch(iSelection){

                case 1:
                    role = Administrator.getInstance();
                    bNewRoleAdded = allCurrentRoles.add(role);
                    break;
                case 2:
                    role = Contributor.getInstance();
                    bNewRoleAdded = allCurrentRoles.add(role);
                    break;
                case 3:
                    role = Editor.getInstance();
                    bNewRoleAdded = allCurrentRoles.add(role);
                    break;
                case 4:
                    role = Janitor.getInstance();
                    bNewRoleAdded = allCurrentRoles.add(role);
                    break;
                case 5:
                    System.out.println("Quit");
                    break;
                default:
                    System.out.println("Please select one of the menu options.");
                    break;
            }


            if( bNewRoleAdded ){

                System.out.println(role.getClass().getSimpleName() + " has been added!\n");
            }
            else {

                System.out.println(role.getClass().getSimpleName() + " was ALREADY added!\n");
            }

            do{
                System.out.println(sMenuYesNo);
                sInput = inputScan.nextLine();
                if (sInput.equalsIgnoreCase(sMenuNo)){

                    iSelection = iQuit;
                }

            }while( !(sInput.equalsIgnoreCase(sMenuYes)) && !(sInput.equalsIgnoreCase(sMenuNo)));

        }while( !(iSelection == iQuit) );

    }//end public static void addNewRole(HashSet allCurrentRoles)

    public static boolean logIn(Scanner inputScanner){

        String sUser = "";
        String sPassword = "";


        System.out.println("Please enter your user name");
        sUser = inputScanner.nextLine();

        if ( User.authenticateUser(sUser) ){

            System.out.println(" Please enter your password");
            sPassword = inputScanner.nextLine();

            User temp = User.authenticateUserPassword(sUser, sPassword);

            if ( !(temp == null) ){

                    currentUser = temp;
            }
            else
            {
                System.out.println("Incorrect password");
            }
        }
        else{

            System.out.println("User does not exist");
        }

        return false;
    }


    public static void initialize(HashSet allCurrentUsers, HashSet allCurrentRoles){

        Role administrator = Administrator.getInstance();
        allCurrentRoles.add(administrator);
        Role contributor   = Contributor.getInstance();
        allCurrentRoles.add(contributor);
        Role editor        = Editor.getInstance();
        allCurrentRoles.add(editor);

        //User contruct userName password
        User user01 = new User("Tom", "password");
        allCurrentUsers.add(user01);
//        user01.addRole(administrator);
//        administrator.addUser(user01);
        user01.addRole(contributor);
        contributor.addUser(user01);
        user01.addRole(editor);
        editor.addUser(user01);

        User user02 = new User("Ann", "password");
        allCurrentUsers.add(user02);
//        user02.addRole(administrator);
//        administrator.addUser(user02);
        user02.addRole(contributor);
        contributor.addUser(user02);
//        user02.addRole(editor);
//        editor.addUser(user02);

        User user03 = new User("Janet", "password");
        allCurrentUsers.add(user03);
        user03.addRole(administrator);
        administrator.addUser(user03);
//        user03.addRole(contributor);
//        contributor.addUser(user03);
        user03.addRole(editor);
        editor.addUser(user03);

    }//end public static void initialize(HashSet allUsers, HashSet allRoles)


}//end public class Main

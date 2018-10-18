package com.lahey;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static User currentUser = null;

    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);
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
                    if( currentUserIsAuthorized() ){

                        addNewRole(inputScanner, allCurrentRoles);
                    }
                    else {
                        System.out.println("You must be logged on as an Administrator to use this function!");
                    }
                    break;
                case 4:
                    System.out.println("Add a User");
                    if( currentUserIsAuthorized() ){

                        addNewUser(inputScanner, allCurrentUsers);
                    }
                    else {
                        System.out.println("You must be logged on as an Administrator to use this function!");
                    }
                    break;
                case 5:
                    System.out.println("Link User and Role");
                    if( currentUserIsAuthorized() ){

                        linkUserAndRole(inputScanner, allCurrentRoles, allCurrentUsers);
                    }
                    else {
                        System.out.println("You must be logged on as an Administrator to use this function!");
                    }
                    break;
                case 6:
                    System.out.println("Log In");
                    logIn(inputScanner, allCurrentUsers);
                    break;
                case 7:
                    System.out.println("Log Out");
                    logOut();
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


    /**
     * Checks to see whether the current user logged on has an Administrator role
     *
     * @return returns true if there is a user logged on and that user is an administrator, otherwise returns false
     */
    public static boolean currentUserIsAuthorized(){

        if( !(currentUser == null) ){

            HashSet roleSet = currentUser.getRoleSet();
            if( roleSet.contains(Administrator.getInstance()) ){

                return true;
            }
        }//end if( !(currentUser == null) )

        return false;
    }


    /**
     * displays all users
     *
     * @param allUsers
     */
    public static void displayAllCurrentUsers(HashSet<User> allUsers ){

        for(User user : allUsers){

            displaySingleUser(user);
        }

    }//end public static void displayAllUsers()


    /**
     * displays a single user and associated roles
     *
     * @param user
     */
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


    /**
     * displays all Current roles
     * @param allCurrentRoles
     */
    public static void displayAllCurrentRoles(HashSet<Role> allCurrentRoles){

        for(Role role : allCurrentRoles){

            displaySingleRole(role);
        }

    }//end public static void displayAllCurrentRoles()


    /**
     * displays a role and associated users
     *
     * @param role
     */
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


    /**
     * Allows Administrator to add a role to a user
     *
     * @param inputScan
     * @param allCurrentRoles
     * @param allCurrentUsers
     */
    public static void linkUserAndRole(Scanner inputScan, HashSet<Role> allCurrentRoles, HashSet<User> allCurrentUsers){

        int iQuit = 5;
        int iSelection = 0;
        String sMenuYes = "Y";
        String sMenuNo = "N";
        String sInput = "";
        String sInputName = "";
        boolean bNewRoleAdded = false;
        boolean bFoundUser = false;
        User theUser = null;

        String sRoleMenu = "Please make a selection\n" +
                "1. Add Administrator Role\n" +
                "2. Add Contributor Role\n" +
                "3. Add Editor Role\n" +
                "4. Add Janitor Role\n" +
                "5. Quit";

        System.out.println("Enter User Name");
        sInputName = inputScan.nextLine();

        for(User user : allCurrentUsers){

            if(user.getUserName().equalsIgnoreCase(sInputName)){

                theUser = user;
                displaySingleUser(theUser);
                bFoundUser = true;

            }//end if(user.getUserName().equalsIgnoreCase(sInputName))

        }//end for(User user : allCurrentUsers)

        if( !bFoundUser ){

            System.out.printf("User %s was not found\n",sInputName );
        }
        else{

            HashSet roleSet = theUser.getRoleSet();

            for(Role role : allCurrentRoles){

                if( !roleSet.contains(role) ){

                    System.out.printf("Do you want to add Role: %s  to User: %s?  Enter \"Y\" or \"N\"\n", role.getRoleName(), theUser.getUserName());

                    do{
                        sInput = inputScan.nextLine();
                        if(sInput.equalsIgnoreCase(sMenuYes)){

                            theUser.addRole(role);
                            role.addUser(theUser);
                        }
                        else if(sInput.equalsIgnoreCase(sMenuNo)){

                            //do nothing
                        }
                        else{

                            System.out.println("Please enter \"Y\" or \"N\" ");
                        }

                    }while( !(sInput.equalsIgnoreCase(sMenuYes)) && !(sInput.equalsIgnoreCase(sMenuNo)));

                }//end if( !roleSet.contains(role) )

            }//end for(Role role : allCurrentRoles){

        }//end if( !bFoundUser )

    }//end public static void addNewRole(HashSet allCurrentRoles)


    /**
     * Allows Administrator to add a role to the set of Current Roles
     *
     * @param inputScan
     * @param allCurrentRoles
     */
    public static void addNewRole(Scanner inputScan, HashSet<Role> allCurrentRoles){

        int iQuit = 5;
        int iSelection = 0;
        String sMenuYes = "Y";
        String sMenuNo = "N";
        String sInput = "";
        boolean bNewRoleAdded = false;

        String sRoleMenu = "Please make a selection\n" +
                "1. Add Administrator Role\n" +
                "2. Add Contributor Role\n" +
                "3. Add Editor Role\n" +
                "4. Add Janitor Role\n" +
                "5. Quit";
        String sMenuYesNo = "Do you want to add another Role?  Enter \"Y\" or \"N\"";

        do{
            System.out.println(sRoleMenu);

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


    /**
     *  Allows Administrator to add new user
     *
     * @param inputScanner
     * @param allCurrentUsers
     */
    public static void addNewUser(Scanner inputScanner, HashSet<User> allCurrentUsers){

        String sMenuYes = "Y";
        String sMenuNo = "N";
        String sInput = "";
        String newUserName = "";
        String newUserPassword = "";

        User newUser = null;

        System.out.println("Enter user name");
        newUserName = inputScanner.nextLine();

        if(allCurrentUsers.contains(newUser)){

            System.out.printf("User %s already exists\n", newUserName);
        }
        else{

            System.out.println("Enter user password");
            newUserPassword = inputScanner.nextLine();

            newUser = new User(newUserName, newUserPassword);
            allCurrentUsers.add(newUser);

            System.out.printf("User %s has been added\n\n", newUser.getUserName());

        }//end if ( temp.getUserName().equalsIgnoreCase(newUserName) )

    }//end public static void addNewUser(Scanner inputScanner, HashSet<User> allCurrentUsers)


    /**
     * Authenticates user and password, then logs the user in to the application
     *
     * @param inputScanner
     * @param allCurrentUsers
     */
    public static void logIn(Scanner inputScanner, HashSet<User> allCurrentUsers){

        String sUser = "";
        String sPassword = "";

        if(!(currentUser == null)){

            System.out.printf("You are currently logged in as User \"%s\".  You must log out before you can log in as another user.\n\n", currentUser.getUserName());
        }
        else
        {
            System.out.println("Please enter your user name");
            sUser = inputScanner.nextLine();

            if ( User.authenticateUser(sUser) ){

                System.out.println("Please enter your password");
                sPassword = inputScanner.nextLine();

                User temp = User.authenticateUserPassword(sUser, sPassword, allCurrentUsers);

                if ( !(temp == null) ){

                    currentUser = temp;

                }
                else
                {
                    System.out.println("User Password combination is not valid");
                }
            }
            else{

                System.out.printf("User: \"%s\" does not exist\n\n", sUser);

            }//end if ( User.authenticateUser(sUser) )

        }//end if(!(currentUser == null))

    }//end public static void logIn(Scanner inputScanner, HashSet<User> allCurrentUsers)


    /**
     * Logs the user out of the application
     */
    public static void logOut(){

        if( !(currentUser == null) ){

            String name = currentUser.getUserName();
            currentUser = null;
            System.out.println(name + " is now logged out.\n");
        }
        else{

            System.out.println("You can't log out because you aren't logged in.");
        }

    }//end public static void logOut()


    /**
     * Initializes data for this application
     *
     * @param allCurrentUsers
     * @param allCurrentRoles
     */
    public static void initialize(HashSet allCurrentUsers, HashSet allCurrentRoles){

        Role administrator = Administrator.getInstance();
        allCurrentRoles.add(administrator);
        Role contributor   = Contributor.getInstance();
        allCurrentRoles.add(contributor);
        Role editor        = Editor.getInstance();
        allCurrentRoles.add(editor);

        //User contruct userName password
        User user01 = new User("tom", "password");
        allCurrentUsers.add(user01);
        user01.addRole(contributor);
        contributor.addUser(user01);
        user01.addRole(editor);
        editor.addUser(user01);

        User user02 = new User("ann", "password");
        allCurrentUsers.add(user02);
        user02.addRole(contributor);
        contributor.addUser(user02);

        User user03 = new User("janet", "password");
        allCurrentUsers.add(user03);
        user03.addRole(administrator);
        administrator.addUser(user03);
        user03.addRole(editor);
        editor.addUser(user03);

    }//end public static void initialize(HashSet allUsers, HashSet allRoles)


}//end public class Main

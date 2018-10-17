package com.lahey;

/**
 * This class is a singleton class
 */
public class Janitor extends Role {

    private static Janitor anInstance = new Janitor();

    public static Janitor getInstance(){

        return anInstance;
    }

    private Janitor(){

        super.setRoleName(this.getClass().getSimpleName());
    }

}//end public class Administrator extends Role

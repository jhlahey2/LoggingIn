package com.lahey;

/**
 * This class is a singleton class
 */
public class Contributor extends Role {

    private static Contributor anInstance = new Contributor();

    public static Contributor getInstance(){

        return anInstance;
    }

    private Contributor(){

        super.setRoleName(this.getClass().getSimpleName());
    }

}//end public class Administrator extends Role

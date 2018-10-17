package com.lahey;

/**
 * This class is a singleton class
 */
public class Administrator extends Role {

    private static Administrator anInstance = new Administrator();

    public static Administrator getInstance(){

        return anInstance;
    }

    private Administrator(){

        super.setRoleName(this.getClass().getSimpleName());
    }

}//end public class Administrator extends Role

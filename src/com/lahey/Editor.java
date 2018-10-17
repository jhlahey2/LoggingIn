package com.lahey;

/**
 * This class is a singleton class
 */
public class Editor extends Role {

    private static Editor anInstance = new Editor();

    public static Editor getInstance(){

        return anInstance;
    }

    private Editor(){

        super.setRoleName(this.getClass().getSimpleName());
    }

}//end public class Administrator extends Role

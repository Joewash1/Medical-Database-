package model;

import java.io.Serializable;

/**
 *
 */
public class Supervisor implements Serializable{
    
    /**
     * id
     */
    private String instructorID;
    
    /**
     * first name
     */
    private String firstName;
    
    /**
     * last name
     */
    private String lastName;

    /**
     * constructor
     * @param instructorID
     * @param firstName
     * @param lastName 
     */
    public Supervisor(String instructorID, String firstName, String lastName) {
        this.instructorID = instructorID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    
}

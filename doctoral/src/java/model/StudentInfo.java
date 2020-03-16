package model;

import java.io.Serializable;

/**
 */
public class StudentInfo implements Serializable{
    
    /**
     * supervisor
     */
    private String supervisor;
    
    /**
     * support type
     */
    private String supportType;
    
    /**
     * name of milestone
     */
    private String milestoneName;
    
    /**
     * date passed
     */
    private String datePassed;

    /**
     * constructor
     * 
     * @param supervisor
     * @param supportType
     * @param milestoneName
     * @param datePassed 
     */
    public StudentInfo(String supervisor, String supportType, String milestoneName, String datePassed) {
        this.supervisor = supervisor;
        this.supportType = supportType;
        this.milestoneName = milestoneName;
        this.datePassed = datePassed;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public String getSupportType() {
        return supportType;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public String getDatePassed() {
        return datePassed;
    }
    
    
}

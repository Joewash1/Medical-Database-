package model;

import java.io.Serializable;

/**
 *
 */
public class Section implements Serializable{
    
    /**
     * section id
     */
    private String sectionID;
    
    /**
     * course id
     */
    private String courseID;
    
    /**
     * course name
     */
    private String courseName;

    public Section(String sectionID, String courseID, String courseName) {
        this.sectionID = sectionID;
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public String getSectionID() {
        return sectionID;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }
    
}
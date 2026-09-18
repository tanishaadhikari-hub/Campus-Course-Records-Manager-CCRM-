package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class Student extends Person {
    private String regNo ;


    private boolean active;
    private List<String> enrolledCourses ;

    private LocalDate Enrolment_Date;

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);

        this.regNo =  regNo;
        this.active =    true ;


        this.enrolledCourses   = new ArrayList<>();
        this.Enrolment_Date =  LocalDate.now() ;
    }

    @Override
    public String getProfile() {
        return String.format("Student: %s (%s)", getFullName(), regNo);
    }

    public String get_RegNo() {
        return regNo;


    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public boolean isActive() {


        return active ;
    }

    public void deactivate() {
        this.active    = false;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(String courseCode) {

        if (!enrolledCourses.contains(courseCode)) {

            enrolledCourses.add(courseCode)   ;



        }





    }

    public void un_enroll_Course(String courseCode)


    {



        enrolledCourses.remove(courseCode) ;
    }

    public LocalDate getEnrolment_Date() {
        return Enrolment_Date;
    }
}

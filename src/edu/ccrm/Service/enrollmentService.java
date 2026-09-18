package edu.ccrm.service;




import edu.ccrm.domain.Enrollment ;
import edu.ccrm.domain.Student;

import edu.ccrm.domain.Course;
import edu.ccrm.exceptions.DuplicateEnrollmentException ;


import edu.ccrm.exceptions.Max_Credit_Limit_Exceeded_exception;

import java.util.ArrayList ;
import java.util.List;

public class enrollmentService

{


    private final List<Enrollment> enrollments =    new ArrayList<>() ;
    private static final int MAX_CREDITS_PER_SEMESTER =   18  ;

    public void enroll(Student student, Course course) throws DuplicateEnrollmentException, Max_Credit_Limit_Exceeded_exception {



        // Check duplicate enrollment
        for (Enrollment e : enrollments) {

            if (e.getStudent().equals(student) && e.getCourse().equals(course))
            {



                throw new DuplicateEnrollmentException("Student already enrolled in this course.") ;

            }

        }
        // Check total credits limit
        int totalCredits =   enrollments.stream()
                .filter(e -> e.getStudent().equals(student))
                .mapToInt(e -> e.getCourse().getCredits())
                .sum() ;


        if (totalCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {


            throw new Max_Credit_Limit_Exceeded_exception( "Max credit limit exceeded for the semester.") ;
        }

        Enrollment enrollment =    new Enrollment(student, course)  ;
        enrollments.add(enrollment);


        student.enrollCourse(course.getCode());

    }

    public void unenroll(Student student, Course course)

    {


        enrollments.removeIf(e -> e.getStudent().equals(student) && e.getCourse().equals(course))  ;


        student.un_enroll_Course(course.getCode()) ;


    }

    public List<Enrollment> getEnrollmentsForStudent(Student student)

    {

        List<Enrollment> list =    new ArrayList<>() ;
        for (Enrollment e : enrollments) {

            if (e.getStudent().equals(student))



            {
                list.add(e) ;
            }



        }
        return list;
    }
}

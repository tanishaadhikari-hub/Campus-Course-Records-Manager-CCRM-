package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class course_service {

    private final List<Course> courses =    new ArrayList<>() ;

    public void addCourse(Course course) throws RuntimeException {
        if (findByCode(course.getCode()).isPresent()) {


            throw new RuntimeException("Duplicate course code") ;
        }


        courses.add(course) ;
    }

    public java.util.Optional<Course> findByCode(String code)
    {
        return courses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()   ;
    }

    public List<Course> listCourses() {
        return new ArrayList<>(courses)  ;
    }

    public List<Course> filterByInstructor(Instructor instructor) {
        return courses.stream()



                .filter(c -> c.getInstructor()     != null && c.getInstructor().equals(instructor))
                .collect(Collectors.toList()) ;

    }


}

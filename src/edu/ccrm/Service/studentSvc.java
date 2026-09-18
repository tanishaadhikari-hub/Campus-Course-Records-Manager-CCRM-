package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class studentSvc {


    private final List<Student> students     = new ArrayList<>() ;

    public void addStudent(Student student) throws RuntimeException
    {
        if (findByRegNo(student.get_RegNo()).isPresent())


        {
            throw new RuntimeException("Duplicate registration number") ;
        }


        students.add(student);
    }

    public Optional<Student> findByRegNo(String regNo)


    {


        return students.stream()
                .filter(s -> s.get_RegNo().equalsIgnoreCase(regNo))
                .findFirst();
    }

    public List<Student> listStudents() {
        return new ArrayList<>(students)  ;
    }

    public void deactivateStudent(String regNo)

    {



        findByRegNo(regNo).ifPresent(Student::deactivate)  ;
    }


}

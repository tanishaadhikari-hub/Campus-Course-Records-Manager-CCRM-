package edu.ccrm.util;

import edu.ccrm.domain.Student;

import java.util.Comparator;

public class Comparators {

    public static Comparator<Student> studentByName = Comparator.comparing(Student::getFullName);

    public static Comparator<Student> studentByRegNo = Comparator.comparing(Student::get_RegNo);
}

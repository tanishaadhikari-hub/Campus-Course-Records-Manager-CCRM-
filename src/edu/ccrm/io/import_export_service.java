package edu.ccrm.io;

import edu.ccrm.domain.*;
import java.nio.file.*;
import java.util.*;
import java.io.IOException;

public class import_export_service  {

    public List<Student> importStudents(Path filePath) throws IOException {

        List<Student> students =     new ArrayList<>();


        List<String> lines = Files.readAllLines(filePath);

        // Skip header row


        for (int i =1 ; i < lines.size(); i++) {
            String line  = lines.get(i);

            String[] parts =  line.split(",");

            if (parts.length >=  3) {

                String regNo =  parts[0].trim() ;
                String full_Name = parts[1].trim();



                String email=  parts[2].trim();

                Student student =    new Student(java.util.UUID.randomUUID().toString(), regNo , full_Name, email) ;
                students.add(student)  ;


            }




        }

        return students ;

    }

    public void exportStudents(List<Student> students, Path filePath) throws IOException

    {
        List<String> lines =    new ArrayList<>() ;

        lines.add("regNo,fullName,email") ;

        for (Student s : students)


        {


            lines.add(String.format("%s,%s,%s", s.get_RegNo(), s.getFullName(), s.getEmail())) ;
        }



        Files.write(filePath, lines);
    }

    public List<Course> import_Courses(Path filePath) throws IOException {



        List<Course> courses =    new ArrayList<>()  ;
        List<String> lines    = Files.readAllLines(filePath) ;





        // Skip header row
        for (int i = 1; i < lines.size();i++) {
            String line =   lines.get(i);



            String[] parts= line.split(",")  ;

            if (parts.length  >= 5)
            {
                String code = parts[0].trim();
                String title =  parts[1].trim();

                int credits   = Integer.parseInt(parts[2].trim());

                Semester semester = Semester.valueOf(parts[3].trim().toUpperCase()) ;


                String department  = parts[4].trim();

                Course course =  new Course.Builder()
                        .setCode(code)
                        .setTitle(title)
                        .setCredits(credits)
                        .setSemester(semester)
                        .setDepartment(department)
                        .build();

                courses.add(course)  ;
            }




        }



        return courses;
    }

    public void exportCourses(List<Course> courses, Path filePath) throws IOException {
        List<String> lines =        new ArrayList<>();
        lines.add("code,title,credits,semester,department")  ;

        for (Course c : courses) {
            lines.add(String.format("%s,%s,%d,%s,%s",
                    c.getCode(), c.getTitle(), c.getCredits(), c.getSemester(), c.getDepartment()));
        }



        Files.write(filePath, lines);
    }



}

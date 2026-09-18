package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.service.*;
import edu.ccrm.io.*;
import edu.ccrm.util.*;

import java.nio.file.*;
import java.util.*;
import java.io.IOException;

public class MainMenu  {

    private final  studentSvc studentSvc = new studentSvc() ;

    private final course_service courseservice = new course_service()  ;

    private     final edu.ccrm.service.enrollmentService enrollmentService = new enrollmentService();

    private  final import_export_service ioService  =  new import_export_service();


    private final   Scanner scanner = new Scanner(System.in)  ;

    public void run() {


        boolean   exit =   false ;

        while  (!exit ) {
            System.out.println(  "\n=== Campus Course & Records Manager ===") ;

            System.out.println( "1. Student Management" )  ;

            System.out.println( "2. Course Management" ) ;
            System.out.println( "3. Enrollment & Grades" ) ;
            System.out.println(  "4. File Operations" );
            System.out.println("0. Exit"  ) ;
            System.out.print( "Select : " )    ;

            int  choice =  scanner.nextInt()   ;
            scanner.nextLine() ;

            switch   ( choice  )    {


                case 1 : students_Menu();  break;
                case 2: cours_menu(); break;

                case 3: enrollmentMenu();   break;

                case 4: File_meenu(); break   ;
                case 0: exit = true ;   break ;

                default : System.out.println ( "Invalid!"  ) ;



            }
        }

        System.out.println("Thank you for using CCRM!");

    }


    private void students_Menu()  {


        while (true)
        {



            System.out.println (  "\n-- Student Management --" )  ;

            System.out.println ( "1. Add Student" ) ;

            System.out.println (  "2. List Students"  ) ;
            System.out.println (  "3. Update Student" ) ;

            System.out.println  ( "4. Deactivate Student" ) ;
            System.out.println (   "5. Print Student Profile" )   ;
            System.out.println("0. Back");

            System.out.print("Choose: ");

            int  ch     = scanner.nextInt();      scanner.nextLine()  ;
            switch (ch )  {

                case 1 :  addStudent() ; break ;


                case 2 : listStudents();  break;
                case 3:  updateStudent() ; break  ;

                case 4: deactivateStudent() ; break ;
                case 5:      printStudentProfile()  ; break;
                case 0:    return ;
                default: System.out.println("Invalid!");
            }



        }
    }

    private void cours_menu() {


        while (true ) {
            System.out.println("\n-- Course Management --"  )   ;

            System.out.println("1. Add Course ");

            System.out.println( "2. List Courses " );


            System.out.println("3. Update Course")  ;

            System.out.println  ("4. Deactivate Course")  ;

            System.out.println ("5. Assign Instructor" )      ;
            System.out.println  ( "6. Search/Filter Courses"  ) ;
            System.out.println(   "0. Back" )  ;


            System.out.print( "Choose: " )  ;




            int   ch =  scanner.nextInt()  ;   scanner.nextLine()    ;

            switch (    ch  ) {

                case 1: addCourse()  ; break ;

                case   2: listCourses();   break;


                case 3: updateCourse(); break ;


                case  4: deactivateCourse(); break  ;

                case 5: assignInstructor(); break  ;
                case 6: searchCourses()  ; break     ;


                case 0: return ;



                default:   System.out.println     (  "Invalid!" )  ;
            }


        }

    }

    private void enrollmentMenu() {


        while (  true) {
            System.out.println("\n-- Enrollment & Grades --")  ;


            System.out.println("1. Enroll Student");

            System.out.println("2. Unenroll Student"  ) ;


            System.out.println("3. Record Grade");
            System.out.println(   "4. Show Transcript");

            System.out.println ("0. Back") ;
            System.out.print  ("Choose: ");
            int ch = scanner.nextInt () ; scanner.nextLine   () ;



            switch ( ch  ) {
                case 1: enrollStudent() ; break  ;

                case 2: unenrollStudent () ; break ;


                case 3: recordGrade  () ; break  ;

                case 4: printTranscript(); break;

                case 0 : return;
                default: System.out.println( "Invalid!" )  ;

            }
        }


    }

    private void File_meenu() {


        while (true) {
            System.out.println   ("\n-- File Utilities --"  ) ;
            System.out.println("1. Import Students" ) ;

            System.out.println( "2. Export Students"  );
            System.out.println("3. Import Courses" );
            System.out.println ("4. Export Courses");


            System.out.println(  "5. Backup Data");

            System.out.println (  "6. Show Backup Directory Size");
            System.out.println ("0. Back") ;

            System.out.print("Choose: ") ;
            int ch = scanner.nextInt()   ; scanner.nextLine();


            switch (ch) {


                case 1:   importStudents(); break ;
                case 2: exportStudents();  break;

                case 3:  importCourses();  break ;
                case 4: exportCourses() ; break;
                case 5:   backupData(); break;

                case 6: showBackupSize() ; break;

                case 0: return;

                default: System.out.println("Invalid!");
            }
        }


    }

    // ==== Student Methods ====
    private void addStudent() {
        System.out.print("Reg No: ");
        String regNo =   scanner.nextLine() ;

        System.out.print( "Name: ")  ;
        String name  =    scanner.nextLine();


        System.out.print( "Email: ")  ;
        String email   = scanner.nextLine()  ;
        Student   s =  new    Student(java.util.UUID.randomUUID().toString() , regNo, name,  email)  ;


        try {


            studentSvc.addStudent(s);

            System.out.println("Student added successfully!" ) ;
        } catch(Exception ex)   {
            System.out.println(   "Error: " +    ex.getMessage())  ;
        }

    }

    private  void listStudents()  {
        var students = studentSvc.listStudents()  ;
        if (students.isEmpty()) {
            System.out.println("No students found.")   ;

            return  ;
        }


        for (Student   s : students)
            System.out.printf("RegNo: %s, Name: %s, Email: %s, Active: %s\n" ,
                    s.get_RegNo(), s.getFullName(), s.getEmail(), s.isActive()) ;


    }

    private void updateStudent() {

        System.out.print("RegNo: ");
        String regNo = scanner.nextLine();
        studentSvc.findByRegNo(regNo).ifPresentOrElse(student -> {


            System.out.print  ("New Name (leave blank): " )   ;
            String name    = scanner.nextLine(); if (!name.isBlank()) student.setFullName(name);


            System.out.print(   "New Email (leave blank): ");

            String email =    scanner.nextLine(); if (!email.isBlank()) student.setEmail(email);
            System.out.println (   "Updated successfully.") ;

        }, () -> System.out.println("Student not found." ) );
    }

    private void deactivateStudent()
    {
        System.out.print ( "RegNo: ") ;
        String   regNo =   scanner.nextLine();
        studentSvc.deactivateStudent(regNo)  ;

        System.out.println("Student deactivated."  );

    }

    private void   printStudentProfile() {
        System.out.print("RegNo: ") ;
        String regNo  = scanner.nextLine() ;

        studentSvc.findByRegNo(regNo).ifPresentOrElse(

                s -> System.out.println(s.getProfile() ),
                () -> System.out.println("Student not found.")) ;
    }



    // Here Course Methods
    private void  addCourse()
    {


        System.out.print(  "Course Code: ") ;
        String  code   = scanner.nextLine() ;


        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Credits: "  ) ;
        int credits = scanner.nextInt();   scanner.nextLine()  ;



        System.out.print  ("Semester (SPRING/SUMMER/FALL): ")  ;
        String semStr =   scanner.nextLine();

        Semester sem =  Semester.valueOf(semStr.toUpperCase());

        System.out.print( "Department: ");
        String dept = scanner.nextLine();


        Course course = new Course.Builder()
                .setCode(code)
                .setTitle(title)

                .setCredits(credits)
                .setSemester(sem)
                .setDepartment(dept)

                .build()  ;
        try
        {
            courseservice.addCourse(course) ;
            System.out.println ("Course added!");
        } catch (RuntimeException ex) {
            System.out.println( "Error: " + ex.getMessage());
        }


    }

    private void listCourses()

    {
        var courses = courseservice.listCourses() ;
        if (courses.isEmpty())
        {
            System.out.println( "No courses found."   );

            return ;
        }


        courses.forEach(System.out::println) ;
    }

    private void updateCourse() {
        System.out.print("Code: ") ;

        String  code =     scanner.nextLine();



        courseservice.findByCode(code).ifPresentOrElse(course -> {
            System.out.print("New Title (leave blank): ");
            String title =  scanner.nextLine();  if (!title.isBlank()) course.setTitle(title);
            System.out.print("New Credits (leave blank): ");


            String credit   =  scanner.nextLine(); if (!credit.isBlank()) course.setCredits(Integer.parseInt(credit));

            System.out.println("Updated.") ;

        }, () -> System.out.println("Course not found.")) ;
    }

    private void deactivateCourse() {


        System.out.print("Code: ") ;
        String code = scanner.nextLine() ;
        courseservice.findByCode(code).ifPresentOrElse(course ->  {
            course.setCredits(0); // Soft deactivation

            System.out.println("Course deactivated.");
        }, () -> System.out.println("Course not found."));

    }

    private void assignInstructor() {


        System.out.print("Course code: ");
        String code = scanner.nextLine();
        courseservice.findByCode(code).ifPresentOrElse(course -> {
            System.out.print(  "Instructor name: ") ;


            String name   = scanner.nextLine() ;
            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Department: "  );

            String   dept = scanner.nextLine() ;
            course.setInstructor(new   Instructor(java.util.UUID.randomUUID().toString(), name, email, dept)) ;

            System.out.println("Assigned instructor." )  ;



        }, () -> System.out.println("Course not found." )) ;

    }

    private void   searchCourses()
    {
        System.out.print("Search by (instructor/department/semester): " )   ;

        String by = scanner.nextLine().toLowerCase();



        List<Course> found  =new ArrayList<>();

        switch (by) {
            case "instructor":
                System.out.print("Instructor name: ");
                String name  = scanner.nextLine();


                found =  courseservice.listCourses().stream()

                        .filter(c -> c.getInstructor() != null && c.getInstructor().getFullName().equalsIgnoreCase(name))
                        .toList() ;


                break ;


            case "department":


                System.out.print("Department: ");
                String dept = scanner.nextLine()  ;


                found = courseservice.listCourses().stream()

                        .filter(c -> c.getDepartment().equalsIgnoreCase(dept))
                        .toList() ;

                break ;


            case "semester"  :

                System.out.print("SPRING/SUMMER/FALL: ");


                Semester s = Semester.valueOf(scanner.nextLine().toUpperCase());

                found = courseservice.listCourses().stream()
                        .filter(c -> c.getSemester() == s)
                        .toList() ;


                break ;



            default :
                System.out.println("Invalid search type.") ;

                return;

        }



        if (found.isEmpty())

            System.out.println("No courses found.") ;


        else
            found.forEach(System.out::println) ;
    }

    //  Enrollment & Grades Methods
    private void enrollStudent() {

        System.out.print("Student RegNo: ");
        String regNo = scanner.nextLine() ;

        System.out.print("Course code: ");
        String   code =   scanner.nextLine();

        var studentOpt = studentSvc.findByRegNo(regNo)  ;
        var courseOpt  = courseservice.findByCode(code);


        if ( studentOpt.isEmpty() || courseOpt.isEmpty()) {

            System.out.println("Student or Course not found.") ;




            return;
        }
        try   {
            enrollmentService.enroll(studentOpt.get(), courseOpt.get());

            System.out.println("Enrolled." );
        } catch(Exception ex)   {
            System.out.println("Error: "     + ex.getMessage());
        }
    }

    private void unenrollStudent() {


        System.out.print("Student RegNo: ");
        String regNo = scanner.nextLine();

        System.out.print("Course code: ");
        String code = scanner.nextLine();

        var   studentOpt =     studentSvc.findByRegNo(regNo);
        var courseOpt   = courseservice.findByCode(code) ;


        if (studentOpt.isEmpty() || courseOpt.isEmpty())
        {
            System.out.println("Student or course not found." )     ;



            return ;
        }



        enrollmentService.unenroll(studentOpt.get(), courseOpt.get())   ;
        System.out.println("Unenrolled.");


    }

    private void recordGrade() {
        System.out.print(    "Student RegNo: " )  ;

        String    regNo =    scanner.nextLine()  ;
        System.out.print("Course code: ");


        String  code  = scanner.nextLine();
        var studentOpt =  studentSvc.findByRegNo(regNo);
        if (studentOpt.isEmpty())
        {
            System.out.println("Student not found.");


            return;
        }
        var enrollments = enrollmentService.getEnrollmentsForStudent(studentOpt.get()) ;
        for (Enrollment e : enrollments)
        {

            if (e.getCourse().getCode().equalsIgnoreCase(code)) {

                System.out.print("Enter grade (S/A/B/C/D/E/F): " )      ;

                e.setGrade(Grade.valueOf(scanner.nextLine().toUpperCase()));
                System.out.println("Grade updated.");
                return;

            }



        }
        System.out.println("Enrollment not found.");
    }









    private void printTranscript() {
        System.out.print("Student RegNo: ")  ;
        String regNo = scanner.nextLine();




        var studentOpt = studentSvc.findByRegNo(regNo);

        if(studentOpt.isEmpty()) {

            System.out.println("Student not found.") ;

            return;
        }
        Student student = studentOpt.get() ;
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(student) ;

        System.out.println(student.getProfile());


        double totalPoints  = 0, totalCredits  =  0  ;



        for(Enrollment e: enrollments) {
            int cr=e.getCourse().getCredits();
            double gp =   e.getGrade().getGradePoint();

            totalPoints +=  gp*cr  ;

            totalCredits    += cr  ;
            System.out.printf("Course: %s, Credits: %d, Grade: %s\n", e.getCourse().getTitle(), cr, e.getGrade()) ;
        }

        double gpa = totalCredits==0?0:totalPoints/totalCredits;

        System.out.printf("Total Credits: %.0f, GPA: %.2f\n", totalCredits , gpa) ;


    }

    // File Operations Methods
    private void importStudents() {
        System.out.print("Path to CSV: ");

        Path path = Path.of(scanner.nextLine());
        try
        {

            var students = ioService.importStudents(path);
            students.forEach(studentSvc::addStudent) ;

            System.out.println ( "Imported " + students.size()  + " students.") ;
        } catch (IOException ex)

        {
            System.out.println("Error: "+ex.getMessage())  ;
        }




    }

    private void exportStudents() {
        System.out.print("Export path: ");


        Path path = Path.of(scanner.nextLine());
        try
        {


            ioService.exportStudents(studentSvc.listStudents(), path)  ;
            System.out.println("Exported.") ;

        } catch (IOException ex) {


            System.out.println("Error: "+ex.getMessage())   ;
        }
    }

    private void importCourses() {

        System.out.print("Path to courses CSV: ");
        Path path = Path.of(scanner.nextLine()) ;
        try
        {
            var courses = ioService.import_Courses(path);
            courses.forEach(courseservice::addCourse);


            System.out.println("Imported " + courses.size() + " courses.");
        }  catch (IOException ex)  {


            System.out.println("Error: " + ex.getMessage()) ;
        }
    }

    private void exportCourses() {


        System.out.print("Export courses to path: ");
        Path path = Path.of(scanner.nextLine())     ;





        try

        {

            ioService.exportCourses(courseservice.listCourses(), path);
            System.out.println("Courses exported.");

        } catch (IOException ex) {


            System.out.println("Error: "    + ex.getMessage());




        }

    }

    private void   backupData()   {
        System.out.print("Source dir: ") ;
        Path src = Path.of(scanner.nextLine());


        System.out.print("Backup root: " )   ;
        Path backup = Path.of(scanner.nextLine()) ;



        try {

            new BackupService().backupDirectory(src, backup);
            System.out.println("Backup complete.");
        }  catch(IOException ex){


            System.out.println("Backup error: "+ex.getMessage())  ;
        }


    }

    private void  showBackupSize()


    {
        System.out.print("Backup dir: ") ;
        Path dir = Path.of(scanner.nextLine()  ) ;





        try {
            long size       = RecursionUtils.getDirectorySize(dir);
            System.out.println("Size: " +      size + " bytes")   ;

        } catch(IOException ex){


            System.out.println("Error: "+ex.getMessage());



        }
    }

    public static void main(String[] args) {


        new MainMenu().run();




    }
}

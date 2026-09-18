package edu.ccrm.domain;

public class Course {

    private String code  ;
    private String title ;

    private int credits ;


    private Instructor  instructor;
    private Semester semester;


    private String department ;

    // Builder pattern used here for flexibility in creation


    public static class Builder {



        private String code ;
        private String title  ;
        private int credits;


        private Instructor instructor;
        private Semester semester ;

        private String department;



        public Builder setCode(String code) {
            this.code =    code ;

            return this ;



        }



        public Builder setTitle(String title) {
            this.title =  title;

            return this;

        }



        public Builder setCredits(int  credits) {
            this.credits    = credits;
            return this  ;

        }

        public Builder setInstructor(Instructor instructor) {

            this.instructor =    instructor  ;


            return this  ;
        }

        public Builder setSemester(Semester semester)
        {


            this.semester     = semester;
            return this;
        }

        public Builder setDepartment(String department ) {
            this.department =  department ;
            return this ;
        }




        public Course build() {
            return new Course(this);
        }
    }

    private Course(Builder builder) {


        this.code   =  builder.code;

        this.title =  builder.title ;
        this.credits  = builder.credits ;


        this.instructor = builder.instructor  ;

        this.semester   = builder.semester ;
        this.department =  builder.department;


    }



    // Getters and setters

    public String getCode() { return code ; }
    public String getTitle() { return title; }

    public int getCredits() { return credits; }


    public Instructor getInstructor() { return instructor; }
    public Semester getSemester() { return semester ; }


    public String getDepartment() { return department; }


    public void setCode(String code) { this.code  = code;  }
    public void setTitle(String title) { this.title   = title; }
    public void setCredits(int credits) { this.credits =  credits; }
    public void setInstructor(Instructor instructor) { this.instructor =   instructor ; }



    public void setSemester(Semester semester) { this.semester = semester  ; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return String.format("Course: %s - %s (%d credits), Instructor: %s, Semester: %s, Dept: %s",
                code, title, credits,
                instructor !=  null ? instructor.getFullName() : "TBA",
                semester, department)   ;
    }
}

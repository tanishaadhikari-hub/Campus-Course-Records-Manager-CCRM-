package edu.ccrm.domain;

public abstract class Person
{
    private String id ;


    private String fullName ;
    private String  email  ;

    public Person(String id, String fullName, String email)
    {
        this.id =  id;

        this.fullName   = fullName;

        this.email   = email ;



    }

    // Abstract method for subclasses
    public abstract String  getProfile() ;

    // Getters and setters
    public String getId() {


        return id ;
    }

    public void setId(String id)
    {
        this.id =   id ;

    }

    public String getFullName() {
        return fullName  ;
    }

    public void setFullName(String fullName) {
        this.fullName =   fullName;
    }

    public String getEmail() {
        return  email;
    }

    public void setEmail(String email) {
        this.email =     email;
    }
}

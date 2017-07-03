package org.notapache.writeful.domain;

import org.notapache.writeful.forms.Writeful;

import java.util.Date;


public class Person {
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private Date birthDate;
    private String emailAddress;

    public Person() {}
    public Person(String fullName) {
        this.fullName = fullName;
    }

    public Person(String first, String middle, String last) {
        this.firstName = first;
        this.middleName = middle;
        this.lastName = last;
    }

    //Don't annotate this field - used for default testing.
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Writeful(display="Surname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

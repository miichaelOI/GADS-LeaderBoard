package com.example.gadsleaderboard.models;

public class UserDetails {

    public String firstName;
    public String lastName;
    public String email;
    public String linkToProject;

    public UserDetails(){}

    public UserDetails(String firstName, String lastName, String email, String linkToProject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.linkToProject = linkToProject;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkToProject() {
        return linkToProject;
    }

    public void setLinkToProject(String linkToProject) {
        this.linkToProject = linkToProject;
    }
}

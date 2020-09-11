package com.example.gadsleaderboard.models;

public class LearningLeader {



    public String name ;
    public int hours ;
    public String country ;
    public String badgeUrl;

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }



public LearningLeader(String name, int hours, String country, String badgeUrl){
    this.name = name;
    this.hours = hours;
    this.country = country;
    this.badgeUrl = badgeUrl;

}




public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}

package com.reo.ApplicationsManager;

public class Applicant {
    private String name;
    private int yearsOfExperience;
    private int age;
    private String sex;

    Applicant(String name, int yearsOfExperience, int age, String sex) {
        this.name = name;
        this.yearsOfExperience = yearsOfExperience;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %d, %s", name, yearsOfExperience, age, sex);
    }

    //GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

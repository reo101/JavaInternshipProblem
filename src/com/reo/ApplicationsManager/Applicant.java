package com.reo.ApplicationsManager;

import com.reo.ApplicationsManager.Utils.PrintingUtils;

public class Applicant implements Comparable<Applicant> {
    public static final int MAX_NAME_LENGTH = 24;
    public static final int MAX_SEX_LENGTH = 8;
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
        int nameTabs = (int) Math.floor((MAX_NAME_LENGTH - name.length()) / 4) + 1;
        int sexTabs = (int) Math.floor((MAX_SEX_LENGTH - sex.length()) / 4) + 1;

        return String.format("[Name: %s%s, YoE: %d\t, Age: %d\t, Sex: %s%s]",
                name, PrintingUtils.returnLine('\t', nameTabs),
                yearsOfExperience,
                age,
                sex, PrintingUtils.returnLine('\t', sexTabs)
        );
    }

    public String makeCompactData() {
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

    @Override
    public int compareTo(Applicant o) {
        return 0;
    }
}

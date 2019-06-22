package com.reo.ApplicationsManager.Comparators;

import com.reo.ApplicationsManager.Applicant;
import java.util.Comparator;

public class YearsOfExperienceSorter implements Sorter {
    public int compare(Applicant a1, Applicant a2) {
        return a2.getYearsOfExperience() - a1.getYearsOfExperience();
    }

    @Override
    public String getName() {
        return "years of experience";
    }
}

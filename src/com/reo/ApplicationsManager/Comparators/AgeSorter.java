package com.reo.ApplicationsManager.Comparators;

import com.reo.ApplicationsManager.Applicant;
import java.util.Comparator;

public class AgeSorter implements Sorter {
    public int compare(Applicant a1, Applicant a2) {
        return a2.getAge() - a1.getAge();
    }

    @Override
    public String getName() {
        return "age";
    }
}

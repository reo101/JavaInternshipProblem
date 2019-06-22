package com.reo.ApplicationsManager.Comparators;

import com.reo.ApplicationsManager.Applicant;
import java.util.Comparator;

public class SexSorter implements Sorter {
    public int compare(Applicant a1, Applicant a2) {
        return a1.getSex().compareTo(a2.getSex());
    }

    @Override
    public String getName() {
        return "sex";
    }
}

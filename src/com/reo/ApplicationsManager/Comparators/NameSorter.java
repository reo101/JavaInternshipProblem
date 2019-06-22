package com.reo.ApplicationsManager.Comparators;

import com.reo.ApplicationsManager.Applicant;
import java.util.Comparator;

public class NameSorter implements Sorter {
    public int compare(Applicant a1, Applicant a2) {
        return a1.getName().compareTo(a2.getName());
    }

    @Override
    public String getName() {
        return "name";
    }
}

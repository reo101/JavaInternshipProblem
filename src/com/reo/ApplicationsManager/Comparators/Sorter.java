package com.reo.ApplicationsManager.Comparators;

import com.reo.ApplicationsManager.Applicant;

import java.util.Comparator;

public interface Sorter extends Comparator<Applicant> {
    @Override
    public int compare(Applicant a1, Applicant a2);

    @Override
    boolean equals(Object obj);

    String getName();

//    Comparator<? super Applicant> thenComparing(char sort);
}

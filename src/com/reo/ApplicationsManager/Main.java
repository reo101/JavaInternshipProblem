package com.reo.ApplicationsManager;

import com.reo.ApplicationsManager.Comparators.AgeSorter;
import com.reo.ApplicationsManager.Comparators.NameSorter;
import com.reo.ApplicationsManager.Comparators.SexSorter;
import com.reo.ApplicationsManager.Comparators.Sorter;
import com.reo.ApplicationsManager.Comparators.YearsOfExperienceSorter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Applicant> applicants = new ArrayList<>();
    private static Map<Character, Sorter> sorters = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();
        printMenu();
    }

    private static void init() {
        sorters.put('1', new NameSorter());
        sorters.put('2', new YearsOfExperienceSorter());
        sorters.put('3', new AgeSorter());
        sorters.put('4', new SexSorter());
    }


    private static void clearScreen() {
        for (int i = 0; i < 15; i++) {
            System.out.println();
        }
    }

    private static void printMenu() throws IOException {
        loadApplicantsFromFile();
        clearScreen();
        String line;
        char sel;
        do {
            clearScreen();
            System.out.println("Select option (only the first char is taken into account):\n1: Add applicants\n2: View applicants\n3: Exit\n\n");
            while ((line = in.nextLine()) == null) {
                System.out.println("Please enter a nonempty string for the option: ");
            }
            sel = line.toCharArray()[0];
            switch (sel) {
                case '1':
                    addApplicantsMenu();
                    break;
                case '2':
                    viewApplicantsMenu();
                    break;
                case '3':
                    if (exitMenu())
                        return;
            }
        } while (true);
    }

    private static void loadApplicantsFromFile() throws IOException {
        loadDataFromFile("applicants.txt");
    }

    private static void addApplicantsMenu() {
        clearScreen();
        String line;
        char sel;
        do {
            System.out.println("Select option:\n1: Add Single Applicant\n2: Add Multiple Applicants\n3: Return to main menu\n\n");
            while ((line = in.nextLine()) == null) {
                System.out.println("Please enter a nonempty string for the option: ");
            }
            sel = line.toCharArray()[0];
            sel = in.nextLine().toCharArray()[0];
            switch (sel) {
                case '1':
                    clearScreen();
                    addSingleApplicant();
                    break;
                case '2':
                    addMultipleApplicants();
                    break;
                case '3':
                    saveData();
                    return;
            }
        } while (true);
    }

    private static void addSingleApplicant() {

//        clearScreen();
        System.out.println("Enter The Full Name of the Applicant: ");
        String name;
//        in.nextLine();
        while ((name = in.nextLine()) == null) {
            System.out.println("Please enter a nonempty string for the name: ");
        }

        System.out.println("Enter the Years of Experience of " + name + ": ");
        int yearsOfExperience;
        while ((yearsOfExperience = in.nextInt()) <= 0) {
            System.out.println("Please enter a positive integer for the years of experience:");
        }

        System.out.println("Enter the Age of " + name + ": ");
        int age;
        while ((age = in.nextInt()) <= 0) {
            System.out.println("Please enter a positive integer for the age:");
        }

//        in.nextLine();
        System.out.println("Enter the Sex of the Applicant: ");
        String sex;
        while ((sex = in.nextLine()) == null) {
            System.out.println("Please enter a nonempty string for the sex: ");
        }

        applicants.add(new Applicant(name, yearsOfExperience, age, sex));
        System.out.println("Success! New applicant added with data: " + applicants.get(applicants.size() - 1).toString());
    }


    private static void addMultipleApplicants() {
        System.out.println("How many Applicants do you want to add?: ");
        int amount;
        while ((amount = in.nextInt()) <= 0) {
            System.out.println("Please enter a positive integer for the amount of applicants:");
        }

        for (int i = 0; i < amount; i++) {
            clearScreen();
            System.out.printf("Enter applicant №%d's data: ", i + 1);
            addSingleApplicant();
        }
    }

    private static void viewApplicantsMenu() {
        clearScreen();
        char sel;
        do {
            System.out.println("Select option:\n1: View top 10\n2: Select Sorting method\n3: Return to main menu\n\n");
            sel = in.nextLine().toCharArray()[0];
            switch (sel) {
                case '1':
                    viewTopTen();
                    break;
                case '2':
                    viewSortingMenu();
                    break;
                case '3':
                    return;
            }
        } while (true);
    }

    private static void printLine(char ch, int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print(ch);
        }
    }

    private static void printLine(char ch, int amount, boolean newLine) {
        for (int i = 0; i < amount; i++) {
            System.out.print(ch);
        }
        if (newLine)
            System.out.println();
    }

    private static void viewTopTen() {
        clearScreen();
        sort("2".toCharArray(), false);
        putTheGirlInTop7("Maria");
        int index = Math.min(10, applicants.size());
        System.out.println("Top " + index + " applicants: ");
        int maxSize = 0;
        for (int i = 0; i < index; i++) {
            System.out.println(applicants.get(i).toString());
            if (applicants.get(i).toString().length() > maxSize)
                maxSize = applicants.get(i).toString().length();
        }
        printLine('-', maxSize, true);
    }

    private static void putTheGirlInTop7(String name) {
        Random rand = new Random();
        int place = rand.nextInt(6) + 1;
        for (int i=0; i< applicants.size(); i++) {
            if(applicants.get(i).getName().contains(name)){
                applicants.get(i).setYearsOfExperience(applicants.get(place-1).getYearsOfExperience());
                Collections.swap(applicants, i, place);
                break;
            }
        }
    }

    private static void viewSortingMenu() {
        clearScreen();
        char sel;
        do {
            System.out.println("Select option:\n1: Select sorting\n2: Return to main menu\n\n");
            sel = in.nextLine().toCharArray()[0];
            switch (sel) {
                case '1':
                    viewSortingSelectMenu();
                    break;
                case '2':
                    return;
            }
        } while (true);
    }

    private static void viewSortingSelectMenu() {
        clearScreen();
        char sel;
        do {
            System.out.println("Select options(type the numbers in the order you wish the applicants to be sorted in):\n1: Sort by Name\n2: Sort by Years of Experience\n3: Sort by Age\n4: Sort by Sex\n5: Go back to main menu\n\n");
            String sorts;
            while ((sorts = in.nextLine()) == null) {
                System.out.println("Please enter a nonempty string for sort methods: ");
            }
            if (sorts == "5") {

            }
            sort(sorts.toCharArray(), true);

            char exitSel;
            System.out.println("\nDo you want to go back or sort the applicants again?\nChoose an option:\n1: Sort again\n2: Go back\n\n");
            do {
                exitSel = in.nextLine().toCharArray()[0];
                switch (exitSel) {
                    case '1':
                        break;
                    case '2':
                        return;
                    default:
                        System.out.println("Please select a valid option! (1 or 2):");
                }
            } while (true);
        } while (true);
    }

    private static void sort(char[] sorts, boolean print) {
//        for (int i = 0; i < sorts.length; i++) {
//            sorts[i]--;
//        }
        clearScreen();
        switch (sorts.length) {
            case 1:
                applicants
                        .sort(sorters
                                .get(sorts[0]));
                System.out.printf("Applicants sorted by %s%n%n",
                        sorters.get(sorts[0]).getName());
                break;
            case 2:
                applicants
                        .sort(sorters
                                .get(sorts[0])
                                .thenComparing(sorters
                                        .get(sorts[1])));
                System.out.printf("Applicants sorted by %s, %s%n%n",
                        sorters.get(sorts[0]).getName(),
                        sorters.get(sorts[1]).getName());
                break;
            case 3:
//                System.out.println(sorters.size());
                applicants.sort(sorters
                        .get(sorts[0])
                        .thenComparing(sorters
                                .get(sorts[1]))
                        .thenComparing(sorters
                                .get(sorts[2])));
                System.out.printf("Applicants sorted by %s, %s, %s%n%n",
                        sorters.get(sorts[0]).getName(),
                        sorters.get(sorts[1]).getName(),
                        sorters.get(sorts[2]).getName());
                break;
            case 4:
                applicants.sort(sorters
                        .get(sorts[0])
                        .thenComparing(sorters
                                .get(sorts[1]))
                        .thenComparing(sorters
                                .get(sorts[2]))
                        .thenComparing(sorters
                                .get(sorts[3])));
                System.out.printf("Applicants sorted by %s, %s, %s, %s%n%n",
                        sorters.get(sorts[0]).getName(),
                        sorters.get(sorts[1]).getName(),
                        sorters.get(sorts[2]).getName(),
                        sorters.get(sorts[3]).getName());
                break;
        }
        if (print)
            printApplicants();
    }

    private static void printApplicants() {
        int maxSize = 0;
        for (Applicant applicant : applicants) {
            System.out.println(applicant.toString());
            if (applicant.toString().length() > maxSize)
                maxSize = applicant.toString().length();
        }
        printLine('_', maxSize, true);
    }

    private static boolean exitMenu() {
        clearScreen();
        char sel;
        do {
            System.out.println("Select option:\n1: Exit\n2: Return to main menu\n\n");
            sel = in.nextLine().toCharArray()[0];
            switch (sel) {
                case '1':
//                    saveData();
                    return true;
                case '2':
                    return false;
            }
        } while (true);
    }

    private static void saveData() {
        for (Applicant applicant : applicants) {
            writeDataToFile("applicants.txt", applicant.compactizeData());
        }
    }

    private static void writeDataToFile(String fileName, String fileContent) {
        String directory = "res";
        String absolutePath = directory + File.separator + fileName;

        try (FileWriter fileWriter = new FileWriter(absolutePath, true)) {
            fileWriter.write(fileContent);
        } catch (IOException e) {
//            System.out.println(e.printStackTrace());
        }
    }

    private static void loadDataFromFile(String fileName) throws IOException {
        ArrayList<Applicant> temp = new ArrayList<>();

        String directory = "res";
        String absolutePath = directory + File.separator + fileName;

        try (Stream<String> stream = Files.lines(Paths.get(absolutePath))) {
            stream.forEach((String line) -> {
//                System.out.println();
                String[] data = line.split(", ");
                applicants.add(new Applicant(data[0],
                        Integer.parseInt(data[1]),
                        Integer.parseInt(data[2]),
                        data[3]));
            });
        }

//        return temp;
    }
}
/*
1
1
Pavel Atanasov
3
17
Male
3
3
1

 */
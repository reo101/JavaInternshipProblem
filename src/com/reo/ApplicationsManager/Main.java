package com.reo.ApplicationsManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Applicant> applicants = new ArrayList<>();

    public static void main(String[] args) {
        printMenu();
    }

    private static void clearScreen() {
//        System.out.print("\033\143");
//        System.out.flush();
//        try {
//            if (System.getProperty("os.name").contains("Windows"))
//                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//            else
//                Runtime.getRuntime().exec("clear");
//        } catch (IOException | InterruptedException ex) {}
//        System.out.println("\b\b\b\b\b\b\b\b\b\b\b");
        for (int i = 0; i < 15; i++) {
            System.out.println();
        }
    }

    private static void printMenu() {
        loadApplicantsFromFile();
        clearScreen();
//        System.out.println("Select option:\n1: Add applicants\n2: View applicants\n3: Exit\n\n");
        char sel;
        do {
            clearScreen();
            System.out.println("Select option (only the first char is taken into account):\n1: Add applicants\n2: View applicants\n3: Exit\n\n");
            sel = in.nextLine().toCharArray()[0];
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

    private static void loadApplicantsFromFile() {

    }

    private static void addApplicantsMenu() {
        clearScreen();
        char sel;
        do {
            System.out.println("Select option:\n1: Add Single Applicant\n2: Add Multiple Applicants\n3: Return to main menu\n\n");
            sel = in.nextLine().toCharArray()[0];
            switch (sel) {
                case '1':
                    addSingleApplicant();
                    break;
                case '2':
                    addMultipleApplicants();
                    break;
                case '3':
                    return;
            }
        } while (true);
    }

    private static void addSingleApplicant() {

        clearScreen();
        System.out.println("Enter The Full Name of the Applicant: ");
        String name;
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

        in.nextLine();
        System.out.println("Enter the Sex of the Applicant: ");
        String sex;
        while ((sex = in.nextLine()) == null) {
            System.out.println("Please enter a nonempty string for the sex: ");
        }

        applicants.add(new Applicant(name, yearsOfExperience, age, sex));
        System.out.println("Success! New applicant added with data: " + applicants.get(applicants.size()-1).toString());
    }

    private static void addMultipleApplicants() {
        System.out.println("How many Applicants do you want to add?: ");
        int amount;
        while ((amount = in.nextInt()) <= 0) {
            System.out.println("Please enter a positive integer for the amount of applicants:");
        }

        for (int i = 0; i < amount; i++) {
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

    private static void viewTopTen() {
        int index = Math.min(10, applicants.size());
    }

    private static void viewSortingMenu() {

    }

    private static boolean exitMenu() {
        clearScreen();
        char sel;
        do {
            System.out.println("Select option:\n1: Exit\n2: Return to main menu\n\n");
            sel = in.nextLine().toCharArray()[0];
            switch (sel) {
                case '1':
                    saveData();
                    return true;
                case '2':
                    return false;
            }
        } while (true);
    }

    private static void saveData() {
        for (Applicant applicant : applicants) {
            writeToFile("applicants.txt", applicant.toString());
        }
    }

    private static void writeToFile(String fileName, String fileContent) {
//        String directory = System.getProperty("user.home");
        String directory = "res";
//        String fileName = path;
        String absolutePath = directory + File.separator + fileName;

//        System.out.println(absolutePath);
//        absolutePath = in.nextLine();
        try (FileWriter fileWriter = new FileWriter(absolutePath)) {
//            String fileContent = value;
            fileWriter.write(fileContent);
        } catch (IOException e) {
            // exception handling
        }
    }

    private static void readFromFile(String fileName) {
//        String directory = System.getProperty("user.home");
        String directory = "res";
//        String fileName = path;
        String absolutePath = directory + File.separator + fileName;

        try (FileReader fileReader = new FileReader(absolutePath)) {
            int ch = fileReader.read();
            while (ch != - 1) {
                System.out.print((char) ch);
                ch = fileReader.read();
            }
        } catch (IOException e) {
            // exception handling
        }
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
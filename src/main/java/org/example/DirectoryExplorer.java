package org.example;

import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryExplorer {

    public static String oldPath = Meta.getCurrentDir();
    String[] Outputs;

    public static void pwd() {

        if (CommandParser.getRedirectionTarget().equals("Screen")) {
            System.out.println(Meta.getCurrentDir());
        }
        Meta.setLastOutput(Meta.getCurrentDir());
    }

    public static String error;

    public static void cd(ArrayList<String> arguments) {
        if (!arguments.isEmpty()) {
            String NewPath = String.join(" ", arguments);
            String CurrentPath = Meta.getCurrentDir();
            File directory = new File(NewPath);
            if (!directory.exists()) {
                error = "The directory \"" + NewPath + "\" does not exist";
                System.err.println(error);
            } else {
                if (NewPath.equals(CurrentPath)) {
                    System.out.println("You are already in the " + CurrentPath + " directory");
                } else {
                    Meta.setCurrentDir(NewPath);
                    System.out.println("the new current path is : " + NewPath);
                }
            }
        } else {
            Meta.setCurrentDir(oldPath);
            System.out.println(oldPath);

        }
    }

    public static void ls(ArrayList<Character> options, ArrayList<String> arguments) {
        String pathToList;
        if (arguments.isEmpty()) {
            pathToList = Meta.getCurrentDir();
        } else {
            pathToList = String.join(" ",arguments);
        }

        StringBuilder sb = new StringBuilder(); //concatenate options as one string
        for (Character ch : options) {
            sb.append(ch);
        }
        String result = sb.toString();

        switch (result) {
            case "":
                ls(pathToList);
                break;
            case "a":
                lsA(pathToList);
                break;
            case "r":
                lsR(pathToList);
                break;
            case "ra":
            case "ar":
                lsAR(pathToList);
                break;
            default:
                System.err.println(result + ": Unsupported ls option");
        }

    }

    // Basic ls command
    public static void ls(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles();
        StringBuilder outputBuilder = new StringBuilder();
        if (files != null) {
            Arrays.sort(files);
            for (File file : files) {
                if (!file.isHidden()) {
                    if (CommandParser.getRedirectionTarget().equals("Screen")) {
                        System.out.println(file.getName());
                    }
                    outputBuilder.append(file.getName()).append("\n");
                }
            }
        }
        Meta.setLastOutput(outputBuilder.toString().trim()); // Store final output in Meta
    }
    // ls -a command shows files including hidden ones
    private static void lsA(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles();
        StringBuilder outputBuilder = new StringBuilder();
        if (files != null) {
            Arrays.sort(files);
            //show all including hidden ones
            for (File file : files) {
                if (CommandParser.getRedirectionTarget().equals("Screen")) {
                    System.out.println(file.getName());
                }
                outputBuilder.append(file.getName()).append(System.lineSeparator());
            }
        }
        Meta.setLastOutput(outputBuilder.toString().trim());
    }

    // ls -r command files in reverse order
    private static void lsR(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles();
        StringBuilder outputBuilder = new StringBuilder();
        if (files != null) {
            Arrays.sort(files, (f1, f2) -> f2.getName().compareTo(f1.getName())); // Reverse order
            for (File file : files) {
                if (!file.isHidden()) {
                    if (CommandParser.getRedirectionTarget().equals("Screen")) {
                        System.out.println(file.getName());
                    }
                    outputBuilder.append(file.getName()).append(System.lineSeparator());
                }
            }
        }
        Meta.setLastOutput(outputBuilder.toString().trim());
    }

    // ls -a-r command shows ALL including files in reverse order
    private static void lsAR(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles();
        StringBuilder outputBuilder = new StringBuilder();
        if (files != null) {
            List<String> allEntries = new ArrayList<>();
            // Add all file names to the list
            for (File file : files) {
                allEntries.add(file.getName());
            }
            // Sort in reverse order
            allEntries.sort(Collections.reverseOrder());
            // Print all entries
            for (String entry : allEntries) {
                if (CommandParser.getRedirectionTarget().equals("Screen")) {
                    System.out.println(entry);
                }
                outputBuilder.append(entry).append(System.lineSeparator());
            }
        }
        Meta.setLastOutput(outputBuilder.toString().trim());
    }
}
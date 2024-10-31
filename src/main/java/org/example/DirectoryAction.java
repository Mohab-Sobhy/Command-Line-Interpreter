package org.example;
import java.io.File;
import java.util.ArrayList;

public class DirectoryAction {
    public static void mkdir(ArrayList<String> input) {
        if (input == null || input.isEmpty()) {
            System.err.println("No directories specified.");
            return;
        }

        for (String Dirname : input) {
            File newdir = Factory.createForD(Dirname);

            if (!newdir.exists()) {
                if (newdir.mkdirs())
                    System.out.println("Directory: " + newdir.getName() + " created successfully.");
                else
                    System.err.println("Failed to create directory: " + newdir.getName());
            } else
                System.err.println("Directory: " + newdir.getName() + " already exists.");
        }
    }

    public static void rmdir(ArrayList<String> input) {
        if (input == null || input.isEmpty()) {
            System.err.println("No directories specified.");
            return;
        }

        for (String Dirname : input) {
            File Dir = Factory.createForD(Dirname);

            if (Dir.exists() && Dir.isDirectory()) {
                if (Dir.delete())
                    System.out.println("Directory: " + Dir.getName() + " deleted successfully.");
                else
                    System.err.println("Failed to remove directory: " + Dir.getName());
            } else
                System.err.println("Directory: " + Dir.getName() + " does not exist.");
        }
    }
}

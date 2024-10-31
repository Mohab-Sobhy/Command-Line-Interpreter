package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileAction {
    public static void touch(ArrayList<String> input) {
        if (input == null || input.isEmpty()) {
            System.err.println("No files specified.");
            return;
        }

        for (String filename : input) {
            File newfile = Factory.createForD(filename);
            try {
                if (newfile.createNewFile())
                    System.out.println("File: " + newfile.getName() + " created successfully.");
                else
                    System.err.println("File: " + newfile.getName() + " already exists.");
            } catch (IOException e) {
                System.err.println("Error creating file: " + newfile.getName());
            }
        }
    }

    public static void rm(ArrayList<String> input) {
        if (input == null || input.isEmpty()) {
            System.err.println("No files specified.");
            return;
        }

        for (String filename : input) {
            File file = Factory.createForD(filename);
            if (file.exists()) {
                if (file.delete())
                    System.out.println("File: " + file.getName() + " deleted successfully.");
                else
                    System.err.println("Failed to delete file: " + file.getName());
            } else
                System.err.println("File: " + file.getName() + " does not exist.");
        }
    }

    public static void cat(ArrayList<String> input) {
        if (input == null || input.isEmpty()) {
            System.err.println("No files specified.");
            return;
        }

        for (String filename : input) {
            File file = Factory.createForD(filename);
            if (file.exists()) {
                System.out.println("Content of file " + file.getName() + ":");
                try {
                    Files.lines(Path.of(file.getPath())).forEach(System.out::println);
                } catch (IOException e) {
                    System.err.println("Error reading file: " + file.getName());
                }
            } else
                System.err.println("File: " + file.getName() + " does not exist.");
        }
    }

    public static void mv(String sourcePath, String destDir) {
        try {
            Path source = Paths.get(sourcePath);
            Path destination = Paths.get(destDir, source.getFileName().toString());
            Files.move(source, destination);
            System.out.println("File moved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while moving the file: " + e.getMessage());
        }
    }
}

package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static void mv(ArrayList<String> input, String dir_or_file) {
        if (input == null || input.isEmpty()) {
            System.err.println("No files specified.");
            return;
        }

        if (input.size() == 1) {
            File source_file = Factory.createForD(input.get(0));
            File destFile = Factory.createForD(dir_or_file);

            if (source_file.exists()) {
                if (source_file.renameTo(destFile))
                    System.out.println("File: " + source_file.getName() + " moved to: " + destFile.getPath());
                else
                    System.err.println("Error moving file: " + source_file.getName() + " to: " + destFile.getPath());
            } else
                System.err.println("File: " + source_file.getName() + " does not exist.");
        } else {
            File dir = Factory.createForD(dir_or_file);
            if (!dir.exists())
                dir.mkdirs();
            else if (!dir.isDirectory()) {
                System.err.println("Destination is not a directory: " + dir_or_file);
                return;
            }

            for (String filename : input) {
                File sourceFile = Factory.createForD(filename);
                if (sourceFile.exists()) {
                    File destFile = new File(dir, sourceFile.getName());
                    if (sourceFile.renameTo(destFile))
                        System.out.println("File: " + sourceFile.getName() + " moved to directory: " + dir.getPath() + " successfully.");
                    else
                        System.err.println("Error moving file: " + sourceFile.getName() + " to directory: " + dir.getPath());
                } else
                    System.err.println("File: " + sourceFile.getName() + " does not exist.");
            }
        }
    }
}

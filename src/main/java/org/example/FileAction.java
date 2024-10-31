package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
                if(CommandParser.getRedirectionTarget().equals("Screen")) {
                    System.out.println("Content of file " + file.getName() + ":");
                }
                StringBuilder lastOutput = new StringBuilder();
                try {
                    List<String> lines = Files.readAllLines(Path.of(file.getPath()));
                    for (String line : lines) {
                        if(CommandParser.getRedirectionTarget().equals("Screen")){
                            System.out.println(line);
                        }
                        lastOutput.append(line).append(System.lineSeparator());
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + file.getName());
                }
                String finalOutput = lastOutput.toString();
                Meta.setLastOutput(finalOutput);
            } else {
                System.err.println("File: " + file.getName() + " does not exist.");
            }
        }
    }



    public static void mv(String sourcePath, String destinationPath) {
        try {
            Path source = Paths.get(sourcePath);
            Path destination = Paths.get(destinationPath);

            if (Files.isDirectory(destination)) {
                destination = destination.resolve(source.getFileName());
            }

            Files.move(source, destination);
            System.out.println("File moved successfully to: " + destination.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while moving the file: " + e.getMessage());
        }
    }

}

import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryExplorer {

    public static void pwd() {
        System.out.println(Meta.getCurrentDir());
    }

    public static void cd(ArrayList<String> arguments){
        if (!arguments.isEmpty()) {
            String NewPath = String.join(" ", CommandParser.getArguments());
            String CurrentPath = Meta.getCurrentDir();
            File directory = new File(NewPath);
            if (!directory.exists()) {
                System.err.println("The directory " + NewPath + " does not exist");
            } else {
                if (NewPath.equals(CurrentPath)) {
                    System.out.println("You are already in the " + CurrentPath + " directory");
                } else {
                    Meta.setCurrentDir(NewPath);
                    System.out.println("the new current path is : " + NewPath);
                }
            }
        }
        else {
            System.out.println("No Given Path");
        }
    }

    public static void ls(ArrayList<Character> options , ArrayList<String> arguments){
        String pathToList;
        if(arguments.isEmpty()){
            pathToList = Meta.getCurrentDir();
        }
        else{
            pathToList = arguments.getFirst();
        }

        StringBuilder sb = new StringBuilder(); //concatinate options as one string
        for (Character ch : options) {
            sb.append(ch);
        }
        String result = sb.toString();

        switch (result){
            case "" :
                ls(pathToList);
                break;
            case "a" :
                lsA(pathToList);
                break;
            case "r" :
                lsR(pathToList);
                break;
            case "ra" :
            case "ar" :
                lsAR(pathToList);
                break;
            default:
                System.err.println(result + ": Unsupported ls option");
        }

    }

    // Basic ls command
    private static void ls(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files != null) {
            Arrays.sort(files);
            for (File file : files) {
                if (!file.isHidden()) {  // Skip hidden files (those starting with .)
                    System.out.println(file.getName());
                }
            }
        }
    }
    // ls -a command shows files including hidden ones
    private static void lsA(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files != null) {
            Arrays.sort(files);
            //show all including hidden ones
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
    }
    // ls -r command files in reverse order
    private static void lsR(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files != null) {
            Arrays.sort(files, (f1, f2) -> f2.getName().compareTo(f1.getName())); // Reverse order
            for (File file : files) {
                if (!file.isHidden()) {
                    System.out.println(file.getName());
                }
            }
        }
    }
    // ls -a-r command shows ALL including files in reverse order
    private static void lsAR(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles();
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
                System.out.println(entry);
            }
        }
    }
}
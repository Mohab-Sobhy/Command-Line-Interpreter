import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryExplorer {
    public static void pwd() {
        System.out.println(Meta.getCurrentDir());
    }

    // Basic ls command
    public static void ls() {
        File directory = new File(Meta.getCurrentDir());
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
    public static void lsA() {
        File directory = new File(Meta.getCurrentDir());
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
    public static void lsR() {
        File directory = new File(Meta.getCurrentDir());
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
    public static void lsAR() {
        File directory = new File(Meta.getCurrentDir());
        File[] files = directory.listFiles();
        if (files != null) {
            List<String> allEntries = new ArrayList<>();

            // Add all file names to the list
            for (File file : files) {
                allEntries.add(file.getName());
            }


            // Sort in reverse order
            Collections.sort(allEntries, Collections.reverseOrder());

            // Print all entries
            for (String entry : allEntries) {
                System.out.println(entry);
            }
        }
    }
}
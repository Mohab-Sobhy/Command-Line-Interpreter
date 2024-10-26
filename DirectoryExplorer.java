import java.io.File;

public class DirectoryExplorer {

    public static void pwd() {
        System.out.println(Meta.getCurrentDir());
    }

    public static void ls() {
        String currentDir = Meta.getCurrentDir();
        File directory = new File(currentDir);
        if (directory.isDirectory()) {
            String[] files = directory.list();
            if (files != null) {
                for (String file : files) {
                    System.out.println(file);
                }
            } else {
                System.out.println("No files found in the directory.");
            }
        } else {
            System.out.println("Invalid directory.");
        }
    }
}

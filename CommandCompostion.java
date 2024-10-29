import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ContentHandler;

public class CommandCompostion {
    
    public static void pipe(String NextAfterPipe) {
        Controller.executeCommand(NextAfterPipe);
    }
    public static void redirectOutput(String Redirection) {
        String[] parts = Redirection.trim().split("\\\\");
        String fileName = parts[parts.length - 1];
        StringBuilder directoryPath = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            if (!parts[i].isEmpty()) { // Skip empty elements
                directoryPath.append(File.separator).append(parts[i]);
            }
        }
        try {
            File file;
            if (directoryPath.length() == 0) {
                file = new File(fileName);
            } else {
                File directory = new File(directoryPath.toString());
                if (!directory.exists()) {
                    System.err.println("Failed to create directory: " + directoryPath);
                    return;
                }
                file = new File(directory, fileName);
            }
            if (file.exists()) {
                if (!file.delete()) {
                    System.err.println("Failed to delete existing file: " + file.getAbsolutePath());
                    return;
                }
            }
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + file.getAbsolutePath());
            } else {
                System.err.println("Failed to create file: " + file.getAbsolutePath());
            }

        } catch (IOException e) {
            System.err.println("Error handling file: " + e.getMessage());
        }
    }
    public static void appendOutput(String Redirection){
         String[] parts = Redirection.trim().split("\\\\");
        String fileName = parts[parts.length - 1];
        StringBuilder directoryPath = new StringBuilder();

        for (int i = 0; i < parts.length - 1; i++) {
            if (!parts[i].isEmpty()) { // Skip empty elements
                directoryPath.append(File.separator).append(parts[i]);
            }
        }

        try {
            File file;
            if (directoryPath.length() == 0) {
                file = new File(fileName);
            } else {
                File directory = new File(directoryPath.toString());
                if (!directory.exists() && !directory.mkdirs()) {
                    System.err.println("Failed to create directory: " + directoryPath);
                    return;
                }
                file = new File(directory, fileName);
            }

            // Append to the file (or create it if it does not exist)
            try (FileWriter writer = new FileWriter(file, true)) {  // `true` enables append mode , should be a content parameter to overwrite
                System.out.println("File exists or created: " + file.getAbsolutePath());
            }

        } catch (IOException e) {
            System.err.println("Error handling file: " + e.getMessage());
        }
    }
}

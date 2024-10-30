public class InternalCommands {
    public static void exit() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }

    public static void help() {
        System.out.println("commands guid:");

        System.out.println("  pwd       - Display the current directory path.");
        System.out.println("  ls        - List files and directories in the current directory.");
        System.out.println("  mkdir     - Create new directories.");
        System.out.println("  rmdir     - Remove empty directories.");
        System.out.println("  touch     - Create an empty file.");
        System.out.println("  mv        - Move or rename a file or directory.");
        System.out.println("  rm        - Remove files.");
        System.out.println("  cd        - Change the current directory.");
        System.out.println("  cat       - Display contents of a file.");
        System.out.println("  exit      - Exit the program.");
        System.out.println("  help      - Display this help message.");
    }
}
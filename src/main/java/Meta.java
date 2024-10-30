public class Meta {
    private static String currentDir = System.getProperty("user.dir");

    private static String lastOutput;
    public static String getCurrentDir() {
        return currentDir;
    }
    public static void setCurrentDir(String currentDir) {
        Meta.currentDir = currentDir;
    }
    public static String getLastOutput() {
        return lastOutput;
    }
    public static void setLastOutput(String lastOutput) {
        Meta.lastOutput = lastOutput;
    }
}
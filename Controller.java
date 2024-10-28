public class Controller {

    public static void executeCommand(String rawInput) {
        CommandParser.setRawInput(rawInput);
        CommandParser.splitRawInput();

        switch (CommandParser.getCommand()) {
            case "pwd":
                DirectoryExplorer.pwd();
                break;

            case "ls":
                if (!CommandParser.getOptions().isEmpty()) {
                    boolean hasA = CommandParser.getOptions().contains('a');
                    boolean hasR = CommandParser.getOptions().contains('r');
                    if (hasA && hasR) {
                        DirectoryExplorer.lsAR();
                    } else if (hasA) {
                        DirectoryExplorer.lsA();
                    } else if (hasR) {
                        DirectoryExplorer.lsR();
                    } else {
                        System.err.println("Unsupported ls option");
                    }
                } else {
                    if (!CommandParser.getArguments().isEmpty()) {
                        String originalDir = Meta.getCurrentDir();
                        Meta.setCurrentDir(CommandParser.getArguments().get(0));
                        DirectoryExplorer.ls();
                        Meta.setCurrentDir(originalDir);
                    } else {
                        DirectoryExplorer.ls();
                    }
                }
                break;

            case "cd":
                try {
                    if (CommandParser.getArguments().isEmpty()) {
                        throw new IllegalArgumentException("missing Argument");
                    } else {
                        DirectoryExplorer.cd();
                    }
                } catch (IllegalArgumentException e) {
                    System.err.print(e.getMessage());
                }
                break;

            case "mkdir":
                try {
                    if (CommandParser.getArguments().isEmpty()) {
                        throw new IllegalArgumentException("missing Argument");
                    } else {
                        //DirectoryAction.mkdir(arguments);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.print(e.getMessage());
                }
                break;

            case "rmdir":
                try {
                    if (CommandParser.getArguments().isEmpty()) {
                        throw new IllegalArgumentException("missing Argument");
                    } else {
                        //DirectoryAction.rmdir(arguments);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.print(e.getMessage());
                }
                break;

            case "touch":
                try {
                    if (CommandParser.getArguments().isEmpty()) {
                        throw new IllegalArgumentException("missing Argument");
                    } else {
                        //FileAction.touch(arguments);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.print(e.getMessage());
                }
                break;

            case "mv":
                try {
                    if (CommandParser.getArguments().size() < 2) {
                        throw new IllegalArgumentException("missing Argument");
                    } else {
                        //FileAction.mv(arguments);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.print(e.getMessage());
                }
                break;

            case "rm":
                try {
                    if (CommandParser.getArguments().isEmpty()) {
                        throw new IllegalArgumentException("missing Argument");
                    } else {
                        //FileAction.rm(arguments);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.print(e.getMessage());
                }
                break;

            case "cat":
                try {
                    if (CommandParser.getArguments().isEmpty()) {
                        throw new IllegalArgumentException("missing Argument");
                    } else {
                        //FileAction.cat(arguments);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.print(e.getMessage());
                }
                break;

            case "exit":
                InternalCommands.exit();
                break;

            case "help":
                InternalCommands.help();
                break;

            default:
                System.err.print(CommandParser.getCommand() + ": command not found");

        }

        if (CommandParser.isThereAPipe) {
            CommandCompostion.pipe(CommandParser.getNextRawCommandAfterPipe());
        }
        if (CommandParser.isThereARedirectOutput) {
            //CommandCompostion.RedirectOutput(CommandParser.getRedirectionTarget());
        }
        if (CommandParser.isThereAnAppendOutput) {
            //CommandCompostion.AppendOutput(CommandParser.getRedirectionTarget());
        }
    }
}

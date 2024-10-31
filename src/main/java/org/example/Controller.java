package org.example;

public class Controller {

    public static void executeCommand(String rawInput) {
        CommandParser.setRawInput(rawInput);
        CommandParser.splitRawInput();

        switch (CommandParser.getCommand()) {
            case "pwd":
                DirectoryExplorer.pwd();
                break;

            case "ls":
                DirectoryExplorer.ls(CommandParser.getOptions() , CommandParser.getArguments());
                break;

            case "cd":
                DirectoryExplorer.cd(CommandParser.getArguments());
                break;

            case "mkdir":
                try {
                    if (CommandParser.getArguments().isEmpty()) {
                        throw new IllegalArgumentException("missing Argument");
                    } else {
                        DirectoryAction.mkdir(CommandParser.getArguments()); 
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
                        DirectoryAction.rmdir(CommandParser.getArguments()); 
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
                        FileAction.touch(CommandParser.getArguments());
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
                        String dest = CommandParser.getArguments().getLast();
                        for(int i =0 ; i<CommandParser.getArguments().size()-1 ; i++){
                            FileAction.mv(CommandParser.getArguments().get(i),dest);
                        }
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
                        FileAction.rm(CommandParser.getArguments());
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
                        FileAction.cat(CommandParser.getArguments());
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

            case ">>": //if user make pipe and command starts with >>
                CommandCompostion.appendOutput(CommandParser.getRedirectionArg());
                break;

            case ">": //if user make pipe and command starts with >
                CommandCompostion.redirectOutput(CommandParser.getRedirectionArg());
                break;

            case "echo":
                for (String argument : CommandParser.getArguments()) {
                    System.out.println(argument);
                }
                break;

            default:
                System.err.print(CommandParser.getCommand() + ": command not found");

        }

        if (CommandParser.isThereAPipe) {
            CommandCompostion.pipe(CommandParser.getNextRawCommandAfterPipe());
        }
        if (CommandParser.isThereARedirectOutput) {
            CommandCompostion.redirectOutput(CommandParser.getRedirectionTarget());
        }
        if (CommandParser.isThereAnAppendOutput) {
            CommandCompostion.appendOutput(CommandParser.getRedirectionTarget());
        }
    }
}

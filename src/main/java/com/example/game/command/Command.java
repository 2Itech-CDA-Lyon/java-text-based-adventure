package com.example.game.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command entered by the user
 */
public class Command
{
    /**
     * The user input this command responds to
     */
    private String command;
    /**
     * The message to display when the command was used with an item that has no effect bound to it
     */
    private String defaultMessage;

    /**
     * Create new command
     * @param command The user input this command responds to
     * @param defaultMessage The message to display when the command was used with an item that has no effect bound to it
     */
    public Command(String command, String defaultMessage)
    {
        this.command = command;
        this.defaultMessage = defaultMessage;
    }

    /**
     * Assess whether an arbitrary user input matches the command
     * @param userInput The user input to process
     * @return The rest of the input if it matched the command, null otherwise
     */
    public String match(String userInput)
    {
        Pattern pattern = Pattern.compile("^" + command + "\\s(.+)$");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public String getCommand() {
        return command;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}

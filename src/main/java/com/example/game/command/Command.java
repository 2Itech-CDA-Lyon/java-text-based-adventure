package com.example.game.command;

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

    public String getCommand() {
        return command;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}

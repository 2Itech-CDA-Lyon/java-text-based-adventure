package com.example.entity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.*;

import com.example.entity.effect.AbstractEffect;

/**
 * Represents a command entered by the user
 */
@Entity
@Table(name = "commands")
public class Command extends AbstractEntity
{
    /**
     * The user input this command responds to
     */
    @Column(name = "command")
    private String command;
    /**
     * The message to display when the command was used with an item that has no effect bound to it
     */
    @Column(name = "default_message")
    private String defaultMessage;
    /**
     * List of all effects associated with the command
     */
    @OneToMany
    @JoinColumn(name = "command_id")
    private List<AbstractEffect> effects;

    public Command()
    {
        
    }

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
        Pattern pattern = Pattern.compile(String.format("^%s$", command.replace("%", "(.+)")));
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

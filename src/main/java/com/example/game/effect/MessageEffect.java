package com.example.game.effect;

/**
 * Represents an effect that produces a message display in the console
 */
public class MessageEffect implements Effect
{
    /**
     * The message to display
     */
    private String message;

    /**
     * Create new message effect
     * @param message
     */
    public MessageEffect(String message)
    {
        this.message = message;
    }

    /**
     * Get the message to display
     * @return
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Trigger effect
     */
    public void trigger()
    {
        System.out.println(message);
    }
}

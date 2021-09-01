package com.example.entity.effect;

import javax.persistence.*;

/**
 * Represents an effect that produces a message display in the console
 */
@Entity
@DiscriminatorValue("Message")
public class MessageEffect extends AbstractEffect
{
    /**
     * The message to display
     */
    @Column(name = "message")
    private String message;

    public MessageEffect()
    {

    }

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

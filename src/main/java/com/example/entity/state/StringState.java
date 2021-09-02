package com.example.entity.state;

import javax.persistence.*;

/**
 * Represents a state in the form of a string (name or message)
 */
@Entity
@DiscriminatorValue("String")
public class StringState extends State<String>
{
    /**
     * The state's default value
     */
    @Column(name = "string_default_value")
    private String defaultValue;

    public StringState()
    {
        
    }

    @Override
    public String getDefaultValue()
    {
        return defaultValue;
    }

    @Override
    protected void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }
}

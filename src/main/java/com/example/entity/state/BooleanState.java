package com.example.entity.state;

import javax.persistence.*;

/**
 * Represents a state in the form of a boolean (a switch)
 */
@Entity
@DiscriminatorValue("Boolean")
public class BooleanState extends State<Boolean>
{
    /**
     * The state's default value
     */
    @Column(name = "boolean_default_value")
    private Boolean defaultValue;

    public BooleanState()
    {
        
    }

    @Override
    public Boolean getDefaultValue()
    {
        return defaultValue;
    }

    @Override
    protected void setDefaultValue(Boolean defaultValue)
    {
        this.defaultValue = defaultValue;
    }
}

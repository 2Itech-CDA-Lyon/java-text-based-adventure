package com.example.entity.state;

import javax.persistence.*;

/**
 * Represents a state in the form of a number (counter or points)
 */
@Entity
@DiscriminatorValue("Number")
public class NumberState extends State<Double>
{
    /**
     * The state's default value
     */
    @Column(name = "number_default_value")
    private Double defaultValue;

    public NumberState()
    {
        
    }

    @Override
    public Double getDefaultValue()
    {
        return defaultValue;
    }

    @Override
    protected void setDefaultValue(Double defaultValue)
    {
        this.defaultValue = defaultValue;
    }
}

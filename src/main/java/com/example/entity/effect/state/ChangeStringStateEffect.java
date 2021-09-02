package com.example.entity.effect.state;

import javax.persistence.*;

/**
 * Represents an effect that produces a change in a string state
 */
@Entity
@DiscriminatorValue("ChangeStringState")
public class ChangeStringStateEffect extends ChangeStateEffect<String>
{
    final static public int OPERATOR_STRING_SET = 0;

    /**
     * The operand to apply
     */
    @Column(name = "string_value")
    private String value;

    @Override
    public void trigger()
    {
        
    }

    @Override
    public String getValue()
    {
        return value;
    }

    @Override
    protected void setValue(String value)
    {
        this.value = value;
    } 
}

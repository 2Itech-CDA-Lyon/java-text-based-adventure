package com.example.entity.effect.state;

import javax.persistence.*;

/**
 * Represents an effect that produces a change in a boolean state
 */
@Entity
@DiscriminatorValue("ChangeBooleanState")
public class ChangeBooleanStateEffect extends ChangeStateEffect<Boolean>
{
    /**
     * The operand to apply
     */
    @Column(name = "boolean_value")
    private Boolean value;

    @Override
    public void trigger()
    {
        
    }

    @Override
    public Boolean getValue()
    {
        return value;
    }

    @Override
    protected void setValue(Boolean value)
    {
        this.value = value;
    }
}

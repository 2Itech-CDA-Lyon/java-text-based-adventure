package com.example.entity.effect.state;

import javax.persistence.*;

import com.example.entity.effect.AbstractEffect;
import com.example.entity.state.State;

/**
 * Represents an effect that produces a state change
 */
@Entity
public abstract class ChangeStateEffect<T> extends AbstractEffect
{
    /**
     * The state whose value to change
     */
    @ManyToOne
    @JoinColumn(name = "target_state_id")
    protected State<T> state;
    /**
     * The operation to perform
     */
    @Column(name = "operator") 
    protected int operator;

    public ChangeStateEffect()
    {

    }

    /**
     * Create new boolean state change effect
     * @param state The state whose value to change
     * @param value The new value to assign the state
     * @param operator The operation to perform 
     */
    public ChangeStateEffect(State<T> state, T value, int operator)
    {
        this.state = state;
        setValue(value);
        this.operator = operator;
    }

    public abstract T getValue();
    
    protected abstract void setValue(T value);
}

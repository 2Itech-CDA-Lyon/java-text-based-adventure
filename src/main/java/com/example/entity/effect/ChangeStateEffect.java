package com.example.entity.effect;

import com.example.entity.state.State;

/**
 * Represents an effect that produces a state change
 */
public class ChangeStateEffect<T> extends AbstractEffect
{
    /**
     * The state whose value to change
     */
    private T newValue;
    /**
     * The new value to assign the state
     */
    private State<T> state;

    /**
     * Create new boolean state change effect
     * @param state The new value to assign the state
     * @param newValue The state whose value to change
     */
    public ChangeStateEffect(State<T> state, T newValue)
    {
        this.state = state;
        this.newValue = newValue;
    }

    /**
     * Trigger effect
     */
    public void trigger()
    {
        state.setValue(newValue);
    }
}

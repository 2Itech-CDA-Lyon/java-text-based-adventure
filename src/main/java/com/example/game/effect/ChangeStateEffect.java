package com.example.game.effect;

import com.example.game.state.State;

/**
 * Represents an effect that produces a state change
 */
public class ChangeStateEffect<T> implements Effect
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

package com.example.game.effect;

import com.example.game.state.BooleanState;

/**
 * Represents an effect that produces a state change
 */
public class ChangeBooleanStateEffect implements Effect
{
    /**
     * The state whose value to change
     */
    private boolean newValue;
    /**
     * The new value to assign the state
     */
    private BooleanState state;

    /**
     * Create new boolean state change effect
     * @param state The new value to assign the state
     * @param newValue The state whose value to change
     */
    public ChangeBooleanStateEffect(BooleanState state, boolean newValue)
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

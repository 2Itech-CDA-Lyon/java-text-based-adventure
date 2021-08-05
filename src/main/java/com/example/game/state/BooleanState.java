package com.example.game.state;

import com.example.game.Item;

/**
 * Represents a state that can be either true or false
 */
public class BooleanState
{
    /**
     * The state's default value
     */
    private boolean defaultValue;
    /**
     * The state's value
     */
    private boolean value;
    /**
     * The state's name
     */
    private String name;
    /**
     * The item the state applies to
     */
    private Item item;

    /**
     * Create new boolean state
     * @param item The item the state applies to
     * @param name The state's name
     * @param defaultValue The state's default value
     */
    public BooleanState(Item item, String name, boolean defaultValue)
    {
        this.item = item;
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    /**
     * Reset state's value to its default value
     */
    public void resetValue()
    {
        value = defaultValue;
    }

    public boolean getDefaultValue() {
        return defaultValue;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Item getItem() {
        return item;
    }
}

package com.example.game.state;

import com.example.game.Item;

/**
 * Represents a state
 */
public class State<T>
{
    /**
     * The state's default value
     */
    private T defaultValue;
    /**
     * The state's value
     */
    private T value;
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
    public State(Item item, String name, T defaultValue)
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

    public T getDefaultValue() {
        return defaultValue;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Item getItem() {
        return item;
    }
}
